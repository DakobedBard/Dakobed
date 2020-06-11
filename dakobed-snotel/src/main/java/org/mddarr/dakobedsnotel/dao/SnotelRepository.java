package org.mddarr.dakobedsnotel.dao;

import org.mddarr.dakobedsnotel.entity.FlowData;
import org.mddarr.dakobedsnotel.entity.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnotelRepository extends CrudRepository<FlowData, Integer> {
    List<FlowData> findByLocation(Location location);
}
