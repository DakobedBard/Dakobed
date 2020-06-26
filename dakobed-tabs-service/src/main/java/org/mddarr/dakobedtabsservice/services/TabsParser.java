package org.mddarr.dakobedtabsservice.services;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class TabsParser {
    public static void loadOrdersData(DynamoDB dynamoDB) throws IOException {

        Table table = dynamoDB.getTable("Dakobed-Tabs");
        JsonParser parser = new JsonFactory().createParser(new File("/data/mddarr/Dakobed/dakobed-mir/data/dakobed-tabs/fileID0.json"));
        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();
        ObjectNode currentNode;
        int count = 0;
        while (iter.hasNext()) {

            currentNode = (ObjectNode) iter.next();
            String time = currentNode.path("time").asText();
            String duration = currentNode.path("duration").asText();
            String midi = currentNode.path("midi").asText();
            String string = currentNode.path("string").asText();

        }
        System.out.println("The count is " + count);
        parser.close();
    }


}
