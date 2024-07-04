package com.insight.backend.repository;

import com.insight.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Category} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations and query methods for {@link Category} entities.
 * By extending {@code JpaRepository}, it inherits a wide range of methods for interacting with the database, such as
 * saving, deleting, and finding entities.
 * </p>
 * 
 * @author Janis Kupfer
 * @version 1.0
 * @since 2024
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}