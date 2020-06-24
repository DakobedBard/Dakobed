package org.mddarr.dakobedreports.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.mddarr.dakobedreports.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportsService {

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    public List<Report> getReports(String userID){
        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement("Snotel")).build();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":userID", new AttributeValue().withS(userID));

        DynamoDBQueryExpression<Report> queryExpression = new DynamoDBQueryExpression<Report>()
                .withKeyConditionExpression("UserId = :userID").withExpressionAttributeValues(eav);

        List<Report> snotelData = mapper.query(Report.class, queryExpression);
        return snotelData;
    }

}
