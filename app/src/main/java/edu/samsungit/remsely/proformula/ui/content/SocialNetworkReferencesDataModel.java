package edu.samsungit.remsely.proformula.ui.content;

public class SocialNetworkReferencesDataModel {
    private String reference;
    private String image;

    SocialNetworkReferencesDataModel(String reference, String image){
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
