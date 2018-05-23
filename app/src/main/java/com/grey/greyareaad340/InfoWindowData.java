package com.grey.greyareaad340;

public class InfoWindowData {
    String location,city;
    private String imageURL;

    public InfoWindowData() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public InfoWindowData(String location, String city) {
        this.location = location;
        this.city = city;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}