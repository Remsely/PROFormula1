package edu.samsungit.remsely.proformula.ui.content;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.TooltipCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.databinding.ContentScreenRecyclerViewItemBinding;
import edu.samsungit.remsely.proformula.util.DpToPx;
import edu.samsungit.remsely.proformula.util.RoundedCornersToImageViewTransformation;

public class ContentScreenRecyclerViewAdapter extends RecyclerView.Adapter<ContentScreenRecyclerViewAdapter.ViewHolder> {
    private final List<ContentAuthorDataModel> contentAuthorDataModels;

    public ContentScreenRecyclerViewAdapter(List<ContentAuthorDataModel> contentAuthorDataModels){
        this.contentAuthorDataModels = contentAuthorDataModels;
    }

    @NonNull
    @Override
    public ContentScreenRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentScreenRecyclerViewItemBinding binding = ContentScreenRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ContentScreenRecyclerViewAdapter.ViewHolder holder, int position) {
        ContentAuthorDataModel contentAuthorDataModel = contentAuthorDataModels.get(position);

        RecyclerView nestedRecyclerView = holder.socialNetworksRecyclerView;
        SocialNetworksLinksRecyclerViewAdapter nestedRecyclerViewAdapter = new SocialNetworksLinksRecyclerViewAdapter(contentAuthorDataModel.getSocialNetworks());
        nestedRecyclerView.setAdapter(nestedRecyclerViewAdapter);
        nestedRecyclerViewAdapter.notifyDataSetChanged();
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
        Log.d("Recommendation", String.valueOf(contentAuthorDataModel.getRecommendation()));
    }

    @Override
    public int getItemCount() {
        return contentAuthorDataModels.size();
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
        }
    }
}
