package edu.samsungit.remsely.proformula.ui.content;

import java.util.List;

public class ContentAuthorDataModel {
    private String description;
    private boolean recommendation;
    private String logo;
    private String name;
    private List<SocialNetworkReferencesDataModel> socialNetworks;

    ContentAuthorDataModel(String name, String logo, String description,  boolean recommendation, List<SocialNetworkReferencesDataModel> socialNetworks){
        this.description = description;
        this.logo = logo;
        this.recommendation = recommendation;
        this.name = name;
        this.socialNetworks = socialNetworks;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getLogo(){
        return logo;
    }

    public boolean getRecommendation(){
        return recommendation;
    }

    public List<SocialNetworkReferencesDataModel> getSocialNetworks(){
        return socialNetworks;
    }
}
