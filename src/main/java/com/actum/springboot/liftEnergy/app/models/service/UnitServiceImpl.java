package com.actum.springboot.liftEnergy.app.models.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actum.springboot.liftEnergy.app.models.dao.ISensorDao;
import com.actum.springboot.liftEnergy.app.models.dao.ISensorDataDao;
import com.actum.springboot.liftEnergy.app.models.dao.IUnitDao;
import com.actum.springboot.liftEnergy.app.models.dao.IUnitEventDao;
import com.actum.springboot.liftEnergy.app.models.dao.IUnitNoteDao;
import com.actum.springboot.liftEnergy.app.models.dao.IUserDao;
import com.actum.springboot.liftEnergy.app.models.dao.IZoneDao;
import com.actum.springboot.liftEnergy.app.models.dao.IZoneDao.ZoneNameAndId;
import com.actum.springboot.liftEnergy.app.models.entity.Sensor;
import com.actum.springboot.liftEnergy.app.models.entity.SensorData;
import com.actum.springboot.liftEnergy.app.models.entity.Unit;
import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;
import com.actum.springboot.liftEnergy.app.models.entity.UnitNote;
import com.actum.springboot.liftEnergy.app.models.entity.User;
import com.actum.springboot.liftEnergy.app.models.entity.Zone;

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
	public List<User> findAllUsers(){
		return (List<User>) userDao.findAll();
	}

	@Override
	public List<Zone> findZonesbyUserId(Long id) {
		return (List<Zone>) zoneDao.findAllByUserId(id);
	}

	@Override
	public User findUserByName(String name) {
		return userDao.findByUsername(name);
	}

	@Override
	public void saveUnitNote(UnitNote note) {
		unitNoteDao.save(note);
	}

	@Override
	public List<UnitNote> findAllUnitNotes() {
		return (List<UnitNote>) unitNoteDao.findAll();
	}

	@Override
	public void deleteUnitNote(Long id) {
		unitNoteDao.deleteById(id);
	}

	@Override
	public UnitNote findOneUnitNote(Long id) {
		return unitNoteDao.findById(id).orElse(null);
	}

	@Override
	public void saveUnitEvent(UnitEvent event) {
		unitEventDao.save(event);		
	}

	@Override
	public List<UnitEvent> findAllUnitEvents() {
		return (List<UnitEvent>) unitEventDao.findAll();
	}

	@Override
	public void deleteUnitEvent(Long id) {
		unitEventDao.deleteById(id);
	}

	@Override
	public UnitEvent findOneUnitEvent(Long id) {
		return unitEventDao.findById(id).orElse(null);
	}

	@Override
	public List<UnitNote> getNotesByUnit(Long id) {
		return unitNoteDao.findAllNotesByUnitId(id);
	}

	@Override
	public List<SensorData> getSensorDataFromToday(Long sensorId) {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        return sensorDataDao.findBySensorIdAndTimestampBetween(sensorId, startOfDay, now);
	}

}
