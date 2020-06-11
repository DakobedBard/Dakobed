package org.mddarr.dakobedservices.api;

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


    @GetMapping(value = "serve")
    public String postReport(){
        return "Posted Sucessfully";
    }

    @GetMapping(value="documents")
    public List<Document> getDocuments(){
        return documentService.getDocuments();
    }

}
