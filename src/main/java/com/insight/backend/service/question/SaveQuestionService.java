package com.insight.backend.service.question;

import com.insight.backend.repository.QuestionRepository;
import com.insight.backend.model.Question;
import org.springframework.stereotype.Service;

/**
 * Service class for handling operations related to {@link Question} entities.
 * <p>
 * This service provides methods for performing CRUD operations on {@link Question} objects.
 * It interacts with the {@link QuestionRepository} to persist and retrieve {@link Question} entities.
 * </p>
 *
 * @author Robin Kehl
 * @version 1.0
 * @since 2024
 */
@Service
public class SaveQuestionService {

    /** Repository for performing database operations on {@link Question} entities. */
    private final QuestionRepository questionRepository;

    /**
     * Constructs a new {@link SaveQuestionService} with the specified {@link QuestionRepository}.
     *
     * @param questionRepository the {@link QuestionRepository} to be used by this service
     */
    public SaveQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Saves the provided {@link Question} entity to the database.
     * <p>
     * This method delegates the save operation to the {@link QuestionRepository}.
     * </p>
     *
     * @param question the {@link Question} entity to be saved
     * @return the saved {@link Question} entity
     */
    public Question saveQuestion(Question question) {
        if (question == null) return null;
        return questionRepository.saveAndFlush(question);
    }
}
