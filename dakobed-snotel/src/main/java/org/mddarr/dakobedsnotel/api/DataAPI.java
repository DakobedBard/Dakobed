package org.mddarr.dakobedsnotel.api;


import org.mddarr.dakobedsnotel.entity.Location;
import org.mddarr.dakobedsnotel.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/data/")
@CrossOrigin
public class DataAPI {

    @Autowired
    private DataService dataService;

    @GetMapping()
    public String get() throws IOException {
        return "hello";
    }


    @GetMapping(value = "/location/")
    public List<Location> getLocations() throws IOException {
        return dataService.getLocations();
    }

//
//    @GetMapping(value = "/location/")
//    public List<LocationEntity> getTimeSeries(@RequestParam(value="id") String id, @RequestParam(value="") String name) throws IOException {
//        return dataService.getLocations();
//    }



}
