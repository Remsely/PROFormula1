package edu.samsungit.remsely.proformula.ui.content;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.samsungit.remsely.proformula.databinding.FragmentContentBinding;
import edu.samsungit.remsely.proformula.ui.adapters.ContentScreenRecyclerViewAdapter;

public class ContentFragment extends Fragment {
    private FragmentContentBinding binding;
    private ContentViewModel contentViewModel;
    private RecyclerView recyclerView;

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

        recyclerView = binding.contentScreenRecyclerView;
        ContentScreenRecyclerViewAdapter adapter = new ContentScreenRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        contentAuthorsLiveDataLiveDataObservation();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void contentAuthorsLiveDataLiveDataObservation(){
        contentViewModel.getContentAuthorsLiveData().observe(getViewLifecycleOwner(), contentAuthorDataModels -> {
            ContentScreenRecyclerViewAdapter adapter = (ContentScreenRecyclerViewAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.setViewLifecycleOwner(getViewLifecycleOwner());
                adapter.setContentAuthors(contentAuthorDataModels);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}