package edu.samsungit.remsely.proformula.data.models;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContentAuthorDataModel {
    private final String description;
    private final boolean recommendation;
    private final String logo;
    private final String name;
    private final LiveData<List<SocialNetworkReferencesDataModel>> socialNetworks;

    public ContentAuthorDataModel(String name, String logo, String description, boolean recommendation, LiveData<List<SocialNetworkReferencesDataModel>> socialNetworks){
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

    public LiveData<List<SocialNetworkReferencesDataModel>> getSocialNetworks(){
        return socialNetworks;
    }
}
