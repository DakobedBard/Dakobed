package org.mddarr.dakobedsnotel.entity;

import org.mddarr.dakobedsnotel.writing.entity.Page;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.Flow;

@Entity
@Table(name = "locations" )
public class Location implements Serializable {
    @Id
    private Integer location_id;
    private String location_name;
    private Integer elevation;
    private Integer region_id;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<FlowData> flowData;


    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }
    public Location(){}

    public Location(Integer location_id, String location_name, Integer elevation, Integer region_id) {
        this.location_id = location_id;
        this.location_name = location_name;
        this.elevation = elevation;
        this.region_id = region_id;
    }
}
