package edu.samsungit.remsely.proformula.data.models;

public class SocialNetworkReferencesDataModel {
    private String reference;
    private String image;

    public SocialNetworkReferencesDataModel(String reference, String image){
        this.image = image;
        this.reference = reference;
    }

    public String getImage(){
        return image;
    }

    public String getReference(){
        return reference;
    }
}
