package com.actum.springboot.liftEnergy.app.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitSettings {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("value")
	private Object value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
		
}
