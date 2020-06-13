package org.mddarr.dakobedsnotel.services;

import org.mddarr.dakobedsnotel.api.SnotelController;
import org.mddarr.dakobedsnotel.dao.LocationRepository;
import org.mddarr.dakobedsnotel.dao.SnotelRepository;
import org.mddarr.dakobedsnotel.entity.FlowData;
import org.mddarr.dakobedsnotel.entity.Location;
import org.mddarr.dakobedsnotel.entity.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow;

@Service
public class SnotelService {

    @Autowired
    SnotelRepository snotelRepository;

    @Autowired
    LocationRepository locationRepository;


    public List<FlowData> getFlowDataLocationBetweenDates(Integer id, Date start, Date end) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            return snotelRepository.findByLocationBetweenDates(location.get().getLocation_id(), start, end);
        }
        List<FlowData> flowData = new ArrayList<>();
        return flowData;
    }


    public List<FlowData> getFlowDataLocation(Integer id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            return snotelRepository.findByLocation(location.get());
        }
        List<FlowData> flowData = new ArrayList<>();
        return flowData;
    }

    public List<FlowData> getFlow(){
        List<FlowData> reports = new ArrayList<>();
        snotelRepository.findAll().forEach(reports::add);
        return reports;
    }

}
