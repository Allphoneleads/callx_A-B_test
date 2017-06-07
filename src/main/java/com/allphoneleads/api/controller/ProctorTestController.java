
package com.allphoneleads.api.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allphoneleads.api.repo.CampaignRepository;
import com.allphoneleads.api.repo.OfferRepository;
import com.allphoneleads.api.service.DefinitionManager;
import com.allphoneleads.api.service.S3UploadService;
import com.indeed.proctor.common.Proctor;


@RestController
public class ProctorTestController {
	private static final Logger logger = LoggerFactory.getLogger(ProctorTestController.class);

	private static final String template = "You are re-directed to , %s !!!!";
	
	private final AtomicLong callsCounter = new AtomicLong();
	private final AtomicLong  campainAutoInsuranceBundleinActiveAlgoCounter= new AtomicLong();
	private final AtomicLong  campainAutoInsuranceBundleOldAlgoCounter= new AtomicLong();
	private final AtomicLong  campainAutoInsuranceBundleNewAlgoCounter= new AtomicLong();
	
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

	/*
	 * @RequestMapping(value="/proctor/test", method = RequestMethod.GET) public
	 * ProctorResponseObject greeting(@RequestParam(value = "userid",
	 * defaultValue = "0") String userid,
	 * 
	 * @RequestParam(value = "type", defaultValue = "campaign",required=true)
	 * String type,
	 * 
	 * @RequestParam(value = "parm", defaultValue =
	 * "AutoInsuranceBundle",required=true) String parm) {
	 * logger.debug("Calling ProctorTest");
	 * 
	 * final Proctor proctor = definitionManager.getRoutingGroups(
	 * "https://s3.amazonaws.com/assets-apl/routingdefination.json",userid);
	 * final RoutingSpecificationManager groupsManager = new
	 * RoutingSpecificationManager(Suppliers.ofInstance(proctor)); final
	 * Identifiers identifiers = new Identifiers(TestType.USER, userid); //
	 * final boolean allowForceGroups = true; final ProctorResult result =
	 * groupsManager.determineBuckets(identifiers,null,null); final
	 * RoutingSpecification groups = new RoutingSpecification(result); //
	 * https://s3.amazonaws.com/assets-apl/proctor/routingdefination.json String
	 * algo = null; ProctorResponseObject greeting = new
	 * ProctorResponseObject();
	 * 
	 * Method[] methods = RoutingSpecification.class.getMethods(); for (Method
	 * method : methods) { logger.debug("method name ="+method.getName());
	 * if(method.getName().equalsIgnoreCase("getAutoinsurancebundleoffers") &&
	 * method.getGenericParameterTypes().length == 0){
	 * logger.debug("Method Name ="+method.getName() );
	 * System.out.println("Method Name ="+method.getName() );
	 * 
	 * Object typeValue = null; Object resultValue = null ; try {
	 * //Class.forName(className) typeValue = method.invoke(groups); resultValue
	 * = typeValue ;
	 * 
	 * } catch (IllegalAccessException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IllegalArgumentException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch
	 * (InvocationTargetException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } logger.debug("Method Value = "+ resultValue);
	 * System.out.println("Method Value = "+ resultValue); if(typeValue == -1){
	 * campainAutoInsuranceBundleinActiveAlgoCounter.getAndIncrement(); algo =
	 * "inactive"; }else if(resultValue == 0){
	 * campainAutoInsuranceBundleOldAlgoCounter.getAndIncrement(); algo =
	 * "old Algo"; }else if(resultValue == 1){
	 * campainAutoInsuranceBundleNewAlgoCounter.getAndIncrement(); algo =
	 * "new Algo"; } greeting.setId(callsCounter.getAndIncrement());
	 * greeting.setContent(String.format(template, algo)); }
	 * 
	 * 
	 * } if(type.equalsIgnoreCase("campaign") &&
	 * parm.equalsIgnoreCase("addictionbundlecampaign") ){
	 * 
	 * Addictionbundlecampaign bundle = groups.getAddictionbundlecampaign();
	 * logger.debug("test Value ="+bundle.getValue() );
	 * logger.debug("test name ="+bundle.getName() );
	 * logger.debug("isOldAlgo ="+groups.isAddictionbundlecampaignOldalgo() );
	 * logger.debug("isNewAlgo ="+groups.isAddictionbundlecampaignNewlgo() );
	 * logger.debug("value ="+groups.getAddictionbundlecampaignValue() );
	 * 
	 * if(bundle.getValue() == -1){
	 * campainAutoInsuranceBundleinActiveAlgoCounter.getAndIncrement(); algo =
	 * "inactive"; }else if(bundle.getValue() == 0){
	 * campainAutoInsuranceBundleOldAlgoCounter.getAndIncrement(); algo =
	 * "old Algo"; }else if(bundle.getValue() == 1){
	 * campainAutoInsuranceBundleNewAlgoCounter.getAndIncrement(); algo =
	 * "new Algo"; } greeting.setId(callsCounter.getAndIncrement());
	 * greeting.setContent(String.format(template, algo));
	 * 
	 * }else if(type.equalsIgnoreCase("offer") &&
	 * parm.equalsIgnoreCase("addictionbundlecampaignoffers") ){
	 * 
	 * Addictionbundlecampaignoffers bundle =
	 * groups.getAddictionbundlecampaignoffers();
	 * logger.debug("test Value ="+bundle.getValue()
	 * +", name ="+bundle.getName() );
	 * logger.debug("is Mfaddictioninsured = "+groups
	 * .isAddictionbundlecampaignoffersMfaddictioninsured()
	 * +" ,is Mfaddictionnotinsured ="
	 * +groups.isAddictionbundlecampaignoffersMfaddictionnotinsured());
	 * logger.debug
	 * ("value from object ="+groups.getAddictionbundlecampaignoffersValue() );
	 * logger.debug("------------------------------------------------------");
	 * logger.debug("totalcalls ="+offercallsCounter.get());
	 * if(bundle.getValue() == -1){
	 * offerAutoInsuranceBundleOfferOne.getAndIncrement(); algo =
	 * "mf-addiction-insured";
	 * logger.debug("mf-addiction-insured total calls  ="
	 * +offerAutoInsuranceBundleOfferOne.get()); }else if(bundle.getValue() ==
	 * 0){ offerAutoInsuranceBundleOfferTwo.getAndIncrement(); algo =
	 * "mf-addiction-notinsured";
	 * logger.debug("mf-addiction-notinsured total calls  ="
	 * +offerAutoInsuranceBundleOfferTwo.get()); }else if(bundle.getValue() ==
	 * 1){ offerAutoInsuranceBundleOfferThree.getAndIncrement(); algo =
	 * "DL-AutoInsurance"; logger.debug("DL-AutoInsurance total calls  ="+
	 * offerAutoInsuranceBundleOfferThree.get()); }
	 * logger.debug("------------------------------------------------------");
	 * greeting.setId(offercallsCounter.getAndIncrement());
	 * greeting.setContent(String.format(template, algo));
	 * 
	 * }
	 * 
	 * return greeting; }
	 */
	
	@RequestMapping(value="/japi/proctor/refresh", method = RequestMethod.GET)
	public String refreshProctor() {
		logger.debug("Calling Proctor ");
		definitionManager.createDefinition();
		 final Proctor proctor = definitionManager.getRoutingGroups(default_definition,String.valueOf("1"));
		 if(proctor != null)
			  return "success";
		 else
			 return "Something went wrong !!";
	}
	
	/*
	 * @RequestMapping(value="/proctor/report", method = RequestMethod.GET)
	 * public ProctorReport getReport() { logger.debug("Calling proctorreport");
	 * 
	 * ProctorReport report = new ProctorReport();
	 * report.setTotalCalls(callsCounter.get());
	 * report.setInactiveCalls(campainAutoInsuranceBundleinActiveAlgoCounter
	 * .get());
	 * report.setOldAlgoCalls(campainAutoInsuranceBundleOldAlgoCounter.get());
	 * report.setNewAlgoCalls(campainAutoInsuranceBundleNewAlgoCounter.get());
	 * 
	 * return report; }
	 */
	
	@RequestMapping(value="/japi/proctor/build-specification", method = RequestMethod.GET)
	public Map<String, Object>  getSpecification() {
		return definitionManager.createSpecification();
	}

	
	@RequestMapping(value="/japi/proctor/build-definition", method = RequestMethod.GET)
	public Map<String, Object>  getDefinition() {
		return definitionManager.createDefinition();
	}
	
	@RequestMapping(value="/proctor/reload", method = RequestMethod.GET)
	public void  reloadProctor() {
		 String s;
		 Process p;
		 definitionManager.createSpecification();
		 System.out.println("Call /proctor/reload service");
		 String[] cmds = {"mvn proctor:generate","gradle -x test clean","jar uf jar-file input-file(s)"};
		 for (int i = 0; i < cmds.length; i++) {
			try {
				p = Runtime.getRuntime().exec(cmds[i]);
				// gradle -x test clean build based on environment

				BufferedReader br = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				while ((s = br.readLine()) != null)
					System.out.println("line: " + s);
				p.waitFor();
				System.out.println("exit: " + p.exitValue());
				p.destroy();
			} catch (Exception e) {
				System.out.println("ERROR.RUNNING.CMD");
			}
        System.out.println("End execution of CMD :"+cmds[i]);
		}
		
		 System.out.println("exit from /proctor/reload service");
	}
	
	
	  
	  

}
