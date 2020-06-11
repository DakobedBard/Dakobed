package org.mddarr.dakobedsnotel.services;

import org.mddarr.dakobedsnotel.api.SnotelController;
import org.mddarr.dakobedsnotel.dao.SnotelRepository;
import org.mddarr.dakobedsnotel.entity.FlowData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class SnotelService {

    @Autowired
    SnotelRepository snotelRepository;

    public List<FlowData> getFlowDataLocation(Integer id){
        List<FlowData> flowData = new ArrayList<>();
        return flowData;
    }

    public List<FlowData> getFlow(){
        List<FlowData> reports = new ArrayList<>();
        snotelRepository.findAll().forEach(reports::add);
        return reports;
    }

}
