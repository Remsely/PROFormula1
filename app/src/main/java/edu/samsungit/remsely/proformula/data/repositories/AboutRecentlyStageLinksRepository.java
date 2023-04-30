package edu.samsungit.remsely.proformula.data.repositories;

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

import edu.samsungit.remsely.proformula.data.models.LinksDataModel;

public class AboutRecentlyStageLinksRepository {
    private DatabaseReference databaseReference;

    public AboutRecentlyStageLinksRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<LinksDataModel>> getAboutStageLinksLiveData(){
        MutableLiveData<List<LinksDataModel>> aboutStageLinksLiveData = new MutableLiveData<>();
        databaseReference.child("Main screen").child("Recently")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String seasonsKey = snapshot.child("Seasons key").getValue(String.class);
                String stageNumber = snapshot.child("Stage number").getValue(String.class);

                assert seasonsKey != null && stageNumber != null;
                databaseReference.child("Seasons").child(seasonsKey).child("Stages")
                        .child(stageNumber).child("About stage")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                List<LinksDataModel> linksDataModelList = new ArrayList<>();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    String link = dataSnapshot.getValue(String.class);
                                    linksDataModelList.add(new LinksDataModel(link));
                                }
                                aboutStageLinksLiveData.setValue(linksDataModelList);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return aboutStageLinksLiveData;
    }
}
