package com.allphoneleads.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.allphoneleads.api.util.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Table(name = "offers")
@Entity
public class Offer implements Serializable, Cloneable, Comparable<Offer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "call_duration")
	private int callDuration;

	@ManyToOne
	@JoinColumn(name = "campaign_id", referencedColumnName = "id")
	private Campaign campaign;

	@Column(name = "created_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonSerialize(using = DateTimeSerializer.class)
	private DateTime createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "expiration_date")
	private Date expirationDate;

	@Column(name = "expiration_type")
	private boolean expirationType;

	@Column(name = "ivr_enabled")
	private boolean ivrEnabled;

	private String name;

	@Column(name = "call_repeat_days")
	private int repeatDays;

	/*
	 * @Column(name="payout",
	 * columnDefinition="Decimal(15,8) default '0.00000000'") private BigDecimal
	 * payout;
	 * 
	 * @Column(name="phone_number") private String phoneNumber;
	 */

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	private String status;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", columnDefinition = "ENUM('direct','bundled')")
	private CampaignType OfferType;

	@Column(name = "duration_type")
	private String durationType;

	@Column(name = "updated_at", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonSerialize(using = DateTimeSerializer.class)
	private DateTime updatedAt = DateTime.now();

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
	private List<OfferFilter> offerFilters ;

	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
	private List<OfferOperationTime> offerOperationTimes ;

	/*
	 * @OneToMany(mappedBy="offer") private List<OfferRegion> offerRegions;
	 */

	@OneToMany( mappedBy = "id.offer", cascade = CascadeType.ALL)
	private List<OfferRegion> offerRegions;

	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
	private List<OfferDestinationNumber> destinationNumbers;

	@Column(name = "transfer_prompt")
	private boolean transferPrompt;

	@Lob
	@Column(name = "text_prompt", columnDefinition = "TEXT")
	private String textPrompt;

	@Column(name = "audio_prompt_id")
	private int audioPromptId;
	
	@Column(name = "concurrency_type")
	private boolean concurrencyType;
	
	@Column(name = "concurrency_cap")
	private int concurrencyCap;
	
	@Column(name = "budget_type")
	private boolean budgetType;
	
	@Column(name = "budget_cap", columnDefinition="Decimal(15,8) default '0.00000000'")
	private BigDecimal budgetCap;
	
	@Column(name = "copycount")
	private int copycount;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the callDuration
	 */
	public int getCallDuration() {
		return callDuration;
	}

	/**
	 * @param callDuration
	 *            the callDuration to set
	 */
	public void setCallDuration(int callDuration) {
		this.callDuration = callDuration;
	}

	/**
	 * @return the createdAt
	 */
	public DateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the expirationType
	 */
	public boolean getExpirationType() {
		return expirationType;
	}

	/**
	 * @param expirationType
	 *            the expirationType to set
	 */
	public void setExpirationType(boolean expirationType) {
		this.expirationType = expirationType;
	}

	/**
	 * @return the ivrEnabled
	 */
	public boolean getIvrEnabled() {
		return ivrEnabled;
	}

	/**
	 * @param ivrEnabled
	 *            the ivrEnabled to set
	 */
	public void setIvrEnabled(boolean ivrEnabled) {
		this.ivrEnabled = ivrEnabled;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the payout
	 */
	/*
	 * public BigDecimal getPayout() { return payout; }
	 *//**
	 * @param payout
	 *            the payout to set
	 */
	/*
	 * public void setPayout(BigDecimal payout) { this.payout = payout; }
	 *//**
	 * @return the phoneNumber
	 */
	/*
	 * public String getPhoneNumber() { return phoneNumber; }
	 *//**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	/*
	 * public void setPhoneNumber(String phoneNumber) { this.phoneNumber =
	 * phoneNumber; }
	 */

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the offerType
	 */
	public CampaignType getOfferType() {
		return OfferType;
	}

	/**
	 * @param offerType
	 *            the offerType to set
	 */
	public void setOfferType(CampaignType offerType) {
		OfferType = offerType;
	}

	/**
	 * @return the updatedAt
	 */
	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the campaign
	 */
	public Campaign getCampaign() {
		return campaign;
	}

	/**
	 * @param campaign
	 *            the campaign to set
	 */
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the offerFilters
	 */
	public List<OfferFilter> getOfferFilters() {
		return offerFilters;
	}

	/**
	 * @param offerFilters
	 *            the offerFilters to set
	 */
	public void setOfferFilters(List<OfferFilter> offerFilters) {
		this.offerFilters = offerFilters;
	}

	/**
	 * @return the offerOperationTimes
	 */
	public List<OfferOperationTime> getOfferOperationTimes() {
		return offerOperationTimes;
	}

	/**
	 * @param offerOperationTimes
	 *            the offerOperationTimes to set
	 */
	public void setOfferOperationTimes(
			List<OfferOperationTime> offerOperationTimes) {
		this.offerOperationTimes = offerOperationTimes;
	}

	/**
	 * @return the offerRegions
	 */
	public List<OfferRegion> getOfferRegions() {
		return offerRegions;
	}

	/**
	 * @param offerRegions
	 *            the offerRegions to set
	 */
	public void setOfferRegions(List<OfferRegion> offerRegions) {
		this.offerRegions = offerRegions;
	}

	public int getRepeatDays() {
		return repeatDays;
	}

	public void setRepeatDays(int repeatDays) {
		this.repeatDays = repeatDays;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<OfferDestinationNumber> getDestinationNumbers() {
		return destinationNumbers;
	}

	public void setDestinationNumbers(
			List<OfferDestinationNumber> destinationNumbers) {
		this.destinationNumbers = destinationNumbers;
	}

	public boolean isTransferPrompt() {
		return transferPrompt;
	}

	public void setTransferPrompt(boolean transferPrompt) {
		this.transferPrompt = transferPrompt;
	}

	public String getTextPrompt() {
		return textPrompt;
	}

	public void setTextPrompt(String textPrompt) {
		this.textPrompt = textPrompt;
	}

	public int getAudioPromptId() {
		return audioPromptId;
	}

	public void setAudioPromptId(int audioPromptId) {
		this.audioPromptId = audioPromptId;
	}

	public String getDurationType() {
		return durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}
	
	public int getConcurrencyCap() {
		return concurrencyCap;
	}

	public void setConcurrencyCap(int concurrencyCap) {
		this.concurrencyCap = concurrencyCap;
	}

	public boolean isConcurrencyType() {
		return concurrencyType;
	}

	public void setConcurrencyType(boolean concurrencyType) {
		this.concurrencyType = concurrencyType;
	}

	public boolean isBudgetType() {
		return budgetType;
	}

	public void setBudgetType(boolean budgetType) {
		this.budgetType = budgetType;
	}

	public BigDecimal getBudgetCap() {
		return budgetCap;
	}

	public void setBudgetCap(BigDecimal budgetCap) {
		this.budgetCap = budgetCap;
	}
	
	

	public int getCopycount() {
		return copycount;
	}

	public void setCopycount(int copycount) {
		this.copycount = copycount;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	
	
	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", status=" + status
				+ ", OfferType=" + OfferType + "]";
	}

	@Override
	public int compareTo(Offer object) {
		if (this.id == object.id)
			return 0;
		if (this.id < object.id)
			return -1;
		if (this.id > object.id)
			return 1;
		return 0;

	}

}
