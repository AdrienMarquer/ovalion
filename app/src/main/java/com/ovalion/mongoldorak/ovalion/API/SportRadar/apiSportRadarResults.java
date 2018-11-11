package com.ovalion.mongoldorak.ovalion.API.SportRadar;

import android.os.AsyncTask;

import com.ovalion.mongoldorak.ovalion.Activities.Dialogs.ClassementDialog;
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

public class apiSportRadarResults extends AsyncTask<Void, Void, List<Competitor>> {

    ClassementDialog frag;

    public apiSportRadarResults(ClassementDialog frag){
        this.frag = frag;
    }

    @Override
    protected List<Competitor> doInBackground(Void... voids) {
        return request();
    }

    @Override
    protected void onPostExecute(List<Competitor> compet){
        frag.setClassement(compet);
    }

    public List<Competitor> request()  {
        try {

            String  urlString = "https://api.sportradar.us/rugby/trial/v2/union/en/seasons/sr:season:55291/live_standings.json?api_key=2pe692a3456vwezx8wyxrf8x";


            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);


            String res = responseStrBuilder.toString();
            // System.out.println(res);
            JSONObject obj = new JSONObject(res);
            JSONArray list_rank = obj.getJSONArray("standings");

            JSONObject list_group = (JSONObject) list_rank.get(0);
            // System.out.println(list_group);
            JSONArray list_group2 = list_group.getJSONArray("groups");
            //System.out.println(list_group2);
            JSONObject obj_teamStanding = (JSONObject) list_group2.get(0);
            //System.out.println(obj_teamStanding);
            JSONArray list_teamStanding = obj_teamStanding.getJSONArray("team_standings");
            // System.out.println(list_teamStanding);


            System.out.println("number of team: " + list_teamStanding.length());
            List<Competitor> list = new ArrayList<Competitor>();
            for (int i = 0; i < list_teamStanding.length(); i++) {
                Competitor data = new Competitor();
                data = build_competitor(list_teamStanding.getJSONObject(i), data);
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
    public Competitor build_competitor(JSONObject competitor, Competitor data) {
        data = get_id(competitor, data);
        data = get_name(competitor, data);
        data = get_rank(competitor, data);

        return data;
    }


    public Competitor get_id(JSONObject competitor, Competitor data)
    {
        try {
            JSONObject team = (JSONObject) competitor.get("team");
            String id = team.getString("id");
            data.setId(id);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;

    }
    public Competitor get_name(JSONObject competitor, Competitor data)
    {
        try {
            JSONObject team = (JSONObject) competitor.get("team");
            String name = team.getString("name");
            data.setName(name);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;

    }
    public Competitor get_rank(JSONObject competitor, Competitor data)
    {
        try {
            int rank = (int) competitor.get("rank");
            data.setRank(rank);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;

    }


}