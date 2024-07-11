package com.insight.backend.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.insight.backend.model.Audit;
import com.insight.backend.repository.CategoryRepository;
import com.insight.backend.repository.QuestionRepository;
import com.insight.backend.service.DatabaseSeederService;
import com.insight.backend.service.category.SaveCategoryService;
import com.insight.backend.service.question.SaveQuestionService;

import org.hibernate.dialect.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatabaseSeederServiceTest {
    Method isInteger;
    Method isFloat;
    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    SaveCategoryService saveCategoryService = new SaveCategoryService(categoryRepository);

    @Mock
    SaveQuestionService saveQuestionService = new SaveQuestionService(questionRepository);

    @Mock
    DatabaseSeederService databaseSeederService = new DatabaseSeederService(saveCategoryService, saveQuestionService);

    @BeforeEach
    void setUp() throws Exception {
        isInteger = DatabaseSeederService.class.getDeclaredMethod("isInteger", String.class);
        isInteger.setAccessible(true);
        isFloat = DatabaseSeederService.class.getDeclaredMethod("isFloat", String.class);
        isFloat.setAccessible(true);
    }

    @Test
    public void isIntegerTypeTest() throws Exception {
        assertTrue((boolean) isInteger.invoke(null, "123"));
        assertTrue((boolean) isInteger.invoke(null, "-123"));
        assertFalse((boolean) isInteger.invoke(null, "12.3"));
        assertFalse((boolean) isInteger.invoke(null, "abc"));
        assertFalse((boolean) isInteger.invoke(null, ""));
        assertFalse((boolean) isInteger.invoke(null, (String) null));
    }

    @Test
    public void isFloatTypeTest() throws Exception {
        assertTrue((boolean) isFloat.invoke(null, "123"));
        assertTrue((boolean) isFloat.invoke(null, "-123"));
        assertTrue((boolean) isFloat.invoke(null, "12.3"));
        assertFalse((boolean) isFloat.invoke(null, "abc"));
        assertFalse((boolean) isFloat.invoke(null, ""));
        //assertFalse((boolean) isFloat.invoke(null, (String) null));
        assertThrows(InvocationTargetException.class, () -> {
            isFloat.invoke(null, (String) null);
        });
    }
}

