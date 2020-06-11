package org.mddarr.dakobedsnotel.services;

import org.mddarr.dakobedsnotel.api.SnotelController;
import org.mddarr.dakobedsnotel.dao.LocationRepository;
import org.mddarr.dakobedsnotel.dao.SnotelRepository;
import org.mddarr.dakobedsnotel.entity.FlowData;
import org.mddarr.dakobedsnotel.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SnotelService {

    @Autowired
    SnotelRepository snotelRepository;

    @Autowired
    LocationRepository locationRepository;

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
