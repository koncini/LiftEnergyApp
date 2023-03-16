package com.actum.springboot.liftEnergy.app.models.service;

import java.util.Date;
import java.util.List;

import com.actum.springboot.liftEnergy.app.models.dao.IZoneDao.ZoneNameAndId;
import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.SensorData;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;
import com.actum.springboot.liftEnergy.app.models.entity.UnitNote;
import com.actum.springboot.liftEnergy.app.models.entity.User;
import com.actum.springboot.liftEnergy.app.models.entity.Zone;

public interface IUnitService {

	public void saveZone(Zone zone);

	public List<Zone> findAllZones();

	public void deleteZone(Long id);

	public Zone findOneZone(Long id);
	
	public List<Zone> findZonesbyUserId(Long id);
	
	public List<Zone> findTop5ZonesByProduction();
	
	public List<Zone> findTop5ZonesByProductionAndUserId(Long id);

	public void saveUnit(Unit unit);

	public List<Unit> findAllUnits();

	public void deleteUnit(Long id);

	public Unit findOneUnit(Long id);

	public void saveSensor(Sensor sensor);

	public List<Sensor> findAllSensors();

	public void deleteSensor(Long id);

	public Sensor findOneSensor(Long id);

	public List<Sensor> findEnabledSensors();

	public List<SensorData> findDinagraphData();

	public List<Sensor> findEnabledSensorsById(Long id);

	public Sensor findEnabledSensorById(Long id);

	public void insertSensorData(Long sensorId, Double data, String unit, Boolean dinagraphReading, Date timeStamp);

	public void saveSensorData(SensorData sensorData);

	public List<ZoneNameAndId> findEnabledZones();

	public List<User> findAllUsers();
	
	public User findOneUser(Long id);

	public User findUserByName(String name);
	
	public void saveUnitNote(UnitNote note);

	public List<UnitNote> findAllUnitNotes();

	public void deleteUnitNote(Long id);

	public UnitNote findOneUnitNote(Long id);
	
	public List<UnitNote> getNotesByUnit(Long id);
	
	public void saveUnitEvent(UnitEvent event);
	
	public List<UnitEvent> findAllUnitEvents();
	
	public void deleteUnitEvent(Long id);
	
	public UnitEvent findOneUnitEvent(Long id);
	
	public List<SensorData> getSensorDataFromToday(Long sensorId);
	
	public List<SensorData> getSensorDataFromCurrentMonth(Long sensorId);
	
	public List<SensorData> getSensorDataFromCurrentYear(Long sensorId);
	
	public long getCountOfUnattendedEvents();
			
}