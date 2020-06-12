package org.mddarr.dakobedsnotel.dao;

import org.mddarr.dakobedsnotel.entity.FlowData;
import org.mddarr.dakobedsnotel.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SnotelRepository extends JpaRepository<FlowData, Integer> {
    List<FlowData> findByLocation(Location location);

    @Query(value = "SELECT * FROM snowpack u WHERE u.location_id = :location_id", nativeQuery = true) //  and u.date between :sdate and :edate")
    List<FlowData> findByLocationBetweenDates(Integer location_id); // ,@Param("sdate") Date sdate, @Param("edate") Date edate);

}
