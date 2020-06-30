package org.mddarr.dakobedtabsservice.api;

import org.mddarr.dakobedtabsservice.models.Transcription;
import org.mddarr.dakobedtabsservice.services.TabsService;
import org.mddarr.dakobedtabsservice.services.TranscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TranscriptionController {

    @Autowired
    TabsService tabsService;

    @RequestMapping(value = "transcription")
    public Transcription getTranscription() throws IOException {
        return tabsService.getTab();
    }

}
