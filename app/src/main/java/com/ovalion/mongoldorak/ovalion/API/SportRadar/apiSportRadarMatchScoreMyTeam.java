package com.ovalion.mongoldorak.ovalion.API.SportRadar;

import android.os.AsyncTask;

import com.ovalion.mongoldorak.ovalion.Activities.Fragments.CalendarFragment;
import com.ovalion.mongoldorak.ovalion.Activities.Fragments.TeamFragment;
import com.ovalion.mongoldorak.ovalion.Models.Competitor;
import com.ovalion.mongoldorak.ovalion.Models.Match;

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
import java.util.ArrayList;
import java.util.List;

public class apiSportRadarMatchScoreMyTeam extends AsyncTask<Void, Void, List<Match>> {
    private TeamFragment frag;

    public apiSportRadarMatchScoreMyTeam(TeamFragment frag)
    {
        this.frag = frag;
    }

    @Override
    protected List<Match> doInBackground(Void... voids) {
        return getListMatch();
    }

    @Override
    protected void onPostExecute(List<Match> matchs){
        frag.setScores(matchs);
    }

    public List<Match> getListMatch() {
        try {
            String   urlString = "https://api.sportradar.us/rugby/trial/v2/union/en/seasons/sr:season:55291/results.json?api_key=2pe692a3456vwezx8wyxrf8x";
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            InputStream is = conn.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            String res = responseStrBuilder.toString();
            JSONObject obj = new JSONObject(res);
            JSONArray ArrayResult = (JSONArray) obj.get("results");
            List<Match> list = new ArrayList<Match>();
            for (int i = 0; i < ArrayResult.length(); i++) {
                Match data = new Match();
                data = build_match(ArrayResult.getJSONObject(i), data);
                list.add(data);
            }

            return list;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Match build_match(JSONObject match, Match data) {
        data = get_competitors(match, data);
        data = get_id(match, data);
        data = get_status(match, data);
        data = get_score_home(match, data);
        data = get_score_away(match, data);
        return data;
    }
    public Match get_id(JSONObject match, Match data)
    {
        try {
            JSONObject sportEvent = (JSONObject) match.get("sport_event");
            String id = (String) sportEvent.get("id");
            data.setId(id);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
    public Match get_status(JSONObject match, Match data)
    {
        try {
            JSONObject sportEvent = (JSONObject) match.get("sport_event_status");
            String status = (String) sportEvent.get("status");
            if (status.contains("not_started")){
                data.setStatus(false);
            }else{
                data.setStatus(true);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
    public Match get_competitors(JSONObject match, Match data)
    {
        try {
            JSONObject sportEvent = (JSONObject) match.get("sport_event");
            JSONArray competitors = (JSONArray) sportEvent.get("competitors");
            JSONObject competitors2 = (JSONObject) competitors.get(0);
            Competitor competitorA = new Competitor();
            competitorA.setId(competitors2.getString("id"));
            competitorA.setAbbreviation(competitors2.getString("abbreviation"));
            competitorA.setName(competitors2.getString("name"));
            if (competitors2.getString("qualifier").contains("home")){
                competitorA.setQualifier(true);
            }else{
                competitorA.setQualifier(false);
            }
            data.setCompetitorA(competitorA);
            JSONObject competitors3 = (JSONObject) competitors.get(1);
            Competitor competitorB = new Competitor();
            competitorB.setId(competitors3.getString("id"));
            competitorB.setAbbreviation(competitors3.getString("abbreviation"));
            competitorB.setName(competitors3.getString("name"));
            if (competitors3.getString("qualifier").contains("home")){
                competitorB.setQualifier(true);
            }else {
                competitorB.setQualifier(false);
            }
            data.setCompetitorB(competitorB);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
    public Match get_score_home(JSONObject match, Match data)
    {
        try {
            JSONObject sport_event_status = (JSONObject) match.get("sport_event_status");
            int home_score = (int) sport_event_status.get("home_score");
            data.setScoreHome(home_score);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
    public Match get_score_away(JSONObject match, Match data)
    {
        try {
            JSONObject sport_event_status = (JSONObject) match.get("sport_event_status");
            int away_score = (int) sport_event_status.get("away_score");
            data.setScoreAway(away_score);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
