package com.allphoneleads.api.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.allphoneleads.api.util.DateTimeDeserializer;
import com.allphoneleads.api.util.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * The persistent class for the user_accounts database table.
 * 
 */
@Entity
@Table(name="user_accounts")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="account_name")
	private String accountName;

	@Column(name="billing_card_exp")
	private String billingCardExp;

	@Column(name="billing_card_number")
	private String billingCardNumber;

	@Column(name="billing_card_type")
	private String billingCardType;

	@Column(name="billing_name")
	private String billingName;

	@Column(name="created_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private DateTime createdAt;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String notes;

	@Column(name="start_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private DateTime startAt;

	@Column(name="updated_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private DateTime updatedAt;

	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	@JsonIgnore
	private User user;
	
	@Transient
	private String startDate;

	public UserAccount() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBillingCardExp() {
		return this.billingCardExp;
	}

	public void setBillingCardExp(String billingCardExp) {
		this.billingCardExp = billingCardExp;
	}

	public String getBillingCardNumber() {
		return this.billingCardNumber;
	}

	public void setBillingCardNumber(String billingCardNumber) {
		this.billingCardNumber = billingCardNumber;
	}

	public String getBillingCardType() {
		return this.billingCardType;
	}

	public void setBillingCardType(String billingCardType) {
		this.billingCardType = billingCardType;
	}

	public String getBillingName() {
		return this.billingName;
	}

	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	public DateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public DateTime getStartAt() {
		return this.startAt;
	}

	public void setStartAt(DateTime startAt) {
		this.startAt = startAt;
	}

	public DateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
		String SimpleStartDate =simpleDateFormat.format(startAt.toDate());
		return SimpleStartDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	

}