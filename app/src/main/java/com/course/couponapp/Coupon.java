package com.course.couponapp;

/**
 * Created by jbt on 28/06/2017.
 */

public class Coupon {
    private String startDate;
    private String endDate;
    private String image;
    private String title;
    private Double price;

    public Coupon(String startDate, String endDate, String image, String title, Double price) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.title = title;
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
