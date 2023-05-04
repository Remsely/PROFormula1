package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.Keys.EVENTS;
import static edu.samsungit.remsely.proformula.util.Keys.FIVE;
import static edu.samsungit.remsely.proformula.util.Keys.FLAG;
import static edu.samsungit.remsely.proformula.util.Keys.LOGO_UPPER;
import static edu.samsungit.remsely.proformula.util.Keys.MAIN_SCREEN;
import static edu.samsungit.remsely.proformula.util.Keys.NAME_LOWER;
import static edu.samsungit.remsely.proformula.util.Keys.PILOT;
import static edu.samsungit.remsely.proformula.util.Keys.PILOTS;
import static edu.samsungit.remsely.proformula.util.Keys.POINTS;
import static edu.samsungit.remsely.proformula.util.Keys.RECENTLY;
import static edu.samsungit.remsely.proformula.util.Keys.RESULTS;
import static edu.samsungit.remsely.proformula.util.Keys.SEASONS;
import static edu.samsungit.remsely.proformula.util.Keys.SEASONS_KEY;
import static edu.samsungit.remsely.proformula.util.Keys.SHORT_NAME;
import static edu.samsungit.remsely.proformula.util.Keys.STAGES;
import static edu.samsungit.remsely.proformula.util.Keys.STAGE_NUMBER;
import static edu.samsungit.remsely.proformula.util.Keys.TEAM;
import static edu.samsungit.remsely.proformula.util.Keys.TEAMS;
import static edu.samsungit.remsely.proformula.util.Keys.TIME;

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

import edu.samsungit.remsely.proformula.data.models.RaceResultsDataModel;

public class RecentlyStageRaceResultsRepository {
    private final DatabaseReference databaseReference;

    public RecentlyStageRaceResultsRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<RaceResultsDataModel>> getRecentlyRaceResultsLiveData(){
        MutableLiveData<List<RaceResultsDataModel>> recentlyRaceResultsLiveData = new MutableLiveData<>();
        databaseReference.child(MAIN_SCREEN).child(RECENTLY)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String seasonsKey = snapshot.child(SEASONS_KEY).getValue(String.class);
                String stageNumber = snapshot.child(STAGE_NUMBER).getValue(String.class);

                if (seasonsKey != null && stageNumber != null) {
                    databaseReference.child(SEASONS).child(seasonsKey).child(STAGES)
                            .child(stageNumber).child(EVENTS).child(FIVE).child(RESULTS)
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                    List<RaceResultsDataModel> mData = new ArrayList<>();
                                    for (DataSnapshot data : snapshot2.getChildren()){
                                        String pilotKey = data.child(PILOT).getValue(String.class);
                                        String teamKey = data.child(TEAM).getValue(String.class);

                                        int position = Integer.parseInt(Objects.requireNonNull(data.getKey()));
                                        String time = data.child(TIME).getValue(String.class);
                                        String points = data.child(POINTS).getValue(String.class);

                                        if (pilotKey != null) {
                                            databaseReference.child(PILOTS).child(pilotKey)
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot3) {
                                                    String pilotName = snapshot3.child(NAME_LOWER).getValue(String.class);
                                                    String pilotFlag = snapshot3.child(FLAG).getValue(String.class);

                                                    if (teamKey != null) {
                                                        databaseReference.child(TEAMS).child(teamKey)
                                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot4) {
                                                                        String teamName = snapshot4.child(SHORT_NAME).getValue(String.class);
                                                                        String teamLogo = snapshot4.child(LOGO_UPPER).getValue(String.class);
                                                                        RaceResultsDataModel raceResultsDataModel = new RaceResultsDataModel(
                                                                                position, pilotFlag, pilotName, teamLogo, teamName, time, points);
                                                                        mData.add(raceResultsDataModel);
                                                                        mData.sort(Comparator.comparingInt(RaceResultsDataModel::getP));
                                                                        recentlyRaceResultsLiveData.postValue(mData);
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
                                        if(position == 10){
                                            break;
                                        }
                                    }
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
        return recentlyRaceResultsLiveData;
    }
}
