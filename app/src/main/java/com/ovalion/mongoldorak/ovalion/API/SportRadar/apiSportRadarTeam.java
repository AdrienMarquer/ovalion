package com.ovalion.mongoldorak.ovalion.API.SportRadar;

import com.ovalion.mongoldorak.ovalion.Models.Competitor;

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

public class apiSportRadarTeam {
    public List<Competitor> request() {
        try {
            String urlString = "https://api.sportradar.us/rugby/trial/v2/union/en/seasons/sr:season:55291/info.json?api_key=vcz9st3jfwmkqamhbwv7pvyv";
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);


            String res = responseStrBuilder.toString();
            //System.out.println(res);
            JSONObject obj = new JSONObject(res);
            JSONArray list_stages = obj.getJSONArray("stages");
            //   System.out.println(list_stages);
            JSONObject list_group = (JSONObject) list_stages.get(0);
            // System.out.println(list_group);
            JSONArray list_group2 = (JSONArray) list_group.get("groups");
            // System.out.println(list_group2);
            JSONObject list_competitors = (JSONObject) list_group2.get(0);
            // System.out.println(list_competitors);
            JSONArray list_competitors2 = (JSONArray) list_competitors.get("competitors");
            // System.out.println(list_competitors2);

            // System.out.println("number of trip: " + list_competitor.length());
            List<Competitor> list = new ArrayList<Competitor>();
            for (int i = 0; i < list_competitors2.length(); i++) {
                Competitor data = new Competitor();
                data = build_competitor(list_competitors2.getJSONObject(i), data);
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
        data = get_abbreviation(competitor, data);
        return data;
    }

    public Competitor get_id(JSONObject competitor, Competitor data)
    {
        try {
            String id = (String) competitor.get("id");
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
            String name = (String) competitor.get("name");
            data.setName(name);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;

    }

    public Competitor get_abbreviation(JSONObject competitor, Competitor data)
    {
        try {
            String abbreviation = (String) competitor.get("abbreviation");
            data.setAbbreviation(abbreviation);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return data;

    }
}