package com.example.gamer.directory.app.config;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainerConfig {

	@Bean
	@ServiceConnection
	@RestartScope
	public PostgreSQLContainer<?> postgreSQLContainer(DynamicPropertyRegistry registry) {
		@SuppressWarnings("resource")
		var postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
							.withDatabaseName("gamers")
							.withUsername("admin")
							.withPassword("admin");
		return postgres;
	}
}
