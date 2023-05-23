package com.actum.springboot.liftEnergy.app.models.service;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.actum.springboot.liftEnergy.app.models.dao.IDinagraphSampleDao;
import com.actum.springboot.liftEnergy.app.models.dao.ISensorDao;
import com.actum.springboot.liftEnergy.app.models.dao.ISensorDataDao;
import com.actum.springboot.liftEnergy.app.models.dao.ISettingDao;
import com.actum.springboot.liftEnergy.app.models.dao.IUnitDao;
import com.actum.springboot.liftEnergy.app.models.dao.IUnitEventDao;
import com.actum.springboot.liftEnergy.app.models.dao.IUnitNoteDao;
import com.actum.springboot.liftEnergy.app.models.dao.IUnitProductionDao;
import com.actum.springboot.liftEnergy.app.models.dao.IUserDao;
import com.actum.springboot.liftEnergy.app.models.dao.IZoneDao;
import com.actum.springboot.liftEnergy.app.models.dao.IZoneDao.ZoneNameAndId;
import com.actum.springboot.liftEnergy.app.models.dao.IZoneProductionDao;
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

import jakarta.transaction.Transactional;

@Service
public class UnitServiceImpl implements IUnitService {

	@Autowired
	private IZoneDao zoneDao;

	@Autowired
	private IUnitDao unitDao;

	@Autowired
	private ISensorDao sensorDao;

	@Autowired
	private ISensorDataDao sensorDataDao;

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUnitNoteDao unitNoteDao;

	@Autowired
	private IUnitEventDao unitEventDao;
	
	@Autowired
	private ISettingDao settingDao;
	
	@Autowired
	private IDinagraphSampleDao dinagraphSampleDao;
	
	@Autowired
	private IZoneProductionDao zoneProductionDao;
	
	@Autowired
	private IUnitProductionDao unitProductionDao;

	@Override
	@Transactional
	public List<Unit> findAllUnits() {
		return (List<Unit>) unitDao.findAll();
	}

	@Override
	@Transactional
	public Unit findOneUnit(Long id) {
		return unitDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void saveUnit(Unit unit) {
		unitDao.save(unit);
	}

	@Override
	@Transactional
	public void deleteUnit(Long id) {
		unitDao.deleteById(id);
	}

	@Override
	@Transactional
	public void saveZone(Zone zone) {
		zoneDao.save(zone);
	}

	@Override
	@Transactional
	public List<Zone> findAllZones() {
		return (List<Zone>) zoneDao.findAll();
	}

	@Override
	@Transactional
	public List<ZoneNameAndId> findEnabledZones() {
		return zoneDao.findIdAndNameByEnabledIsTrue();
	}

	@Override
	@Transactional
	public void deleteZone(Long id) {
		zoneDao.deleteById(id);
	}

	@Override
	@Transactional
	public Zone findOneZone(Long id) {
		return zoneDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void saveSensor(Sensor sensor) {
		sensorDao.save(sensor);
	}

	@Override
	@Transactional
	public List<Sensor> findAllSensors() {
		return (List<Sensor>) sensorDao.findAll();
	}

	@Override
	@Transactional
	public void deleteSensor(Long id) {
		sensorDao.deleteById(id);
	}

	@Override
	@Transactional
	public Sensor findOneSensor(Long id) {
		return sensorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<Sensor> findEnabledSensors() {
		return sensorDao.findByEnabledTrue();
	}

	@Override
	@Transactional
	public List<Sensor> findEnabledSensorsById(Long id) {
		return sensorDao.findByEnabledIsTrueAndUnitId(id);
	}

	@Override
	@Transactional
	public Sensor findEnabledSensorById(Long id) {
		return sensorDao.findByIdAndEnabledTrue(id).orElse(null);
	}

	@Override
	@Transactional
	public void saveSensorData(SensorData sensorData) {
		sensorDataDao.save(sensorData);
	}

	@Override
	@Transactional
	public List<SensorData> findDinagraphData() {
		return sensorDataDao.findByDinagraphReadingTrue();
	}

	@Override
	@Transactional
	public void insertSensorData(Long sensorId, Double data, String unit, Boolean dinagraphReading, Date timeStamp) {
		sensorDataDao.insertSensorData(sensorId, data, unit, dinagraphReading, timeStamp);
	}

	@Override
	@Transactional
	public List<User> findAllUsers() {
		return (List<User>) userDao.findAll();
	}

	@Override
	@Transactional
	public List<Zone> findZonesbyUserId(Long id) {
		return (List<Zone>) zoneDao.findAllByUserId(id);
	}

	@Override
	@Transactional
	public User findUserByName(String name) {
		return userDao.findByUsername(name);
	}

	@Override
	@Transactional
	public void saveUnitNote(UnitNote note) {
		unitNoteDao.save(note);
	}

	@Override
	@Transactional
	public List<UnitNote> findAllUnitNotes() {
		return (List<UnitNote>) unitNoteDao.findAll();
	}

	@Override
	@Transactional
	public void deleteUnitNote(Long id) {
		unitNoteDao.deleteById(id);
	}

	@Override
	@Transactional
	public UnitNote findOneUnitNote(Long id) {
		return unitNoteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void saveUnitEvent(UnitEvent event) {
		unitEventDao.save(event);
	}

	@Override
	@Transactional
	public List<UnitEvent> findAllUnitEvents() {
		return (List<UnitEvent>) unitEventDao.findAll();
	}

	@Override
	@Transactional
	public void deleteUnitEvent(Long id) {
		unitEventDao.deleteById(id);
	}

	@Override
	@Transactional
	public UnitEvent findOneUnitEvent(Long id) {
		return unitEventDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<UnitNote> getNotesByUnit(Long id) {
		return unitNoteDao.findAllNotesByUnitId(id);
	}

	@Override
	@Transactional
	public List<SensorData> getSensorDataFromToday(Long sensorId) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date startOfDay = calendar.getTime();
		Date now = new Date();
		return sensorDataDao.findBySensorIdAndTimestampCurrentDay(sensorId, startOfDay, now);
	}

	@Override
	@Transactional
	public List<Zone> findTop5ZonesByProduction() {
		return zoneDao.findTop5ByOrderByProductionDesc();
	}

	@Override
	@Transactional
	public List<Zone> findTop5ZonesByProductionAndUserId(Long id) {
		return zoneDao.findTop5ByUserIdOrderByProductionDesc(id);
	}

	@Override
	@Transactional
	public List<SensorData> getSensorDataFromCurrentMonth(Long sensorId) {
		Date now = new Date();
		return sensorDataDao.findBySensorIdAndTimeStampCurrentMonth(sensorId, now);
	}

	@Override
	@Transactional
	public List<SensorData> getSensorDataFromCurrentYear(Long sensorId) {
		int year = Year.now().getValue();
		return sensorDataDao.findBySensorIdAndTimeStampCurrentYear(sensorId, year);
	}

	@Override
	@Transactional
	public long getCountOfUnattendedEvents() {
		return unitEventDao.countByEventAttended(false);
	}

	@Override
	@Transactional
	public User findOneUser(Long id) {
		return userDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void saveSetting(Setting setting) {
		settingDao.save(setting);
	}

	@Override
	@Transactional
	public List<Setting> findAllSettings() {
		return (List<Setting>) settingDao.findAll();
	}

	@Override
	@Transactional
	public void deleteSetting(Long id) {
		settingDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public Setting findOneSetting(Long id) {
		return settingDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void saveDinagraphSample(DinagraphSample dinagraphSample) {
		dinagraphSampleDao.save(dinagraphSample);
	}

	@Override
	@Transactional
	public List<DinagraphSample> findAllDinagraphSamples() {
		return (List<DinagraphSample>) dinagraphSampleDao.findAll();
	}

	@Override
	@Transactional
	public void deleteDinagraphSample(Long id) {
		dinagraphSampleDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public DinagraphSample findOneDinagraphSample(Long id) {
		return dinagraphSampleDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<ZoneProduction> findAllZoneProduction() {
		return (List<ZoneProduction>) zoneProductionDao.findAll();
	}

	@Override
	@Transactional
	public void saveZoneProduction(ZoneProduction zoneProduction) {
		zoneProductionDao.save(zoneProduction);
	}

	@Override
	@Transactional
	public void deleteZoneProduction(Long id) {
		zoneProductionDao.deleteById(id);		
	}

	@Override
	@Transactional
	public ZoneProduction findOneZoneProduction(Long id) {
		return zoneProductionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<UnitProduction> findAllUnitProduction() {
		return (List<UnitProduction>) unitProductionDao.findAll();
	}

	@Override
	@Transactional
	public void saveUnitProduction(UnitProduction unitProduction) {
		unitProductionDao.save(unitProduction);
	}

	@Override
	@Transactional
	public void deleteUnitProduction(Long id) {
		unitProductionDao.deleteById(id);		
	}

	@Override
	@Transactional
	public UnitProduction findOneUnitProduction(Long id) {
		return unitProductionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<ZoneProduction> getZoneProductionFromToday(Long zoneId) {
		return zoneProductionDao.findByZoneIdAndCurrentDate(zoneId);
	}

	@Override
	@Transactional
	public List<ZoneProduction> getZoneProductionFromCurrentMonth(Long zoneId) {
		return zoneProductionDao.findByZoneIdAndCurrentMonth(zoneId);
	}

	@Override
	@Transactional
	public List<ZoneProduction> getZoneProductionFromCurrentYear(Long zoneId) {
		int year = Year.now().getValue();
		return zoneProductionDao.findByZoneIdAndYear(zoneId, year);
	}

	@Override
	@Transactional
	public List<UnitProduction> getUnitProductionFromToday(Long unitId) {
		return unitProductionDao.findByUnitIdAndCurrentDate(unitId);
	}

	@Override
	@Transactional
	public List<UnitProduction> getUnitProductionFromCurrentMonth(Long unitId) {
		return unitProductionDao.findByUnitIdAndCurrentMonth(unitId);
	}

	@Override
	@Transactional
	public List<UnitProduction> getUnitProductionFromCurrentYear(Long unitId) {
		int year = Year.now().getValue();
		return unitProductionDao.findByUnitIdAndYear(unitId, year);
	}

	@Override
	@Transactional
	public Unit findEnabledUnitById(Long id) {
		return unitDao.findByIdAndEnabledTrue(id).orElse(null);
	}

	@Override
	@Transactional
	public void insertUnitProduction(Long unitId, Double production, Date timeStamp) {
		unitProductionDao.insertUnitProduction(unitId, production, timeStamp);		
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		userDao.deleteById(id);
	}

}
