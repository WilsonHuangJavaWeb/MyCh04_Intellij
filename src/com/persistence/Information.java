package com.persistence;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by WilsonHuang on 2017/3/18.
 */
@Entity
public class Information {
    private String id;
    private String title;
    private String content;
    private Timestamp publishingTime;

    @Id
    @Column(name = "ID", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Title", nullable = true, length = 64)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Content", nullable = true, length = 1024)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "PublishingTime", nullable = true)
    public Timestamp getPublishingTime() {
        return publishingTime;
    }

    public void setPublishingTime(Timestamp publishingTime) {
        this.publishingTime = publishingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Information that = (Information) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;
        if (content != null ? !content.equals(that.content) : that.content != null)
            return false;
        if (publishingTime != null ? !publishingTime.equals(that.publishingTime) : that.publishingTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (publishingTime != null ? publishingTime.hashCode() : 0);
        return result;
    }
}
