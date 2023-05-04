package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.Keys.DATE;
import static edu.samsungit.remsely.proformula.util.Keys.EVENTS;
import static edu.samsungit.remsely.proformula.util.Keys.GRAND_PRIX;
import static edu.samsungit.remsely.proformula.util.Keys.GRAND_PRIX_KEY;
import static edu.samsungit.remsely.proformula.util.Keys.MAIN_SCREEN;
import static edu.samsungit.remsely.proformula.util.Keys.NAME_LOWER;
import static edu.samsungit.remsely.proformula.util.Keys.SEASONS;
import static edu.samsungit.remsely.proformula.util.Keys.SEASONS_KEY;
import static edu.samsungit.remsely.proformula.util.Keys.SOON;
import static edu.samsungit.remsely.proformula.util.Keys.STAGES;

import android.util.Log;

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

import edu.samsungit.remsely.proformula.data.models.StageHeadingDataModel;
import edu.samsungit.remsely.proformula.data.models.StageScheduleDataModel;
import edu.samsungit.remsely.proformula.data.models.CalendarItemDataModel;

public class CalendarRepository {
    private final DatabaseReference databaseReference;

    public CalendarRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public MutableLiveData<List<CalendarItemDataModel>> getCalendarLiveData(){
        MutableLiveData<List<CalendarItemDataModel>> liveData = new MutableLiveData<>();
        databaseReference.child(MAIN_SCREEN).child(SOON).child(SEASONS_KEY)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String seasonKey = snapshot.getValue(String.class);

                        if (seasonKey != null) {
                            databaseReference.child(SEASONS).child(seasonKey).child(STAGES)
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot seasonSnapshot) {
                                            Comparator<CalendarItemDataModel> calendarItemComparator =
                                                    Comparator.comparingInt(CalendarItemDataModel::getNumber);
                                            List<CalendarItemDataModel> calendarItems = new ArrayList<>();
                                            for (DataSnapshot stageSnapshot : seasonSnapshot.getChildren()){

                                                List<StageScheduleDataModel> stageSchedule = new ArrayList<>();

                                                int number = Integer.parseInt(Objects.requireNonNull(stageSnapshot.getKey()));

                                                for (DataSnapshot eventSnapshot : stageSnapshot.child(EVENTS).getChildren()){
                                                    String date = eventSnapshot.child(DATE).getValue(String.class);
                                                    String name = eventSnapshot.child(NAME_LOWER).getValue(String.class);

                                                    StageScheduleDataModel stageScheduleDataModel =
                                                            new StageScheduleDataModel(date, name);

                                                    stageSchedule.add(stageScheduleDataModel);
                                                }

                                                String grandPrixKey = stageSnapshot.child(GRAND_PRIX_KEY)
                                                        .getValue(String.class);

                                                if (grandPrixKey != null) {
                                                    databaseReference.child(GRAND_PRIX).child(grandPrixKey)
                                                            .addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot gradPrixSnapshot) {
                                                            StageHeadingDataModel stageHeading = gradPrixSnapshot
                                                                    .getValue(StageHeadingDataModel.class);

                                                            CalendarItemDataModel calendarItemDataModel =
                                                                    new CalendarItemDataModel(stageHeading,
                                                                            stageSchedule, number);

                                                            calendarItems.add(calendarItemDataModel);
                                                            if (calendarItems.size() >= seasonSnapshot.getChildrenCount()) {
                                                                calendarItems.sort(calendarItemComparator);
                                                                liveData.postValue(calendarItems);
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            Log.d("CalendarRepository", "Error in Grand prix\\grandPrixKey");
                                                        }
                                                    });
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Log.d("CalendarRepository", "Error in Seasons\\seasonKey\\Stages");
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("CalendarRepository", "Error in Main screen\\Soon\\Seasons key");
                    }
                });
        return liveData;
    }
}
