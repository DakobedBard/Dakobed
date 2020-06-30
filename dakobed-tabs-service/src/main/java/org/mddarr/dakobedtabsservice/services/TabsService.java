package org.mddarr.dakobedtabsservice.services;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.mddarr.dakobedtabsservice.models.Transcription;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Service
public class TabsService {

    String note_json_file_path = "/home/mddarr/data/Dakobed/dakobed-tabs-service/src/main/resources/transcription.json";


    public Transcription getTab() throws IOException {
        Transcription transcription = new Transcription(note_json_file_path);
        return transcription;
    }
}





