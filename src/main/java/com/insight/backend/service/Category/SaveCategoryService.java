package com.insight.backend.service.Category;

import com.insight.backend.repository.CategoryRepository;
import com.insight.backend.model.Category;
import org.springframework.stereotype.Service;

/**
 * Service class for handling operations related to {@link Category} entities.
 * <p>
 * This service is responsible for performing CRUD operations on {@link Category} objects. It utilizes the
 * {@link CategoryRepository} to interact with the database.
 * </p>
 *
 * @author Robin Kehl
 * @version 1.0
 * @since 2024
 */
@Service
public class SaveCategoryService {

    /** Repository for performing database operations on {@link Category} entities. */
    private final CategoryRepository categoryRepository;

    /**
     * Constructs a new {@link SaveCategoryService} with the specified {@link CategoryRepository}.
     *
     * @param categoryRepository the {@link CategoryRepository} to be used by this service
     */
    public SaveCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Saves the provided {@link Category} entity to the database.
     * <p>
     * This method delegates the save operation to the {@link CategoryRepository}.
     * </p>
     *
     * @param category the {@link Category} entity to be saved
     * @return the saved {@link Category} entity
     */
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
