package org.mddarr.dakobedsnotel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Document {
    @Id
    String id;
    String bucket;
    String username;
    String path;
    public Document(){}
    public Document(String id, String bucket, String user, String path) {
        this.id = id;
        this.bucket = bucket;
        this.username = user;
        this.path = path;
    }

    public String getBucket() {
        return bucket;
    }
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
    public String getUser() {
        return username;
    }
    public void setUser(String user) {
        this.username = user;
    }
    public String getUsername() { return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPath() {return path; }
    public void setPath(String path) { this.path = path;}
}
