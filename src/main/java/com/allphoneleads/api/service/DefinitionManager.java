package com.allphoneleads.api.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.allphoneleads.api.domain.Campaign;
import com.allphoneleads.api.domain.CampaignType;
import com.allphoneleads.api.domain.Offer;
import com.allphoneleads.api.domain.ProctorOfferData;
import com.allphoneleads.api.proctor.RoutingSpecification;
import com.allphoneleads.api.proctor.RoutingSpecificationManager;
import com.allphoneleads.api.proctor.domain.Allocations;
import com.allphoneleads.api.proctor.domain.Audit;
import com.allphoneleads.api.proctor.domain.Buckets;
import com.allphoneleads.api.proctor.domain.BucketsDef;
import com.allphoneleads.api.proctor.domain.ProvidedContext;
import com.allphoneleads.api.proctor.domain.Ranges;
import com.allphoneleads.api.repo.CampaignRepository;
import com.allphoneleads.api.repo.OfferRepository;
import com.allphoneleads.api.util.AplUtils;
import com.allphoneleads.api.util.BuildAllocationForOffers;
import com.google.common.base.Suppliers;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.indeed.proctor.common.Identifiers;
import com.indeed.proctor.common.Proctor;
import com.indeed.proctor.common.ProctorResult;
import com.indeed.proctor.common.ProctorSpecification;
import com.indeed.proctor.common.ProctorUtils;
import com.indeed.proctor.common.UrlProctorLoader;
import com.indeed.proctor.common.model.TestType;

@Service
public class DefinitionManager {

	private static final Logger logger = LoggerFactory.getLogger(DefinitionManager.class);

	@Value("${PROCTOR_DEFAULT_SPEC}")
	private String defaultSpec;

	@Value("${PROCTOR_DEFAULT_DEFINITION}")
	private String defaultDefinition;
	
	@Value("${PROCTOR_DEFAULT_DEFINATION_FILE_NAME}")
	private String defaultDefinationFileName;
	
	
	@Value("${PROCTOR_DEFAULT_SPECIFICATION_FILE_NAME}")
	private String defaultSpecificationFileName;
	
	
	@Value("${APL.S3_BUCKET}")
	private String S3_BUCKET;
	

	@Autowired
	protected CampaignRepository campaignRepository;

	@Autowired
	protected OfferRepository offerRepository;

	@Autowired
	protected S3UploadService s3UploadService;

	private Map<String, Proctor> proctorCache = Maps.newHashMap();
	private Random random = new Random();

	public Proctor load(String definitionUrl, boolean forceReload) {
		InputStream specificationData = null;
		Proctor proctor = proctorCache.get(definitionUrl);
		if (proctor != null && !forceReload) {
			logger.debug("reusing cached " + definitionUrl);
			return proctor;
		}
		try {
			logger.debug(" specification= " + defaultSpec);
			logger.debug(" definition= " + defaultDefinition);
			HttpURLConnection.setFollowRedirects(true); // for demo purposes,
														// allow Java to follow
														// redirects
			
			
			specificationData =	s3UploadService.downloadResouce("assets-apl", "dev-routingspecification.json");
			ProctorSpecification spec = ProctorUtils.readSpecification(specificationData);
					//.readSpecification(new File(defaultSpec));
			UrlProctorLoader loader = new UrlProctorLoader(spec, definitionUrl+ "?r=" + random.nextInt());
			proctor = loader.doLoad();
			logger.debug("loaded definition from " + definitionUrl);
			proctorCache.put(definitionUrl, proctor);
		} catch (Throwable t) {
			logger.error("Failed to load test spec/definition", t);
			t.printStackTrace();
		}finally {
			try {
				specificationData.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return proctor;
	}

	public Map<String, Object> createDefinition() {
		long startTime = System.currentTimeMillis();
		// map to store root level json object
		Map<String, Object> resultsMap = new LinkedHashMap<String, Object>();
		// map to store actual json object
		Map<String, Object> campMap = new LinkedHashMap<String, Object>();

		logger.debug("Calling Build Definiation");
		List<Campaign> campaigns = campaignRepository.getCampaignsForABtest(
				CampaignType.bundled, "active", true);

		for (Campaign campaign : campaigns) {
			// map to store all bucket objects
			Map<String, Object> bucketsMap = new LinkedHashMap<String, Object>();
			Buckets[] bucketsObjects = new Buckets[3];
			Allocations[] allocations = new Allocations[1];
			Ranges[] ranges = new Ranges[3];

			for (int i = -1; i < 2; i++) {
				Buckets bucket = new Buckets();
				if (i == -1) {
					bucket.setName("inactive");
					bucket.setDescription("inactive");
				} else if (i == 0) {
					bucket.setName("oldalgo");
					bucket.setDescription("oldalgo");
				} else if (i == 1) {
					bucket.setName("newalgo");
					bucket.setDescription("newalgo");
				}

				bucket.setValue(i);
				int k = i + 1;
				bucketsObjects[k] = bucket;
			}

			Allocations allocation = new Allocations();
			allocation.setRule(null);
			
			BigDecimal newAlgoPercen =  new BigDecimal(campaign.getAbtestPercentage()).divide(new BigDecimal(100));
			System.out.println("new ALGO ="+newAlgoPercen);
			BigDecimal oldAlgoPercen =  new BigDecimal(100-campaign.getAbtestPercentage()).divide(new BigDecimal(100));
			System.out.println("old ALGO ="+oldAlgoPercen);
			for (int i = -1; i < 2; i++) {
				Ranges rangeOne = new Ranges();
				rangeOne.setBucketValue(i);
				if (i == -1) {
					rangeOne.setLength(new BigDecimal(0.0));
				} else if (i == 0) { //old algo
					rangeOne.setLength(oldAlgoPercen);
				} else if (i == 1) { // new algo
					rangeOne.setLength(newAlgoPercen);
				}
				int k = i + 1;
				ranges[k] = rangeOne;
			}

			allocation.setRanges(ranges);
			allocations[0] = allocation;

			bucketsMap.put("version", new Integer(1));
			bucketsMap.put("description", campaign.getName());
			bucketsMap.put("salt", campaign.getName());
			bucketsMap.put("buckets", bucketsObjects);
			bucketsMap.put("allocations", allocations);
			bucketsMap.put("testType", "USER");

			campMap.put(AplUtils.removeSpecialChar(campaign.getName()),
					bucketsMap);

		}

		for (Campaign campaign : campaigns) {
			List<ProctorOfferData> offers = convertoOfferObject(campaignRepository
					.getOfferSpecificationData(campaign.getId()));
			// 0 = Campaign name 1= campaign description and 2= offer name
			Map<String, Object> bucketsMap = new LinkedHashMap<String, Object>();
			Buckets[] bucketsObjects = new Buckets[offers.size()+1];
			

			int bucketValue = 0;

			for (ProctorOfferData offer : offers) {
				Buckets bucket = new Buckets();
				bucket.setName(AplUtils.removeSpecialChar(offer.getOfferName()));
				bucket.setDescription(AplUtils.removeSpecialChar(offer
						.getOfferName()));
				bucket.setValue(offer.getOfferid());
				bucketsObjects[bucketValue] = bucket;
				++bucketValue;
			}
			Buckets bucket = new Buckets();
			bucket.setName("inactive");
			bucket.setDescription("inactive");
			bucket.setValue(-1);
			bucketsObjects[bucketValue] = bucket;
			
			
			int[] offerids = new int[offers.size()];
			for (int j = 0; j < offers.size(); j++) {
				offerids[j] = offers.get(j).getOfferid();
			}
			BuildAllocationForOffers buaf =new BuildAllocationForOffers();
			buaf.find(offerids);
			Allocations[] allocations = new Allocations[buaf.offerAllocationList.size()];
			int i =0;
			for (String  combi : buaf.offerAllocationList) {
				System.out.println("cobination ="+combi);
				Allocations allocation = new Allocations();
				
				// allocation based on EPC
				BigDecimal totalEpc = getTotalEPC(offers);
				BigDecimal sumUpIndividualEpc = BigDecimal.ZERO;
				System.out.println("Total EPC ="+totalEpc.toString());
				int rangValue = 0;
				
				List<ProctorOfferData> filteredOffers = filterAllocationOffers(offers,combi );
				System.out.println("size of offers ="+filteredOffers.size());
				Ranges[] ranges = new Ranges[filteredOffers.size()+1];
				int size = filteredOffers.size();
				for (ProctorOfferData offer : filteredOffers) {
									
					Ranges rangeOne = new Ranges();
					rangeOne.setBucketValue(offer.getOfferid());
					try {
						if(rangValue == size-1){
							System.out.println("Last one :"+BigDecimal.ONE.subtract(sumUpIndividualEpc));
							rangeOne.setLength((BigDecimal.ONE.subtract(sumUpIndividualEpc)));
						}else{
							System.out.println(offer.getEpc()+".divide("+totalEpc+") ="+(offer.getEpc().divide(totalEpc,1,RoundingMode.HALF_EVEN)));
							rangeOne.setLength((offer.getEpc().divide(totalEpc,1,RoundingMode.HALF_EVEN)));
							sumUpIndividualEpc = sumUpIndividualEpc.add(rangeOne.getLength());
						}
					} catch (ArithmeticException e) {
						logger.error("Error in find EPC"+e.getMessage());
					}
					ranges[rangValue] = rangeOne;
					++rangValue;
				}
				
				Ranges rangeOne = new Ranges();
				rangeOne.setBucketValue(-1);
				rangeOne.setLength(new BigDecimal(0.0));
				ranges[rangValue] = rangeOne;
				
				allocation.setRule("${offerssize == '"+ size+"' && offersidasc =='"+combi+"'}");
				allocation.setRanges(ranges);
				allocations[i] = allocation;
				i=i+1;
			
			}

			bucketsMap.put("version", new Integer(1));
			bucketsMap.put("description", campaign.getDescription());
			bucketsMap.put("rule", "${offerssize >= '2'}"); // new fied add today
			                                               
			bucketsMap.put("salt", campaign.getDescription());
			bucketsMap.put("buckets", bucketsObjects);
			bucketsMap.put("allocations", allocations);
			bucketsMap.put("testType", "USER");

			campMap.put(AplUtils.removeSpecialChar(campaign.getName())+ "offers", bucketsMap);
		}

		long endTime = System.currentTimeMillis();
		logger.debug("Total time taken to build definition Json :"+ (endTime - startTime) + "ms");
		resultsMap.put("tests", campMap);
		Audit audit = new Audit(1, "Chandan", System.currentTimeMillis());
		resultsMap.put("audit", audit);
		/*try {
			Writer writer = new FileWriter("src/main/proctor/com/allphoneleads/api/proctor/RoutingDefination.json");
			Gson gson = new GsonBuilder().create();
			gson.toJson(resultsMap, writer);
			writer.close();
		} catch (IOException e) {
			logger.debug("Error in Writing to JSON file :" + e);
		}*/
		
		try {
			String file_name = defaultDefinationFileName;
			Writer writer = new FileWriter(file_name);
			Gson gson = new GsonBuilder().create();
			gson.toJson(resultsMap, writer);
			writer.close();
			File file = new File(file_name);
			InputStream objectData = new FileInputStream(file);
			String aplS3Path = s3UploadService.uploadResource(file_name,objectData, file.length(), false, "application/json", S3_BUCKET);
			logger.debug("Uploaded defination file :  https:" + aplS3Path);

		} catch (IOException e) {
			logger.debug("Error in Writing to JSON file :" + e);
		} catch (InterruptedException e) {
			logger.debug("Error in upload to JSON file :" + e);
		}

		return resultsMap;
	}

	private List<ProctorOfferData> filterAllocationOffers( List<ProctorOfferData> offers, String offerCombi) {
		List<ProctorOfferData> proctOffers = new ArrayList<ProctorOfferData>();
		for (ProctorOfferData proctorOfferData : offers) {
			if(offerCombi.contains(String.valueOf(proctorOfferData.getOfferid()) ))
				proctOffers.add(proctorOfferData);
					
		}
		return proctOffers;
	}

	private BigDecimal getTotalEPC(List<ProctorOfferData> offers) {
		BigDecimal totalEPC = BigDecimal.ZERO;
		for (ProctorOfferData proctorOfferData : offers) {
			totalEPC=totalEPC.add(proctorOfferData.getEpc());
			System.out.println("offer epc ="+proctorOfferData.getEpc());
		}
		return totalEPC;
	}

	private List<ProctorOfferData> convertoOfferObject(
			List<Object[]> offerSpecificationData) {
		List<ProctorOfferData> results = new ArrayList<ProctorOfferData>();
		for (Object[] objects : offerSpecificationData) {
			ProctorOfferData offer = new ProctorOfferData();
			offer.setOfferid(Integer.valueOf(String.valueOf(objects[0])));
			offer.setOfferName(String.valueOf(objects[1]));
			offer.setTotalCalls(new BigDecimal(String.valueOf(objects[2])));
			offer.setTotalRevenue(new BigDecimal(String.valueOf(objects[3])));
			offer.setEpc(new BigDecimal(String.valueOf(objects[4])));
			results.add(offer);
		}
		return results;
	}

	public Map<String, Object> createSpecification() {
		long startTime = System.currentTimeMillis();
		// map object to store root level json object
		Map<String, Object> resultsMap = new LinkedHashMap<String, Object>();
		// map object to store actual test json object
		Map<String, Object> campMap = new LinkedHashMap<String, Object>();
		logger.debug("Create Specification");

		List<Campaign> campaigns = campaignRepository.getCampaignsForABtest(
				CampaignType.bundled, "active", true);
		// building campaign level test buckets
		for (Campaign campaign : campaigns) {
			// 0 = Campaign name 1= campaign description and 2= offer name
			Map<String, Integer> buckets = new LinkedHashMap<String, Integer>();
			BucketsDef buk = new BucketsDef();
			buckets.put("inactive", -1);
			buckets.put("oldalgo", 0);
			buckets.put("newlgo", 1);
			buk.setBuckets(buckets);
			buk.setFallbackvalue(-1);
			campMap.put(AplUtils.removeSpecialChar(campaign.getName()), buk);

		}
		// building campaign offers test buckets
		for (Campaign campaign : campaigns) {
			Map<String, Integer> buckets = new LinkedHashMap<String, Integer>();
			BucketsDef buk = new BucketsDef();
			List<Offer> offers = offerRepository.getCampaignOffer(campaign.getId());
			for (Offer offer : offers) {
				buckets.put(AplUtils.removeSpecialChar(offer.getName()),offer.getId());
			}
			/*buk.setBuckets(buckets);
			buk.setFallbackvalue(offers.get(0).getId());*/
			buckets.put("inactive", -1);
			buk.setBuckets(buckets);
			buk.setFallbackvalue(-1);
			campMap.put(AplUtils.removeSpecialChar(String.valueOf(campaign.getName()) + "offers"), buk);

		}
		long endTime = System.currentTimeMillis();
		logger.debug("Total time taken to build Specification Json :"
				+ (endTime - startTime) + "ms");
		resultsMap.put("tests", campMap);
		ProvidedContext context = new ProvidedContext("String", "String");
		resultsMap.put("providedContext",context);
		try {
			Writer writer = new FileWriter("src/main/proctor/com/allphoneleads/api/proctor/RoutingSpecification.json");
			Gson gson = new GsonBuilder().create();
			gson.toJson(resultsMap, writer);
			writer.close();
		} catch (IOException e) {
			logger.debug("Error in Writing to JSON file :" + e);
		}
		
		try {
			String file_name = defaultSpecificationFileName;
			Writer writer = new FileWriter(file_name);
			Gson gson = new GsonBuilder().create();
			gson.toJson(resultsMap, writer);
			writer.close();
			File file = new File(file_name);
			InputStream objectData = new FileInputStream(file);
			String aplS3Path = s3UploadService.uploadResource(file_name,objectData, file.length(), false, "application/json", S3_BUCKET);
			logger.debug("Uploaded defination file :  https:" + aplS3Path);

		} catch (IOException e) {
			logger.debug("Error in Writing to JSON file :" + e);
		} catch (InterruptedException e) {
			logger.debug("Error in upload to JSON file :" + e);
		}

		return resultsMap;
	}

	public Proctor getRoutingGroups(String definitionUrl,String userid) {
		return load(definitionUrl, true);
	}
	
	public RoutingSpecification getRoutingGroups(String definitionUrl,String userid, String offersSize, String offerIdsAsc) {
		final Proctor proctor = load(definitionUrl, true);
		final RoutingSpecificationManager groupsManager = new RoutingSpecificationManager(Suppliers.ofInstance(proctor));
		final Identifiers identifiers = new Identifiers(TestType.USER, userid);
		// final boolean allowForceGroups = true;
		final ProctorResult result = groupsManager.determineBuckets(identifiers,offersSize,offerIdsAsc);
		final RoutingSpecification groups = new RoutingSpecification(result);
		return groups;
	}
	
	public RoutingSpecification getRoutingGroupsTest(String definitionUrl,String callID, String offersSize, String offerIdsAsc) {
		final Proctor proctor = load(definitionUrl, true);
		final RoutingSpecificationManager groupsManager = new RoutingSpecificationManager(Suppliers.ofInstance(proctor));
		final Identifiers identifiers = Identifiers.of(TestType.ACCOUNT, callID);
		// final boolean allowForceGroups = true;
		final ProctorResult result = groupsManager.determineBuckets(identifiers,offersSize,offerIdsAsc);
		final RoutingSpecification groups = new RoutingSpecification(result);
		return groups;
	}
	
	private final String USER_AGENT = "Mozilla/5.0";
	public int jenkinsBuildJob(String jobID) {
		int statuscode = 500 ;
		try {
			String url = "http://chandan:ch1nd1n@52.24.56.190:8080/job/backend-dev-build/build";
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			// add header
			post.setHeader("User-Agent", USER_AGENT);
			HttpResponse response = client.execute(post);
			System.out.println("\nSending 'POST' request to URL : " + url);
			// System.out.println("Post parameters : " + post.getEntity());

			statuscode = response.getStatusLine().getStatusCode();
			System.out.println("Response Code : " + statuscode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return statuscode;
		
	}

}
