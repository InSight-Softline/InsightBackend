package com.insight.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.insight.backend.model.Category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

/**
 * Controller for handling requests related to categories.
 * <p>
 * This class provides a RESTful endpoint to retrieve a list of categories. It uses the {@code @RestController}
 * annotation to handle HTTP requests and return data directly to the client in JSON format.
 * </p>
 * <p>
 * The {@code @GetMapping} annotation specifies the endpoint for retrieving category information.
 * </p>
 * 
 * @author Janis Kupfer, Robert Eikmanns
 * @version 1.0
 * @since 2024
 */
@RestController
public class CategoryController {

    /**
     * Retrieves a list of all categories.
     * <p>
     * This method handles {@code GET} requests to the endpoint {@code /api/v1/categories}. It returns a list of
     * {@link Category} objects with placeholder data. In a production environment, this method should be updated
     * to fetch categories from a data source.
     * </p>
     * 
     * @return a {@link ResponseEntity} containing a list of {@link Category} objects and an HTTP status of {@link org.springframework.http.HttpStatus#OK}.
     */
    @GetMapping("/api/v1/categories")
    public ResponseEntity<List<Category>> getCategory() {
        List<Category> response = new ArrayList<>();
        response.add(createCategory(1L, "Server Administration"));
        response.add(createCategory(2L, "Firewall"));
        response.add(createCategory(3L, "Netzwerk"));
        response.add(createCategory(4L, "Antivirus"));
        response.add(createCategory(5L, "VPN"));
        response.add(createCategory(6L, "Monitoring"));
        response.add(createCategory(7L, "Email"));
        response.add(createCategory(8L, "Secure Browsing"));
        response.add(createCategory(9L, "Client"));
        response.add(createCategory(10L, "Patch-Management"));
        response.add(createCategory(11L, "Schwachstellen-Management"));
        response.add(createCategory(12L, "Verschlüsselung"));
        response.add(createCategory(13L, "Zertifikate und PKI"));
        response.add(createCategory(14L, "Mobile Device Management"));
        response.add(createCategory(15L, "Backup"));
        response.add(createCategory(16L, "Privilege Access Management (PAM)"));
        response.add(createCategory(17L, "Identity, Passwörter und Secure Logon"));
        response.add(createCategory(18L, "Nutzung von Clouddiensten"));
        response.add(createCategory(19L, "Konzepte und Richtlinien"));
        response.add(createCategory(20L, "IAM"));
        response.add(createCategory(21L, "Digitale Signatur"));

        return ResponseEntity.ok(response);
    }

    /**
     * Creates a {@link Category} with the specified ID and name.
     * <p>
     * This private helper method is used to create {@link Category} objects with placeholder data.
     * </p>
     * 
     * @param id the ID of the category.
     * @param name the name of the category.
     * @return a {@link Category} object with the specified ID and name.
     */
    private Category createCategory(Long id, String name) {
        Category category = new Category(name, Set.of());
        category.setId(id);
        return category;
    }
}