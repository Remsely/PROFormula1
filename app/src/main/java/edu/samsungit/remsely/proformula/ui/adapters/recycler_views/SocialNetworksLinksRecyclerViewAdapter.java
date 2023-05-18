package edu.samsungit.remsely.proformula.ui.adapters.recycler_views;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import edu.samsungit.remsely.proformula.data.models.SocialNetworkReferencesDataModel;
import edu.samsungit.remsely.proformula.databinding.SocialNetworksLinksRecyclerViewItemBinding;

public class SocialNetworksLinksRecyclerViewAdapter extends RecyclerView.Adapter<SocialNetworksLinksRecyclerViewAdapter.ViewHolder> {
    private List<SocialNetworkReferencesDataModel> networksReferences = Collections.emptyList();

    public SocialNetworksLinksRecyclerViewAdapter(){ }

    public void setNetworksReferences(List<SocialNetworkReferencesDataModel> networksReferences){
        this.networksReferences = networksReferences;
        notifyItemRangeChanged(0, networksReferences.size());
    }

    @NonNull
    @Override
    public SocialNetworksLinksRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        edu.samsungit.remsely.proformula.databinding.SocialNetworksLinksRecyclerViewItemBinding binding =
                SocialNetworksLinksRecyclerViewItemBinding.inflate(LayoutInflater
                        .from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialNetworksLinksRecyclerViewAdapter.ViewHolder holder, int position) {
        SocialNetworkReferencesDataModel socialNetworkReferencesDataModel = networksReferences.get(position);
        holder.contentAuthorReference.setText(socialNetworkReferencesDataModel.getReference());

        Glide.with(holder.contentAuthorSocialNetworkLogo.getContext())
                .load(socialNetworkReferencesDataModel.getImage())
                .into(holder.contentAuthorSocialNetworkLogo);

        holder.referenceItemsLayout.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(socialNetworkReferencesDataModel.getReference()));
            holder.contentAuthorReference.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return networksReferences.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView contentAuthorReference;
        private final ImageView contentAuthorSocialNetworkLogo;
        private final LinearLayout referenceItemsLayout;
        public ViewHolder(@NonNull SocialNetworksLinksRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            contentAuthorReference = binding.contentAuthorReference;
            contentAuthorSocialNetworkLogo = binding.contentAuthorSocialNetworkLogo;
            referenceItemsLayout = binding.socialNetworksItemsLayout;
        }
    }
}
