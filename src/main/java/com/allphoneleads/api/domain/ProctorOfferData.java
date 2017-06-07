package com.allphoneleads.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.allphoneleads.api.util.AplUtils;

public class ProctorOfferData implements Serializable {
	
	private int offerid;
	private String offerName;
	private BigDecimal totalCalls;
	private BigDecimal totalRevenue;
	private BigDecimal epc;
	
	
	
	
	public int getOfferid() {
		return offerid;
	}
	public void setOfferid(int offerid) {
		this.offerid = offerid;
	}
	public String getOfferName() {
		return AplUtils.removeSpecialChar(offerName);
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public BigDecimal getTotalCalls() {
		return totalCalls;
	}
	public void setTotalCalls(BigDecimal totalCalls) {
		this.totalCalls = totalCalls;
	}
	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public BigDecimal getEpc() {
		return epc;
	}
	public void setEpc(BigDecimal epc) {
		this.epc = epc;
	}
	
	
	

}
