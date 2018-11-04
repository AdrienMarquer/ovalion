package com.ovalion.mongoldorak.ovalion.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ovalion.mongoldorak.ovalion.API.BDD.DatabaseManager;
import com.ovalion.mongoldorak.ovalion.Activities.Fragments.CalendarFragment;
import com.ovalion.mongoldorak.ovalion.Activities.Fragments.TripFragment;
import com.ovalion.mongoldorak.ovalion.Models.CreditCard;
import com.ovalion.mongoldorak.ovalion.Models.Reservation;
import com.ovalion.mongoldorak.ovalion.Models.TeamsEnum;
import com.ovalion.mongoldorak.ovalion.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PaymentActivity extends AppCompatActivity {

    private String[] years = new String[]{"2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032"};
    private String[] months = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};

    private Button paymment_validate;
    private Button paymment_cancel;
    private TextView payment_info_depart_1;
    private TextView payment_info_depart_2;
    private TextView payment_info_return_1;
    private TextView payment_info_return_2;
    private TextView payment_info_hotel;
    private TextView payment_crypto_text;
    private TextView payment_card_text;
    private RadioGroup payment_card_type;
    private EditText payment_card_number;
    private EditText payment_card_crypto;
    private Spinner payment_exp_month;
    private Spinner payment_exp_year;
    private TextView payment_card_exp;

    private Reservation reserv;
    private CreditCard cb = new CreditCard();
    private boolean checkingOk = true;
    private DatabaseManager db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        db = new DatabaseManager(this);

        paymment_validate = (Button) findViewById(R.id.paymment_validate);
        paymment_cancel = (Button) findViewById(R.id.payment_cancel);
        payment_info_depart_1 = (TextView) findViewById(R.id.payment_info_depart_1);
        payment_info_depart_2 = (TextView) findViewById(R.id.payment_info_depart_2);
        payment_info_return_1 = (TextView) findViewById(R.id.payment_info_return_1);
        payment_info_return_2 = (TextView) findViewById(R.id.payment_info_return_2);
        payment_info_hotel = (TextView) findViewById(R.id.payment_info_hotel);
        payment_crypto_text = (TextView) findViewById(R.id.payment_crypto_text);
        payment_card_text = (TextView) findViewById(R.id.payment_card_text);
        payment_card_type = (RadioGroup) findViewById(R.id.payment_card_type);
        payment_card_number = (EditText) findViewById(R.id.payment_card_number);
        payment_card_crypto = (EditText) findViewById(R.id.payment_card_crypto);
        payment_card_exp = (TextView) findViewById(R.id.payment_card_exp);
        payment_exp_month = (Spinner) findViewById(R.id.payment_exp_month);
        payment_exp_year = (Spinner) findViewById(R.id.payment_exp_year);

        payment_exp_month.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, months));
        payment_exp_year.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, years));

        Bundle data = getIntent().getExtras();
        reserv = (Reservation) data.getParcelable("Reservation");

        if(reserv.getBusGo() != null){
            payment_info_depart_1.setText(reserv.getBusGo().getDeparture_adress());
            payment_info_depart_2.setText(reserv.getBusGo().getArrival_adress());
        }

        if(reserv.getBusBack() != null){
            payment_info_return_1.setText(reserv.getBusBack().getDeparture_adress());
            payment_info_return_2.setText(reserv.getBusBack().getArrival_adress());
        }

        payment_info_hotel.setText(reserv.getHostel());

        paymment_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateClick();
            }
        });
        paymment_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelClick();
            }
        });

    }

    private void validateClick(){
        String exp;
        //check
        switch (payment_card_type.getCheckedRadioButtonId())
        {
            case R.id.payment_visa:
                cb.setType("visa");
                break;
            case R.id.payment_mastercard:
                cb.setType("mastercard");
                break;
        }

        if(payment_card_number.getText() != null && !payment_card_number.getText().toString().isEmpty()){
            payment_card_text.setTextColor(Color.WHITE);
            cb.setNumber(payment_card_number.getText().toString());
        }else{
            payment_card_text.setTextColor(Color.RED);
            checkingOk = false;
        }

        if(payment_card_crypto.getText().toString() != null && !payment_card_crypto.getText().toString().isEmpty()
                && payment_card_crypto.getText().length() == 3){
            payment_crypto_text.setTextColor(Color.WHITE);
            cb.setCrypto(payment_card_crypto.getText().toString());
        }else{
            payment_crypto_text.setTextColor(Color.RED);
            checkingOk = false;
        }

        exp = payment_exp_month.getSelectedItem().toString() + "/" + payment_exp_year.getSelectedItem().toString();
        if(isExpirationOk(exp)){
            payment_card_exp.setTextColor(Color.WHITE);
            cb.setExpiration(exp);
        }else{
            payment_card_exp.setTextColor(Color.RED);
            checkingOk = false;
        }

        reserv.setCb(cb);

        if(checkingOk){

            db.insertReservation(reserv);
            Toast.makeText(this,getResources().getString(R.string.payvalid),Toast.LENGTH_SHORT).show();

            finish();
        }else{
            checkingOk = true;
        }
    }


    private void cancelClick(){
        finish();
    }

    private boolean isExpirationOk(String date)
    {
        SimpleDateFormat df = new SimpleDateFormat("MM/yyyy");

        Date current = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        current = cal.getTime();

        Date dateB = df.parse(date, new ParsePosition(0));

        if (current.compareTo(dateB) <= 0) {
            return true;
        }else{
            return false;
        }
    }

}
