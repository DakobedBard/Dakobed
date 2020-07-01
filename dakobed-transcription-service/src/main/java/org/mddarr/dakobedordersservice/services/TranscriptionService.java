package org.mddarr.dakobedordersservice.services;


import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.mddarr.dakobedordersservice.models.Transcription;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class TranscriptionService {

    String note_json_file_path = "/home/mddarr/data/Dakobed/dakobed-transcription-service/src/main/resources/transcription.json";

    public Transcription getTab() throws IOException {
        Transcription transcription = new Transcription(note_json_file_path);
        return transcription;
    }
}
