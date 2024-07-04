package com.insight.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a rating given in an audit.
 * <p>
 * This class encapsulates the rating details such as the score, associated comments, and the related audit and question.
 * It uses JPA annotations for database entity mapping and validation annotations to enforce constraints on rating values.
 * </p>
 *
 * @author Robin Kehl, Robert Eikmanns
 * @version 1.0
 * @since 2024
 */
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isNa = false;

    private String comment = "";

    @Min(0)
    @Max(5)
    private Integer points;

    @ManyToOne
    @JoinColumn(name="audit_id", nullable=false)
    private Audit audit;

    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

    /**
     * Constructs a new {@code Rating} with the specified values.
     * <p>
     * This constructor initializes a {@code Rating} instance with the provided values for `isNa`, `comment`, `points`,
     * associated `audit`, and `question`.
     * </p>
     *
     * @param isNa the flag indicating if the rating is not applicable
     * @param comment the comment associated with the rating
     * @param points the points assigned in the rating (must be between 0 and 5)
     * @param audit the audit to which this rating belongs
     * @param question the question to which this rating is related
     */
    public Rating(Boolean isNa, String comment, Integer points, Audit audit, Question question) {
        this.isNa = isNa;
        this.comment = comment;
        this.points = points;
        this.audit = audit;
        this.question = question;
    }

    /**
     * No-argument constructor required by JPA.
     * <p>
     * This constructor is used by JPA to create instances of {@code Rating} without setting initial values.
     * </p>
     */
    public Rating() {
        // No-arg constructor for JPA
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getNa() {
        return isNa;
    }

    public void setNa(Boolean na) {
        isNa = na;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPoints() {
        return points;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
