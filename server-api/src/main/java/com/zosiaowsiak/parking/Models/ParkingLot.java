package com.zosiaowsiak.parking.Models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParkingLot implements Serializable {
    private int id;
    private Boolean isoccupied;
    private Integer area;


        @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "isoccupied")
    public Boolean getIsoccupied() {
        return isoccupied;
    }

    public void setIsoccupied(Boolean isoccupied) {
        this.isoccupied = isoccupied;
    }

    @Basic
    @Column(name = "area")
    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingLot that = (ParkingLot) o;

        if (id != that.id) return false;
        if (isoccupied != null ? !isoccupied.equals(that.isoccupied) : that.isoccupied != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isoccupied != null ? isoccupied.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        return result;
    }
}
