package com.actum.springboot.liftEnergy.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.actum.springboot.liftEnergy.app.auth.handler.LoginSuccessHandler;
import com.actum.springboot.liftEnergy.app.models.service.JpaUsersDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Autowired
	private LoginSuccessHandler successHandler;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JpaUsersDetailsService usersDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/", "/css/**", "/js/**", "/images/**", "/index").permitAll()
				.requestMatchers("/ver/**").hasAnyRole("USER").requestMatchers("/uploads/**").hasAnyRole("USER")
				.requestMatchers("/form/**").hasAnyRole("ADMIN")
				/*
				 * .requestMatchers("/eliminar/**").hasAnyRole("ADMIN")
				 * .requestMatchers("/factura/**").hasAnyRole("ADMIN")
				 */
				.anyRequest().authenticated().and().formLogin().successHandler(successHandler).loginPage("/login")
				.permitAll().and().logout().permitAll();
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersDetailsService).passwordEncoder(passwordEncoder);
	}

}
