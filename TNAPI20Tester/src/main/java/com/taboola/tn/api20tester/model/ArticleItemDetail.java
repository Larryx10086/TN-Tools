package com.taboola.tn.api20tester.model;

import java.util.List;

public class ArticleItemDetail {

    private String description;
    private String type;
    private String name;
    private String created;
    private String branding;
    private String duration;
    private String views;
    private List<Thumnail> thumbnails;
    private List<TNCategory> categories;
    private String is_dc_only_provider;
    private String is_dc;
    private String id;
    private String origin;
    private String url;


    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setBranding(String branding) {
        this.branding = branding;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public void setThumbnails(List<Thumnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public void setCategories(List<TNCategory> categories) {
        this.categories = categories;
    }

    public void setIs_dc_only_provider(String is_dc_only_provider) {
        this.is_dc_only_provider = is_dc_only_provider;
    }

    public void setIs_dc(String is_dc) {
        this.is_dc = is_dc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getCreated() {
        return created;
    }

    public String getBranding() {
        return branding;
    }

    public String getDuration() {
        return duration;
    }

    public String getViews() {
        return views;
    }

    public List<Thumnail> getThumbnails() {
        return thumbnails;
    }

    public List<TNCategory> getCategories() {
        return categories;
    }

    public String getIs_dc_only_provider() {
        return is_dc_only_provider;
    }

    public String getIs_dc() {
        return is_dc;
    }

    public String getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return "ArticleIntemDetail{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", created='" + created + '\'' +
                ", branding='" + branding + '\'' +
                ", duration='" + duration + '\'' +
                ", views='" + views + '\'' +
                ", thumbnails=" + thumbnails +
                ", categories=" + categories +
                ", is_dc_only_provider='" + is_dc_only_provider + '\'' +
                ", is_dc='" + is_dc + '\'' +
                ", id='" + id + '\'' +
                ", origin='" + origin + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
