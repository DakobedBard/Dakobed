package org.mddarr.dakobedordersservice.api;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;


import org.mddarr.dakobedordersservice.models.GuitarsetTrainingExample;
import org.mddarr.dakobedordersservice.models.Transcription;
import org.mddarr.dakobedordersservice.services.TranscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
public class TranscriptionController {
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    TranscriptionService transcriptionService;

    @RequestMapping(value="transcription")
    public Transcription getTranscription() throws IOException {
        return transcriptionService.getTab();
    }

    @RequestMapping(value = "guitarset")
    public List<GuitarsetTrainingExample> getTrainingData(){
        return transcriptionService.getGuitarSetTrainingData();
    }

    @RequestMapping(value="S3transcription")
    public String what(){
        return "heyh";
    }

//    @RequestMapping(value = "S3transcription")
//    public Transcription getS3Transcription(@RequestParam("fileID") int fileid) throws IOException {
//        return transcriptionService.getTranscriptionS3(fileid);
//    }

    @RequestMapping(value = "hello")
    public String hello(){
        return "hello";
    }

}
