package org.mddarr.dakobedtabsservice.api;


import org.mddarr.dakobedtabsservice.services.TabsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TabsController {


    @Autowired
    TabsService tabsService;
}
