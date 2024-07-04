package com.insight.backend.controller;

import com.insight.backend.service.DatabaseSeederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling database seeding requests.
 * <p>
 * This class provides a RESTful endpoint to trigger the seeding of the database. It uses the {@code @RestController}
 * annotation to handle HTTP requests and interact with the {@link DatabaseSeederService} to seed the database.
 * </p>
 * <p>
 * The {@code @GetMapping} annotation specifies the endpoint for seeding the database.
 * </p>
 * 
 * @author Robin Kehl
 * @version 1.0
 * @since 2024
 */
@RestController
public class DatabaseSeederController {

    private final DatabaseSeederService databaseSeederService;

    /**
     * Constructs a {@code DatabaseSeederController} with the specified {@link DatabaseSeederService}.
     * 
     * @param databaseSeederService the {@link DatabaseSeederService} used to seed the database.
     */
    public DatabaseSeederController(DatabaseSeederService databaseSeederService) {
        this.databaseSeederService = databaseSeederService;
    }

    /**
     * Seeds the database with initial data.
     * <p>
     * This method handles {@code GET} requests to the endpoint {@code /api/v1/database/seed}. It invokes the
     * {@link DatabaseSeederService} to seed the database from files and returns a response indicating the success or
     * failure of the operation.
     * </p>
     * 
     * @return a {@link ResponseEntity} containing a message indicating the result of the database seeding operation.
     */
    @GetMapping("/api/v1/database/seed")
    public ResponseEntity<String> seedDatabase() {
        boolean result = databaseSeederService.seedDatabaseFromFiles();
        if(result) {
            return ResponseEntity.ok("Database seeded successfully");
        } else {
            return ResponseEntity.ok("Database seeding failed");
        }
    }
}
