package com.insight.backend.service.Question;

import com.insight.backend.repository.QuestionRepository;
import com.insight.backend.model.Question;
import org.springframework.stereotype.Service;

@Service
public class SaveQuestionService {
    private final QuestionRepository questionRepository;

    public SaveQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question saveQuestion(Question question) {
        if (question == null) return null;
        return questionRepository.save(question);
    }
}
