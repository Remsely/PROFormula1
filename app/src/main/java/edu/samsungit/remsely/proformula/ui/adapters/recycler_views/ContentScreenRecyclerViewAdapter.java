package edu.samsungit.remsely.proformula.ui.adapters.recycler_views;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.TooltipCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.data.models.ContentAuthorDataModel;
import edu.samsungit.remsely.proformula.databinding.ContentScreenRecyclerViewItemBinding;
import edu.samsungit.remsely.proformula.util.DpToPx;
import edu.samsungit.remsely.proformula.util.RoundedCornersToImageViewTransformation;

public class ContentScreenRecyclerViewAdapter extends RecyclerView.Adapter<ContentScreenRecyclerViewAdapter.ViewHolder> {
    private List<ContentAuthorDataModel> contentAuthors = Collections.emptyList();
    private LifecycleOwner viewLifecycleOwner;

    public ContentScreenRecyclerViewAdapter(){ }

    public void setViewLifecycleOwner(LifecycleOwner viewLifecycleOwner) {
        this.viewLifecycleOwner = viewLifecycleOwner;
    }

    public void setContentAuthors(List<ContentAuthorDataModel> contentAuthors){
        this.contentAuthors = contentAuthors;
        notifyItemRangeChanged(0, contentAuthors.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentScreenRecyclerViewItemBinding binding = ContentScreenRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContentAuthorDataModel contentAuthorDataModel = contentAuthors.get(position);

        RecyclerView nestedRecyclerView = holder.socialNetworksRecyclerView;

        contentAuthorDataModel.getSocialNetworks().observe(viewLifecycleOwner, socialNetworkReferencesDataModels -> {
            SocialNetworksLinksRecyclerViewAdapter adapter = (SocialNetworksLinksRecyclerViewAdapter)
                    holder.socialNetworksRecyclerView.getAdapter();
            if (adapter != null) {
                adapter.setNetworksReferences(socialNetworkReferencesDataModels);
            }
        });

        nestedRecyclerView.setLayoutManager(new LinearLayoutManager(nestedRecyclerView.getContext()));

        holder.contentAuthorName.setText(contentAuthorDataModel.getName());
        holder.contentAuthorInformation.setText(contentAuthorDataModel.getDescription());

        Glide.with(holder.contentAuthorLogo.getContext())
                .load(contentAuthorDataModel.getLogo())
                .transform(new RoundedCornersToImageViewTransformation(DpToPx.dpToPx(14)))
                .into(holder.contentAuthorLogo);

        ImageView imageView = holder.contentAuthorRecommendation;

        if (contentAuthorDataModel.getRecommendation()){
            imageView.setBackgroundResource(R.drawable.favourites);
            TooltipCompat.setTooltipText(imageView, "Рекомендуем этого автора!");
        }
        else {
            imageView.setBackground(null);
            TooltipCompat.setTooltipText(imageView, null);
        }
    }

    @Override
    public int getItemCount() {
        return contentAuthors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView contentAuthorName;
        private final ImageView contentAuthorLogo;
        private final ImageView contentAuthorRecommendation;
        private final TextView contentAuthorInformation;
        private final RecyclerView socialNetworksRecyclerView;
        public ViewHolder(@NonNull ContentScreenRecyclerViewItemBinding binding) {
            super(binding.getRoot());

            contentAuthorName = binding.contentAuthorName;
            contentAuthorLogo = binding.contentAuthorLogo;
            contentAuthorRecommendation = binding.contentAuthorRecomendation;
            contentAuthorInformation = binding.contentAuthorInformation;

            socialNetworksRecyclerView = binding.socialNetworksRecyclerView;
            SocialNetworksLinksRecyclerViewAdapter socialNetworksLinksRecyclerViewAdapter =
                    new SocialNetworksLinksRecyclerViewAdapter();
            socialNetworksRecyclerView.setLayoutManager(new LinearLayoutManager(socialNetworksRecyclerView.getContext()));
            socialNetworksRecyclerView.setAdapter(socialNetworksLinksRecyclerViewAdapter);
            socialNetworksRecyclerView.setItemAnimator(null);
        }
    }
}
