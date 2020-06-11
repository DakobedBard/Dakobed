package org.mddarr.dakobedreports.comments.models;

import javax.persistence.Id;
import java.util.Date;

public class CommentPostRequest {

    @Id
    String id;
    String entityID;
    String name;
    String email;
    String comment;
    Date date;

    public CommentPostRequest(String id, String entityID, String name, String email, String comment) {
        this.id = id;
        this.entityID = entityID;
        this.name = name;
        this.email = email;
        this.comment = comment;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CommentPostRequest(){}

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
