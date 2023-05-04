package edu.samsungit.remsely.proformula.ui.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CalendarViewModel extends ViewModel {
    private CalendarRepository calendarRepository;
    private MutableLiveData<List<CalendarItemDataModel>> calendarItemsLiveData;
    private NextRaceNumberRepository nextRaceNumberRepository;
    private LiveData<Integer> nextRaceNumberLiveData;

    public CalendarViewModel(){
        calendarRepository = new CalendarRepository();
//        calendarItemsLiveData = ;

        nextRaceNumberRepository = new NextRaceNumberRepository();
//        nextRaceNumberLiveData = nextRaceNumberRepository.getNextRaceNumberLiveData();
    }

    public MutableLiveData<List<CalendarItemDataModel>> getCalendarItemsLiveData(){
        return calendarRepository.getCalendarLiveData();
    }

    public LiveData<Integer> getNextRaceNumberLiveData(){
        return nextRaceNumberRepository.getNextRaceNumberLiveData();
    }
}