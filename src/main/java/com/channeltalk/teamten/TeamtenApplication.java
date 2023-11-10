package com.channeltalk.teamten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TeamtenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamtenApplication.class, args);
	}

}
