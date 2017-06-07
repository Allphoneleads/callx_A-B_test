package com.allphoneleads.api.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the offer_filters database table.
 * 
 */
@Entity
@Table(name="offer_filters")
public class OfferFilter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name="offer_id", referencedColumnName = "id")
	@JsonIgnore
	private Offer offer;

	@ManyToOne
	@JoinColumn(name="campaign_filter_id", referencedColumnName = "id")
	private CampaignFilter campaignFilter;

	public OfferFilter() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Offer getOffer() {
		return this.offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public CampaignFilter getCampaignFilter() {
		return this.campaignFilter;
	}

	public void setCampaignFilter(CampaignFilter campaignFilter) {
		this.campaignFilter = campaignFilter;
	}

}