package org.mddarr.dakobedservices.services;

import org.mddarr.dakobedservices.dao.DocumentRepository;
import org.mddarr.dakobedservices.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    public List<Document> getDocuments(){
        List<Document> documents = new ArrayList<>();
        documentRepository.findAll().forEach(documents::add);
        return documents;
    }
}
