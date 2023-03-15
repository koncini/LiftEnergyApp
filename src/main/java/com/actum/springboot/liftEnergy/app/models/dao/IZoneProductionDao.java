package com.actum.springboot.liftEnergy.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.ZoneProduction;

public interface IZoneProductionDao extends CrudRepository<ZoneProduction, Long>{

}
