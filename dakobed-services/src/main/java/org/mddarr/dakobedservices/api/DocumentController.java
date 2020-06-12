package org.mddarr.dakobedservices.api;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.mddarr.dakobedservices.entity.Document;
import org.mddarr.dakobedservices.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocumentController {
    @Autowired
    DocumentService documentService;

    @Autowired
    private AmazonSNS snsClient;

    @GetMapping(value = "serve")
    public String postReport(){
        return "Posted Sucessfully";
    }

    @GetMapping(value="documents")
    public List<Document> getDocuments(){
        return documentService.getDocuments();
    }


    @GetMapping(value="publish")
    public String pubSNS(){


        final String msg = "If you receive this message, publishing a message to an Amazon SNS topic works.";
        final PublishRequest publishRequest = new PublishRequest("arn:aws:sns:us-west-2:710339184759:dakobed-style", msg);
        final PublishResult publishResponse = snsClient.publish(publishRequest);
        return "fuckk";
    }

}
