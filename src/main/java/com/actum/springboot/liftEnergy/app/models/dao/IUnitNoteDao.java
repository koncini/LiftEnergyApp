package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.actum.springboot.liftEnergy.app.models.entity.UnitNote;

public interface IUnitNoteDao extends PagingAndSortingRepository<UnitNote, Long> {

	@Query("SELECT z FROM Zone z WHERE z.userId = :unitId")
	public Page<UnitNote> findAllNotesByUnitId(Long unitId, Pageable pageable);

	public Optional<UnitNote> findById(Long id);

	public void deleteById(Long id);

	public void save(UnitNote note);

}
