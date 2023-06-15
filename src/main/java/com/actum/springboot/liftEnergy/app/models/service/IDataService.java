package com.actum.springboot.liftEnergy.app.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	
	//______________________________Zones____________________________________

	public void saveZone(Zone zone);

	public List<Zone> getAllZones();

	public void deleteZone(Long id);

	public Zone getOneZone(Long id);
	
	public List<Zone> getZonesbyUserId(Long id);

	public List<Zone> getAllEnabledZones();
	
	public List<Zone> getTop5ZonesByProduction();
	
	public List<Zone> getTop5ZonesByProductionAndUserId(Long id);
	
	public String getZoneNameByUnitId(Long unitId);
	
	//______________________________Units____________________________________

	public void saveUnit(Unit unit);

	public List<Unit> getAllUnits();
	
	public List<Unit> getAllEnabledUnits();

	public void deleteUnit(Long id);

	public Unit getOneUnit(Long id);

	public Unit getEnabledUnitById(Long id);
	
	//______________________________Sensors____________________________________

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
	
	//_______________________________Users_____________________________________

	public List<User> getAllUsers();
	
	public User getOneUser(Long id);

	public User getUserByName(String name);
	
	public void deleteUser(Long id);
	
	//_______________________________Unit Notes________________________________
	
	public void saveUnitNote(UnitNote note);

	public Page<UnitNote> getAllUnitNotes(Pageable pageable);

	public UnitNote getOneUnitNote(Long id);
	
	public Page<UnitNote> getNotesByUnit(Long id, Pageable pageable);
	
	public void deleteUnitNote(Long id);
	
	//_______________________________Unit Events_______________________________
	
	public void saveUnitEvent(UnitEvent event);
	
	public Page<UnitEvent> getAllUnitEvents(Pageable pageable);
	
	public Page<UnitEvent> getAllAttendedUnitEvents(Pageable pageable);
	
	public Page<UnitEvent> getAllUnattendedUnitEvents(Pageable pageable);
	
	public void deleteUnitEvent(Long id);
	
	public UnitEvent getOneUnitEvent(Long id);
	
	public long getCountOfUnattendedEvents();
	
	//_______________________________Sensor Data_______________________________
	
	public List<SensorData> getSensorDataFromToday(Long sensorId);
	
	public List<SensorData> getSensorDataFromCurrentWeek(Long sensorId);
	
	public List<SensorData> getSensorDataFromCurrentMonth(Long sensorId);
	
	public List<SensorData> getSensorDataFromCurrentYear(Long sensorId);
	
	//_______________________________Settings__________________________________
	
	public void saveSetting(Setting setting);

	public List<Setting> getAllSettings();

	public void deleteSetting(Long id);

	public Setting getOneSetting(Long id);
	
	//__________________________Dinagraph Samples______________________________

	public List<DinagraphSample> getAllDinagraphSamples();

	public void saveDinagraphSample(DinagraphSample dinagraphSample);

	public void deleteDinagraphSample(Long id);

	public DinagraphSample getOneDinagraphSample(Long id);
	
	//____________________________Zone Production______________________________
	
	public List<ZoneProduction> getAllZoneProduction();
	
	public void saveZoneProduction (ZoneProduction zoneProduction);
	
	public void deleteZoneProduction (Long id);
	
	public ZoneProduction getOneZoneProduction(Long id);
	
	public void saveZoneProduction(Long zoneId, Double production, Date timeStamp);
	
	public Long getTotalZoneProductionFromLastHour(Long zoneId);
	
	public List<ZoneProduction> getZoneProductionFromToday(Long zoneId);
	
	public List<ZoneProduction> getZoneProductionFromCurrentWeek(Long zoneId);
	
	public List<ZoneProduction> getZoneProductionFromCurrentMonth(Long zoneId);
	
	public List<ZoneProduction> getZoneProductionFromCurrentYear(Long zoneId);
	
	//____________________________Unit Production______________________________
		
	public List<UnitProduction> getAllUnitProduction();
	
	public void saveUnitProduction (UnitProduction unitProduction);
			
	public void deleteUnitProduction (Long id);

	public UnitProduction getOneUnitProduction(Long id);

	public void saveUnitProduction(Long unitId, Double production, Date timeStamp);

	public Long getTotalUnitProductionFromLastHour(Long unitId);

	public List<UnitProduction> getUnitProductionFromToday(Long unitId);
	
	public List<UnitProduction> getUnitProductionFromCurrentWeek(Long unitId);

	public List<UnitProduction> getUnitProductionFromCurrentMonth(Long unitId);
	
	public List<UnitProduction> getUnitProductionFromCurrentYear(Long unitId);
}