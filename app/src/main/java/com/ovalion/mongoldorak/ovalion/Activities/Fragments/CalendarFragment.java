package com.ovalion.mongoldorak.ovalion.Activities.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ovalion.mongoldorak.ovalion.API.SportRadar.apiSportRadarMatchCalendar;
import com.ovalion.mongoldorak.ovalion.API.SportRadar.apiSportRadarMatchScoreCalendar;
import com.ovalion.mongoldorak.ovalion.Adapters.ListCalendarAdapter;
import com.ovalion.mongoldorak.ovalion.Models.Match;
import com.ovalion.mongoldorak.ovalion.Models.TeamsEnum;
import com.ovalion.mongoldorak.ovalion.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CalendarFragment  extends Fragment
{
    private RecyclerView rv;
    private List<Match> matchs = new ArrayList<>();

    boolean all_team;
    TeamsEnum team;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_calendar, container, false);

        //REFERENCE
        rv= (RecyclerView) rootView.findViewById(R.id.calendar_recycler_view);

        //LAYOUT MANAGER
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        all_team = this.getActivity().getSharedPreferences("userInfo", MODE_PRIVATE)
                .getBoolean("all_team",false);

        team = TeamsEnum.fromId(this.getActivity().getSharedPreferences("userInfo", MODE_PRIVATE)
                .getString("team","sr:competitor:5747"));


        fireMatchTask();

        return rootView;
    }

    public void setMatchs(List<Match> matchs) {
        this.matchs = matchs;

        try {
            Thread.sleep(1100);
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

            rv.setAdapter(new ListCalendarAdapter(getContext(),matchs));
        }
    }

    private void fireMatchTask(){
        new apiSportRadarMatchCalendar(this,team.getId(),all_team).execute();
    }

    private void fireScoreTask(){
        new apiSportRadarMatchScoreCalendar(this).execute();
    }
}
