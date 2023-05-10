package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.Keys.FLAG;
import static edu.samsungit.remsely.proformula.util.Keys.INDIVIDUAL_STANDINGS;
import static edu.samsungit.remsely.proformula.util.Keys.LOGO_UPPER;
import static edu.samsungit.remsely.proformula.util.Keys.MAIN_SCREEN;
import static edu.samsungit.remsely.proformula.util.Keys.NAME_LOWER;
import static edu.samsungit.remsely.proformula.util.Keys.PILOT;
import static edu.samsungit.remsely.proformula.util.Keys.PILOTS;
import static edu.samsungit.remsely.proformula.util.Keys.POINTS;
import static edu.samsungit.remsely.proformula.util.Keys.RECENTLY;
import static edu.samsungit.remsely.proformula.util.Keys.SEASONS;
import static edu.samsungit.remsely.proformula.util.Keys.SEASONS_KEY;
import static edu.samsungit.remsely.proformula.util.Keys.SHORT_NAME;
import static edu.samsungit.remsely.proformula.util.Keys.TEAM;
import static edu.samsungit.remsely.proformula.util.Keys.TEAMS;

import androidx.annotation.NonNull;
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

import edu.samsungit.remsely.proformula.data.models.IndividualStandingsPositionDataModel;

public class IndividualStandingsRepository {
    private final DatabaseReference databaseReference;

    public IndividualStandingsRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public MutableLiveData<List<IndividualStandingsPositionDataModel>> getIndividualStandingsLiveData(){
        MutableLiveData<List<IndividualStandingsPositionDataModel>> liveData = new MutableLiveData<>();
        databaseReference.child(MAIN_SCREEN).child(RECENTLY).child(SEASONS_KEY)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String seasonsKey = snapshot.getValue(String.class);
                if (seasonsKey != null) {
                    databaseReference.child(SEASONS).child(seasonsKey).child(INDIVIDUAL_STANDINGS)
                            .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot individualStandingsSnapshot) {
                            List<IndividualStandingsPositionDataModel> standings = new ArrayList<>();
                            for (DataSnapshot nextPosition : individualStandingsSnapshot.getChildren()){
                                String pilotKey = nextPosition.child(PILOT).getValue(String.class);
                                String teamKey = nextPosition.child(TEAM).getValue(String.class);

                                int position = Integer.parseInt(Objects.requireNonNull(nextPosition.getKey()));
                                String points = String.valueOf(nextPosition.child(POINTS).getValue(Integer.class));

                                if (pilotKey != null){
                                    databaseReference.child(PILOTS).child(pilotKey)
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot pilotSnapshot) {
                                                    String pilotName = pilotSnapshot.child(NAME_LOWER).getValue(String.class);
                                                    String pilotFlag = pilotSnapshot.child(FLAG).getValue(String.class);

                                                    if (teamKey != null){
                                                        databaseReference.child(TEAMS).child(teamKey)
                                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot teamSnapshot) {
                                                                        String teamName = teamSnapshot.child(SHORT_NAME).getValue(String.class);
                                                                        String teamLogo = teamSnapshot.child(LOGO_UPPER).getValue(String.class);

                                                                        IndividualStandingsPositionDataModel individualStandingsPositionDataModel
                                                                                = new IndividualStandingsPositionDataModel(
                                                                                        position, pilotFlag, pilotName, teamLogo, teamName, points);
                                                                        standings.add(individualStandingsPositionDataModel);
                                                                        standings.sort(Comparator.comparingInt(IndividualStandingsPositionDataModel::getP));
                                                                        liveData.postValue(standings);
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return liveData;
    }
}