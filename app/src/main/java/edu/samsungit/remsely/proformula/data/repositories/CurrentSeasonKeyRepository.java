package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.FirebaseKeys.MAIN_SCREEN;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SEASONS_KEY;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SOON;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CurrentSeasonKeyRepository {
    private final DatabaseReference databaseReference;

    public CurrentSeasonKeyRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<String> getCurrentSeasonKeyLiveData(){
        MutableLiveData<String> liveData = new MutableLiveData<>();
        databaseReference.child(MAIN_SCREEN).child(SOON).child(SEASONS_KEY)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String seasonKey = snapshot.getValue(String.class);
                        liveData.postValue(seasonKey);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return liveData;
    }
}
