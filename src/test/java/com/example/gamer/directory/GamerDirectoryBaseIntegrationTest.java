package com.example.gamer.directory;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import lombok.extern.slf4j.Slf4j;

@Testcontainers
@Slf4j
public class GamerDirectoryBaseIntegrationTest {
	
	static PostgreSQLContainer<?> postgres = 
			new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.1"));
	
	static {
		Startables.deepStart(postgres).join();
		//var databaseDelegate = new JdbcDatabaseDelegate(postgres, "");
		//ScriptUtils.runInitScript(databaseDelegate, "/some/classpath/location");
		//ScriptUtils.runInitScript(databaseDelegate, "/some/other/classpath/location");
	}
	
	@DynamicPropertySource
	static void loadDataSourceProperties(DynamicPropertyRegistry registry) {
		log.info("Datasource properties is being populated");
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
		registry.add("spring.sql.init.mode", () -> "never");
	}
	
	
}
