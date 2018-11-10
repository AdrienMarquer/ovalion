package com.ovalion.mongoldorak.ovalion.API.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ovalion.mongoldorak.ovalion.API.BDD.Cursor.CursorToReserv;
import com.ovalion.mongoldorak.ovalion.Models.Reservation;
import com.ovalion.mongoldorak.ovalion.Models.ReservationBDD;
import com.ovalion.mongoldorak.ovalion.R;

import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    //Logcathelper
    private static final String LOG = "DATABASE";

    //Database infos
    private static final String DATABASE_NAME = "Ovalion.db";
    private static final int DATABASE_VERSION = 3;

    //Tables names
    private static final String TABLE_RESERV = "table_reserv";

    //Common column
    private static final String COL_ID = "ID";

    //TASK TABLE
    private static final String COL_HOME_TEAM = "home_team";
    private static final String COL_AWAY_TEAM = "away_team";
    private static final String COL_LOCATION = "location";
    private static final String COL_BUS_GO = "departure";
    private static final String COL_BUS_GO_ID = "departure_id";
    private static final String COL_BUS_GO_GPS = "departure_gps";
    private static final String COL_BUS_BACK = "return";
    private static final String COL_BUS_BACK_ID = "return_id";
    private static final String COL_BUS_BACK_GPS = "return_gps";
    private static final String COL_HOSTEL = "hostel";
    private static final String COL_DATE = "date";
    private static final String COL_HOUR = "hour";
    private static final String COL_PRICE = "price";


    private static final String CREATE_RESERVBDD = "CREATE TABLE " + TABLE_RESERV + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_HOME_TEAM + " TEXT , " + COL_AWAY_TEAM + " TEXT , "
            + COL_LOCATION + " TEXT , " + COL_BUS_GO + " TEXT , " + COL_BUS_GO_ID + " TEXT ," + COL_BUS_GO_GPS + " TEXT ,"
            + COL_BUS_BACK + " TEXT , " + COL_BUS_BACK_ID + " TEXT , " + COL_BUS_BACK_GPS + " TEXT , " + COL_HOSTEL + " TEXT, "
            + COL_DATE + " TEXT, "+ COL_HOUR + " TEXT, " + COL_PRICE + " REAL);";

    private final Context context;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_RESERVBDD);
        Log.i(LOG, "onCreate called");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_RESERV + ";");

        onCreate(db);
    }

    //INSERTTS
    public void insertReservation (Reservation reserv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_HOME_TEAM, reserv.getMatch().getCompetitorA().getName());
        values.put(COL_AWAY_TEAM, reserv.getMatch().getCompetitorB().getName());
        values.put(COL_LOCATION, reserv.getLocation());
        if(reserv.getBusGo() != null){
            values.put(COL_BUS_GO, reserv.getBusGo().getDeparture_adress());
            values.put(COL_BUS_GO_ID, reserv.getBusGo().getId());
            values.put(COL_BUS_GO_GPS, reserv.getBusGo().getDeparture_gps());
        }else{
            values.put(COL_BUS_GO, context.getResources().getString(R.string.nodisp));
            values.put(COL_BUS_GO_ID, context.getResources().getString(R.string.nodisp));
            values.put(COL_BUS_GO_GPS, context.getResources().getString(R.string.nodisp));
        }

        if(reserv.getBusBack() != null){
            values.put(COL_BUS_BACK, reserv.getBusBack().getDeparture_adress());
            values.put(COL_BUS_BACK_ID, reserv.getBusBack().getId());
            values.put(COL_BUS_BACK_GPS, reserv.getBusBack().getDeparture_gps());
        }else{
            values.put(COL_BUS_BACK, context.getResources().getString(R.string.nodisp));
            values.put(COL_BUS_BACK_ID, context.getResources().getString(R.string.nodisp));
            values.put(COL_BUS_BACK_GPS, context.getResources().getString(R.string.nodisp));
        }

        values.put(COL_HOSTEL, reserv.getHostel());
        values.put(COL_DATE, reserv.getMatch().getDate());
        values.put(COL_HOUR, reserv.getMatch().getTime());
        values.put(COL_PRICE, reserv.getPrice());

        db.insert(TABLE_RESERV, null, values);

        Log.i(LOG,"insterReserv called");
    }

    public List<ReservationBDD> getReservs(){
        List<ReservationBDD> reservs;

        String strSql = "SELECT * FROM " + TABLE_RESERV;
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);

        reservs = CursorToReserv.toReservations(cursor);

        return reservs;
    }

    //DELETE
    public void deleteTask(int id){
        String strSql = "DELETE FROM " + TABLE_RESERV + " WHERE " + COL_ID + " = " + id;
        this.getWritableDatabase().execSQL(strSql);
    }


}