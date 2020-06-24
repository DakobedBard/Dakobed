package org.mddarr.dakobedreportservice.api;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.mddarr.dakobedreportservice.model.Report;

import org.mddarr.dakobedreportservice.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReportsController {

    @Autowired
    ReportsService reportsService;

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    @RequestMapping(value = "reports")
    public List<Report> getReports(@RequestParam("userID")  String userID){
        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement("Dakobed-Reports")).build();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":userID", new AttributeValue().withS(userID));

        DynamoDBQueryExpression<Report> queryExpression = new DynamoDBQueryExpression<Report>()
                .withKeyConditionExpression("userID= :userID").withExpressionAttributeValues(eav);

        List<Report> snotelData = mapper.query(Report.class, queryExpression);
        return snotelData;

    }

}
