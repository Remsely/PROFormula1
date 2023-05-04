package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.Keys.ABOUT_STAGE;
import static edu.samsungit.remsely.proformula.util.Keys.MAIN_SCREEN;
import static edu.samsungit.remsely.proformula.util.Keys.RECENTLY;
import static edu.samsungit.remsely.proformula.util.Keys.SEASONS;
import static edu.samsungit.remsely.proformula.util.Keys.SEASONS_KEY;
import static edu.samsungit.remsely.proformula.util.Keys.STAGES;
import static edu.samsungit.remsely.proformula.util.Keys.STAGE_NUMBER;

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
    private final DatabaseReference databaseReference;

    public AboutRecentlyStageLinksRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<LinksDataModel>> getAboutStageLinksLiveData(){
        MutableLiveData<List<LinksDataModel>> aboutStageLinksLiveData = new MutableLiveData<>();
        databaseReference.child(MAIN_SCREEN).child(RECENTLY)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String seasonsKey = snapshot.child(SEASONS_KEY).getValue(String.class);
                String stageNumber = snapshot.child(STAGE_NUMBER).getValue(String.class);

                assert seasonsKey != null && stageNumber != null;
                databaseReference.child(SEASONS).child(seasonsKey).child(STAGES)
                        .child(stageNumber).child(ABOUT_STAGE)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                List<LinksDataModel> linksDataModelList = new ArrayList<>();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    String link = dataSnapshot.getValue(String.class);
                                    linksDataModelList.add(new LinksDataModel(link));
                                }
                                aboutStageLinksLiveData.postValue(linksDataModelList);
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
