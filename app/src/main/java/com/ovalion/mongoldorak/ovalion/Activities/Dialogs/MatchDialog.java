package com.ovalion.mongoldorak.ovalion.Activities.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ovalion.mongoldorak.ovalion.Activities.FirstLaunchActivity;
import com.ovalion.mongoldorak.ovalion.Activities.MainActivity;
import com.ovalion.mongoldorak.ovalion.Activities.ReservationActivity;
import com.ovalion.mongoldorak.ovalion.Models.Match;
import com.ovalion.mongoldorak.ovalion.Models.TeamsEnum;
import com.ovalion.mongoldorak.ovalion.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class MatchDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Context context;
    private Match match;

    TextView txtclose;
    Button btnReserv;
    TextView homeTeamDialog;
    TextView awayTeamDialog;
    TextView homeScoreDialog;
    TextView awayScoreDialog;
    TextView dateDialog;
    TextView heureDialog;
    TextView locationDialog;
    TextView dialogtiret;
    ImageView homeImageDialog;
    ImageView awayImageDialog;


    public MatchDialog(Context context, Match match) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.match = match;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.match_dialog);

        txtclose = (TextView) findViewById(R.id.closeMatchDialog);
        btnReserv = (Button) findViewById(R.id.btnReserv);
        homeTeamDialog = (TextView) findViewById(R.id.homeTeamDialog);
        awayTeamDialog = (TextView) findViewById(R.id.awayTeamDialog);
        homeScoreDialog = (TextView) findViewById(R.id.homeScoreDialog);
        awayScoreDialog = (TextView) findViewById(R.id.awayScoreDialog);
        dialogtiret = (TextView) findViewById(R.id.dialogtiret);
        dateDialog = (TextView) findViewById(R.id.dateDialog);
        heureDialog = (TextView) findViewById(R.id.heureDialog);
        locationDialog = (TextView) findViewById(R.id.locationDialog);
        homeImageDialog = (ImageView) findViewById(R.id.homeImageDialog);
        awayImageDialog = (ImageView) findViewById(R.id.awayImageDialog);

        homeTeamDialog.setText(match.getCompetitorA().getName());
        awayTeamDialog.setText(match.getCompetitorB().getName());
        dateDialog.setText(match.getDate());
        heureDialog.setText(match.getTime());

        Resources res = context.getResources();
        int homeID = res.getIdentifier(TeamsEnum.getAbrevById(match.getCompetitorA().getId()) , "drawable", context.getPackageName());
        int awayID = res.getIdentifier(TeamsEnum.getAbrevById(match.getCompetitorB().getId()) , "drawable", context.getPackageName());
        homeImageDialog.setImageResource(homeID);
        awayImageDialog.setImageResource(awayID);

        String matchDate =  match.getDate();

        if(!onMonthCheck(matchDate)){
            btnReserv.setVisibility(View.GONE);
        }else{
            Toast.makeText(context,context.getResources().getString(R.string.reservavailable),Toast.LENGTH_SHORT);
        }

        if(match.getStatus()){

        }else{
            awayScoreDialog.setVisibility(View.GONE);
            homeScoreDialog.setVisibility(View.GONE);
            dialogtiret.setVisibility(View.GONE);
        }

        locationDialog.setText(TeamsEnum.getLocationbyId(match.getCompetitorA().getId()));

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnReserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReservationActivity.class);
                intent.putExtra("Match",match);
                context.startActivity(intent);

            }
        });

    }

    //La réservation est disponible uniquement un mois à l'avance
    //Si la méthode renvoie true, on peut réserver
    private boolean onMonthCheck(String date){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date current = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+1));
        current = cal.getTime();

        Date dateB = df.parse(date, new ParsePosition(0));

        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)-1));

        if (current.compareTo(dateB) <= 0) {
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void onClick(View v) {

    }
}
