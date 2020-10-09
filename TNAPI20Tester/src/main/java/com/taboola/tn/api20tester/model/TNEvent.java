package com.taboola.tn.api20tester.model;

public class TNEvent {

    private String available;
    private String visible;

    public void setAvailable(String available) {
        this.available = available;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }


    public String getAvailable() {
        return available;
    }

    public String getVisible() {
        return visible;
    }

    @Override
    public String toString() {
        return "TNEvent{" +
                "available='" + available + '\'' +
                ", visible='" + visible + '\'' +
                '}';
    }
}
