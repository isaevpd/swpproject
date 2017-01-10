package com.mysuperapplication;

import com.mysuperapplication.domain.Driver;
import com.mysuperapplication.domain.DriverRepository;
import com.mysuperapplication.domain.RaceRepository;
import com.mysuperapplication.domain.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MysuperprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysuperprojectApplication.class, args);
	}
	@Bean
	public CommandLineRunner driverDemo(
            DriverRepository driverRepository, TeamRepository teamRepository, RaceRepository raceRepository) {
		return (args) -> {
		    driverRepository.save(new Driver("ivan", "pacan", "ru", (long) 1));
		};
	}
}