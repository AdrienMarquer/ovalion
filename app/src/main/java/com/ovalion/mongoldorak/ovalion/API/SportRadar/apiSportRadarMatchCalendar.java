package com.ovalion.mongoldorak.ovalion.API.SportRadar;

import android.os.AsyncTask;

import com.ovalion.mongoldorak.ovalion.Activities.Fragments.CalendarFragment;
import com.ovalion.mongoldorak.ovalion.Models.Competitor;
import com.ovalion.mongoldorak.ovalion.Models.Match;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class apiSportRadarMatchCalendar extends AsyncTask<Void, Void, List<Match>> {
    private List<Match> matchs = new ArrayList<>();
    private CalendarFragment frag;
    private String id;
    private boolean isAllTeam;

    public apiSportRadarMatchCalendar(CalendarFragment frag, String id, boolean isAllTeam)
    {
        this.frag = frag;
        this.id = id;
        this.isAllTeam = isAllTeam;
    }

    @Override
    protected List<Match> doInBackground(Void... voids) {
        return request();
    }

    @Override
    protected void onPostExecute(List<Match> matchs){
        frag.setMatchs(matchs);
    }

    public List<Match> request() {
        try {
            String urlString = "";
            urlString = "https://api.sportradar.us/rugby/trial/v2/union/en/seasons/sr:season:55291/schedule.json?api_key=vcz9st3jfwmkqamhbwv7pvyv";
            //   old url when using id   urlString =   "https://api.sportradar.us/rugby/trial/v2/union/en/teams/"+id+"/schedule.json?api_key=vcz9st3jfwmkqamhbwv7pvyv";
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
            JSONArray list_match = obj.getJSONArray("sport_events");
            // System.out.println("number of match: " + list_match.length());
            List<Match> list = new ArrayList<Match>();
            for (int i = 0; i < list_match.length(); i++) {
                Match data = new Match();
                data = build_match(list_match.getJSONObject(i), data);
                if(isAllTeam){
                    list.add(data);
                }
                else if (data.getCompetitorA().getId().contains(id) ||  data.getCompetitorB().getId().contains(id)){
                    list.add(data);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Match build_match(JSONObject match, Match data) {
        data = get_id(match, data);
        data = get_dateAndTime(match, data);
        data = get_status(match, data);
        data = get_competitors(match, data);
        data = get_map_coordinates(match, data);

        return data;
    }


    public Match get_id(JSONObject match, Match data)
    {
        try {
            String id = (String) match.get("id");
            data.setId(id);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;

    }
    public Match get_dateAndTime(JSONObject match, Match data)
    {
        try {
            String scheduled = (String) match.get("scheduled");
            String[] date = scheduled.split("T");
            String[] dateModif = date[0].split("-");
            String dateFinal = dateModif[0]+"/"+dateModif[1]+"/"+dateModif[2];

            SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");

            Date dateObj = df1.parse(dateFinal,new ParsePosition(0));

            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");

            data.setDate(df2.format(dateObj));

            String[] time = date[1].split("\\+");
            String timeFinal = time[0];
            data.setTime(timeFinal);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;

    }
    public Match get_status(JSONObject match, Match data)
    {
        try {
            String status = (String) match.get("status");
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
            JSONArray competitors = (JSONArray) match.get("competitors");
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
    public Match get_map_coordinates(JSONObject match, Match data)
    {
        try {
            JSONObject venue = (JSONObject) match.get("venue");
            String map_coordinates = (String) venue.get("map_coordinates");
            data.setMap_coordinates(map_coordinates);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;

    }

}