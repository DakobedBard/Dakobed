package org.mddarr.dakobedreports.reports.entity;


import org.mddarr.dakobedreports.comments.entity.Comment;
import org.mddarr.dakobedreports.reports.dto.ReportDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class Report implements Serializable {

    @Id
    private String id;
    private String name;

    private String region;
    private String report;
    private Double distance;
    private Long elevationGain;
    private Date postDate;
    private String imageURL;


    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Comment> comments;

    public Report(){

    }

    public Report(ReportDTO reportDTO){
        this.id= UUID.randomUUID().toString();
        this.distance = reportDTO.getDistance();
        this.elevationGain = reportDTO.getElevationGain();
        this.report = reportDTO.getReport();
        this.name = reportDTO.getName();
        this.region = reportDTO.getRegion();
        this.imageURL = reportDTO.getImageurl();
    }

    public Report(String id, String name, String region, String report, Double distance, Long elevationGain, Date pDate, String imageURL) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.report = report;
        this.distance = distance;
        this.elevationGain = elevationGain;
        this.postDate = pDate;
        this.imageURL = imageURL;

    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getDate() {
        return postDate;
    }

    public void setDate(Date date) {
        this.postDate = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Long getElevationGain() {
        return elevationGain;
    }

    public void setElevationGain(Long elevationGain) {
        this.elevationGain = elevationGain;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
