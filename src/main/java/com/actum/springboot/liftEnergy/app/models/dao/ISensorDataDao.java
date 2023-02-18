package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.SensorData;

public interface ISensorDataDao extends CrudRepository<SensorData, Long> {

	public List<SensorData> findByDinagraphReadingTrue();

}
