package com.taboola.tn.api20tester.model;

import java.util.List;

public class TNArticleBean {

    private String session;
    private String externalVariant;
    private List<Placement> placements;

    public void setSession(String session) {
        this.session = session;
    }

    public void setExternalVariant(String externalVariant) {
        this.externalVariant = externalVariant;
    }

    public void setPlacements(List<Placement> placements) {
        this.placements = placements;
    }


    public String getSession() {
        return session;
    }

    public String getExternalVariant() {
        return externalVariant;
    }

    public List<Placement> getPlacements() {
        return placements;
    }


    @Override
    public String toString() {
        return "TNArticleBean{" +
                "session='" + session + '\'' +
                ", externalVariant='" + externalVariant + '\'' +
                ", placements=" + placements +
                '}';
    }
}
