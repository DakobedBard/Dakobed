package org.mddarr.dakobedsnotel.entity;

import org.mddarr.dakobedsnotel.writing.entity.Book;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "snowpack")
public class FlowData implements Serializable {
    @Id
    Integer id;

    Date date;
    Double snow_current;
    Double snow_median;
    Double snow_pct_median;
    Double water_current;
    Double water_avg;
    Double water_pct_avg;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;


    public FlowData(Integer id, Integer location_id, Date date, Double snow_current, Double snow_median,
                              Double snow_pct_median, Double water_current, Double water_avg, Double water_pct_avg, Location location) {
        this.id = id;
        this.date = date;
        this.snow_current = snow_current;
        this.snow_median = snow_median;
        this.snow_pct_median = snow_pct_median;
        this.water_current = water_current;
        this.water_avg = water_avg;
        this.water_pct_avg = water_pct_avg;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public FlowData(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getSnow_current() {
        return snow_current;
    }

    public void setSnow_current(Double snow_current) {
        this.snow_current = snow_current;
    }

    public Double getSnow_median() {
        return snow_median;
    }

    public void setSnow_median(Double snow_median) {
        this.snow_median = snow_median;
    }

    public Double getSnow_pct_median() {
        return snow_pct_median;
    }

    public void setSnow_pct_median(Double snow_pct_median) {
        this.snow_pct_median = snow_pct_median;
    }

    public Double getWater_current() {
        return water_current;
    }

    public void setWater_current(Double water_current) {
        this.water_current = water_current;
    }

    public Double getWater_avg() {
        return water_avg;
    }

    public void setWater_avg(Double water_avg) {
        this.water_avg = water_avg;
    }

    public Double getWater_pct_avg() {
        return water_pct_avg;
    }

    public void setWater_pct_avg(Double water_pct_avg) {
        this.water_pct_avg = water_pct_avg;
    }
}
