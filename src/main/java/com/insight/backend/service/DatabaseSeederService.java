package com.insight.backend.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.insight.backend.service.category.SaveCategoryService;
import com.insight.backend.service.question.SaveQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.insight.backend.model.Category;
import com.insight.backend.model.Question;

/**
 * Service class for seeding the database with initial data from CSV files.
 * <p>
 * This service is responsible for reading data from CSV files and populating the database
 * with {@link Category} and {@link Question} entities. It uses {@link SaveCategoryService}
 * and {@link SaveQuestionService} to perform database operations.
 * </p>
 *
 * @author Robin Kehl
 * @version 1.0
 * @since 2024
 */
@Service
public class DatabaseSeederService {

    /** Service for saving {@link Category} entities. */
    private final SaveCategoryService saveCategoryService;

    /** Service for saving {@link Question} entities. */
    private final SaveQuestionService saveQuestionService;

    /**
     * Constructs a new {@link DatabaseSeederService} with the specified services for
     * saving categories and questions.
     *
     * @param saveCategoryService the service used to save {@link Category} entities
     * @param saveQuestionService the service used to save {@link Question} entities
     */
    public DatabaseSeederService(SaveCategoryService saveCategoryService, SaveQuestionService saveQuestionService) {
        this.saveCategoryService = saveCategoryService;
        this.saveQuestionService = saveQuestionService;
    }

    /**
     * Seeds the database with data read from CSV files.
     * <p>
     * This method reads category and question data from predefined CSV files,
     * creates {@link Category} and {@link Question} entities, and saves them to
     * the database using the provided services.
     * </p>
     *
     * @return {@code true} if the database was successfully seeded, {@code false} otherwise
     */
    public boolean seedDatabaseFromFiles() {
        System.out.println("Seeding database from files...");

        List<Object[]> categories = readCSV("fixtures/dummy-categories.csv");

        Map<Integer, Category> categoryMap = new java.util.HashMap<>(Map.of());

        // Ausgabe der eingelesenen Daten
        for (Object[] entry : categories) {
            System.out.println(entry[0] + ", Value: " + entry[1]);
            Category category = new Category();
            category.setName((String) entry[1]);
            categoryMap.put((Integer) entry[0], saveCategoryService.saveCategory(category));
        }

        List<Object[]> questions = readCSV("fixtures/dummy-questions.csv");
        for (Object[] entry : questions) {
            System.out.println("ID: " + entry[0] + ", Value: " + entry[1]);
            Question question = new Question();
            question.setName((String) entry[1]);
            question.setCategory(categoryMap.get(entry[0]));
            saveQuestionService.saveQuestion(question);
        }

        return true;
    }

    /**
     * Reads data from a CSV file and parses it into a list of {@code Object[]} arrays.
     * <p>
     * Each array represents a row in the CSV file, with the first element being
     * either an {@link Integer} or a {@link Float}, and the second element being a {@link String}.
     * </p>
     *
     * @param csvFile the path to the CSV file to be read
     * @return a list of {@code Object[]} arrays containing the parsed data
     */
    private static List<Object[]> readCSV(String csvFile) {
        String line;
        String csvSplitBy = ",";

        List<Object[]> data = new ArrayList<>();

        try {
            Resource resource = new ClassPathResource(csvFile);
            FileInputStream file = new FileInputStream(resource.getFile());

            try (BufferedReader br = new BufferedReader(new InputStreamReader(file))) {
                while ((line = br.readLine()) != null) {
                    String[] columns = line.split(csvSplitBy);

                    if (isInteger(columns[0])) {
                        data.add(new Object[]{Integer.parseInt(columns[0]), columns[1]});
                    } else if (isFloat(columns[0]) && !columns[1].isEmpty()) {
                        data.add(new Object[]{Float.parseFloat(columns[0]), columns[1]});
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Checks if the provided string can be parsed as an integer.
     *
     * @param s the string to check
     * @return {@code true} if the string can be parsed as an integer, {@code false} otherwise
     */
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the provided string can be parsed as a float.
     *
     * @param s the string to check
     * @return {@code true} if the string can be parsed as a float, {@code false} otherwise
     */
    private static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
