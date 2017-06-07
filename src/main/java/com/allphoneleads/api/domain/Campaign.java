package com.allphoneleads.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.allphoneleads.api.util.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Table(name="campaigns")
@Entity
public class Campaign implements Serializable, Cloneable,Comparable<Campaign>{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type",columnDefinition ="ENUM('direct','bundled')")
	private CampaignType OfferType;
	
	@Column(name="name", length = 255)
	private String name;
	
	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "start_date")
	private Date startDate;
	
	@Column(name="expiration_type")
	private boolean expirationType;
	
	@Temporal(TemporalType.DATE)
	@Column(name="expiration_date")
	private Date expirationDate;
	
	@Column(name="payout_percentage", columnDefinition="Decimal(15,8) default '0.00000000'")
	private BigDecimal payoutPercentage;
	
	@Column(name = "status", length = 20)
	private String status;
	
	@Column(name="category_name", length = 255)
	private String categoryName;
	
    @Column(name = "created_at", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime createdAt;
	
    @Column(name = "updated_at", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime updatedAt;
    
    @Column(name="is_repeat")
	private boolean isRepeat;
    
    @Column(name="is_record")
	private boolean isRecord;
    
    @Column(name="enable_abtest")
  	private boolean enableABtest;
    
    @Column(name="enable_mediaalpha")
  	private boolean enableMediaAlpha;
    
    @Column(name="abtest_percentage")
  	private int abtestPercentage;
    
    @Column(name = "visible_to")
    private String visibleTo;
    
    @Column(name = "copycount")
	private int copycount;
    
    @Column(name = "out_bound")
    private boolean outBound;
    
    @Column(name = "lead_field_type")
    private String leadFieldType;
    

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the offerType
	 */
	public CampaignType getOfferType() {
		return OfferType;
	}

	/**
	 * @param offerType the offerType to set
	 */
	public void setOfferType(CampaignType offerType) {
		OfferType = offerType;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the expirationType
	 */
	public boolean isExpirationType() {
		return expirationType;
	}

	/**
	 * @param expirationType the expirationType to set
	 */
	public void setExpirationType(boolean expirationType) {
		this.expirationType = expirationType;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the payoutPercentage
	 */
	public BigDecimal getPayoutPercentage() {
		return payoutPercentage;
	}

	/**
	 * @param payoutPercentage the payoutPercentage to set
	 */
	public void setPayoutPercentage(BigDecimal payoutPercentage) {
		this.payoutPercentage = payoutPercentage;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the createdAt
	 */
	public DateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isRepeat() {
		return isRepeat;
	}

	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isRecord() {
		return isRecord;
	}

	public void setRecord(boolean isRecord) {
		this.isRecord = isRecord;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	

	public boolean isEnableABtest() {
		return enableABtest;
	}

	public void setEnableABtest(boolean enableABtest) {
		this.enableABtest = enableABtest;
	}
	
	public String getVisibleTo() {
		return visibleTo;
	}

	public void setVisibleTo(String visibleTo) {
		this.visibleTo = visibleTo;
	}

	public int getCopycount() {
		return copycount;
	}

	public void setCopycount(int copycount) {
		this.copycount = copycount;
	}

	public boolean isOutBound() {
		return outBound;
	}

	public void setOutBound(boolean outBound) {
		this.outBound = outBound;
	}

	public String getLeadFieldType() {
		return leadFieldType;
	}

	public void setLeadFieldType(String leadFieldType) {
		this.leadFieldType = leadFieldType;
	}

	public int getAbtestPercentage() {
		return abtestPercentage;
	}

	public void setAbtestPercentage(int abtestPercentage) {
		this.abtestPercentage = abtestPercentage;
	}

	public boolean isEnableMediaAlpha() {
		return enableMediaAlpha;
	}

	public void setEnableMediaAlpha(boolean enableMediaAlpha) {
		this.enableMediaAlpha = enableMediaAlpha;
	}

	@Override
	public int compareTo(Campaign object) {
		if (this.id == object.id)
			return 0;
		if (this.id < object.id)
			return -1;
		if (this.id > object.id)
			return 1;
		return 0;
	}
	
	
	
}
