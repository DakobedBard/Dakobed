package org.mddarr.dakobedsnotel.services;


import org.mddarr.dakobedsnotel.dao.LocationRepository;
import org.mddarr.dakobedsnotel.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);
        System.out.println("The number of locationis " + locations.size());
        Location l = locations.get(0);
        System.out.println("The first location is " +  l.getLocation_name());
        return locations;
    }



//    public Optional<LocationDataEntity> getLocation(Integer id){
//        return locationRepository.findById(id);
//    }




}
