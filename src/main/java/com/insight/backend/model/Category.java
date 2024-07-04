package com.insight.backend.model;

import java.util.Set;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a category entity in the system.
 * <p>
 * This class defines the structure of a category, including its ID, name, and associated questions.
 * It uses JPA annotations to map to a database entity and Jackson annotations to handle JSON serialization.
 * </p>
 * 
 * @author Janis Kupfer, Robin Kehl, Robert Eikmanns
 * @version 1.0
 * @since 2024
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Question> questions;

    /**
     * Constructs a new {@code Category} with the specified name and questions.
     * <p>
     * The constructor initializes a {@code Category} instance with the provided name and associated
     * questions.
     * </p>
     *
     * @param name the name of the category
     * @param questions the set of questions associated with the category
     */
    public Category(String name, Set<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    /**
     * No-argument constructor required by JPA.
     * <p>
     * This constructor is used by JPA to create instances of {@code Category} without setting initial values.
     * </p>
     */
    public Category() {
        // No-arg constructor for JPA
    }

    // Getters and setters

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
