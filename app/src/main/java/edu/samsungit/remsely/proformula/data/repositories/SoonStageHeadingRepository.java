package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.Keys.GRAND_PRIX;
import static edu.samsungit.remsely.proformula.util.Keys.GRAND_PRIX_KEY;
import static edu.samsungit.remsely.proformula.util.Keys.MAIN_SCREEN;
import static edu.samsungit.remsely.proformula.util.Keys.SOON;

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
        databaseReference.child(MAIN_SCREEN).child(SOON)
                .child(GRAND_PRIX_KEY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String grandPrixKey = snapshot.getValue(String.class);
                if(grandPrixKey != null){
                    databaseReference.child(GRAND_PRIX).child(grandPrixKey)
                            .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            StageHeadingDataModel soonStageHeading = snapshot.getValue(StageHeadingDataModel.class);
                            soonStageHeadingMutableLiveData.postValue(soonStageHeading);
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
