package org.mddarr.dakobedsnotel.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Series {
    @Id
    Integer id;
    String measurment_value;
    Date start_date;
    Date end_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeasurment_value() {
        return measurment_value;
    }

    public void setMeasurment_value(String measurment_value) {
        this.measurment_value = measurment_value;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) { this.start_date = start_date; }

    public Date getEnd_date() { return end_date; }

    public void setEnd_date(Date end_date) { this.end_date = end_date; }

    public Series() { }

    public Series(Integer id, String measurment_value, Date start_date, Date end_date) {
        this.id = id;
        this.measurment_value = measurment_value;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
