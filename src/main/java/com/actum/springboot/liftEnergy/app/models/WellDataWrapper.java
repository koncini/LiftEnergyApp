package com.actum.springboot.liftEnergy.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WellDataWrapper {

	@JsonProperty("well_data")
	private List<WellData> wellData;
	
	@JsonProperty("unit_data")
	private List<UnitData> unitData;
	
	@JsonProperty("motor_data")
	private List<MotorData> motorData;
	
	@JsonProperty("power_cost")
	private List<PowerCost> powerCost;

	public List<WellData> getWellData() {
		return wellData;
	}

	public void setWellData(List<WellData> wellData) {
		this.wellData = wellData;
	}

	public List<UnitData> getUnitData() {
		return unitData;
	}

	public void setUnitData(List<UnitData> unitData) {
		this.unitData = unitData;
	}

	public List<MotorData> getMotorData() {
		return motorData;
	}

	public void setMotorData(List<MotorData> motorData) {
		this.motorData = motorData;
	}

	public List<PowerCost> getPowerCost() {
		return powerCost;
	}

	public void setPowerCost(List<PowerCost> powerCost) {
		this.powerCost = powerCost;
	}
	
	public WellData getWellDataByName(String name) {
	    for (WellData wellData : wellData) {
	        if (wellData.getName().equals(name)) {
	            return wellData;
	        }
	    }
	    return null;
	}
	
	public UnitData getUnitDataByName(String name) {
	    for (UnitData unitData : unitData) {
	        if (unitData.getName().equals(name)) {
	            return unitData;
	        }
	    }
	    return null;
	}
	
	public MotorData getMotorDataByName(String name) {
	    for (MotorData motorData : motorData) {
	        if (motorData.getName().equals(name)) {
	            return motorData;
	        }
	    }
	    return null;
	}
	
	public PowerCost getPowerCostByName(String name) {
	    for (PowerCost powerCost : powerCost) {
	        if (powerCost.getName().equals(name)) {
	            return powerCost;
	        }
	    }
	    return null;
	}

}
