package edu.samsungit.remsely.proformula.ui.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CalendarViewModel extends ViewModel {
    private CalendarRepository calendarRepository;
    private LiveData<List<CalendarItemDataModel>> calendarItemsLiveData;
    private NextRaceNumberRepository nextRaceNumberRepository;
    private LiveData<Integer> nextRaceNumberLiveData;

    public CalendarViewModel(){
        calendarRepository = new CalendarRepository();
        calendarItemsLiveData = calendarRepository.getCalendarLiveData();

        nextRaceNumberRepository = new NextRaceNumberRepository();
        nextRaceNumberLiveData = nextRaceNumberRepository.getNextRaceNumberLiveData();
    }

    public LiveData<List<CalendarItemDataModel>> getCalendarItemsLiveData(){
        return calendarItemsLiveData;
    }

    public LiveData<Integer> getNextRaceNumberLiveData(){
        return nextRaceNumberLiveData;
    }
}