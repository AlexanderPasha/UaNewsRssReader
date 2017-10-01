package com.mannydev.rssalluanews.model;

/**
 * Created by manny on 01.10.17.
 */

public class Feed {

    private String name;
    private String description;
    private String urlLogo;
    private String urlFeed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getUrlFeed() {
        return urlFeed;
    }

    public void setUrlFeed(String urlFeed) {
        this.urlFeed = urlFeed;
    }
}
