package com.allphoneleads.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.allphoneleads.api.converters.IvrKeyConverter;
import com.allphoneleads.api.util.DateTimeSerializer;
import com.allphoneleads.api.util.IvrKeyDeserializer;
import com.allphoneleads.api.util.IvrKeySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * The persistent class for the campaign_ivr_trees database table.
 * 
 */
@Entity
@Table(name="campaign_ivr_trees")
public class CampaignIvrTree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="campaign_id", referencedColumnName = "id")
	private Campaign campaign;

	@Column(name="parent_id")
	private int parentId;
	
	/*@Enumerated(EnumType.STRING)
	@Column(name = "key",columnDefinition ="ENUM('1','2','3','4','5','6','7','8','9','0','#','*')")*/
	@Convert(converter = IvrKeyConverter.class)
	@JsonSerialize(using = IvrKeySerializer.class)
	@JsonDeserialize(using = IvrKeyDeserializer.class)
	@Column(name = "\"key\"",columnDefinition ="ENUM('1','2','3','4','5','6','7','8','9','0','#','*')")
	private IvrKey key;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "\"action\"",columnDefinition ="ENUM('ask','fwd','hangup','verifylocation','sms')")
	private IvrAction action;
	
	@Column(name="campaign_filter_id")
	private int campaignFilterId;

	@Column(name="audio_prompt_id")
	private int audioPromptId;
	
	@Column(name ="sms_at")
	private int smsAt;
	
	@Column(name ="sms_message")
	private String smsMessage;

	@Column(name="created_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime createdAt;


	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="play_prompt_first")
	private boolean playPromptFirst;

	@Lob
	@Column(name="prompt", columnDefinition = "TEXT")
	private String prompt;

	@Column(name="updated_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime updatedAt;
	
	@Column(name="cdn_url")
	private String cdnUrl;
	
	@Column(name= "destination_id")
	private int destinationId;
	
	@Column(name= "destination_name")
	private String destinationName;
	
	@Column(name = "destination_number")
	private String destinationNumber;


	public CampaignIvrTree() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IvrAction getAction() {
		return this.action;
	}

	public void setAction(IvrAction action) {
		this.action = action;
	}

	public int getAudioPromptId() {
		return this.audioPromptId;
	}

	public void setAudioPromptId(int audioPromptId) {
		this.audioPromptId = audioPromptId;
	}

	public int getCampaignFilterId() {
		return this.campaignFilterId;
	}

	public void setCampaignFilterId(int campaignFilterId) {
		this.campaignFilterId = campaignFilterId;
	}

	public DateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public IvrKey getKey() {
		return this.key;
	}

	public void setKey(IvrKey key) {
		this.key = key;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getPlayPromptFirst() {
		return this.playPromptFirst;
	}

	public void setPlayPromptFirst(boolean playPromptFirst) {
		this.playPromptFirst = playPromptFirst;
	}

	public String getPrompt() {
		return this.prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public DateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCdnUrl() {
		return cdnUrl;
	}

	public void setCdnUrl(String cdnUrl) {
		this.cdnUrl = cdnUrl;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationNumber() {
		return destinationNumber;
	}

	public void setDestinationNumber(String destinationNumber) {
		this.destinationNumber = destinationNumber;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	public int getSmsAt() {
		return smsAt;
	}

	public void setSmsAt(int smsAt) {
		this.smsAt = smsAt;
	}

	public String getSmsMessage() {
		return smsMessage;
	}

	public void setSmsMessage(String smsMessage) {
		this.smsMessage = smsMessage;
	}

}