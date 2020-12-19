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

    @Override
    public String toString() {
        return "PostDto [content=" + content + ", featured=" + featured + ", overview=" + overview + ", thumbnail="
                + thumbnail + ", title=" + title + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((featured == null) ? 0 : featured.hashCode());
        result = prime * result + ((overview == null) ? 0 : overview.hashCode());
        result = prime * result + ((thumbnail == null) ? 0 : thumbnail.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PostDto other = (PostDto) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (featured == null) {
            if (other.featured != null)
                return false;
        } else if (!featured.equals(other.featured))
            return false;
        if (overview == null) {
            if (other.overview != null)
                return false;
        } else if (!overview.equals(other.overview))
            return false;
        if (thumbnail == null) {
            if (other.thumbnail != null)
                return false;
        } else if (!thumbnail.equals(other.thumbnail))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
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
