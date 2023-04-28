package edu.samsungit.remsely.proformula.ui.content;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.samsungit.remsely.proformula.databinding.FragmentContentBinding;

public class ContentFragment extends Fragment {
    private FragmentContentBinding binding;
    private ContentViewModel contentViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        contentViewModel = new ViewModelProvider(this).get(ContentViewModel.class);

        contentAuthorsLiveDataLiveDataObservation();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void contentAuthorsLiveDataLiveDataObservation(){
        contentViewModel.getContentAuthorsLiveData().observe(getViewLifecycleOwner(), contentAuthorDataModels -> {
            RecyclerView recyclerView = binding.contentScreenRecyclerView;
            ContentScreenRecyclerViewAdapter adapter = new ContentScreenRecyclerViewAdapter(contentAuthorDataModels);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}