package io.github.alberes.guestdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GuestdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestdbApplication.class, args);
	}

}
