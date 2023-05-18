package edu.samsungit.remsely.proformula.ui.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.samsungit.remsely.proformula.databinding.FragmentCalendarBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.CalendarScreenRecyclerViewAdapter;


public class CalendarFragment extends Fragment {
    private FragmentCalendarBinding binding;
    private CalendarViewModel calendarViewModel;
    private RecyclerView recyclerView;

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

        recyclerView = binding.calendarScreenRecyclerView;
        CalendarScreenRecyclerViewAdapter adapter = new CalendarScreenRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(null);

        calendarItemsLiveDataObservation();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void calendarItemsLiveDataObservation(){
        calendarViewModel.getNextRaceNumberLiveData().observe(getViewLifecycleOwner(), num ->
                calendarViewModel.getCalendarItemsLiveData().observe(getViewLifecycleOwner(),
                        calendarItemDataModels -> {

            CalendarScreenRecyclerViewAdapter adapter = (CalendarScreenRecyclerViewAdapter)
                    recyclerView.getAdapter();

            if (adapter != null) {
                adapter.setViewLifecycleOwner(getViewLifecycleOwner());
                adapter.setCalendarItems(calendarItemDataModels);
                adapter.setNextStageNumber(num - 1);
                recyclerView.scrollToPosition(num - 1);
            }
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}