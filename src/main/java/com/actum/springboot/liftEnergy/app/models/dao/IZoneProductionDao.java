package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.actum.springboot.liftEnergy.app.models.entity.ZoneProduction;

public interface IZoneProductionDao extends CrudRepository<ZoneProduction, Long>{

	@Query("SELECT zp FROM ZoneProduction zp JOIN zp.zone z WHERE z.id = :zoneId AND zp.timeStamp BETWEEN :startOfDay AND :now")
	public List<ZoneProduction> findByZoneIdAndTimestampCurrentDay(@Param("zoneId") Long zoneId,
			@Param("startOfDay") Date startOfDay, @Param("now") Date now);

	@Query("SELECT zp FROM ZoneProduction zp JOIN zp.zone z WHERE z.id = :zoneId AND YEAR(zp.timeStamp) = YEAR(:now) AND MONTH(zp.timeStamp) = MONTH(:now)")
	public List<ZoneProduction> findByZoneIdAndTimeStampCurrentMonth(@Param("zoneId") Long zoneId,
			@Param("now") Date now);
	
	@Query("SELECT zp FROM ZoneProduction zp WHERE zp.zone.id = :zoneId AND YEAR(zp.timeStamp) = :year")
    public List<ZoneProduction> findByZoneIdAndTimeStampCurrentYear(@Param("zoneId") Long zoneId, @Param("year") int year);
	
}
