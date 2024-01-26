package com.example.gamer.directory.app;

import org.springframework.boot.SpringApplication;

import com.example.gamer.directory.GamerDirectoryServiceApplication;
import com.example.gamer.directory.app.config.TestContainerConfig;

public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.from(GamerDirectoryServiceApplication::main)
			.with(TestContainerConfig.class)
			.run(args);
	}
}
