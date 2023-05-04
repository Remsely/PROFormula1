package edu.samsungit.remsely.proformula.ui.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.CalendarItemDataModel;
import edu.samsungit.remsely.proformula.data.repositories.CalendarRepository;
import edu.samsungit.remsely.proformula.data.repositories.NextRaceNumberRepository;

public class CalendarViewModel extends ViewModel {
    private final CalendarRepository calendarRepository;
    private final NextRaceNumberRepository nextRaceNumberRepository;

    public CalendarViewModel(){
        calendarRepository = new CalendarRepository();
        nextRaceNumberRepository = new NextRaceNumberRepository();
    }

    public MutableLiveData<List<CalendarItemDataModel>> getCalendarItemsLiveData(){
        return calendarRepository.getCalendarLiveData();
    }

    public LiveData<Integer> getNextRaceNumberLiveData(){
        return nextRaceNumberRepository.getNextRaceNumberLiveData();
    }
}