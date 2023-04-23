package edu.samsungit.remsely.proformula.ui.home;

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
import java.util.List;

public class SoonStageScheduleRepository {

    private final DatabaseReference databaseReference;

    public SoonStageScheduleRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<StageScheduleDataModel>> getSoonStageScheduleLiveData(){
        MutableLiveData<List<StageScheduleDataModel>> soonStageScheduleLiveData = new MutableLiveData<>();
        databaseReference.child("Main screen").child("Soon")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String seasonsKey = snapshot.child("Seasons key").getValue(String.class);
                        String stageNumber = snapshot.child("Stage number").getValue(String.class);

                        assert seasonsKey != null && stageNumber != null;
                        databaseReference.child("Seasons").child(seasonsKey)
                                .child("Stages").child(stageNumber).child("events")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        List<StageScheduleDataModel> list = new ArrayList<>();
                                        for(DataSnapshot data : dataSnapshot.getChildren()){
                                            String date = data.child("date").getValue(String.class);
                                            String event = data.child("name").getValue(String.class);
                                            StageScheduleDataModel dataModel = new StageScheduleDataModel(date, event);
                                            list.add(dataModel);
                                        }
                                        soonStageScheduleLiveData.setValue(list);
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
        return soonStageScheduleLiveData;
    }
}
