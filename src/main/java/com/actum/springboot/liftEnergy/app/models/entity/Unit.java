package com.actum.springboot.liftEnergy.app.models.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "units", uniqueConstraints = { @UniqueConstraint(columnNames = { "zone_id" }) })
public class Unit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Zone zone;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "unit_id")
	@JsonManagedReference
	private List<Sensor> sensors;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	@JsonManagedReference
	private List<UnitNote> notes;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	@JsonManagedReference
	private List<UnitEvent> events;

	private String settings;

	private Double latitude;

	private Double longitude;

	private Boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	public List<UnitNote> getNotes() {
		return notes;
	}

	public void setNotes(List<UnitNote> notes) {
		this.notes = notes;
	}

	public List<UnitEvent> getEvents() {
		return events;
	}

	public void setEvents(List<UnitEvent> events) {
		this.events = events;
	}

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
