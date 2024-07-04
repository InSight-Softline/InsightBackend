package com.insight.backend.model;

import java.util.Set;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents an audit entity in the system.
 * <p>
 * This class defines the structure of an audit, including its ID, name, and associated ratings.
 * It uses JPA annotations to map to a database entity and Jackson annotations to handle JSON serialization.
 * </p>
 * 
 * @author Robin Kehl, Robert Eikmanns
 * @version 1.0
 * @since 2024
 */
@Entity
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "audit")
    private Set<Rating> ratings;

    /**
     * Constructs a new {@code Audit} with the specified name and ratings.
     * <p>
     * The constructor initializes an {@code Audit} instance with the provided name and associated
     * ratings.
     * </p>
     *
     * @param name the name of the audit
     * @param ratings the set of ratings associated with the audit
     */
    public Audit(String name, Set<Rating> ratings) {
        this.name = name;
        this.ratings = ratings;
    }

    /**
     * No-argument constructor required by JPA.
     * <p>
     * This constructor is used by JPA to create instances of {@code Audit} without setting initial values.
     * </p>
     */
    public Audit() {
        // No-arg constructor for JPA
    }

    // Getters and setters

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
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
