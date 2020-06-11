package org.mddarr.dakobedsnotel.api;


import org.mddarr.dakobedsnotel.entity.Document;
import org.mddarr.dakobedsnotel.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
