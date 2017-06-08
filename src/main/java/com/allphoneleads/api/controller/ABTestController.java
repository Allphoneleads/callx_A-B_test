
package com.allphoneleads.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allphoneleads.api.repo.CampaignRepository;
import com.allphoneleads.api.repo.OfferRepository;
import com.allphoneleads.api.service.DefinitionManager;
import com.allphoneleads.api.service.S3UploadService;

@RestController
public class ABTestController {

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


	@RequestMapping(value = "/abtest/proctor/build-specification", method = RequestMethod.GET)
	public Map<String, Object> getSpecification() {
		return definitionManager.createSpecification();
	}

	@RequestMapping(value = "/abtest/proctor/build-definition", method = RequestMethod.GET)
	public Map<String, Object> getDefinition() {
		return definitionManager.createDefinition();
	}
	
	@RequestMapping(value = "/abtest/result/campaign", method = RequestMethod.GET)
	public int getABTestResultByCampaign() {
		return 0;
	}
	
	@RequestMapping(value = "/abtest/result/campaignoffers", method = RequestMethod.GET)
	public int getABTestResultByCampaignOffers() {
		return 0;
	}


}
