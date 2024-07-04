package com.insight.backend.model.nestedRatings;

import java.util.HashMap;

/**
 * Represents a class for managing ratings assigned to audits.
 * <p>
 * This class holds a map where the key is an audit ID and the value is a list
 * of ratings assigned to that audit. 
 * </p>
 * 
 * @author Matthias Lauer, Thomas Mugler
 * @version 1.0
 * @since 2024
 */
public class RatingAssigned {

    private HashMap<Integer, RatingList> ratingsAssigned = new HashMap<>();

    /**
     * Sets the map of ratings assigned to audits.
     * <p>
     * This method allows for updating the internal map of ratings. The map's
     * keys represent audit IDs, and the values represent the corresponding
     * lists of ratings.
     * </p>
     * 
     * @param ratingsAssigned the map to set, where the key is the audit ID and the value is the list of ratings
     */
    public void setRatingsAssigned(HashMap<Integer, RatingList> ratingsAssigned) {
        this.ratingsAssigned = ratingsAssigned;
    }

    /**
     * Returns the map of ratings assigned to audits.
     * <p>
     * This method retrieves the internal map of ratings. The map's keys represent
     * audit IDs, and the values represent the corresponding lists of ratings.
     * </p>
     * 
     * @return the map of ratings assigned, where the key is the audit ID and the value is the list of ratings
     */
    public HashMap<Integer, RatingList> getRatingsAssigned() {
        return this.ratingsAssigned;
    }
}