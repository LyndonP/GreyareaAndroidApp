package com.greyarea.grey.greyareaad340;


public class Webcam {

    private String camLabel;
    private String camImage;
    private String camOwnership;
    private String camDescription;
    private Double latitude;
    private Double longitude;



    public Webcam(String camLabel, String camImage, String camOwnership) {
        this.camLabel = camLabel;
        this.camImage = camImage;
        this.camOwnership = camOwnership;
        this.camDescription = camDescription;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getImageURL() {return this.camImage; }
    public String getLabel() {return this.camLabel;}
    public String getCamOwnership() {return this.camOwnership;}
    public String getCamDescription() {return this.camDescription;}
    public Double getLatitude() {return this.latitude;}
    public Double getLongitude() {return this.longitude;}


    public void setImageUrl(String imageUrl) {
        if(getCamOwnership().equals("sdot")) {
            this.camImage = "http://www.seattle.gov/trafficcams/images/" + imageUrl;
        } else {
            this.camImage = "http://images.wsdot.wa.gov/nw/" + imageUrl;
        }
    }
    public void setCamLabel(String camLabel) {
        this.camLabel = camLabel;
    }
    public void setCamOwnership(String camOwnership) {
        this.camOwnership = camOwnership;
    }
    public void setCamDescription(String camDescription) {
        this.camDescription = camDescription;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
