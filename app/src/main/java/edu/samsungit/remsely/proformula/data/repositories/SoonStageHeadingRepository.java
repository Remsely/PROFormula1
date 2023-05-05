package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.Keys.GRAND_PRIX;
import static edu.samsungit.remsely.proformula.util.Keys.GRAND_PRIX_KEY;
import static edu.samsungit.remsely.proformula.util.Keys.MAIN_SCREEN;
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

import edu.samsungit.remsely.proformula.data.models.StageHeadingDataModel;

public class SoonStageHeadingRepository {
    private final DatabaseReference databaseReference;

    public SoonStageHeadingRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<StageHeadingDataModel> getSoonStageHeading(){
        final MutableLiveData<StageHeadingDataModel> soonStageHeadingMutableLiveData = new MutableLiveData<>();
        databaseReference.child(MAIN_SCREEN).child(SOON).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String seasonKey = snapshot.child(SEASONS_KEY).getValue(String.class);
                String stageNumber = snapshot.child(STAGE_NUMBER).getValue(String.class);

                if (seasonKey != null && stageNumber != null) {
                    databaseReference.child(SEASONS).child(seasonKey).child(STAGES).child(stageNumber)
                            .child(GRAND_PRIX_KEY).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String gradPrixKey = snapshot.getValue(String.class);

                                    if (gradPrixKey != null) {
                                        databaseReference.child(GRAND_PRIX).child(gradPrixKey)
                                                .addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                StageHeadingDataModel stageHeadingDataModel = snapshot
                                                        .getValue(StageHeadingDataModel.class);
                                                soonStageHeadingMutableLiveData.postValue(stageHeadingDataModel);
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return soonStageHeadingMutableLiveData;
    }
}
