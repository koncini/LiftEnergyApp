package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.actum.springboot.liftEnergy.app.models.entity.Unit;

public interface IUnitDao extends CrudRepository<Unit, Long> {

	public Optional<Unit> findByIdAndEnabledTrue(Long unitId);
	
    @Query("SELECT u.zone.name FROM Unit u WHERE u.id = :unitId")
    public String findZoneNameByUnitId(@Param("unitId") Long unitId);

}