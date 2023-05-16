package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.actum.springboot.liftEnergy.app.models.entity.UnitProduction;

public interface IUnitProductionDao extends CrudRepository<UnitProduction, Long>{
	
	@Query("SELECT up FROM UnitProduction up JOIN up.unit u WHERE u.id = :unitId AND up.timeStamp BETWEEN :startOfDay AND :now")
	public List<UnitProduction> findByUnitIdAndTimestampCurrentDay(@Param("unitId") Long unitId,
			@Param("startOfDay") Date startOfDay, @Param("now") Date now);

	@Query("SELECT up FROM UnitProduction up JOIN up.unit u WHERE u.id = :unitId AND YEAR(up.timeStamp) = YEAR(:now) AND MONTH(up.timeStamp) = MONTH(:now)")
	public List<UnitProduction> findByUnitIdAndTimeStampCurrentMonth(@Param("unitId") Long unitId,
			@Param("now") Date now);
	
	@Query("SELECT up FROM UnitProduction up WHERE up.unit.id = :unitId AND YEAR(up.timeStamp) = :year")
    public List<UnitProduction> findByUnitIdAndTimeStampCurrentYear(@Param("unitId") Long unitId, @Param("year") int year);

}
