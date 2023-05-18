package edu.samsungit.remsely.proformula.ui.adapters.recycler_views;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import edu.samsungit.remsely.proformula.R;
import edu.samsungit.remsely.proformula.data.models.IndividualStandingsPositionDataModel;
import edu.samsungit.remsely.proformula.databinding.IndividualStandingsRecyclerViewItemBinding;

public class IndividualStandingsRecyclerViewAdapter extends RecyclerView.Adapter<IndividualStandingsRecyclerViewAdapter.ViewHolder> {
    private List<IndividualStandingsPositionDataModel> standings = Collections.emptyList();
    public IndividualStandingsRecyclerViewAdapter(){}

    public void setStandings(List<IndividualStandingsPositionDataModel> standings){
        this.standings = standings;
        notifyItemRangeChanged(0, standings.size());
    }

    @NonNull
    @Override
    public IndividualStandingsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IndividualStandingsRecyclerViewItemBinding binding = IndividualStandingsRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IndividualStandingsRecyclerViewAdapter.ViewHolder holder, int position) {
        IndividualStandingsPositionDataModel standingsPosition = standings.get(position);
        holder.position.setText(String.valueOf(standingsPosition.getP()));
        switch (standingsPosition.getP()){
            case 1:
                holder.position.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.gold));
                break;
            case 2:
                holder.position.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.silver));
                break;
            case 3:
                holder.position.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.bronze));
                break;
            default:
                holder.position.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
                break;
        }

        holder.pilotName.setText(standingsPosition.getPilot());
        holder.teamName.setText(standingsPosition.getTeam());
        holder.points.setText(standingsPosition.getPoints());

        Glide.with(holder.itemView.getContext()).load(standingsPosition.getPilotFlag()).into(holder.pilotFlag);
        Glide.with(holder.itemView.getContext()).load(standingsPosition.getTeamLogo()).into(holder.teamLogo);
    }

    @Override
    public int getItemCount() {
        return standings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView position;
        ImageView pilotFlag;
        ImageView teamLogo;
        TextView pilotName;
        TextView teamName;
        TextView points;

        public ViewHolder(IndividualStandingsRecyclerViewItemBinding binding){
            super(binding.getRoot());
            position = binding.individualStandingsNumber;
            pilotFlag = binding.individualStandingsPilotFlag;
            pilotName = binding.individualStandingsPilotName;
            teamLogo = binding.individualStandingsTeamLogo;
            teamName = binding.individualStandingsTeamName;
            points = binding.individualStandingsPoints;
        }
    }
}
