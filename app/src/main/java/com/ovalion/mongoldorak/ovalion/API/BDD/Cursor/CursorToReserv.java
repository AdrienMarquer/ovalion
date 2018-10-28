package com.ovalion.mongoldorak.ovalion.API.BDD.Cursor;

import android.database.Cursor;

import com.ovalion.mongoldorak.ovalion.Models.Reservation;
import com.ovalion.mongoldorak.ovalion.Models.ReservationBDD;

import java.util.ArrayList;
import java.util.List;

public class CursorToReserv {

    private static final int NUM_COL_ID = 0;

    //TASK TABLE
    private static final int NUM_COL_HOME_TEAM = 1;
    private static final int NUM_COL_AWAY_TEAM = 2;
    private static final int NUM_COL_LOCATION = 3;
    private static final int NUM_COL_BUS_GO = 4;
    private static final int NUM_COL_BUS_GO_ID = 5;
    private static final int NUM_COL_BUS_GO_GPS = 6;
    private static final int NUM_COL_BUS_BACK = 7;
    private static final int NUM_COL_BUS_BACK_ID = 8;
    private static final int NUM_COL_BUS_BACK_GPS = 9;
    private static final int NUM_COL_HOSTEL = 10;

    public static ReservationBDD toReserv (Cursor cursor){
        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();

        ReservationBDD reserv = new ReservationBDD();

        reserv.setId(cursor.getInt(NUM_COL_ID));
        reserv.setHome_team(cursor.getString(NUM_COL_HOME_TEAM));
        reserv.setAway_team(cursor.getString(NUM_COL_AWAY_TEAM));
        reserv.setLocation(cursor.getString(NUM_COL_LOCATION));
        reserv.setDeparture(cursor.getString(NUM_COL_BUS_GO));
        reserv.setDeparture_id(cursor.getString(NUM_COL_BUS_GO_ID));
        reserv.setDeparture_gps(cursor.getString(NUM_COL_BUS_GO_GPS));
        reserv.setReturn_(cursor.getString(NUM_COL_BUS_BACK));
        reserv.setReturn_id(cursor.getString(NUM_COL_BUS_BACK_ID));
        reserv.setReturn_gps(cursor.getString(NUM_COL_BUS_BACK_GPS));
        reserv.setHostel(cursor.getString(NUM_COL_HOSTEL));

        cursor.close();

        return reserv;
    }

    public static List<ReservationBDD> toReservations (Cursor cursor){
        if (cursor.getCount() == 0) return new ArrayList<>();

        cursor.moveToFirst();

        List<ReservationBDD> reservs = new ArrayList<>();

        cursor.moveToFirst();
        while ( ! cursor.isAfterLast()){
            //Cursor to task
            ReservationBDD reserv = new ReservationBDD();

            reserv.setId(cursor.getInt(NUM_COL_ID));
            reserv.setHome_team(cursor.getString(NUM_COL_HOME_TEAM));
            reserv.setAway_team(cursor.getString(NUM_COL_AWAY_TEAM));
            reserv.setLocation(cursor.getString(NUM_COL_LOCATION));
            reserv.setDeparture(cursor.getString(NUM_COL_BUS_GO));
            reserv.setDeparture_id(cursor.getString(NUM_COL_BUS_GO_ID));
            reserv.setDeparture_gps(cursor.getString(NUM_COL_BUS_GO_GPS));
            reserv.setReturn_(cursor.getString(NUM_COL_BUS_BACK));
            reserv.setReturn_id(cursor.getString(NUM_COL_BUS_BACK_ID));
            reserv.setReturn_gps(cursor.getString(NUM_COL_BUS_BACK_GPS));
            reserv.setHostel(cursor.getString(NUM_COL_HOSTEL));

            reservs.add(reserv);
            cursor.moveToNext();
        }
        cursor.close();

        return reservs;
    }
}
