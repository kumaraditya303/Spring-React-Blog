package io.github.kumaraditya303.blog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Post implements Serializable {
    private static final long serialVersionUID = -8935164224252710860L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String overview;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    private String content;
    private Boolean featured;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private User author;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private DBFile thumbnail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @JsonGetter(value = "thumbnail")
    public String getThumbnail() {
        if (thumbnail != null)
            return "/static/" + thumbnail.getId();
        return null;
    }

    public void setThumbnail(DBFile thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((featured == null) ? 0 : featured.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((overview == null) ? 0 : overview.hashCode());
        result = prime * result + ((thumbnail == null) ? 0 : thumbnail.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
        Post other = (Post) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Post [author=" + author + ", content=" + content + ", featured=" + featured + ", id=" + id
                + ", overview=" + overview + ", thumbnail=" + thumbnail + ", timestamp=" + timestamp + ", title="
                + title + "]";
    }

    public Post() {
    }

}
