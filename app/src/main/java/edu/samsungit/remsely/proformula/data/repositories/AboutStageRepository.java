package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.FirebaseKeys.ABOUT_STAGE;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SEASONS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.STAGES;

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

public class AboutStageRepository {
    private final DatabaseReference databaseReference;

    public AboutStageRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<LinksDataModel>> getAboutStageLiveData(String seasonsKey, String stageNumber) {
        MutableLiveData<List<LinksDataModel>> liveData = new MutableLiveData<>();
        if (seasonsKey != null && stageNumber != null) {
            databaseReference.child(SEASONS).child(seasonsKey).child(STAGES)
                    .child(stageNumber).child(ABOUT_STAGE)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            List<LinksDataModel> linksDataModelList = new ArrayList<>();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String link = dataSnapshot.getValue(String.class);
                                linksDataModelList.add(new LinksDataModel(link));
                            }
                            liveData.postValue(linksDataModelList);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
        return liveData;
    }
}