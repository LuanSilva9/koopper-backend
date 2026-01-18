package com.ekos.koopper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KoopperApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoopperApplication.class, args);
	}

}
