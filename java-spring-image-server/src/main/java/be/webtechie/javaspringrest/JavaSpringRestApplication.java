package be.webtechie.javaspringrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import be.webtechie.gpio.LedController;

@SpringBootApplication
public class JavaSpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringRestApplication.class, args);
	}

}
