package com.actum.springboot.liftEnergy.app.models.service;

import java.util.Date;
import java.util.List;

import com.actum.springboot.liftEnergy.app.models.entity.DinagraphSample;
import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.SensorData;
import com.actum.springboot.liftEnergy.app.models.entity.Setting;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;
import com.actum.springboot.liftEnergy.app.models.entity.UnitNote;
import com.actum.springboot.liftEnergy.app.models.entity.UnitProduction;
import com.actum.springboot.liftEnergy.app.models.entity.User;
import com.actum.springboot.liftEnergy.app.models.entity.Zone;
import com.actum.springboot.liftEnergy.app.models.entity.ZoneProduction;

public interface IDataService {

	public void saveZone(Zone zone);

	public List<Zone> getAllZones();

	public void deleteZone(Long id);

	public Zone getOneZone(Long id);
	
	public List<Zone> getZonesbyUserId(Long id);

	public List<Zone> getAllEnabledZones();
	
	public List<Zone> getTop5ZonesByProduction();
	
	public List<Zone> getTop5ZonesByProductionAndUserId(Long id);
	
	public String getZoneNameByUnitId(Long unitId);

	public void saveUnit(Unit unit);

	public List<Unit> getAllUnits();
	
	public List<Unit> getAllEnabledUnits();

	public void deleteUnit(Long id);

	public Unit getOneUnit(Long id);

	public Unit getEnabledUnitById(Long id);

	public void saveSensor(Sensor sensor);

	public List<Sensor> getAllSensors();

	public void deleteSensor(Long id);

	public Sensor getOneSensor(Long id);

	public List<Sensor> getEnabledSensors();

	public List<SensorData> getDinagraphData();

	public List<Sensor> getEnabledSensorsById(Long id);

	public Sensor getEnabledSensorById(Long id);

	public void insertSensorData(Long sensorId, Double data, String unit, Boolean dinagraphReading, Date timeStamp);

	public void saveSensorData(SensorData sensorData);

	public List<User> getAllUsers();
	
	public User getOneUser(Long id);

	public User getUserByName(String name);
	
	public void saveUnitNote(UnitNote note);
	
	public void deleteUser(Long id);

	public List<UnitNote> getAllUnitNotes();

	public void deleteUnitNote(Long id);

	public UnitNote getOneUnitNote(Long id);
	
	public List<UnitNote> getNotesByUnit(Long id);
	
	public void saveUnitEvent(UnitEvent event);
	
	public List<UnitEvent> getAllUnitEvents();
	
	public void deleteUnitEvent(Long id);
	
	public UnitEvent getOneUnitEvent(Long id);
	
	public List<SensorData> getSensorDataFromToday(Long sensorId);
	
	public List<SensorData> getSensorDataFromCurrentWeek(Long sensorId);
	
	public List<SensorData> getSensorDataFromCurrentMonth(Long sensorId);
	
	public List<SensorData> getSensorDataFromCurrentYear(Long sensorId);
	
	public long getCountOfUnattendedEvents();

	public void saveSetting(Setting setting);

	public List<Setting> getAllSettings();

	public void deleteSetting(Long id);

	public Setting getOneSetting(Long id);

	public List<DinagraphSample> getAllDinagraphSamples();

	public void saveDinagraphSample(DinagraphSample dinagraphSample);

	public void deleteDinagraphSample(Long id);

	public DinagraphSample getOneDinagraphSample(Long id);
	
	public List<ZoneProduction> getAllZoneProduction();
	
	public void saveZoneProduction (ZoneProduction zoneProduction);
	
	public void deleteZoneProduction (Long id);
	
	public ZoneProduction getOneZoneProduction(Long id);
		
	public List<UnitProduction> getAllUnitProduction();
	
	public void saveUnitProduction (UnitProduction unitProduction);
			
	public void deleteUnitProduction (Long id);

	public UnitProduction getOneUnitProduction(Long id);
	
	public void saveZoneProduction(Long zoneId, Double production, Date timeStamp);

	public Long getTotalZoneProductionFromLastHour(Long zoneId);

	public List<ZoneProduction> getZoneProductionFromToday(Long zoneId);
	
	public List<ZoneProduction> getZoneProductionFromCurrentWeek(Long zoneId);
	
	public List<ZoneProduction> getZoneProductionFromCurrentMonth(Long zoneId);
	
	public List<ZoneProduction> getZoneProductionFromCurrentYear(Long zoneId);

	public void saveUnitProduction(Long unitId, Double production, Date timeStamp);

	public Long getTotalUnitProductionFromLastHour(Long unitId);

	public List<UnitProduction> getUnitProductionFromToday(Long unitId);
	
	public List<UnitProduction> getUnitProductionFromCurrentWeek(Long unitId);

	public List<UnitProduction> getUnitProductionFromCurrentMonth(Long unitId);
	
	public List<UnitProduction> getUnitProductionFromCurrentYear(Long unitId);
}