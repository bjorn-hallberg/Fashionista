package com.example.Fashionista;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class FashionistaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionistaApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
		return Server.createTcpServer(
				"-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}
}
