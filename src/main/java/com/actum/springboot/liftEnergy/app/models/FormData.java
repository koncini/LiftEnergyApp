package com.actum.springboot.liftEnergy.app.models;

import com.actum.springboot.liftEnergy.app.models.entity.Sensor;

public class FormData {
	
	private Sensor sensor;
	private SensorSetting sensorSetting;
	
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	public SensorSetting getSensorSetting() {
		return sensorSetting;
	}
	public void setSensorSetting(SensorSetting sensorSetting) {
		this.sensorSetting = sensorSetting;
	}
	
	

}
