package com.insight.backend.model;

import java.util.Set;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a question entity in the system.
 * <p>
 * This class defines the structure of a question, including its ID, name, associated ratings, and the category
 * to which it belongs. It uses JPA annotations for database entity mapping and Jackson annotations for JSON serialization.
 * </p>
 * 
 * @author Robin Kehl, Robert Eikmanns
 * @version 1.0
 * @since 2024
 */
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private Set<Rating> rating;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    /**
     * Constructs a new {@code Question} with the specified name, ratings, and category.
     * <p>
     * The constructor initializes a {@code Question} instance with the provided name, ratings, and associated
     * category.
     * </p>
     *
     * @param name the name of the question
     * @param rating the set of ratings associated with the question
     * @param category the category to which the question belongs
     */
    public Question(String name, Set<Rating> rating, Category category) {
        this.name = name;
        this.rating = rating;
        this.category = category;
    }

    /**
     * No-argument constructor required by JPA.
     * <p>
     * This constructor is used by JPA to create instances of {@code Question} without setting initial values.
     * </p>
     */
    public Question() {
        // No-arg constructor for JPA
    }

    // Getters and setters

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Rating> getRating() {
        return rating;
    }

    public void setRating(Set<Rating> rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
