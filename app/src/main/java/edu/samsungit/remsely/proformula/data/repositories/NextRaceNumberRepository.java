package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.Keys.MAIN_SCREEN;
import static edu.samsungit.remsely.proformula.util.Keys.SOON;
import static edu.samsungit.remsely.proformula.util.Keys.STAGE_NUMBER;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class NextRaceNumberRepository {
    private final DatabaseReference databaseReference;

    public NextRaceNumberRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<Integer> getNextRaceNumberLiveData(){
        MutableLiveData<Integer> liveData = new MutableLiveData<>();
        databaseReference.child(MAIN_SCREEN).child(SOON).child(STAGE_NUMBER)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int stageNumber = Integer.parseInt(Objects.requireNonNull(snapshot.getValue(String.class)));
                        liveData.postValue(stageNumber);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return liveData;
    }
}
