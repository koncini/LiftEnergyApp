package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.Unit;

public interface IUnitDao extends CrudRepository<Unit, Long> {

	public Optional<Unit> findByIdAndEnabledTrue(Long unitId);

}