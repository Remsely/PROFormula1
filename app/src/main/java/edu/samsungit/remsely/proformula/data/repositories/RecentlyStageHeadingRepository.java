package edu.samsungit.remsely.proformula.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.samsungit.remsely.proformula.data.models.StageHeadingDataModel;

public class RecentlyStageHeadingRepository {
    private DatabaseReference databaseReference;

    public RecentlyStageHeadingRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<StageHeadingDataModel> getRecentlyStageHeading() {
        final MutableLiveData<StageHeadingDataModel> recentlyStageHeadingMutableLiveData = new MutableLiveData<>();
        databaseReference.child("Main screen").child("Recently").child("Grand prix key").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String grandPrixKey = snapshot.getValue(String.class);
                if (grandPrixKey != null) {
                    databaseReference.child("Grand prix").child(grandPrixKey).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            StageHeadingDataModel soonStageHeading = snapshot.getValue(StageHeadingDataModel.class);
                            recentlyStageHeadingMutableLiveData.postValue(soonStageHeading);
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
        return recentlyStageHeadingMutableLiveData;
    }
}
