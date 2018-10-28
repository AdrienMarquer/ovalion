package com.ovalion.mongoldorak.ovalion.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ovalion.mongoldorak.ovalion.Models.Match;
import com.ovalion.mongoldorak.ovalion.Models.Reservation;
import com.ovalion.mongoldorak.ovalion.R;

public class ReservationActivity extends AppCompatActivity
{
    private TextView homeReservationAct;
    private TextView awayReservationAct;
    private RadioGroup reserv_radio_trip;
    private RadioGroup reserv_radio_ticket;
    private Button reserv_cancel;
    private Button reserv_validate;

    private Match match;
    private Reservation reserv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        homeReservationAct = (TextView) findViewById(R.id.homeReservationAct);
        awayReservationAct = (TextView) findViewById(R.id.awayReservationAct);
        reserv_radio_trip = (RadioGroup) findViewById(R.id.reserv_radio_trip);
        reserv_radio_ticket = (RadioGroup) findViewById(R.id.reserv_radio_ticket);
        reserv_cancel = (Button) findViewById(R.id.reserv_cancel);
        reserv_validate = (Button) findViewById(R.id.reserv_validate);

        match = (Match) getIntent().getParcelableExtra("Match");

        homeReservationAct.setText(match.getCompetitorA().getName());
        awayReservationAct.setText(match.getCompetitorB().getName());

        reserv_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservclick();
            }
        });

        reserv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelClick();
            }
        });
    }

    private void reservclick(){
        String bustripType = "simple";
        String ticketType = "deluxe";

        switch (reserv_radio_trip.getCheckedRadioButtonId())
        {
            case R.id.reserv_radioBus:
                bustripType = "simple";
                break;
            case R.id.reserv_radioBusHotel:
                bustripType = "deluxe";
                break;
        }

        switch (reserv_radio_ticket.getCheckedRadioButtonId())
        {
            case R.id.reserv_radioTicketVIP:
                ticketType = "vip";
                break;
            case R.id.reserv_radioTicketFront:
                ticketType = "front";
                break;
            case R.id.reserv_radioTicketTurn:
                ticketType = "turn";
                break;
        }

        //Ajouter les appels a airbnb et blablacar
        reserv = new Reservation(match,bustripType,ticketType);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("Reservation",match);
        this.startActivity(intent);
    }

    private void cancelClick(){
        finish();
    }
}