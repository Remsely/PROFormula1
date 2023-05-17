package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.FirebaseKeys.FLAG;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.INDIVIDUAL_STANDINGS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.LOGO_UPPER;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.MAIN_SCREEN;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.NAME_LOWER;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.ONE;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.PILOT;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.PILOTS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.PREVIEW;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SEASONS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SEASONS_KEY;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SHORT_NAME;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SOON;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.TEAM;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.TEAMS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.TEAM_STANDINGS;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import edu.samsungit.remsely.proformula.data.models.SeasonsItemDataModel;

public class SeasonsRepository {
    private final DatabaseReference databaseReference;
    public SeasonsRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public MutableLiveData<List<SeasonsItemDataModel>> getSeasonsLiveData(){
        MutableLiveData<List<SeasonsItemDataModel>> liveData = new MutableLiveData<>();
        databaseReference.child(MAIN_SCREEN).child(SOON).child(SEASONS_KEY)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String currentSeason = snapshot.getValue(String.class);

                if (currentSeason != null){
                    databaseReference.child(SEASONS).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot seasonsSnapshot) {
                            List<SeasonsItemDataModel> itemsList = new ArrayList<>();
                            for (DataSnapshot seasonSnapshot : seasonsSnapshot.getChildren()){
                                if(!Objects.equals(seasonSnapshot.getKey(), currentSeason)){
                                    String seasonNumber = seasonSnapshot.getKey();
                                    String preview = seasonSnapshot.child(PREVIEW).getValue(String.class);
                                    String pilotKey = seasonSnapshot.child(INDIVIDUAL_STANDINGS)
                                            .child(ONE).child(PILOT).getValue(String.class);
                                    String teamKey = seasonSnapshot.child(TEAM_STANDINGS).child(ONE)
                                            .child(TEAM).getValue(String.class);

                                    if (pilotKey != null) {
                                        databaseReference.child(PILOTS).child(pilotKey)
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot pilotSnapshot) {
                                                String pilotName = pilotSnapshot.child(NAME_LOWER).getValue(String.class);
                                                String pilotFlag = pilotSnapshot.child(FLAG).getValue(String.class);

                                                if (teamKey != null) {
                                                    databaseReference.child(TEAMS).child(teamKey)
                                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot teamSnapshot) {
                                                                    String teamName = teamSnapshot.child(SHORT_NAME).getValue(String.class);
                                                                    String teamLogo = teamSnapshot.child(LOGO_UPPER).getValue(String.class);

                                                                    SeasonsItemDataModel item = new SeasonsItemDataModel(
                                                                            seasonNumber, pilotName, pilotFlag, teamName,
                                                                            teamLogo, preview);

                                                                    itemsList.add(item);

                                                                    itemsList.sort(Comparator.comparing(SeasonsItemDataModel::getSeasonNumber));
                                                                    Collections.reverse(itemsList);
                                                                    liveData.postValue(itemsList);
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
