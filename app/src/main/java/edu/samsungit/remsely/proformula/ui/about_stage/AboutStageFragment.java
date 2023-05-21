package edu.samsungit.remsely.proformula.ui.about_stage;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.databinding.FragmentAboutStageBinding;
import edu.samsungit.remsely.proformula.ui.adapters.recycler_views.LinksRecyclerViewAdapter;

public class AboutStageFragment extends Fragment {
    private String seasonsKey;
    private String stageNumber;
    private FragmentAboutStageBinding binding;
    private AboutStageViewModel aboutStageViewModel;
    private RecyclerView linksRecyclerView;

    public static AboutStageFragment newInstance(String seasonsKey, String stageNumber) {
        AboutStageFragment fragment = new AboutStageFragment();
        Bundle args = new Bundle();
        args.putString("seasonsKey", seasonsKey);
        args.putString("stageNumber", stageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAboutStageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        if (getArguments() != null){
            seasonsKey = getArguments().getString("seasonsKey");
            stageNumber = getArguments().getString("stageNumber");
        }

        aboutStageViewModel = new ViewModelProvider(this).get(AboutStageViewModel.class);

        linksRecyclerView = binding.aboutStageLinksRecyclerView;
        LinksRecyclerViewAdapter linksAdapter = new LinksRecyclerViewAdapter();
        linksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        linksRecyclerView.setAdapter(linksAdapter);
        linksRecyclerView.setItemAnimator(null);

        aboutStageLiveDataObservation();
    }

    private void aboutStageLiveDataObservation(){
        aboutStageViewModel.setAboutStageLiveData(seasonsKey, stageNumber);
        aboutStageViewModel.getAboutStageLiveData().observe(this, links ->{
            LinksRecyclerViewAdapter adapter = (LinksRecyclerViewAdapter) linksRecyclerView.getAdapter();
            if (adapter != null){
                adapter.setLinksList(links);
            }
        });
    }
}