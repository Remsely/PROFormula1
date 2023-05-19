package edu.samsungit.remsely.proformula.ui.adapters.recycler_views;

import static edu.samsungit.remsely.proformula.util.DpToPx.dpToPx;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.Collections;
import java.util.List;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.data.models.SeasonStagesItemDataModel;
import edu.samsungit.remsely.proformula.databinding.SeasonStagesRecyclerViewItemBinding;
import edu.samsungit.remsely.proformula.util.RoundedCornersToImageViewTransformation;

public class SeasonStagesRecyclerViewAdapter extends RecyclerView.Adapter<SeasonStagesRecyclerViewAdapter.ViewHolder> {
    private List<SeasonStagesItemDataModel> stagesList = Collections.emptyList();
    private OnItemClickListener onItemClickListener;

    public void setStagesList(List<SeasonStagesItemDataModel> stagesList){
        this.stagesList = stagesList;
        notifyItemRangeChanged(0, stagesList.size());
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public SeasonStagesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SeasonStagesRecyclerViewItemBinding binding = SeasonStagesRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SeasonStagesRecyclerViewAdapter.ViewHolder holder, int position) {
        SeasonStagesItemDataModel stage = stagesList.get(position);

        holder.stageName.setText(stage.getStageNumber() + ". " + stage.getStageHeadingDataModel().getName());

        if (stage.getPilotName() != null){
            holder.pilotName.setText(stage.getPilotName());
            holder.pilotName.setTextColor(ContextCompat
                    .getColor(holder.pilotName.getContext(), R.color.gold));

            Glide.with(holder.pilotFlag.getContext()).load(stage.getPilotFlag()).into(holder.pilotFlag);

            holder.itemLayout.setOnClickListener(v -> onItemClickListener.onItemClick(stage));
            holder.pilotFlag.setVisibility(View.VISIBLE);
        }
        else{
            holder.pilotName.setText("Предстоит");
            holder.pilotName.setTextColor(ContextCompat
                    .getColor(holder.pilotName.getContext(), R.color.red));

            holder.pilotFlag.setVisibility(View.GONE);
        }

        Glide.with(holder.stageFlag.getContext()).load(stage.getStageHeadingDataModel().getFlag())
                .transform(new CenterCrop(), new RoundedCornersToImageViewTransformation(dpToPx(14)))
                .into(holder.stageFlag);

    }

    @Override
    public int getItemCount() {
        return stagesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView pilotName;
        private final TextView stageName;
        private final ImageView pilotFlag;
        private final ImageView stageFlag;
        private final ConstraintLayout itemLayout;

        public ViewHolder(SeasonStagesRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            pilotName = binding.seasonStageWinner;
            pilotFlag = binding.seasonStageWinnerFlag;
            stageName = binding.seasonStageName;
            stageFlag = binding.seasonStagePlaceFlag;
            itemLayout = binding.stageLayout;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(SeasonStagesItemDataModel stagesItem);
    }
}
