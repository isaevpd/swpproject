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

//			log.info("save a couple of drivers");
//			drepository.save(new Department("IT"));
//			drepository.save(new Department("Business"));
//			drepository.save(new Department("Law"));
//
//			srepository.save(new Student("John", "Johnson", "john@john.com", drepository.findByName("IT").get(0)));
//			srepository.save(new Student("Katy", "Kateson", "kate@kate.com", drepository.findByName("Business").get(0)));
//
//			// Create users: admin/admin user/user
//			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
//			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
//			urepository.save(user1);
//			urepository.save(user2);
//
//			log.info("fetch all students");
//			for (Student student : srepository.findAll()) {
//				log.info(student.toString());
//			}

		};
	}
}