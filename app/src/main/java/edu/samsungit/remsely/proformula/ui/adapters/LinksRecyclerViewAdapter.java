package edu.samsungit.remsely.proformula.ui.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import edu.samsungit.remsely.proformula.databinding.WhereWatchRecyclerViewItemBinding;
import edu.samsungit.remsely.proformula.data.models.LinksDataModel;

public class LinksRecyclerViewAdapter extends RecyclerView.Adapter<LinksRecyclerViewAdapter.LinkViewHolder> {
    private List<LinksDataModel> linksList = Collections.emptyList();

    public LinksRecyclerViewAdapter(){ }

    public void setLinksList(List<LinksDataModel> linksList){
        this.linksList = linksList;
        notifyItemRangeChanged(0, linksList.size());
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WhereWatchRecyclerViewItemBinding binding = WhereWatchRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LinkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder holder, int position) {
        holder.bind(linksList.get(position));
    }

    @Override
    public int getItemCount() {
        return linksList.size();
    }

    static class LinkViewHolder extends RecyclerView.ViewHolder{

        private WhereWatchRecyclerViewItemBinding binding;

        public LinkViewHolder(WhereWatchRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(LinksDataModel link){
            binding.whereWatchRecyclerViewItemTextView.setText(link.getLink());
            binding.getRoot().setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.getLink()));
                view.getContext().startActivity(intent);
            });
        }
    }
}
