package org.mddarr.dakobedsnotel.services;


import org.mddarr.dakobedsnotel.dao.DocumentRepository;
import org.mddarr.dakobedsnotel.entity.Document;
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
