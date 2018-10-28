package com.ovalion.mongoldorak.ovalion.API.BlaBlaCar;

import com.ovalion.mongoldorak.ovalion.Models.BusTrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class BlaBlaCarAPI
{
        public List<BusTrip> request(String departure, String arrival, String date) {
            try {

                String urlString = "https://public-api.blablacar.com/api/v2/trips?key=2abd279232e54223bf2fe93793436c1f&fc=" + departure + "&tc=" + arrival + "&db=" + date + "&cur=EUR";
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

                JSONArray list_trips = obj.getJSONArray("trips");

                System.out.println("number of trip: " + list_trips.length());
                List<BusTrip> list = new ArrayList<BusTrip>();
                for(int i = 0 ; i < list_trips.length() ; i++){
                    BusTrip data = new BusTrip();
                    data = build_trip(list_trips.getJSONObject(i), data);
                    list.add(data);
                }
                return list;
            }
            catch(MalformedURLException e) {
                e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
            catch(JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public BusTrip build_trip(JSONObject trip, BusTrip data) {
            data = get_departure(trip, data);
            data = get_arrival(trip, data);
            data = get_date(trip, data);
            data = get_cost(trip, data);
            data = get_distance(trip, data);
            return data;
        }
        public BusTrip get_departure(JSONObject trip, BusTrip data) {
            try {
                JSONObject departure = trip.getJSONObject("departure_place");
                data.setDeparture_adress(departure.get("address") + " " + departure.get("city_name") + "\n");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return data;
        }
        public BusTrip get_arrival(JSONObject trip, BusTrip data) {
            try {
                JSONObject arrival= trip.getJSONObject("arrival_place");
                data.setArrival_adress(arrival.get("address") + " " + arrival.get("city_name") + "\n");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return data;
        }
        public BusTrip get_date(JSONObject trip, BusTrip data) {
            try {
                Object string_value = trip.get("departure_date") + "\n";
                data.setDate(string_value.toString());
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return data;
        }
        public BusTrip get_cost(JSONObject trip, BusTrip data) {
            try {
                JSONObject price = trip.getJSONObject("price");
                Object string_value = price.get("string_value") + "\n";
                data.setCost(string_value.toString());

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return data;
        }
        public BusTrip get_distance(JSONObject trip, BusTrip data) {
            try {
                JSONObject distance = trip.getJSONObject("distance");
                Object string_value = distance.get("value");
                Object string_value1 = distance.get("unity");
                data.setDistance( string_value + " " + string_value1 + "\n" );
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return data;
        }
    }

