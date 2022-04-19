package com.example.lp.tripcalculator;

import com.example.lp.tripcalculator.service.TripCostingService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.TimeZone;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private final TripCostingService tripCostingService;

	Application(TripCostingService tripCostingService) {
		this.tripCostingService = tripCostingService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args) throws IOException {
		this.tripCostingService.processFile();
		System.exit(0);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
