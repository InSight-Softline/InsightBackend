package com.insight.backend.service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import com.insight.backend.dto.AuditResponseDTO;
import com.insight.backend.dto.NewAuditDTO;
import com.insight.backend.exception.NonExistentAuditCategoryException;
import com.insight.backend.model.Audit;
import com.insight.backend.model.Category;
import com.insight.backend.model.Question;
import com.insight.backend.service.audit.CreateAuditService;
import com.insight.backend.service.audit.SaveAuditService;
import com.insight.backend.service.category.FindCategoryService;
import com.insight.backend.service.rating.SaveRatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * Test class for CreateAuditService.
 */
@ExtendWith(MockitoExtension.class)
class CreateAuditServiceTest {

    @Mock
    private FindCategoryService findCategoryService;

    @Mock
    private SaveAuditService saveAuditService;

    @Mock
    private SaveRatingService saveRatingService;

    @InjectMocks
    private CreateAuditService createAuditService;

    @Test
    public void testCreateAudit_success() {
        NewAuditDTO newAuditDTO = new NewAuditDTO();
        newAuditDTO.setName("Audit Name");
        newAuditDTO.setCategories(Arrays.asList(1L, 2L));

        Category category1 = new Category();
        category1.setId(1L);
        Question question1 = new Question();
        question1.setId(1L);
        category1.setQuestions(Set.of(question1));

        Category category2 = new Category();
        category2.setId(2L);
        Question question2 = new Question();
        question2.setId(2L);
        category2.setQuestions(Set.of(question2));

        when(findCategoryService.findCategoryById(1L)).thenReturn(Optional.of(category1));
        when(findCategoryService.findCategoryById(2L)).thenReturn(Optional.of(category2));
        when(saveAuditService.saveAudit(any(Audit.class))).thenAnswer(invocation -> {
            Audit audit = invocation.getArgument(0);
            audit.setId(1L);
            audit.setCreatedAt(java.time.LocalDateTime.of(2024, 11, 23, 21, 59)); // توقيت ثابت
            return audit;
        });

        AuditResponseDTO response = createAuditService.createAudit(newAuditDTO);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Audit Name", response.getName());
        assertEquals(java.time.LocalDateTime.of(2024, 11, 23, 21, 59), response.getCreatedAt());

        verify(saveRatingService, times(1)).saveAllRatings(anyList());
        verify(saveAuditService, times(1)).saveAudit(any(Audit.class));
    }

    @Test
    public void testCreateAudit_invalidCategory() {
        NewAuditDTO newAuditDTO = new NewAuditDTO();
        newAuditDTO.setName("Audit Name");
        newAuditDTO.setCategories(Arrays.asList(1L, 2L));

        when(findCategoryService.findCategoryById(1L)).thenReturn(Optional.empty());

        NonExistentAuditCategoryException exception = assertThrows(NonExistentAuditCategoryException.class, () -> createAuditService.createAudit(newAuditDTO));
        assertEquals("Category with id 1 not found", exception.getMessage());

        verify(saveRatingService, never()).saveAllRatings(anyList());
        verify(saveAuditService, never()).saveAudit(any(Audit.class));

        verify(findCategoryService, times(1)).findCategoryById(1L);
        verify(findCategoryService, never()).findCategoryById(2L);
    }
}
