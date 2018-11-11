package com.ovalion.mongoldorak.ovalion.Activities.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ovalion.mongoldorak.ovalion.API.SportRadar.apiSportRadarMatchCalendar;
import com.ovalion.mongoldorak.ovalion.API.SportRadar.apiSportRadarMatchMyTeam;
import com.ovalion.mongoldorak.ovalion.API.SportRadar.apiSportRadarMatchScoreCalendar;
import com.ovalion.mongoldorak.ovalion.API.SportRadar.apiSportRadarMatchScoreMyTeam;
import com.ovalion.mongoldorak.ovalion.API.SportRadar.apiSportRadarResults;
import com.ovalion.mongoldorak.ovalion.API.SportRadar.apiSportRadarTeamInfo;
import com.ovalion.mongoldorak.ovalion.Activities.Dialogs.ClassementDialog;
import com.ovalion.mongoldorak.ovalion.Activities.Dialogs.MatchDialog;
import com.ovalion.mongoldorak.ovalion.Activities.Dialogs.TeamInfoDialog;
import com.ovalion.mongoldorak.ovalion.Adapters.ListCalendarAdapter;
import com.ovalion.mongoldorak.ovalion.Models.Competitor;
import com.ovalion.mongoldorak.ovalion.Models.Match;
import com.ovalion.mongoldorak.ovalion.Models.TeamInfo;
import com.ovalion.mongoldorak.ovalion.Models.TeamsEnum;
import com.ovalion.mongoldorak.ovalion.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class TeamFragment extends Fragment
{
    List<Match> matchs;
    List<Competitor> compet;
    TeamInfo teaminfo;

    Button team_ranking;
    Button team_info;

    private RecyclerView team_recycler_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_team, container, false);

        team_ranking = (Button) rootView.findViewById(R.id.team_ranking);
        team_info = (Button) rootView.findViewById(R.id.team_info);
        team_recycler_view = (RecyclerView) rootView.findViewById(R.id.team_recycler_view);

        team_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));

        fireInfoTeamTaskMatch();

        team_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassementDialog classementDialog = new ClassementDialog (getContext(), compet);
                classementDialog.show();
            }
        });

        team_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamInfoDialog teaminfoDialog = new TeamInfoDialog (getContext(), teaminfo);
                teaminfoDialog.show();
            }
        });


        return rootView;
    }

    private void fireInfoTeamTaskMatch(){
        String id = TeamsEnum.fromId(this.getActivity().getSharedPreferences("userInfo", MODE_PRIVATE)
                .getString("team","sr:competitor:5758")).getId();
        new apiSportRadarMatchMyTeam(this,id).execute();
    }

    private void fireScoreTask(){
        new apiSportRadarMatchScoreMyTeam(this).execute();
    }

    public void setMatchs(List<Match> matchs) {
        this.matchs = matchs;

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fireScoreTask();
    }

    public void setScores(List<Match> scores){
        if(scores != null){
            for(int j = 0; j < matchs.size(); j++){
                for(int i =0 ; i < scores.size(); i++){
                    if (scores.get(i).getId().contains(matchs.get(j).getId())){
                        matchs.get(j).setScoreHome(scores.get(i).getScoreHome());
                        matchs.get(j).setScoreAway(scores.get(i).getScoreAway());
                    }
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            team_recycler_view.setAdapter(new ListCalendarAdapter(getContext(),matchs));
        }
    }
}
