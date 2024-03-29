package com.actum.springboot.liftEnergy.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LiftEnergyAppApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LiftEnergyAppApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String password = "Flowdynamics3";
		
		for(int i=0; i<2; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
	}

}
