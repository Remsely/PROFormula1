package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.FirebaseKeys.EVENTS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.FIVE;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.FLAG;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.GRAND_PRIX;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.GRAND_PRIX_KEY;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.NAME;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.ONE;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.PILOT;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.PILOTS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.READINESS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.RESULTS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SEASONS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.STAGES;

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

import edu.samsungit.remsely.proformula.data.models.SeasonStagesItemDataModel;
import edu.samsungit.remsely.proformula.data.models.StageHeadingDataModel;

public class SelectedSeasonsStagesRepository {
    private final DatabaseReference databaseReference;

    public SelectedSeasonsStagesRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public MutableLiveData<List<SeasonStagesItemDataModel>> getSeasonStagesLiveData(String seasonsKey){
        MutableLiveData<List<SeasonStagesItemDataModel>> liveData = new MutableLiveData<>();

        databaseReference.child(SEASONS).child(seasonsKey).child(STAGES)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<SeasonStagesItemDataModel> stagesList = new ArrayList<>();
                for (DataSnapshot stageSnapshot : snapshot.getChildren()){
                    boolean readiness = Boolean.TRUE.equals(stageSnapshot.child(READINESS)
                            .getValue(Boolean.class));
                    int stageNumber = Integer.parseInt(Objects.requireNonNull(stageSnapshot.getKey()));
                    String grandPrixKey = stageSnapshot.child(GRAND_PRIX_KEY).getValue(String.class);
                    String pilotKey = stageSnapshot.child(EVENTS).child(FIVE).child(RESULTS)
                            .child(ONE).child(PILOT).getValue(String.class);

                    if (pilotKey != null){
                        databaseReference.child(PILOTS).child(pilotKey)
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot pilotSnapshot) {
                                String pilotName = pilotSnapshot.child(NAME).getValue(String.class);
                                String pilotFlag = pilotSnapshot.child(FLAG).getValue(String.class);

                                if(grandPrixKey != null){
                                    databaseReference.child(GRAND_PRIX).child(grandPrixKey)
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot stageHeadingSnapshot) {
                                                    StageHeadingDataModel stageHeading = stageHeadingSnapshot
                                                                    .getValue(StageHeadingDataModel.class);

                                                    SeasonStagesItemDataModel stagesListItem =
                                                            new SeasonStagesItemDataModel(
                                                                    stageHeading, stageNumber, pilotFlag,
                                                                    pilotName, readiness, seasonsKey);

                                                    stagesList.add(stagesListItem);
                                                    stagesList.sort(Comparator.comparingInt(
                                                            SeasonStagesItemDataModel::getStageNumber));
                                                    liveData.postValue(stagesList);
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
        return liveData;
    }
}
