package edu.samsungit.remsely.proformula.ui.content;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContentMakersRepository {
    private final DatabaseReference databaseReference;

    public ContentMakersRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<ContentAuthorDataModel>> getContentAuthorsLiveData(){
        MutableLiveData<List<ContentAuthorDataModel>> liveData = new MutableLiveData<>();
        databaseReference.child("Content").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ContentAuthorDataModel> contentMakers = new ArrayList<>();
                for (DataSnapshot contentSnapshot : snapshot.getChildren()){
                    String description = contentSnapshot.child("Description").getValue(String.class);
                    boolean recommendation = Boolean.TRUE.equals(contentSnapshot.child("Recommendation").getValue(Boolean.class));
                    String logo = contentSnapshot.child("logo").getValue(String.class);
                    String name = contentSnapshot.child("name").getValue(String.class);

                    List<SocialNetworkReferencesDataModel> referencesList = new ArrayList<>();
                    DataSnapshot socialNetworkSnapshot = contentSnapshot.child("Social networks");
                    for (DataSnapshot referenceSnapshot : socialNetworkSnapshot.getChildren()){
                        String reference = referenceSnapshot.child("Reference").getValue(String.class);
                        String key = referenceSnapshot.child("Key").getValue(String.class);

                        assert key != null;
                        databaseReference.child("Social networks").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                String image = snapshot2.child("logo").getValue(String.class);
                                SocialNetworkReferencesDataModel socialNetworkReferencesDataModel =
                                        new SocialNetworkReferencesDataModel(reference, image);
                                referencesList.add(socialNetworkReferencesDataModel);
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    ContentAuthorDataModel contentAuthorDataModel =
                            new ContentAuthorDataModel(name, logo, description, recommendation, referencesList);
                    contentMakers.add(contentAuthorDataModel);
                }
                contentMakers.sort((o1, o2) -> {
                    if (o1.getRecommendation() == o2.getRecommendation()) return 0;
                    else if (!o1.getRecommendation()) return 1;
                    else return -1;
                });
                liveData.setValue(contentMakers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return liveData;
    }
}
