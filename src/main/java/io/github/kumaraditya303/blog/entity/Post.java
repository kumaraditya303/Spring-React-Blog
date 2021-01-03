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
    public String toString() {
        return "Post [author=" + author + ", content=" + content + ", featured=" + featured + ", id=" + id
                + ", overview=" + overview + ", thumbnail=" + thumbnail + ", timestamp=" + timestamp + ", title="
                + title + "]";
    }

    public Post() {
    }

}
