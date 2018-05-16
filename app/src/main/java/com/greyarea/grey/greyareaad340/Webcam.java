package com.greyarea.grey.greyareaad340;


public class Webcam {

    private String camLabel;
    private String camImage;
    private String camOwnership;


    public Webcam(String camLabel, String camImage, String camOwnership) {
        this.camLabel = camLabel;
        this.camImage = camImage;
        this.camOwnership = camOwnership;
    }

    public String getImageURL() {return this.camImage; }

    public String getLabel() {return this.camLabel;}

    public String getCamOwnership() {return this.camOwnership;}
}
