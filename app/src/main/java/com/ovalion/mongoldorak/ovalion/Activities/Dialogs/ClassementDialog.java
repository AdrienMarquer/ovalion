package com.ovalion.mongoldorak.ovalion.Activities.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ovalion.mongoldorak.ovalion.API.SportRadar.apiSportRadarResults;
import com.ovalion.mongoldorak.ovalion.Activities.ReservationActivity;
import com.ovalion.mongoldorak.ovalion.Models.Competitor;
import com.ovalion.mongoldorak.ovalion.Models.Match;
import com.ovalion.mongoldorak.ovalion.Models.TeamsEnum;
import com.ovalion.mongoldorak.ovalion.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ClassementDialog extends Dialog implements
        View.OnClickListener {

    private Context context;
    private List<Competitor> compet;

    TextView closeClassementDialog;
    TextView classement_un;
    TextView classement_deux;
    TextView classement_trois;
    TextView classement_quatre;
    TextView classement_cinq;
    TextView classement_six;
    TextView classement_sept;
    TextView classement_huit;
    TextView classement_neuf;
    TextView classement_dix;
    TextView classement_onze;
    TextView classement_douze;
    TextView classement_treize;
    TextView classement_quatorze;


    public ClassementDialog(Context context, List<Competitor> compet) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.compet = compet;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.classement_dialog);

        closeClassementDialog = (TextView) findViewById(R.id.closeClassementDialog);
        classement_un = (TextView) findViewById(R.id.classement_un);
        classement_deux = (TextView) findViewById(R.id.classement_deux);
        classement_trois = (TextView) findViewById(R.id.classement_trois);
        classement_quatre = (TextView) findViewById(R.id.classement_quatre);
        classement_cinq = (TextView) findViewById(R.id.classement_cinq);
        classement_six = (TextView) findViewById(R.id.classement_six);
        classement_sept = (TextView) findViewById(R.id.classement_sept);
        classement_huit = (TextView) findViewById(R.id.classement_huit);
        classement_neuf = (TextView) findViewById(R.id.classement_neuf);
        classement_dix = (TextView) findViewById(R.id.classement_dix);
        classement_onze = (TextView) findViewById(R.id.classement_onze);
        classement_douze = (TextView) findViewById(R.id.classement_douze);
        classement_treize = (TextView) findViewById(R.id.classement_treize);
        classement_quatorze = (TextView) findViewById(R.id.classement_quatorze);

        fireClassementTask();

        closeClassementDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public void setClassement(List<Competitor> compet) {
        this.compet = compet;
        if(compet != null){
            classement_un.setText(compet.get(0).getName());
            classement_deux.setText(compet.get(1).getName());
            classement_trois.setText(compet.get(2).getName());
            classement_quatre.setText(compet.get(3).getName());
            classement_cinq.setText(compet.get(4).getName());
            classement_six.setText(compet.get(5).getName());
            classement_sept.setText(compet.get(6).getName());
            classement_huit.setText(compet.get(7).getName());
            classement_neuf.setText(compet.get(8).getName());
            classement_dix.setText(compet.get(9).getName());
            classement_onze.setText(compet.get(10).getName());
            classement_douze.setText(compet.get(11).getName());
            classement_treize.setText(compet.get(12).getName());
            classement_quatorze.setText(compet.get(13).getName());
        }
    }

    private void fireClassementTask(){
        new apiSportRadarResults(this).execute();
    }
}
