package org.example.sharesportsvendorbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShareSportsVendorBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareSportsVendorBackendApplication.class, args);
	}

}
