package org.mddarr.dakobedreports.reports.dto;

public class ReportDTO {
    private String name;
    private String region;
    private String report;
    private Double distance;
    private Long elevationGain;
    private String imageurl;
    public ReportDTO(){}

    public ReportDTO(String name, String region, String report, Double distance, Long elevationGain, String imageurl) {
        this.name = name;
        this.region = region;
        this.report = report;
        this.distance = distance;
        this.elevationGain = elevationGain;
        this.imageurl = imageurl;
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
