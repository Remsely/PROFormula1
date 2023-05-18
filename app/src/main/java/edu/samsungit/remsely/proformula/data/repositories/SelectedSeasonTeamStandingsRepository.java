package edu.samsungit.remsely.proformula.data.repositories;

import static edu.samsungit.remsely.proformula.util.FirebaseKeys.LOGO_UPPER;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.POINTS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.SEASONS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.TEAM;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.TEAMS;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.TEAMS_NAMES_KEY;
import static edu.samsungit.remsely.proformula.util.FirebaseKeys.TEAM_STANDINGS;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import edu.samsungit.remsely.proformula.data.models.TeamStandingsPositionDataModel;

public class SelectedSeasonTeamStandingsRepository {
    private final DatabaseReference databaseReference;

    public SelectedSeasonTeamStandingsRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public MutableLiveData<List<TeamStandingsPositionDataModel>> getTeamStandingsLiveData(String seasonsKey){
        MutableLiveData<List<TeamStandingsPositionDataModel>> liveData = new MutableLiveData<>();

        databaseReference.child(SEASONS).child(seasonsKey).child(TEAMS_NAMES_KEY)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String teamNameKey = snapshot.getValue(String.class);

                if(teamNameKey != null){
                    databaseReference.child(SEASONS).child(seasonsKey).child(TEAM_STANDINGS)
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot teamStandingsSnapshot) {
                                    List<TeamStandingsPositionDataModel> standings = new ArrayList<>();
                                    for (DataSnapshot nextPosition : teamStandingsSnapshot.getChildren()){
                                        int position = Integer.parseInt(Objects.requireNonNull(nextPosition.getKey()));
                                        String points = String.valueOf(nextPosition.child(POINTS).getValue(Integer.class));

                                        String teamKey = nextPosition.child(TEAM).getValue(String.class);

                                        if(teamKey != null){
                                            databaseReference.child(TEAMS).child(teamKey)
                                                    .addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot teamSnapshot) {
                                                            String teamLogo = teamSnapshot.child(LOGO_UPPER).getValue(String.class);
                                                            String teamName = teamSnapshot.child(teamNameKey).getValue(String.class);

                                                            TeamStandingsPositionDataModel dataModel =
                                                                    new TeamStandingsPositionDataModel(
                                                                            position, teamLogo, teamName, points);
                                                            standings.add(dataModel);
                                                            standings.sort(Comparator.comparingInt(TeamStandingsPositionDataModel::getP));
                                                            liveData.postValue(standings);
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return liveData;
    }
}
