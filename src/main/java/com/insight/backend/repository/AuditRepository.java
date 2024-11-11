package com.insight.backend.repository;

import com.insight.backend.model.Audit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditRepository extends JpaRepository<Audit, Long>, JpaSpecificationExecutor<Audit> {
    List<Audit> findAllByDeletedAtIsNull();
}
