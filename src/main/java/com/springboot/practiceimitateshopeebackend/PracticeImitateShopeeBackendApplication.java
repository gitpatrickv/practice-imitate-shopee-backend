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
	//todo kafka to handle all events
	//todo validation
	//todo UNIT TEST

	//todo show all my purchase history
	//todo show all completed orders


	//todo buy again

	//todo flyway practice

	//todo generate random order id
	//todo payment method
	//todo order status = COMPLETED CANCELLED PENDING PROCESSING, OUT FOR DELIVERY make a method for changing order status?
	//todo transaction history different from order? when order is cancelled / completed it will go to transaction history db then be deleted in the order db?
	//todo kafka to handle all events

}
