package edu.samsungit.remsely.proformula.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SoonStageHeadingRepository {
    private DatabaseReference databaseReference;

    public SoonStageHeadingRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<StageHeadingDataModel> getSoonStageHeading(){
        final MutableLiveData<StageHeadingDataModel> soonStageHeadingMutableLiveData = new MutableLiveData<>();
        databaseReference.child("Main screen").child("Soon")
                .child("Grand prix key").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String grandPrixKey = snapshot.getValue(String.class);
                if(grandPrixKey != null){
                    databaseReference.child("Grand prix").child(grandPrixKey)
                            .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            StageHeadingDataModel soonStageHeading = snapshot.getValue(StageHeadingDataModel.class);
                            soonStageHeadingMutableLiveData.setValue(soonStageHeading);
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
