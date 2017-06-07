package com.allphoneleads.api.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allphoneleads.api.config.AbstractRoutingAlgoritham;
import com.allphoneleads.api.domain.Offer;
import com.allphoneleads.api.dto.OfferScoreDTO;
import com.allphoneleads.api.proctor.RoutingSpecification;

public class NewCallRoutingAlgorithm implements AbstractRoutingAlgoritham {
	private static final Logger logger = LoggerFactory.getLogger(NewCallRoutingAlgorithm.class);

	private List<Offer> offerList;
	private RoutingSpecification routingSpecification;
	private String campaignName;
	
	public NewCallRoutingAlgorithm(List<Offer> offerList, List<OfferScoreDTO> callOfferCount,RoutingSpecification routingSpecification, String campaignName){
		this.offerList = offerList;
		this.routingSpecification = routingSpecification;
		this.campaignName = campaignName;
	}

	public Offer process() {
		Offer rOffer = null;
		int resultValue = AplUtils.getObjectValueFromSpecicificaiton(campaignName+"offers", routingSpecification);
	/*	if(campaignName.equalsIgnoreCase("autoinsurancebundle")){
			Autoinsurancebundleoffers bundle = routingSpecification.getAutoinsurancebundleoffers();
			 logger.debug("New algo test Value ="+bundle.getValue() );
			 logger.debug("New algo test name ="+bundle.getName() );
			 logger.debug("New algo value ="+routingSpecification.getAutoinsurancebundleoffersValue());*/
		if (offerList != null && offerList.size() > 0) {
			for (Offer offer : offerList) {
				if (offer.getId() == resultValue) {
					rOffer = offer;
					logger.debug("final result= " + offer.getId());
				}
			}
		}
			
		return rOffer;
			 
	}
	
}
