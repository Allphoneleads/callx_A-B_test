package com.allphoneleads.api.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ABTestReults implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int campaignresult;
	private int oldAlgoCount;
	private int newAlgoCount;
	private int others;
	private int offerresult;
	
	Map<Integer, Integer> offersCount = new HashMap<>();
	
	
	public int getCampaignresult() {
		return campaignresult;
	}
	public void setCampaignresult(int campaignresult) {
		this.campaignresult = campaignresult;
	}
	public int getOfferresult() {
		return offerresult;
	}
	public void setOfferresult(int offerresult) {
		this.offerresult = offerresult;
	}
	public int getOldAlgoCount() {
		return oldAlgoCount;
	}
	public void setOldAlgoCount(int oldAlgoCount) {
		this.oldAlgoCount = oldAlgoCount;
	}
	public int getNewAlgoCount() {
		return newAlgoCount;
	}
	public void setNewAlgoCount(int newAlgoCount) {
		this.newAlgoCount = newAlgoCount;
	}
	public int getOthers() {
		return others;
	}
	public void setOthers(int others) {
		this.others = others;
	}
	public Map<Integer, Integer> getOffersCount() {
		return offersCount;
	}
	public void setOffersCount(Map<Integer, Integer> offersCount) {
		this.offersCount = offersCount;
	}
	
	
	

}
