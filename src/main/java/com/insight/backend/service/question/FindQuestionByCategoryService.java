package com.insight.backend.service.question;

import com.insight.backend.model.Question;
import com.insight.backend.model.Category;
import com.insight.backend.repository.QuestionRepository;
import com.insight.backend.specifications.QuestionSpecifications;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FindQuestionByCategoryService {
    
    private final QuestionRepository questionRepository;

    public FindQuestionByCategoryService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Optional <Question> findQuestionByID(Long id) {
        return questionRepository.findById(id);
    }

    public List<Question> findAllQuestions(Category category, String sortDirection, String sortBy) {
        Sort sort = Sort.by(sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
        return questionRepository.findAll(QuestionSpecifications.inCategory(category), sort);
    }
}
