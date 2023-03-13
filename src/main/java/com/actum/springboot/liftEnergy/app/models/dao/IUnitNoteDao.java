package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.UnitNote;

public interface IUnitNoteDao extends CrudRepository<UnitNote, Long>{

	@Query("SELECT z FROM Zone z WHERE z.userId = :unitId")
	public List<UnitNote> findAllNotesByUnitId(Long unitId);
	
}
