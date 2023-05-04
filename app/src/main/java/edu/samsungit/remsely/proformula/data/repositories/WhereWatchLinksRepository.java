package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.Keys.MAIN_SCREEN;
import static edu.samsungit.remsely.proformula.util.Keys.SOON;
import static edu.samsungit.remsely.proformula.util.Keys.WHERE_WATCH;

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
import java.util.List;

import edu.samsungit.remsely.proformula.data.models.LinksDataModel;

public class WhereWatchLinksRepository {
    private final DatabaseReference databaseReference;

    public WhereWatchLinksRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<List<LinksDataModel>> getWhereWatchLinksLiveData(){
        MutableLiveData<List<LinksDataModel>> whereWatchLinksLiveData = new MutableLiveData<>();
        databaseReference.child(MAIN_SCREEN).child(SOON).child(WHERE_WATCH)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<LinksDataModel> linksDataModels = new ArrayList<>();
                        for (DataSnapshot linkSnapshot : snapshot.getChildren()){
                            String link = linkSnapshot.getValue(String.class);
                            linksDataModels.add(new LinksDataModel(link));
                        }
                        whereWatchLinksLiveData.postValue(linksDataModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("FirebaseRepository", "Error fetching links from database");
                    }
                });
        return whereWatchLinksLiveData;
    }
}
