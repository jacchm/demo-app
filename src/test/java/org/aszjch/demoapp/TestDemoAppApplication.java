package org.aszjch.demoapp;

import org.springframework.boot.SpringApplication;

public class TestDemoAppApplication {

	public static void main(String[] args) {
		SpringApplication.from(DemoAppApplication::main)
				.with(TestcontainersConfiguration.class).run(args);
	}

}
