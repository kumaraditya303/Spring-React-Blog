package io.github.kumaraditya303.blog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostDto {
    @NotEmpty(message = "*Title cannot be empty.*")
    private String title;
    @NotEmpty(message = "*Overview cannot be empty.*")
    private String overview;
    @NotEmpty(message = "*Content cannot be empty.*")
    private String content;
    @NotNull(message = "*Featured cannot be null.*")
    private Boolean featured;
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public PostDto() {

    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
