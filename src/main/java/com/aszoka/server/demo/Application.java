package com.aszoka.server.demo;

import com.aszoka.server.demo.model.Server;
import com.aszoka.server.demo.model.Status;
import com.aszoka.server.demo.repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

/*	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(
					"192.168.0.111",
					"Windows Home",
					"8 GB",
					"Laptop",
					"http://localhost:8080/server/image/server1.png",
					Status.SERVER_UP
			));
		};
	}*/
}
