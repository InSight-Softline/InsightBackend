
package com.insight.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the backend Spring Boot application.
 * <p>
 * This class contains the {@code main} method which is used to launch the Spring Boot application.
 * It is annotated with {@link SpringBootApplication}, encompassing the following:
 * Running this application initializes the Spring Boot application context and starts the embedded server,
 * which then listens for incoming HTTP requests.
 * </p>
 * 
 * @author Robert Eikmanns
 * @version 1.0
 * @since 2024
 */
@SpringBootApplication
public class BackendApplication {

    /**
     * The entry point of the Spring Boot application.
     * <p>
     * This method is called by the Java Virtual Machine (JVM) to start the application. It uses
     * {@link SpringApplication#run(Class, String...)} to bootstrap the application, initialize the
     * Spring application context, and start the embedded server.
     * </p>
     * 
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
