package com.actum.springboot.liftEnergy.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;

public interface IUnitEventDao extends CrudRepository<UnitEvent, Long> {

}
