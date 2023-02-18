package com.actum.springboot.liftEnergy.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.actum.springboot.liftEnergy.app.models.entity.Node;

public interface INodeDao extends CrudRepository<Node, Long> {

}
