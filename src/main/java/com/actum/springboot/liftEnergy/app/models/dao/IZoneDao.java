package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.Zone;

public interface IZoneDao extends CrudRepository<Zone, Long> {

	public List<ZoneNameAndId> findIdAndNameByEnabledIsTrue();

	interface ZoneNameAndId {
		Long getId();

		String getName();
	}

}
