package com.actum.springboot.liftEnergy.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.User;

public interface IUserDao extends CrudRepository<User, Long> {

	public User findByUsername(String username);
	
}