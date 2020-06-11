package org.mddarr.dakobedreports.comments.entity;


import org.mddarr.dakobedreports.reports.entity.Report;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    String id;
    String username;
    String email;
    String content;
    Date postDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    public Comment(){}

    public Comment(String username, String email, String content, Report report, Date postDate) {
        this.username = username;
        this.email = email;
        this.content = content;
        this.report = report;
    }


    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
