package edu.samsungit.remsely.proformula.data.data_sources.room.root;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.samsungit.remsely.proformula.data.data_sources.room.dao.PilotsDAO;
import edu.samsungit.remsely.proformula.data.data_sources.room.entites.PilotsEntity;

@Database(entities = {PilotsEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "pro_formula_database";
    private static AppDatabase instance;

    public static PilotsDAO pilotsDAO() {
        return null;
    }

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db){
                    super.onCreate(db);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference pilotsRef = database.getReference("Pilots");

                    pilotsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot childSnapshot : snapshot.getChildren()){
                                String key = childSnapshot.getKey();
                                String flag = childSnapshot.child("flag").getValue(String.class);
                                String name = childSnapshot.child("name").getValue(String.class);
                                PilotsEntity pilotsEntity = new PilotsEntity(key, flag, name);
                                assert pilotsDAO() != null;
                                pilotsDAO().insert(pilotsEntity);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }).build();
        }
        return instance;
    }
}
