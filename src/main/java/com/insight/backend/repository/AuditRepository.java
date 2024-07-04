package com.insight.backend.repository;

import com.insight.backend.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Audit} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations and query methods for {@link Audit} entities.
 * By extending {@code JpaRepository}, it inherits a wide range of methods for interacting with the database, such as
 * saving, deleting, and finding entities.
 * </p>
 * 
 * @author Benedikt Meyer
 * @version 1.0
 * @since 2024
 */
public interface AuditRepository extends JpaRepository<Audit, Long> {
}