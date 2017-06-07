package com.allphoneleads.api.dto;

import java.math.BigDecimal;

public class OfferScoreDTO{
	int offerId;
	long count;
	BigDecimal totalPayout;
	double averagePayout;
	double score;
	double mbps;
	
	public OfferScoreDTO(int offerId, long count, BigDecimal totalPayout, double averagePayout) {
		this.offerId = offerId;
		this.count = count;
		this.totalPayout = totalPayout;
		this.averagePayout = averagePayout;

				
		
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public BigDecimal getTotalPayout() {
		return totalPayout;
	}
	public void setTotalPayout(BigDecimal totalPayout) {
		this.totalPayout = totalPayout;
	}
	public double getAveragePayout() {
		return averagePayout;
	}
	public void setAveragePayout(double averagePayout) {
		this.averagePayout = averagePayout;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public double getMbps() {
		return mbps;
	}
	public void setMbps(double mbps) {
		this.mbps = mbps;
	}
	
	
}