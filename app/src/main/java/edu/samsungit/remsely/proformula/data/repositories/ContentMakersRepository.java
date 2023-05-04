package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.Keys.CONTENT;
import static edu.samsungit.remsely.proformula.util.Keys.DESCRIPTION;
import static edu.samsungit.remsely.proformula.util.Keys.KEY;
import static edu.samsungit.remsely.proformula.util.Keys.LOGO_LOWER;
import static edu.samsungit.remsely.proformula.util.Keys.NAME_LOWER;
import static edu.samsungit.remsely.proformula.util.Keys.RECOMMENDATION;
import static edu.samsungit.remsely.proformula.util.Keys.REFERENCE;
import static edu.samsungit.remsely.proformula.util.Keys.SOCIAL_NETWORKS;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.samsungit.remsely.proformula.data.models.ContentAuthorDataModel;
import edu.samsungit.remsely.proformula.data.models.SocialNetworkReferencesDataModel;

public class ContentMakersRepository {
    private final DatabaseReference databaseReference;

    public ContentMakersRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<ContentAuthorDataModel>> getContentAuthorsLiveData(){
        MutableLiveData<List<ContentAuthorDataModel>> liveData = new MutableLiveData<>();
        databaseReference.child(CONTENT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ContentAuthorDataModel> contentMakers = new ArrayList<>();
                for (DataSnapshot contentSnapshot : snapshot.getChildren()){
                    String description = contentSnapshot.child(DESCRIPTION).getValue(String.class);
                    boolean recommendation = Boolean.TRUE.equals(contentSnapshot.child(RECOMMENDATION).getValue(Boolean.class));
                    String logo = contentSnapshot.child(LOGO_LOWER).getValue(String.class);
                    String name = contentSnapshot.child(NAME_LOWER).getValue(String.class);

                    MutableLiveData<List<SocialNetworkReferencesDataModel>> referencesLiveData = new MutableLiveData<>();
                    List<SocialNetworkReferencesDataModel> referencesList = new ArrayList<>();
                    DataSnapshot socialNetworkSnapshot = contentSnapshot.child(SOCIAL_NETWORKS);
                    for (DataSnapshot referenceSnapshot : socialNetworkSnapshot.getChildren()){
                        String reference = referenceSnapshot.child(REFERENCE).getValue(String.class);
                        String key = referenceSnapshot.child(KEY).getValue(String.class);

                        assert key != null;
                        databaseReference.child(SOCIAL_NETWORKS).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                String image = snapshot2.child(LOGO_LOWER).getValue(String.class);
                                SocialNetworkReferencesDataModel socialNetworkReferencesDataModel =
                                        new SocialNetworkReferencesDataModel(reference, image);
                                referencesList.add(socialNetworkReferencesDataModel);
                                referencesLiveData.postValue(referencesList);
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    ContentAuthorDataModel contentAuthorDataModel =
                            new ContentAuthorDataModel(name, logo, description, recommendation, referencesLiveData);
                    contentMakers.add(contentAuthorDataModel);
                }
                contentMakers.sort((o1, o2) -> {
                    if (o1.getRecommendation() == o2.getRecommendation()) return 0;
                    else if (!o1.getRecommendation()) return 1;
                    else return -1;
                });
                liveData.postValue(contentMakers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return liveData;
    }
}
