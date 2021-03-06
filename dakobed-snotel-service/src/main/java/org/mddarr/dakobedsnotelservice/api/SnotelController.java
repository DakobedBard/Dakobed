package org.mddarr.dakobedsnotelservice.api;


import org.mddarr.dakobedsnotelservice.model.Location;
import org.mddarr.dakobedsnotelservice.model.SnotelData;
import org.mddarr.dakobedsnotelservice.services.SnotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SnotelController {

    @Autowired
    private SnotelService snotelService;

    @RequestMapping("snotel")
    public List<SnotelData> getSnotelLocation(@RequestParam("id") String id) {
        return snotelService.getSnotelLocationDate(id);
    }

    @RequestMapping("snotel-dates")
    public List<SnotelData> getSnotelLocationBetweenDates(@RequestParam("id") String id, @RequestParam("sdate") String sdate, @RequestParam("edate") String edate) {
        List<SnotelData> snotelData = snotelService.getSnotelLocationBetweenDates(id, sdate, edate);
        return snotelData;
    }



    @RequestMapping("locations")
    public List<Location> getLocations(){
        return snotelService.getLocations();
    }


}
