package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.actum.springboot.liftEnergy.app.models.entity.ZoneProduction;

public interface IZoneProductionDao extends CrudRepository<ZoneProduction, Long>{

	@Query("SELECT zp FROM ZoneProduction zp WHERE zp.zoneId = :zoneId AND DATE(zp.timeStamp) = CURRENT_DATE")
    public List<ZoneProduction> findByZoneIdAndCurrentDate(@Param("zoneId") Long zoneId);
	
	@Query("SELECT zp FROM ZoneProduction zp WHERE zp.zoneId = :zoneId AND MONTH(zp.timeStamp) = MONTH(CURRENT_DATE)")
    public List<ZoneProduction> findByZoneIdAndCurrentMonth(@Param("zoneId") Long zoneId);

    @Query("SELECT zp FROM ZoneProduction zp WHERE zp.zoneId = :zoneId AND YEAR(zp.timeStamp) = :year")
    public List<ZoneProduction> findByZoneIdAndYear(@Param("zoneId") Long zoneId, @Param("year") int year);	
}
