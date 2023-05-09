package com.actum.springboot.liftEnergy.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.Setting;

public interface ISettingDao extends CrudRepository<Setting, Long>{

}
