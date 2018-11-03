package com.ovalion.mongoldorak.ovalion.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ovalion.mongoldorak.ovalion.Models.CreditCard;
import com.ovalion.mongoldorak.ovalion.Models.Reservation;
import com.ovalion.mongoldorak.ovalion.Models.TeamsEnum;
import com.ovalion.mongoldorak.ovalion.R;

public class PaymentActivity extends AppCompatActivity {

    private String[] years = new String[]{"2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032"};
    private String[] months = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};

    private Button paymment_validate;
    private Button paymment_cancel;
    private TextView payment_info_depart;
    private TextView payment_info_return;
    private TextView payment_info_hotel;
    private RadioGroup payment_card_type;
    private EditText payment_card_number;
    private EditText payment_card_crypto;
    private Spinner payment_exp_month;
    private Spinner payment_exp_year;

    private Reservation reserv;
    private CreditCard cb = new CreditCard();
    private boolean checkingOk = true;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        paymment_validate = (Button) findViewById(R.id.paymment_validate);
        paymment_cancel = (Button) findViewById(R.id.payment_cancel);
        payment_info_depart = (TextView) findViewById(R.id.payment_info_depart);
        payment_info_return = (TextView) findViewById(R.id.payment_info_return);
        payment_info_hotel = (TextView) findViewById(R.id.payment_info_hotel);
        payment_card_type = (RadioGroup) findViewById(R.id.payment_card_type);
        payment_card_number = (EditText) findViewById(R.id.payment_card_number);
        payment_card_crypto = (EditText) findViewById(R.id.payment_card_crypto);
        payment_exp_month = (Spinner) findViewById(R.id.payment_exp_month);
        payment_exp_year = (Spinner) findViewById(R.id.payment_exp_year);

        payment_exp_month.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, months));
        payment_exp_year.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, years));

        Intent i = getIntent();
        reserv = (Reservation) i.getSerializableExtra("Reservation");

        if(reserv.getBusGo() != null){
            payment_info_depart.setText(reserv.getBusGo().getDeparture_adress());
        }

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
            cb.setNumber(payment_card_number.getText().toString());
        }else{
            payment_card_number.setBackgroundColor(Color.red(255));
            checkingOk = false;
        }

        if(payment_card_crypto.getText().toString() != null && !payment_card_crypto.getText().toString().isEmpty()
                && payment_card_crypto.getText().length() == 3){
            cb.setCrypto(payment_card_crypto.getText().toString());
        }else{
            payment_card_crypto.setBackgroundColor(Color.red(255));
            checkingOk = false;
        }

        exp = payment_exp_month.toString() + "/" + payment_exp_year.toString();
        cb.setExpiration(exp);

        reserv.setCb(cb);

        if(checkingOk){

            //add bdd
            Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show();
        }else{
            checkingOk = true;
        }
    }


    private void cancelClick(){
        finish();
    }

}
