package com.allphoneleads.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.allphoneleads.api.util.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * The persistent class for the offer_regions database table.
 * 
 */
@Entity
@Table(name="offer_regions")
public class OfferRegion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OfferRegionPK id;

	@Column(name="created_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime createdAt;

	@Column(name="updated_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime updatedAt;
	
	/*@ManyToOne
	@JoinColumn(name="offer_id", referencedColumnName = "id")
	private Offer offer;*/

	public OfferRegion() {
	}

	public OfferRegionPK getId() {
		return this.id;
	}

	public void setId(OfferRegionPK id) {
		this.id = id;
	}

	public DateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public DateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	/*public Offer getOffer() {
		return this.offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}*/

}