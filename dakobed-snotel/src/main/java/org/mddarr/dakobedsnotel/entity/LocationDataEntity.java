package org.mddarr.dakobedsnotel.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "snowpack")
public class LocationDataEntity {
    @Id
    Integer id;
    Integer location_id;
    Date date;
    Double snow_current;
    Double snow_median;
    Double snow_pct_median;
    Double water_current;
    Double water_avg;
    Double water_pct_avg;

    public LocationDataEntity(Integer id, Integer location_id, Date date, Double snow_current, Double snow_median,
                              Double snow_pct_median, Double water_current, Double water_avg, Double water_pct_avg) {
        this.id = id;
        this.location_id = location_id;
        this.date = date;
        this.snow_current = snow_current;
        this.snow_median = snow_median;
        this.snow_pct_median = snow_pct_median;
        this.water_current = water_current;
        this.water_avg = water_avg;
        this.water_pct_avg = water_pct_avg;
    }

    public LocationDataEntity(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
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
