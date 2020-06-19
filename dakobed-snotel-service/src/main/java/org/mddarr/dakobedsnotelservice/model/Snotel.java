package org.mddarr.dakobedsnotelservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;


@DynamoDBTable(tableName="Snotel")
public class Snotel {
    private String location;
    private String date;
    private double snowCurrent;
    private double snowMedian;
    private double snowPctMedian;
    private double waterCurrent;
    private double waterMedian;
    private double waterPctMedian;


    @DynamoDBHashKey(attributeName="Location")
    public String getLocation() {return location; }
    public void setLocation(String location) { location = location;}

    @DynamoDBRangeKey(attributeName = "Date")
    public String getDate() { return date;}
    public void setDate(String date) { date = date; }

    @DynamoDBAttribute(attributeName = "SnowCurrent")
    public double getSnowCurrent() {return snowCurrent; }
    public void setSnowCurrent(double snowCurrent) {this.snowCurrent = snowCurrent;}

    @DynamoDBAttribute(attributeName = "SnowMedian")
    public double getSnowMedian() {return snowCurrent; }
    public void setSnowMedian(double snowCurrent) {this.snowCurrent = snowCurrent;}
    
    @DynamoDBAttribute(attributeName = "SnowPctMedian")
    public double getSnowPctMedian() { return snowPctMedian;}
    public void setSnowPctMedian(double snowPctMedian) {this.snowPctMedian = snowPctMedian;}


    @DynamoDBAttribute(attributeName = "WaterCurrent")
    public double getWaterCurrent() {return waterCurrent;}
    public void setWaterCurrent(double waterCurrent) {this.waterCurrent = waterCurrent;}

    @DynamoDBAttribute(attributeName = "WaterCurrentAverage")
    public double getWaterMedian() {return waterMedian;}
    public void setWaterMedian(double waterMedian) {this.waterMedian = waterMedian;}

    @DynamoDBAttribute(attributeName = "WaterPctAverage")
    public double getWaterPctMedian() {return waterPctMedian;}
    public void setWaterPctMedian(double waterPctMedian) {this.waterPctMedian = waterPctMedian; }



}

