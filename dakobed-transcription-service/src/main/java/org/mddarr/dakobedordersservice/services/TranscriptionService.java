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
import org.mddarr.dakobedordersservice.models.Note;
import org.mddarr.dakobedordersservice.models.Transcription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class TranscriptionService {

    @Autowired
    S3Factory s3Factory;

    String note_json_file_path = "/home/mddarr/data/Dakobed/dakobed-transcription-service/src/main/resources/transcription1.json";

    public Transcription getTab() throws IOException {
        Transcription transcription = new Transcription(note_json_file_path);
        return transcription;
    }

    public Transcription getTranscriptionS3(int fileID) throws IOException {
        List<Note> notes = new ArrayList<>();
        byte[] a = s3Factory.getFile(fileID);

        JsonParser parser = new JsonFactory().createParser(a);
        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();
        ObjectNode currentNode;

        while (iter.hasNext()) {
            currentNode = (ObjectNode) iter.next();
            int beat = currentNode.path("beat").asInt();
            int measure = currentNode.path("measure").asInt();
            int midi = currentNode.path("midi").asInt();
            int string = currentNode.path("string").asInt();
            Note note = new Note(midi, beat, measure, string);
            notes.add(note);
        }
        return new Transcription(notes);

//        try (FileOutputStream fos = new FileOutputStream("src/main/resources/trans1.json")) {
//            fos.write(a);
//            //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

}


