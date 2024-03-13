package foodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class FoodieAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodieAppApplication.class, args);
		System.out.println("Welcome to the Foodie_App ");
	}

}
