package com.zosiaowsiak.parking.Models;

public class Alert {
    private String text;
    private Integer area;

    public Alert(String text, Integer area) {
        this.text = text;
        this.area = area;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }
}
