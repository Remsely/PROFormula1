package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.Keys.DATE;
import static edu.samsungit.remsely.proformula.util.Keys.EVENTS;
import static edu.samsungit.remsely.proformula.util.Keys.MAIN_SCREEN;
import static edu.samsungit.remsely.proformula.util.Keys.NAME_LOWER;
import static edu.samsungit.remsely.proformula.util.Keys.SEASONS;
import static edu.samsungit.remsely.proformula.util.Keys.SEASONS_KEY;
import static edu.samsungit.remsely.proformula.util.Keys.SOON;
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

import edu.samsungit.remsely.proformula.data.models.StageScheduleDataModel;

public class SoonStageScheduleRepository {
    private final DatabaseReference databaseReference;

    public SoonStageScheduleRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<StageScheduleDataModel>> getSoonStageScheduleLiveData(){
        MutableLiveData<List<StageScheduleDataModel>> soonStageScheduleLiveData = new MutableLiveData<>();
        databaseReference.child(MAIN_SCREEN).child(SOON)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String seasonsKey = snapshot.child(SEASONS_KEY).getValue(String.class);
                        String stageNumber = snapshot.child(STAGE_NUMBER).getValue(String.class);

                        if (seasonsKey != null && stageNumber != null) {
                            databaseReference.child(SEASONS).child(seasonsKey)
                                    .child(STAGES).child(stageNumber).child(EVENTS)
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            List<StageScheduleDataModel> list = new ArrayList<>();
                                            for(DataSnapshot data : dataSnapshot.getChildren()){
                                                String date = data.child(DATE).getValue(String.class);
                                                String event = data.child(NAME_LOWER).getValue(String.class);
                                                StageScheduleDataModel dataModel = new StageScheduleDataModel(date, event);
                                                list.add(dataModel);
                                            }
                                            soonStageScheduleLiveData.postValue(list);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return soonStageScheduleLiveData;
    }
}
