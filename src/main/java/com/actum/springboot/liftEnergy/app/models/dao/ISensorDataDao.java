package com.actum.springboot.liftEnergy.app.models.dao;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.actum.springboot.liftEnergy.app.models.entity.SensorData;

public interface ISensorDataDao extends CrudRepository<SensorData, Long> {

	public List<SensorData> findByDinagraphReadingTrue();

	@Modifying
	@Query(value = "INSERT INTO Sensors_data (sensor_id, data, unit, dinagraph_reading, time) VALUES (:sensorId, :data, :unit, :dinagraphReading, :timeStamp)", nativeQuery = true)
	public void insertSensorData(@Param("sensorId") Long sensorId, @Param("data") Double data,
			@Param("unit") String unit, @Param("dinagraphReading") Boolean dinagraphReading,
			@Param("timeStamp") Date timeStamp);

	@Query("SELECT sd FROM SensorData sd JOIN sd.sensor s WHERE s.id = :sensorId AND sd.timeStamp BETWEEN :startOfDay AND :now")
	public List<SensorData> findBySensorIdAndTimestampBetween(@Param("sensorId") Long sensorId,
			@Param("startOfDay") LocalDateTime startOfDay, @Param("now") LocalDateTime now);
}
