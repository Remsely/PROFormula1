package edu.samsungit.remsely.proformula.ui.calendar;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
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

public class CalendarRepository {
    private final DatabaseReference databaseReference;

    public CalendarRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public MutableLiveData<List<CalendarItemDataModel>> getCalendarLiveData(){
        MutableLiveData<List<CalendarItemDataModel>> liveData = new MutableLiveData<>();
        databaseReference.child("Main screen").child("Soon").child("Seasons key")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String seasonKey = snapshot.getValue(String.class);

                        assert seasonKey != null;
                        databaseReference.child("Seasons").child(seasonKey).child("Stages")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot seasonSnapshot) {
                                        List<CalendarItemDataModel> calendarItems = new ArrayList<>();
                                        for (DataSnapshot stageSnapshot : seasonSnapshot.getChildren()){

                                            List<StageScheduleDataModel> stageSchedule = new ArrayList<>();

                                            int number = Integer.parseInt(Objects.requireNonNull(stageSnapshot.getKey()));

                                            for (DataSnapshot eventSnapshot : stageSnapshot.child("events").getChildren()){
                                                String date = eventSnapshot.child("date").getValue(String.class);
                                                String name = eventSnapshot.child("name").getValue(String.class);

                                                StageScheduleDataModel stageScheduleDataModel =
                                                        new StageScheduleDataModel(date, name);

                                                stageSchedule.add(stageScheduleDataModel);
                                            }

                                            String grandPrixKey = stageSnapshot.child("Grand prix key")
                                                    .getValue(String.class);

                                            assert grandPrixKey != null;
                                            databaseReference.child("Grand prix").child(grandPrixKey)
                                                    .addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot gradPrixSnapshot) {
                                                    StageHeadingDataModel stageHeading = gradPrixSnapshot
                                                            .getValue(StageHeadingDataModel.class);

                                                    CalendarItemDataModel calendarItemDataModel =
                                                            new CalendarItemDataModel(stageHeading,
                                                                    stageSchedule, number);

                                                    calendarItems.add(calendarItemDataModel);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                        }
                                        Comparator<CalendarItemDataModel> calendarItemComparator = Comparator.comparingInt(CalendarItemDataModel::getNumber);
                                        calendarItems.sort(calendarItemComparator);
                                        liveData.setValue(calendarItems);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return liveData;
    }
}
