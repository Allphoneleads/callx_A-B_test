package com.allphoneleads.api.domain;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.allphoneleads.api.util.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * The persistent class for the offer_operation_times database table.
 * 
 */
@Entity
@Table(name="offer_operation_times")
public class OfferOperationTime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="created_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime createdAt;

	private String day;

	/*@Column(name="end_hour")
	private Date endHour;

	@Column(name="start_hour")
	private Date startHour;*/
	
	@Column(name="end_hour")
	private Time endHour;

	@Column(name="start_hour")
	private Time startHour;

	@Enumerated(EnumType.STRING)
	@Column(name = "status",columnDefinition ="ENUM('open','close')")
	private StatusType status;

	@Column(name="updated_at")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
	private DateTime updatedAt;

	@ManyToOne
	@JoinColumn(name="offer_id", referencedColumnName = "id")
	@JsonIgnore
	private Offer offer;

	public OfferOperationTime() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}

/*	public Date getEndHour() {
		return this.endHour;
	}

	public void setEndHour(Date endHour) {
		this.endHour = endHour;
	}

	public Date getStartHour() {
		return this.startHour;
	}

	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}*/
	
	public Time getEndHour() {
		return this.endHour;
	}

	public void setEndHour(Time endHour) {
		this.endHour = endHour;
	}

	public Time getStartHour() {
		return this.startHour;
	}

	public void setStartHour(Time startHour) {
		this.startHour = startHour;
	}

	public Offer getOffer() {
		return this.offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
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
	 * @return the status
	 */
	public StatusType getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusType status) {
		this.status = status;
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
	
	

}