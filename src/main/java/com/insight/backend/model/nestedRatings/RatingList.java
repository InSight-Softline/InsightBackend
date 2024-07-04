package com.insight.backend.model.nestedRatings;

import java.util.List;

import com.insight.backend.model.Rating;

/**
 * Represents a list of ratings.
 * <p>
 * This class encapsulates a list of {@link Rating} objects, providing methods to
 * get and set the list of ratings. It is used to group multiple ratings together.
 * </p>
 * 
 * @author Matthias Lauer, Thomas Mugler
 * @version 1.0
 * @since 2024
 */
public class RatingList {

    private List<Rating> ratings;

    /**
     * Constructs a new {@code RatingList} with the specified list of ratings.
     * <p>
     * The constructor initializes the {@code RatingList} instance with the provided
     * list of ratings.
     * </p>
     *
     * @param ratings the list of ratings to initialize the {@code RatingList} with
     */
    public RatingList(List<Rating> ratings) {
        setRatings(ratings);
    }

    /**
     * Returns the list of ratings.
     * <p>
     * This method retrieves the internal list of ratings that is encapsulated by
     * this {@code RatingList} instance.
     * </p>
     * 
     * @return the list of ratings
     */
    public List<Rating> getRatings() {
        return this.ratings;
    }

    /**
     * Sets the list of ratings.
     * <p>
     * This method updates the internal list of ratings with the provided list.
     * </p>
     * 
     * @param ratings the list of ratings to set
     */
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
