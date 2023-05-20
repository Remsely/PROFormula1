package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.FirebaseKeys.EVENTS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.IS_RACE;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.NAME_LOWER;
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

import edu.samsungit.remsely.proformula.data.models.GrandPrixResultsTabLayoutHeadingDataModel;

public class GrandPrixResultsTabLayoutHeadingsRepository {
    private final DatabaseReference databaseReference;

    public GrandPrixResultsTabLayoutHeadingsRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<GrandPrixResultsTabLayoutHeadingDataModel>> getHeadingsLiveData(
            String seasonsKey, String stageNumber){

        MutableLiveData<List<GrandPrixResultsTabLayoutHeadingDataModel>> liveData = new MutableLiveData<>();

        databaseReference.child(SEASONS).child(seasonsKey).child(STAGES).child(stageNumber)
                .child(EVENTS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<GrandPrixResultsTabLayoutHeadingDataModel> events = new ArrayList<>();
                for (DataSnapshot eventSnapshot : snapshot.getChildren()){
                    boolean isRace = Boolean.TRUE.equals(eventSnapshot.child(IS_RACE).getValue(Boolean.class));
                    String name = eventSnapshot.child(NAME_LOWER).getValue(String.class);
                    events.add(new GrandPrixResultsTabLayoutHeadingDataModel(isRace, name));
                }
                liveData.postValue(events);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return liveData;
    }
}
