package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.actum.springboot.liftEnergy.app.models.entity.ZoneProduction;

public interface IZoneProductionDao extends CrudRepository<ZoneProduction, Long>{
	
	@Modifying
	@Query(value = "INSERT INTO zones_production (zone_id, production, time) VALUES (:zoneId, :production, :timeStamp)", nativeQuery = true)
	public void insertZoneProduction(@Param("zoneId") Long zoneId, @Param("production") Double production, @Param("timeStamp") Date timeStamp);
	
	@Query("SELECT SUM(zp.production) FROM ZoneProduction zp WHERE zp.zoneId = :zoneId AND DATE(zp.timeStamp) = CURRENT_DATE AND TIMESTAMPDIFF(MINUTE, zp.timeStamp, CURRENT_TIMESTAMP) < 60")
    public Long findTotalProductionForZoneIdInLast60Minutes(@Param("zoneId") Long zoneId);

	@Query("SELECT zp FROM ZoneProduction zp WHERE zp.zoneId = :zoneId AND DATE(zp.timeStamp) = CURRENT_DATE")
    public List<ZoneProduction> findByZoneIdAndCurrentDate(@Param("zoneId") Long zoneId);
	
	@Query("SELECT zp FROM ZoneProduction zp WHERE zp.zoneId = :zoneId AND WEEK(zp.timeStamp) = WEEK(CURRENT_DATE)")
	public List<ZoneProduction> findByZoneIdAndCurrentWeek(@Param("zoneId") Long zoneId);
	
	@Query("SELECT zp FROM ZoneProduction zp WHERE zp.zoneId = :zoneId AND MONTH(zp.timeStamp) = MONTH(CURRENT_DATE)")
    public List<ZoneProduction> findByZoneIdAndCurrentMonth(@Param("zoneId") Long zoneId);

    @Query("SELECT zp FROM ZoneProduction zp WHERE zp.zoneId = :zoneId AND YEAR(zp.timeStamp) = :year")
    public List<ZoneProduction> findByZoneIdAndYear(@Param("zoneId") Long zoneId, @Param("year") int year);	
}
