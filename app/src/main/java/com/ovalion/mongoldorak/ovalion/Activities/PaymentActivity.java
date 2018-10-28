package com.ovalion.mongoldorak.ovalion.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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

        paymment_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelClick();
            }
        });

    }

    private void cancelClick(){
        finish();
    }

}
