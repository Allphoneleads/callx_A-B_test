package com.allphoneleads.api.domain;

import java.io.Serializable;
import java.util.List;

import com.allphoneleads.api.util.AplUtils;

public class ProctorCampaignData  implements Serializable{
	
	private int campaignid;
	private String campaignName;
	private String campaignDescription;
	
	private List<ProctorOfferData> proctorOfferData;

	public int getCampaignid() {
		return campaignid;
	}

	public void setCampaignid(int campaignid) {
		this.campaignid = campaignid;
	}

	public String getCampaignName() {
		return AplUtils.removeSpecialChar(campaignName);
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCampaignDescription() {
		return campaignDescription;
	}

	public void setCampaignDescription(String campaignDescription) {
		this.campaignDescription = campaignDescription;
	}

	public List<ProctorOfferData> getProctorOfferData() {
		return proctorOfferData;
	}

	public void setProctorOfferData(List<ProctorOfferData> proctorOfferData) {
		this.proctorOfferData = proctorOfferData;
	}
	
	

}
