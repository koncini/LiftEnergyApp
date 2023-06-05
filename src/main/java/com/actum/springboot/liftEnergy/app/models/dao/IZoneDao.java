package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.Zone;

public interface IZoneDao extends CrudRepository<Zone, Long> {
	
    @Query("SELECT z FROM Zone z WHERE z.userId = :userId")
    public List<Zone> findAllByUserId(Long userId);
	
	public List<ZoneNameAndId> findIdAndNameByEnabledIsTrue();
	
    public List<Zone> findTop5ByOrderByProductionDesc();

    public List<Zone> findTop5ByUserIdOrderByProductionDesc(Long userId);
    
    public List<Zone> findByEnabled(boolean enabled);

	interface ZoneNameAndId {
		Long getId();

		String getName();
	}

}
