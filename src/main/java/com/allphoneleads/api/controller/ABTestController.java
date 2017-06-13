
package com.allphoneleads.api.controller;

import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allphoneleads.api.domain.ABTestReults;
import com.allphoneleads.api.proctor.RoutingSpecification;
import com.allphoneleads.api.repo.CampaignRepository;
import com.allphoneleads.api.repo.OfferRepository;
import com.allphoneleads.api.service.DefinitionManager;
import com.allphoneleads.api.service.S3UploadService;
import com.allphoneleads.api.util.AplUtils;

@RestController
public class ABTestController {

	private static final Logger logger = LoggerFactory.getLogger(ABTestController.class);
	
	@Autowired
	protected DefinitionManager definitionManager;

	@Autowired
	protected CampaignRepository campaignRepository;

	@Autowired
	protected OfferRepository offerRepository;

	@Autowired
	protected S3UploadService s3UploadService;

	@Value("${PROCTOR_DEFAULT_DEFINITION}")
	private String default_definition;


	@RequestMapping(value = "/abtest/proctor/build-testmatrix", method = RequestMethod.GET)
	public int buildABTestFiles() {
		
		int resultCode = 500;
		int specificationResult = 500;
		int definationResult = 500;
		
		Map<String, Object> specificationRes = definitionManager.createSpecification();
		Map<String, Object> definationRes    = definitionManager.createDefinition();
		
		if(specificationRes.get("tests") !=null && specificationRes.get("providedContext") != null ){
			specificationResult = 200;
		}else{
			logger.error("Somethign went wrong on building test matrix : Errors in building specification");
		}
		
		if(definationRes.get("tests") !=null && definationRes.get("audit") != null ){
			definationResult = 200;
		}else{
			logger.error("Somethign went wrong on building test matrix : Errors in building definaiton");
		}
		
		if(specificationResult == 200 && definationResult == 200 )
		{
			int jenkinsStatusCode  = definitionManager.jenkinsBuildJob("backend-dev-build");	
			if(jenkinsStatusCode == 201)
				resultCode = 200;
			else
				logger.error("Somethign went wrong in preparing jenkins job ");
				
		}
		return resultCode;
	}

	@RequestMapping(value = "/abtest/proctor/build-specification", method = RequestMethod.GET)
	public Map<String, Object> getSpecification() {
		return definitionManager.createSpecification();
	}

	@RequestMapping(value = "/abtest/proctor/build-definition", method = RequestMethod.GET)
	public Map<String, Object> getDefinition() {
		return definitionManager.createDefinition();
	}
	
	@RequestMapping(value = "/abtest/result/campaign", method = RequestMethod.GET)
	public ABTestReults getABTestResultByCampaign(@RequestParam(value = "callid", required = false) String callID,
			@RequestParam(value = "offerscount", required = false) String offersCount,
			@RequestParam(value = "offeridsasc", required = false) String offersIdsAsc) {
		
		logger.debug("Executing /abtest/result/campaign");
		final RoutingSpecification campaignGroups = definitionManager.getRoutingGroups(default_definition, callID, null, null);
		Method[] campaignMethods = RoutingSpecification.class.getMethods();
		logger.debug("Methods="+ campaignMethods.length);
		String campaignName = "autoinsurancebundle";
		int campaignTestResult = AplUtils.getMehtodValueFromSpecicificaiton(campaignName, campaignMethods,campaignGroups);
		
		ABTestReults abtestResults = new ABTestReults();
		abtestResults.setCampaignresult(campaignTestResult);
		if(campaignTestResult == 0)
			AplUtils.oldAlgoCount++;
		else if(campaignTestResult == 1){
			AplUtils.newAlgoCount++;
			
			final RoutingSpecification offerGroups = definitionManager.getRoutingGroups(default_definition, callID, offersCount, offersIdsAsc );
			Method[] offerMethods = RoutingSpecification.class.getMethods();
			logger.debug("Methods="+ offerMethods.length);
			String campaignOfferName = "autoinsurancebundleoffers";
			int offerTestResult = AplUtils.getMehtodValueFromSpecicificaiton(campaignOfferName,offerMethods, offerGroups);
			logger.debug("offerTestResult="+ offerTestResult);
			abtestResults.setOfferresult(offerTestResult);
			if(AplUtils.offersCount.get(offerTestResult) != null){
				int value = AplUtils.offersCount.get(offerTestResult);
				AplUtils.offersCount.put(offerTestResult, value+1);
			}else{
				AplUtils.offersCount.put(offerTestResult, 1);
			}
			abtestResults.setOffersCount(AplUtils.offersCount);
			
		}else 
			AplUtils.otherAlgoCount++;
		
		abtestResults.setOldAlgoCount(AplUtils.oldAlgoCount);
		abtestResults.setNewAlgoCount(AplUtils.newAlgoCount);
		abtestResults.setOthers(AplUtils.otherAlgoCount);
		
	
		return abtestResults;
	}
	
	@RequestMapping(value = "/abtest/result/campaignoffers", method = RequestMethod.GET)
	public ABTestReults getABTestResultByCampaignOffers(@RequestParam(value = "callid", required = false) String callID,
			@RequestParam(value = "offerscount", required = true) String offersCount,
			@RequestParam(value = "offeridsasc", required = true) String offersIdsAsc) {
		
		logger.debug("Executing /abtest/result/campaign");
		logger.debug("Executing /abtest/result/campaign");
		final RoutingSpecification campaignGroups = definitionManager.getRoutingGroups(default_definition, callID, offersCount, offersIdsAsc);
		Method[] campaignMethods = RoutingSpecification.class.getMethods();
		logger.debug("Methods="+ campaignMethods.length);
		String campaignName = "autoinsurancebundleoffers";
		int campaignTestResult = AplUtils.getMehtodValueFromSpecicificaiton(campaignName, campaignMethods,campaignGroups);
		
		ABTestReults abtestResults = new ABTestReults();
		if(AplUtils.offersCount.get(campaignTestResult) != null){
			int value = AplUtils.offersCount.get(campaignTestResult);
			AplUtils.offersCount.put(campaignTestResult, value+1);
		}else{
			AplUtils.offersCount.put(campaignTestResult, 1);
		}
		abtestResults.setOffersCount(AplUtils.offersCount);
		abtestResults.setOfferresult(campaignTestResult);
		
		return abtestResults;
	}
	
	


}
