package com.insight.backend.model.newAudit;

import java.util.List;

public class AuditRequest {
    private String name;
    private List<Long> categories;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }
}
