package com.insight.backend.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.insight.backend.model.Audit;
import com.insight.backend.repository.AuditRepository;
import com.insight.backend.service.audit.FindAuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAuditServiceTest {

    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private FindAuditService findAuditService;

    private Audit audit1;
    private Audit audit2;

    @BeforeEach
    void setUp() {
        audit1 = new Audit();
        audit1.setId(1L);
        audit1.setName("Audit1");
        audit1.setCustomer("Customer1");

        audit2 = new Audit();
        audit2.setId(2L);
        audit2.setName("Audit2");
        audit1.setCustomer("Customer2");
    }

    @Test
    void testFindAuditById_found() {
        when(auditRepository.findById(1L)).thenReturn(Optional.of(audit1));

        Optional<Audit> foundAudit = findAuditService.findAuditById(1L);

        assertEquals(audit1, foundAudit.get());
    }

    @Test
    void testFindAuditById_notFound() {
        when(auditRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Audit> foundAudit = findAuditService.findAuditById(1L);

        assertTrue(foundAudit.isEmpty());
    }

    @Test
    void testFindAllAudits() {
    audit1.setDeletedAt(null);
    audit2.setDeletedAt(LocalDateTime.now());

    List<Audit> audits = Arrays.asList(audit1);
    when(auditRepository.findAllByDeletedAtIsNull()).thenReturn(audits);

    List<Audit> foundAudits = findAuditService.findAllNonDeletedAudits();

    assertTrue(foundAudits.stream().allMatch(audit -> audit.getDeletedAt() == null));
    assertEquals(1, foundAudits.size());
}
}
