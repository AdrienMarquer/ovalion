package com.ovalion.mongoldorak.ovalion.API.SportRadar;

import android.os.AsyncTask;
import android.util.Log;

import com.ovalion.mongoldorak.ovalion.Activities.Dialogs.TeamInfoDialog;
import com.ovalion.mongoldorak.ovalion.Activities.Fragments.TeamFragment;
import com.ovalion.mongoldorak.ovalion.Models.Competitor;
import com.ovalion.mongoldorak.ovalion.Models.TeamInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class apiSportRadarTeamInfo extends AsyncTask<String, Void, TeamInfo> {

    TeamInfoDialog frag;

    public apiSportRadarTeamInfo(TeamInfoDialog frag){
        this.frag = frag;
    }

    @Override
    protected TeamInfo doInBackground(String... strings) {
        return request(strings[0]);
    }

    @Override
    protected void onPostExecute(TeamInfo team){
        frag.setInfo(team);
    }

    public TeamInfo request(String id) {
        try {

            String  urlString = "https://api.sportradar.us/rugby/trial/v2/union/en/teams/"+id+"/profile.json?api_key=2pe692a3456vwezx8wyxrf8x";


            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);


            String res = responseStrBuilder.toString();
            System.out.println(res);
            JSONObject obj = new JSONObject(res);
            JSONObject obj_competitor = obj.getJSONObject("competitor");
            Competitor competitor = new Competitor();
            // we take id and name
            String id_compt = (String) obj_competitor.get("id");
            competitor.setId(id_compt);
            String name = (String) obj_competitor.get("name");
            competitor.setName(name);

            JSONObject obj_statistics = obj.getJSONObject("statistics");
            JSONArray list_season = obj_statistics.getJSONArray("seasons");

            TeamInfo teamInfo = new TeamInfo();
            teamInfo.setCompetitor(competitor);
            // we take statistics and put it in teamInfo
            for (int i = 0; i < list_season.length(); i++) {
                JSONObject season = (JSONObject) list_season.get(i);
                String idSeason = season.getString("id");
                if (idSeason.contains("sr:season:55291")){
                    JSONObject statiscticInSeason = season.getJSONObject("statistics");
                    System.out.println(statiscticInSeason);
                    int matches_drawn = (int) statiscticInSeason.get("matches_drawn");
                    teamInfo.setMatches_drawn(matches_drawn);
                    int matches_won = (int) statiscticInSeason.get("matches_won");
                    teamInfo.setMatches_won(matches_won);
                    int matches_lost = (int) statiscticInSeason.get("matches_lost");
                    teamInfo.setMatches_lost(matches_lost);
                    int matches_played = (int) statiscticInSeason.get("matches_played");
                    teamInfo.setMatches_played(matches_played);
                    int points_scored = (int) statiscticInSeason.get("points_scored");
                    teamInfo.setPoints_scored(points_scored);
                    int points_conceded = (int) statiscticInSeason.get("points_conceded");
                    teamInfo.setPoints_conceded(points_conceded);
                }


            }

            return teamInfo;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}