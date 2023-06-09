package com.actum.springboot.liftEnergy.app.models.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.actum.springboot.liftEnergy.app.models.entity.UnitEvent;

public interface IUnitEventDao extends PagingAndSortingRepository<UnitEvent, Long> {
	
	public long countByEventAttended(boolean eventAttended);
	
	public Page<UnitEvent> findAllByEventAttendedIsTrue(Pageable pageable);

	public Page<UnitEvent> findAllByEventAttendedIsFalse(Pageable pageable);

	public void save(UnitEvent event);

	public void deleteById(Long id);

	public Optional<UnitEvent> findById(Long id);
	
}
