package edu.samsungit.remsely.proformula.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.Collections;
import java.util.List;

import edu.samsungit.remsely.proformula.data.models.SeasonsItemDataModel;
import edu.samsungit.remsely.proformula.databinding.SeasonsScreenRecyclerViewItemBinding;
import edu.samsungit.remsely.proformula.util.DpToPx;
import edu.samsungit.remsely.proformula.util.RoundedCornersToImageViewTransformation;

public class SeasonsScreenRecyclerViewAdapter extends RecyclerView.Adapter<SeasonsScreenRecyclerViewAdapter.ViewHolder> {
    private List<SeasonsItemDataModel> seasonsList = Collections.emptyList();

    public void setSeasonsList(List<SeasonsItemDataModel> seasonsList){
        this.seasonsList = seasonsList;
        notifyItemRangeChanged(0, seasonsList.size());
    }

    @NonNull
    @Override
    public SeasonsScreenRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SeasonsScreenRecyclerViewItemBinding binding = SeasonsScreenRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SeasonsScreenRecyclerViewAdapter.ViewHolder holder, int position) {
        SeasonsItemDataModel season = seasonsList.get(position);

        holder.seasonName.setText("СЕЗОН " + season.getSeasonNumber());
        holder.pilotName.setText(season.getPilotName());
        holder.teamName.setText(season.getTeamName());

        Glide.with(holder.seasonPreview.getContext())
                .load(season.getPreviewImage())
                .transform(new CenterCrop(), new RoundedCornersToImageViewTransformation(DpToPx.dpToPx(14)))
                .into(holder.seasonPreview);

        Glide.with(holder.pilotFlag.getContext()).load(season.getPilotFlag()).into(holder.pilotFlag);
        Glide.with(holder.teamLogo.getContext()).load(season.getTeamLogo()).into(holder.teamLogo);

        holder.seasonLayout.setOnClickListener(v -> {
            //todo
        });
    }

    @Override
    public int getItemCount() {
        return seasonsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView pilotName;
        private final TextView teamName;
        private final TextView seasonName;
        private final ImageView teamLogo;
        private final ImageView pilotFlag;
        private final ImageView seasonPreview;
        private final ConstraintLayout seasonLayout;

        public ViewHolder(SeasonsScreenRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            pilotName = binding.seasonWinnerPilotName;
            pilotFlag = binding.seasonWinnerPilotFlag;
            teamName = binding.seasonWinnerTeam;
            teamLogo = binding.seasonWinnerTeamLogo;
            seasonName = binding.seasonName;
            seasonLayout = binding.seasonsScreenRecyclerViewItem;
            seasonPreview = binding.seasonLogo;
        }
    }
}
