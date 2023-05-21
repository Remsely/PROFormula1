package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.FirebaseKeys.EVENTS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.FLAG;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.LOGO_UPPER;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.NAME;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.PILOT;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.PILOTS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.RESULTS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SEASONS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SHORT_NAME;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.STAGES;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.TEAM;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.TEAMS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.TIME;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import edu.samsungit.remsely.proformula.data.models.QualificationResultsDataModel;

public class RaceAgainstTimeRepository {
    private final DatabaseReference databaseReference;

    public RaceAgainstTimeRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<QualificationResultsDataModel>> getRaceAgainstTimeLiveData(
            String seasonsKey, String stageNumber, String eventNumber){
        MutableLiveData<List<QualificationResultsDataModel>> liveData = new MutableLiveData<>();

        if (seasonsKey != null && stageNumber != null) {
            databaseReference.child(SEASONS).child(seasonsKey).child(STAGES)
                    .child(stageNumber).child(EVENTS).child(eventNumber).child(RESULTS)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            List<QualificationResultsDataModel> mData = new ArrayList<>();
                            for (DataSnapshot data : snapshot2.getChildren()){
                                String pilotKey = data.child(PILOT).getValue(String.class);
                                String teamKey = data.child(TEAM).getValue(String.class);

                                int position = Integer.parseInt(Objects.requireNonNull(data.getKey()));
                                String time = data.child(TIME).getValue(String.class);

                                if (pilotKey != null) {
                                    databaseReference.child(PILOTS).child(pilotKey)
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot3) {
                                                    String pilotName = snapshot3.child(NAME).getValue(String.class);
                                                    String pilotFlag = snapshot3.child(FLAG).getValue(String.class);

                                                    if (teamKey != null) {
                                                        databaseReference.child(TEAMS).child(teamKey)
                                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot4) {
                                                                        String teamName = snapshot4.child(SHORT_NAME).getValue(String.class);
                                                                        String teamLogo = snapshot4.child(LOGO_UPPER).getValue(String.class);
                                                                        QualificationResultsDataModel qualificationResultsDataModel =
                                                                                new QualificationResultsDataModel(
                                                                                position, pilotFlag, pilotName, teamLogo, teamName, time);
                                                                        mData.add(qualificationResultsDataModel);
                                                                        mData.sort(Comparator.comparingInt(QualificationResultsDataModel::getP));
                                                                        liveData.postValue(mData);
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
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
        return liveData;
    }
}
