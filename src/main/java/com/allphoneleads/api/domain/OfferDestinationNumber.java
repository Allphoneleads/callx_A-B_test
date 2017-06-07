package com.allphoneleads.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "offer_destination_number")
@Entity
public class OfferDestinationNumber implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="offer_id", referencedColumnName = "id")
	@JsonIgnore
	private Offer offer;
	
	@Column(name = "country_code")
	private String countryCode;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="payout", columnDefinition="Decimal(15,8) default '0.00000000'")
	private BigDecimal payout;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getPayout() {
		return payout;
	}

	public void setPayout(BigDecimal payout) {
		this.payout = payout;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
	
}
