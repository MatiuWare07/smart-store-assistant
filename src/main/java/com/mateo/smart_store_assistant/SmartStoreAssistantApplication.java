package com.mateo.smart_store_assistant;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Smart Store Assistant application.
 * Loads environment variables from .env file before Spring context initialization.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
@SpringBootApplication
public class SmartStoreAssistantApplication {

	/**
	 * Application entry point.
	 * Loads .env variables into system properties so Spring can resolve them.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);
		SpringApplication.run(SmartStoreAssistantApplication.class, args);
	}
}