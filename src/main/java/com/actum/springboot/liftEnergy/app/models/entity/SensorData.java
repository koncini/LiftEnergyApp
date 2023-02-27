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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "sensors_data")
public class SensorData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double data;

	private String unit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sensor_id", insertable = false, updatable = false)
	@JsonBackReference
	private Sensor sensor;

	@Column(name = "time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;

	@Column(name = "dinagraph_reading")
	private Boolean dinagraphReading;

	public SensorData() {
		super();
	}

	public SensorData(Long id, Double data, String unit, Sensor sensor, Date timeStamp, Boolean dinagraphReading) {
		super();
		this.id = id;
		this.data = data;
		this.unit = unit;
		this.sensor = sensor;
		this.timeStamp = timeStamp;
		this.dinagraphReading = dinagraphReading;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Boolean getDinagraphReading() {
		return dinagraphReading;
	}

	public void setDinagraphReading(Boolean dinagraphReading) {
		this.dinagraphReading = dinagraphReading;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

}
