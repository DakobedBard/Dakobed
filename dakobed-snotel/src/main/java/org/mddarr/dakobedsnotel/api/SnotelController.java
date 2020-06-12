package org.mddarr.dakobedsnotel.api;

import org.mddarr.dakobedsnotel.entity.FlowData;
import org.mddarr.dakobedsnotel.services.SnotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class SnotelController {
    @Autowired
    SnotelService snotelService;
    @GetMapping(value = "get")
    List<FlowData> getFlowDataByLocation(){
        return snotelService.getFlowDataLocation(1);
    }

    @GetMapping(value = "flow")
    List<FlowData> getFlow(){
        return snotelService.getFlow();
    }

    @GetMapping(value="flowdates")
    List<FlowData> getFlowDataLocationBetweenDates(@RequestParam("id") Integer id,
                                                   @RequestParam("sdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date sdate,
                                                   @RequestParam("edate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date edate){
        return snotelService.getFlowDataLocationBetweenDates(id, sdate, edate);
    }

}
