package edu.samsungit.remsely.proformula.data.repositories;

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
        databaseReference.child("Main screen").child("Recently")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String seasonsKey = snapshot.child("Seasons key").getValue(String.class);
                String stageNumber = snapshot.child("Stage number").getValue(String.class);

                databaseReference.child("Seasons").child(seasonsKey).child("Stages")
                        .child(stageNumber).child("events").child("5").child("Results")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                List<RaceResultsDataModel> mData = new ArrayList<>();
                                for (DataSnapshot data : snapshot2.getChildren()){
                                    String pilotKey = data.child("Pilot").getValue(String.class);
                                    String teamKey = data.child("Team").getValue(String.class);

                                    int position = Integer.parseInt(Objects.requireNonNull(data.getKey()));
                                    String time = data.child("Time").getValue(String.class);
                                    String points = data.child("Points").getValue(String.class);
                                    Log.d("Stupid", String.valueOf(position));

                                    assert pilotKey != null;
                                    databaseReference.child("Pilots").child(pilotKey)
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot3) {
                                            String pilotName = snapshot3.child("name").getValue(String.class);
                                            String pilotFlag = snapshot3.child("flag").getValue(String.class);

                                            assert teamKey != null;
                                            databaseReference.child("Teams").child(teamKey)
                                                    .addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot4) {
                                                            String teamName = snapshot4.child("Short name").getValue(String.class);
                                                            String teamLogo = snapshot4.child("Logo").getValue(String.class);
                                                            RaceResultsDataModel raceResultsDataModel = new RaceResultsDataModel(
                                                                    position, pilotFlag, pilotName, teamLogo, teamName, time, points);
                                                            mData.add(raceResultsDataModel);

                                                            mData.sort(Comparator.comparingInt(RaceResultsDataModel::getP));

                                                            Log.d("Stupid", String.valueOf(position));
                                                            recentlyRaceResultsLiveData.setValue(mData);
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return recentlyRaceResultsLiveData;
    }
}
