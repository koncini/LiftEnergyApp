package com.actum.springboot.liftEnergy.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
		http
			.authorizeHttpRequests().requestMatchers("/", "/css/**", "/js/**", "/images/**", "/error/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/api/sensors-data/upload-data/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/api/unit-production/upload-data/**").permitAll()
			.requestMatchers(HttpMethod.GET, "/api/sensors-data/get-dinagraph-data/**").permitAll()
			.requestMatchers(HttpMethod.GET, "/api/unit/get-unit-setup/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/pushover/message**").permitAll()
			.requestMatchers("/units/**").hasAnyRole("USER")
			.requestMatchers("/zone/**").hasAnyRole("ADMIN")
			.requestMatchers("/artix/**").hasAnyRole("ADMIN")
			.anyRequest().authenticated().and()
			.formLogin().successHandler(successHandler).loginPage("/login").permitAll().and()
			.csrf().disable()
			.logout().permitAll();
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersDetailsService).passwordEncoder(passwordEncoder);
	}

}
