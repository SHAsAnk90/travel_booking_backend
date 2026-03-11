package com.travelbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TravelBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelBookingSystemApplication.class, args);
	}

}
