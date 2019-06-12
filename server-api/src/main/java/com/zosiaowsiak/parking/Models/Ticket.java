package com.zosiaowsiak.parking.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "tickets", schema = "public", catalog = "postgres")
public class Ticket implements Serializable {
    private int id;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer lotId;

    public String timeLeft(){
        Date date = new Date();
        long currTimeMili = date.getTime();
        long endTimeMili = endTime.getTime();
        long diff = endTimeMili - currTimeMili;
        long timeLeftMinutes = diff / (60 * 1000);
        int minutesLeft = (int)timeLeftMinutes;
        int hoursCount = 0;
        while(minutesLeft>60){
            hoursCount += 1;
            minutesLeft -= 60;
        }
        return hoursCount + " hours, " + minutesLeft + " minutes";

    }

    @Id
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name="incrementator", strategy = "increment")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "lot_id")
    public Integer getLotId() {
        return lotId;
    }

    public void setLotId(Integer lotId) {
        this.lotId = lotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) return false;
        if (startTime != null ? !startTime.equals(ticket.startTime) : ticket.startTime != null) return false;
        if (endTime != null ? !endTime.equals(ticket.endTime) : ticket.endTime != null) return false;
        if (lotId != null ? !lotId.equals(ticket.lotId) : ticket.lotId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (lotId != null ? lotId.hashCode() : 0);
        return result;
    }

}
