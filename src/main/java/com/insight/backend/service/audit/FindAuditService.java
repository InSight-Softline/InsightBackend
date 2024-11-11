package com.insight.backend.service.audit;

import java.util.List;
import java.util.Optional;

import com.insight.backend.model.Audit;
import com.insight.backend.repository.AuditRepository;
import com.insight.backend.specifications.AuditSpecifications;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Service class for finding audits.
 */
@Service
public class FindAuditService {

    // AuditRepository used for accessing audit data
    private AuditRepository auditRepository;

    /**
     * Constructs a new FindAuditService with the specified AuditRepository.
     *
     * @param auditRepository the repository to use for audit operations
     */
    public FindAuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    /**
     * Finds an audit by its ID.
     * 
     * @param id the ID of the audit
     * @return an optional object with the audit with the specified ID, or an optional with no audit if not found
     */
    public Optional<Audit> findAuditById(Long id) {
        return auditRepository.findById(id);
    }

    /**
     * Finds all audits with the specified customer name and filters non-deleted ones.
     *
     * @param customerName the name of the customer to search for
     * @param sortDirection the direction to sort the results
     * @param sortBy the field to sort by
     * @return a list of all audits matching the filters
     */
    public List<Audit> findAllAudits(String customerName, String sortDirection, String sortBy) {
        Sort sort = Sort.by(sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
        Specification<Audit> spec = AuditSpecifications.isNotDeleted();

        if (customerName != null && !customerName.isEmpty()) {
            spec = spec.and(AuditSpecifications.customerContains(customerName));
        }

        return auditRepository.findAll(spec, sort);
    }
}
