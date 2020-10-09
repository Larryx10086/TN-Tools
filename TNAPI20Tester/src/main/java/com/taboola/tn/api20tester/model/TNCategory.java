package com.taboola.tn.api20tester.model;

public class TNCategory {

    private String category;

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "TNCategory{" +
                "category='" + category + '\'' +
                '}';
    }
}
