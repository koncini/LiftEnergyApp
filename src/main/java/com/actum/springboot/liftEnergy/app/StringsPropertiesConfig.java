package com.actum.springboot.liftEnergy.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource("classpath:strings.properties")
})
public class StringsPropertiesConfig {

}
