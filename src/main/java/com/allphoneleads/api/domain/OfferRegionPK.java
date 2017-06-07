package com.allphoneleads.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The primary key class for the offer_regions database table.
 * 
 */
@Embeddable
public class OfferRegionPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/*@Column(name="offer_id", insertable=false, updatable=false)
	private int offerId;*/
	@ManyToOne
	@JoinColumn(name="offer_id", referencedColumnName = "id")
	@JsonIgnore
	private Offer offer;

	@Column(name="state_code")
	private String stateCode;

	public OfferRegionPK() {
	}
	
	public OfferRegionPK(Offer offer, String stateCode) {
		this.offer =offer;
		this.stateCode = stateCode;
	}
	
	/*public int getOfferId() {
		return this.offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}*/
	
	
	public String getStateCode() {
		return this.stateCode;
	}
	public Offer getOffer() {
		return offer;
	}


	public void setOffer(Offer offer) {
		this.offer = offer;
	}


	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	
	
	
}