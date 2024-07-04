package com.insight.backend.repository;

import com.insight.backend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Question} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations and query methods for {@link Question} entities.
 * By extending {@code JpaRepository}, it inherits a variety of methods for interacting with the database, such as
 * saving, deleting, and finding entities.
 * </p>
 * 
 * @author Anass Boustani
 * @version 1.0
 * @since 2024
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
    // No additional methods are defined here.
}
