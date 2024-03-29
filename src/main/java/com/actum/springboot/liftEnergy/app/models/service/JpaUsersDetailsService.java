package com.actum.springboot.liftEnergy.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.actum.springboot.liftEnergy.app.models.dao.IUserDao;
import com.actum.springboot.liftEnergy.app.models.entity.Role;

import jakarta.transaction.Transactional;

@Service("jpaUsersDetailService")
public class JpaUsersDetailsService implements UserDetailsService {

	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.actum.springboot.liftEnergy.app.models.entity.User usuario = userDao.findByUsername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Username '" + username + "' no existe en el sistema");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		if (authorities.isEmpty()) {
			throw new UsernameNotFoundException("Username '" + username + "' no tiene ningun rol asignado!");
		}

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

}
