package com.actum.springboot.liftEnergy.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "unit_events")
public class UnitEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Unit unit;

	@Column(name = "time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;

	@Column(name = "event_name")
	private String eventName;

	@Column(name = "event_detail")
	private String eventDetail;

	@Column(name = "event_priority")
	private Integer eventPriority;

	@Column(name = "event_attended")
	private Boolean eventAttended;
	
	@Column(name = "attended_by")
	private String attendedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timeStamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timeStamp = timestamp;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDetail() {
		return eventDetail;
	}

	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}

	public Integer getEventPriority() {
		return eventPriority;
	}

	public void setEventPriority(Integer eventPriority) {
		this.eventPriority = eventPriority;
	}

	public Boolean getEventAttended() {
		return eventAttended;
	}

	public void setEventAttended(Boolean eventAttended) {
		this.eventAttended = eventAttended;
	}

	public String getAttendedBy() {
		return attendedBy;
	}

	public void setAttendedBy(String attendedBy) {
		this.attendedBy = attendedBy;
	}
	
}
