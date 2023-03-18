package com.henhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class HenHouseApplication {
	public static void main(String[] args) {
		SpringApplication.run(HenHouseApplication.class, args);
	}
}
