package com.insight.backend.model.newAudit;

import java.util.List;

public class AuditRequest {
    private String name;
    private List<Integer> categories;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }
}
