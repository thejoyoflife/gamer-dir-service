package com.example.gamer.directory.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@Value("${spring.application.description}")
	private String applicationDescription;
	
	@Value("${spring.application.version}")
	private String applicationVersion;
	
	@Bean
	public OpenAPI apiDocConfig() {
		return new OpenAPI()
					.info(new Info()
							.title(applicationName)
							.description(applicationDescription)
							.version(applicationVersion));
	}
}
