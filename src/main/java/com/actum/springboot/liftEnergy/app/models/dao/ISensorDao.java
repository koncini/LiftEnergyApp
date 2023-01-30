package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.Sensor;

public interface ISensorDao extends CrudRepository<Sensor, Long> {

	public List<Sensor> findAll();

}
