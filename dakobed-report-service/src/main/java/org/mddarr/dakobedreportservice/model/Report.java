package org.mddarr.dakobedreportservice.model;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Dakobed-Reports")
public class Report {

    private String userID;
    private String reportDate;
    private String imageURL;

    @DynamoDBHashKey(attributeName="userID")
    public String getUserID() {return userID; }
    public void setUserID(String userID) { this.userID = userID;}

    @DynamoDBAttribute(attributeName = "ReportDate")
    public String getReportDate() { return reportDate; }
    public void setReportDate(String reportDate) { this.reportDate = reportDate;}

    @DynamoDBAttribute(attributeName = "imageURL")
    public String getImageURL() {return imageURL;}
    public void setImageURL(String imageURL) { this.imageURL = imageURL;}
}
