package edu.samsungit.remsely.proformula.ui.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.samsungit.remsely.proformula.databinding.FragmentCalendarBinding;


public class CalendarFragment extends Fragment {
    private FragmentCalendarBinding binding;
    private CalendarViewModel calendarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);

        int nextRaceNumber;
        if (calendarViewModel.getNextRaceNumberLiveData().getValue() != null){
            nextRaceNumber = calendarViewModel.getNextRaceNumberLiveData().getValue() - 1;
        }
        else {
            nextRaceNumber = 0;
        }
        calendarItemsLiveDataObservation(nextRaceNumber);
    }



    @SuppressLint("NotifyDataSetChanged")
    private void calendarItemsLiveDataObservation(int num){
        calendarViewModel.getCalendarItemsLiveData().observe(getViewLifecycleOwner(), calendarItemDataModels -> {
            RecyclerView recyclerView = binding.calendarScreenRecyclerView;
            CalendarScreenRecyclerViewAdapter adapter = new CalendarScreenRecyclerViewAdapter(calendarItemDataModels, num);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.scrollToPosition(num);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}