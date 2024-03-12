package com.springboot.practiceimitateshopeebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PracticeImitateShopeeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeImitateShopeeBackendApplication.class, args);
	}


	//todo place order
	//todo select payment methods
	//todo mypurchase history
	//todo kafka to handle all events
	//todo validation
	//todo UNIT TEST


}
