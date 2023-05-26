package edu.samsungit.remsely.proformula.ui.fragments.seasons;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.FragmentSeasonsBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.SeasonsScreenRecyclerViewAdapter;

public class SeasonsFragment extends Fragment {
    private FragmentSeasonsBinding binding;
    private SeasonsViewModel seasonsViewModel;
    private RecyclerView seasonsRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSeasonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        seasonsViewModel = new ViewModelProvider(this).get(SeasonsViewModel.class);

        seasonsRecyclerView = binding.seasonsScreenRecyclerView;
        SeasonsScreenRecyclerViewAdapter seasonsAdapter = new SeasonsScreenRecyclerViewAdapter();
        seasonsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        seasonsRecyclerView.setAdapter(seasonsAdapter);
        seasonsRecyclerView.setItemAnimator(null);

        seasonsLiveDataObservation();

        navigationToSelectedSeason(seasonsAdapter);
    }

    private void seasonsLiveDataObservation(){
        seasonsViewModel.getSeasonsLiveData().observe(getViewLifecycleOwner(), seasonsList -> {
            SeasonsScreenRecyclerViewAdapter adapter =
                    (SeasonsScreenRecyclerViewAdapter) seasonsRecyclerView.getAdapter();
            if(adapter != null){
                adapter.setSeasonsList(seasonsList);
            }
        });
    }

    private void navigationToSelectedSeason(SeasonsScreenRecyclerViewAdapter adapter){
        adapter.setOnItemClickListener(seasonItem -> {
            Bundle args = new Bundle();
            args.putString("seasonsKey", seasonItem.getSeasonNumber());
            Navigation.findNavController(seasonsRecyclerView)
                    .navigate(R.id.action_navigation_seasons_to_selectedSeasonFragment, args);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}