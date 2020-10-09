package com.taboola.tn.api20tester.model;

import java.util.List;

public class Placement {

    private String name;
    private String id;
    private String ui;
    private List<ArticleItemDetail> articles;
    private TNEvent events;

    public void setEvents(TNEvent events) {
        this.events = events;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }

    public void setArticles(List<ArticleItemDetail> articles) {
        this.articles = articles;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getUi() {
        return ui;
    }

    public List<ArticleItemDetail> getArticles() {
        return articles;
    }

    public TNEvent getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "Placement{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", ui='" + ui + '\'' +
                ", articles=" + articles +
                ", events=" + events +
                '}';
    }
}
