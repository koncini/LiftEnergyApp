package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.actum.springboot.liftEnergy.app.models.entity.UnitProduction;

public interface IUnitProductionDao extends CrudRepository<UnitProduction, Long> {

	@Modifying
	@Query(value = "INSERT INTO units_production (unit_id, production, time) VALUES (:unitId, :production, :timeStamp)", nativeQuery = true)
	public void insertUnitProduction(@Param("unitId") Long unitId, @Param("production") Double production,
			@Param("timeStamp") Date timeStamp);

	@Query("SELECT SUM(up.production) FROM UnitProduction up WHERE up.unitId = :unitId AND DATE(up.timeStamp) = CURRENT_DATE AND TIMESTAMPDIFF(MINUTE, up.timeStamp, CURRENT_TIMESTAMP) < 60")
	public Long findTotalProductionForUnitIdInLast60Minutes(@Param("unitId") Long unitId);

	@Query("SELECT up FROM UnitProduction up WHERE up.unitId = :unitId AND DATE(up.timeStamp) = CURRENT_DATE")
	public List<UnitProduction> findByUnitIdAndCurrentDate(@Param("unitId") Long unitId);

	@Query("SELECT up FROM UnitProduction up WHERE up.unitId = :unitId AND MONTH(up.timeStamp) = MONTH(CURRENT_DATE)")
	public List<UnitProduction> findByUnitIdAndCurrentMonth(@Param("unitId") Long unitId);

	@Query("SELECT up FROM UnitProduction up WHERE up.unitId = :unitId AND YEAR(up.timeStamp) = :year")
	public List<UnitProduction> findByUnitIdAndYear(@Param("unitId") Long unitId, @Param("year") int year);

}
