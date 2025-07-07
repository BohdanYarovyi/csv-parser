package com.yarovyi.app;

import com.yarovyi.app.annotationProcessing.annotation.RequiredField;

public class Car {
    @RequiredField
    private String id;

    @RequiredField
    private String brand;

    @RequiredField
    private String model;

    @RequiredField
    private String color;

    @RequiredField
    private String year;

    @RequiredField
    private String city;

    @RequiredField
    private String vin;

    @RequiredField
    private String owner;

    @RequiredField
    private String mileage;

    @RequiredField
    private String isElectric;

    @RequiredField
    private String price;

    @Override
    public String toString() {
        return "Car{" +
               "id='" + id + '\'' +
               ", brand='" + brand + '\'' +
               ", model='" + model + '\'' +
               ", color='" + color + '\'' +
               ", year='" + year + '\'' +
               ", city='" + city + '\'' +
               ", vin='" + vin + '\'' +
               ", owner='" + owner + '\'' +
               ", mileage='" + mileage + '\'' +
               ", isElectric='" + isElectric + '\'' +
               ", price='" + price + '\'' +
               '}';
    }
}
