package edu.samsungit.remsely.proformula.ui.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.samsungit.remsely.proformula.data.models.CalendarItemDataModel;
import edu.samsungit.remsely.proformula.data.repositories.CalendarRepository;
import edu.samsungit.remsely.proformula.data.repositories.NextRaceNumberRepository;

public class CalendarViewModel extends ViewModel {
    private final MutableLiveData<List<CalendarItemDataModel>> calendarLiveData;
    private final LiveData<Integer> nextRaceNumberLiveData;

    public CalendarViewModel(){
        CalendarRepository calendarRepository = new CalendarRepository();
        calendarLiveData = calendarRepository.getCalendarLiveData();

        NextRaceNumberRepository nextRaceNumberRepository = new NextRaceNumberRepository();
        nextRaceNumberLiveData = nextRaceNumberRepository.getNextRaceNumberLiveData();
    }

    public MutableLiveData<List<CalendarItemDataModel>> getCalendarItemsLiveData(){
        return calendarLiveData;
    }

    public LiveData<Integer> getNextRaceNumberLiveData(){
        return nextRaceNumberLiveData;
    }
}