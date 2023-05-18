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
import edu.samsungit.remsely.proformula.data.models.TeamStandingsPositionDataModel;
import edu.samsungit.remsely.proformula.databinding.TeamStandingsRecyclerViewItemBinding;

public class TeamStandingsRecyclerViewAdapter extends RecyclerView.Adapter<TeamStandingsRecyclerViewAdapter.ViewHolder> {
    private List<TeamStandingsPositionDataModel> standings = Collections.emptyList();

    public TeamStandingsRecyclerViewAdapter(){}

    public void setStandings(List<TeamStandingsPositionDataModel> standings){
        this.standings = standings;
        notifyItemRangeChanged(0, standings.size());
    }

    @NonNull
    @Override
    public TeamStandingsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TeamStandingsRecyclerViewItemBinding binding = TeamStandingsRecyclerViewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamStandingsRecyclerViewAdapter.ViewHolder holder, int position) {
        TeamStandingsPositionDataModel standingsPosition = standings.get(position);
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
        holder.teamName.setText(standingsPosition.getTeamFullName());
        holder.points.setText(standingsPosition.getPoints());

        Glide.with(holder.itemView.getContext()).load(standingsPosition.getTeamLogo()).into(holder.teamLogo);
    }

    @Override
    public int getItemCount() {
        return standings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView position;
        ImageView teamLogo;
        TextView teamName;
        TextView points;

        public ViewHolder(TeamStandingsRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            position = binding.teamStandingsNumber;
            teamLogo = binding.teamStandingsTeamLogo;
            teamName = binding.teamStandingsTeamName;
            points = binding.teamStandingsPoints;
        }
    }
}
