package com.allphoneleads.api.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.allphoneleads.api.util.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Table(name="users")
@Entity
public class User implements Serializable, Cloneable,Comparable<User>{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private boolean activated;

	@Column(name="activated_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime activatedAt;

	@Column(name="activation_code")
	private String activationCode;

	@Column(name="company_address1")
	private String companyAddress1;

	@Column(name="company_address2")
	private String companyAddress2;

	@Column(name="company_city")
	private String companyCity;

	@Column(name="company_country")
	private String companyCountry;

	@Column(name="company_name")
	private String companyName;

	@Column(name="company_phone_number")
	private String companyPhoneNumber;

	@Column(name="company_postal_code")
	private String companyPostalCode;

	@Column(name="company_state")
	private String companyState;

	@Column(name="company_website")
	private String companyWebsite;

	@Column(name="created_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime createdAt;

	private String email;

	@Column(name="last_login")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime lastLogin;
	
	@Column(name="last_login_browser")
	private String lastLoginBrowser;
	
	@Column(name="last_login_os")
	private String lastLoginOs;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="manager_id")
	private int managerId;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String notes;

	
	private String password;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String permissions;

	@Column(name="persist_code")
	private String persistCode;

	@Column(name="reset_password_code")
	private String resetPasswordCode;

	private String status;

	private String title;

	@Column(name = "type",columnDefinition ="ENUM('ADMIN','ADVERTISER','PUBLISHER'")
	@Enumerated(EnumType.STRING)
	private UserType type;

	@Column(name="updated_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime updatedAt;

	@OneToMany(mappedBy="user",cascade = CascadeType.PERSIST)
	private List<UserAccount> account;
	
	@Column(name ="time_zone")
	private String timeZone;
	
	@Column(name = "utc_zone")
	private String utcZone;
	
	@Column(name = "is_recording")
	private boolean isRecording;
	
	@Column(name = "is_agree")
	private boolean isAgree;
	
	@Column(name = "years_of_experience")
	private String yearsOfExperience;
	
	@Column(name = "is_running_offers")
	private boolean isRunningOffers;
	
	@Column(name = "type_of_media")
	private String typeOfMedia;
	
	@Column(name = "monthly_earnings")
	private String monthlyEarnings;
	
	@Column(name = "pay_to")
	private String payTo;
	
	@Column(name = "vertical")
	private String vertical;
	
	@Column(name = "hear_about_us_from")
	private String hearAboutUsFrom;
	
	@Column(name = "bill_to")
	private String billTo;
	
	@Column(name = "market_with_pay_per_call")
	private boolean marketWithPayPerCall;
	
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
	 * @return the activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	/**
	 * @return the activatedAt
	 */
	public DateTime getActivatedAt() {
		return activatedAt;
	}

	/**
	 * @param activatedAt the activatedAt to set
	 */
	public void setActivatedAt(DateTime activatedAt) {
		this.activatedAt = activatedAt;
	}

	/**
	 * @return the activationCode
	 */
	public String getActivationCode() {
		return activationCode;
	}

	/**
	 * @param activationCode the activationCode to set
	 */
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	/**
	 * @return the companyAddress1
	 */
	public String getCompanyAddress1() {
		return companyAddress1;
	}

	/**
	 * @param companyAddress1 the companyAddress1 to set
	 */
	public void setCompanyAddress1(String companyAddress1) {
		this.companyAddress1 = companyAddress1;
	}

	/**
	 * @return the companyAddress2
	 */
	public String getCompanyAddress2() {
		return companyAddress2;
	}

	/**
	 * @param companyAddress2 the companyAddress2 to set
	 */
	public void setCompanyAddress2(String companyAddress2) {
		this.companyAddress2 = companyAddress2;
	}

	/**
	 * @return the companyCity
	 */
	public String getCompanyCity() {
		return companyCity;
	}

	/**
	 * @param companyCity the companyCity to set
	 */
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	/**
	 * @return the companyCountry
	 */
	public String getCompanyCountry() {
		return companyCountry;
	}

	/**
	 * @param companyCountry the companyCountry to set
	 */
	public void setCompanyCountry(String companyCountry) {
		this.companyCountry = companyCountry;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyPhoneNumber
	 */
	public String getCompanyPhoneNumber() {
		return companyPhoneNumber;
	}

	/**
	 * @param companyPhoneNumber the companyPhoneNumber to set
	 */
	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}

	/**
	 * @return the companyPostalCode
	 */
	public String getCompanyPostalCode() {
		return companyPostalCode;
	}

	/**
	 * @param companyPostalCode the companyPostalCode to set
	 */
	public void setCompanyPostalCode(String companyPostalCode) {
		this.companyPostalCode = companyPostalCode;
	}

	/**
	 * @return the companyState
	 */
	public String getCompanyState() {
		return companyState;
	}

	/**
	 * @param companyState the companyState to set
	 */
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}

	/**
	 * @return the companyWebsite
	 */
	public String getCompanyWebsite() {
		return companyWebsite;
	}

	/**
	 * @param companyWebsite the companyWebsite to set
	 */
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastLogin
	 */
	public DateTime getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(DateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the managerId
	 */
	public int getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the permissions
	 */
	public String getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the persistCode
	 */
	public String getPersistCode() {
		return persistCode;
	}

	/**
	 * @param persistCode the persistCode to set
	 */
	public void setPersistCode(String persistCode) {
		this.persistCode = persistCode;
	}

	/**
	 * @return the resetPasswordCode
	 */
	public String getResetPasswordCode() {
		return resetPasswordCode;
	}

	/**
	 * @param resetPasswordCode the resetPasswordCode to set
	 */
	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the type
	 */
	public UserType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(UserType type) {
		this.type = type;
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

	/**
	 * @return the account
	 */
	public List<UserAccount> getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(List<UserAccount> account) {
		this.account = account;
	}
	
	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	public String getUtcZone() {
		return utcZone;
	}

	public void setUtcZone(String utcZone) {
		this.utcZone = utcZone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public String getLastLoginBrowser() {
		return lastLoginBrowser;
	}

	public void setLastLoginBrowser(String lastLoginBrowser) {
		this.lastLoginBrowser = lastLoginBrowser;
	}

	public String getLastLoginOs() {
		return lastLoginOs;
	}

	public void setLastLoginOs(String lastLoginOs) {
		this.lastLoginOs = lastLoginOs;
	}
	
	public boolean isRecording() {
		return isRecording;
	}

	public void setRecording(boolean isRecording) {
		this.isRecording = isRecording;
	}

	public boolean isAgree() {
		return isAgree;
	}

	public void setAgree(boolean isAgree) {
		this.isAgree = isAgree;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public boolean isRunningOffers() {
		return isRunningOffers;
	}

	public void setRunningOffers(boolean isRunningOffers) {
		this.isRunningOffers = isRunningOffers;
	}

	public String getTypeOfMedia() {
		return typeOfMedia;
	}

	public void setTypeOfMedia(String typeOfMedia) {
		this.typeOfMedia = typeOfMedia;
	}

	public String getMonthlyEarnings() {
		return monthlyEarnings;
	}

	public void setMonthlyEarnings(String monthlyEarnings) {
		this.monthlyEarnings = monthlyEarnings;
	}

	public String getPayTo() {
		return payTo;
	}

	public void setPayTo(String payTo) {
		this.payTo = payTo;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getHearAboutUsFrom() {
		return hearAboutUsFrom;
	}

	public void setHearAboutUsFrom(String hearAboutUsFrom) {
		this.hearAboutUsFrom = hearAboutUsFrom;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public boolean isMarketWithPayPerCall() {
		return marketWithPayPerCall;
	}

	public void setMarketWithPayPerCall(boolean marketWithPayPerCall) {
		this.marketWithPayPerCall = marketWithPayPerCall;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", activated=" + activated + ", activatedAt=" + activatedAt + ", activationCode="
				+ activationCode + ", companyAddress1=" + companyAddress1 + ", companyAddress2=" + companyAddress2
				+ ", companyCity=" + companyCity + ", companyCountry=" + companyCountry + ", companyName=" + companyName
				+ ", companyPhoneNumber=" + companyPhoneNumber + ", companyPostalCode=" + companyPostalCode
				+ ", companyState=" + companyState + ", companyWebsite=" + companyWebsite + ", createdAt=" + createdAt
				+ ", email=" + email + ", lastLogin=" + lastLogin + ", lastLoginBrowser=" + lastLoginBrowser
				+ ", lastLoginOs=" + lastLoginOs + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", managerId=" + managerId + ", notes=" + notes + ", password=" + password + ", permissions="
				+ permissions + ", persistCode=" + persistCode + ", resetPasswordCode=" + resetPasswordCode
				+ ", status=" + status + ", title=" + title + ", type=" + type + ", updatedAt=" + updatedAt
				+ ", account=" + account + ", timeZone=" + timeZone + ", utcZone=" + utcZone + ", isRecording="
				+ isRecording + ", isAgree=" + isAgree + ", yearsOfExperience=" + yearsOfExperience
				+ ", isRunningOffers=" + isRunningOffers + ", typeOfMedia=" + typeOfMedia + ", monthlyEarnings="
				+ monthlyEarnings + ", payTo=" + payTo + ", vertical=" + vertical + ", hearAboutUsFrom="
				+ hearAboutUsFrom + ", billTo=" + billTo + ", marketWithPayPerCall=" + marketWithPayPerCall + "]";
	}

	@Override
	public int compareTo(User object) {
		if (this.id == object.id)
			return 0;
		if (this.id < object.id)
			return -1;
		if (this.id > object.id)
			return 1;
		return 0;
	}
	
}
