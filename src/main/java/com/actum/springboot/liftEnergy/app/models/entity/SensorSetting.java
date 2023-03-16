package com.actum.springboot.liftEnergy.app.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorSetting {

	@JsonProperty("min_value")
	private Double minValue;

	@JsonProperty("max_value")
	private Double maxValue;

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

}