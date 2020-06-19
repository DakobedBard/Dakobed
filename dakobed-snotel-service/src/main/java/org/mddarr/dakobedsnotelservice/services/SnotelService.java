package org.mddarr.dakobedsnotelservice.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.mddarr.dakobedsnotelservice.model.SnotelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SnotelService {

    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    public List<SnotelData> getSnotelLocationDate(String locationID){
        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement("Snotel")).build();
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":locationID", new AttributeValue().withS(locationID));

        DynamoDBQueryExpression<SnotelData> queryExpression = new DynamoDBQueryExpression<SnotelData>()
                .withKeyConditionExpression("LocationID = :locationID").withExpressionAttributeValues(eav);

        List<SnotelData> snotelData = mapper.query(SnotelData.class, queryExpression);
        return snotelData;
    }


    public List<SnotelData> getSnotelLocationBetweenDates(String id, String sdate, String edate) {
        List<SnotelData> snotelData = new ArrayList<>();
        return snotelData;
    }
}


//


