package com.ovalion.mongoldorak.ovalion.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import java.util.Locale;

import com.ovalion.mongoldorak.ovalion.Models.TeamsEnum;
import com.ovalion.mongoldorak.ovalion.R;

import java.util.Locale;

public class FirstLaunchActivity extends AppCompatActivity
{
    Spinner mSpinner;
    private RadioGroup radioLangGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch);

        radioLangGroup = (RadioGroup) findViewById(R.id.first_radio_lang_groupe);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        mSpinner = (Spinner) findViewById(R.id.first_team_spinner);
        mSpinner.setAdapter(new ArrayAdapter<TeamsEnum>(this,android.R.layout.simple_list_item_1, TeamsEnum.values()));

    }

    public void onValidate(View v)
    {
        String lang = "fr";
        int radioButtonId = radioLangGroup.getCheckedRadioButtonId();

        switch (radioButtonId)
        {
            case R.id.first_radioFr:
                lang = "fr";
                break;
            case R.id.first_radioEng:
                lang = "en";
                break;
        }

        getSharedPreferences("userInfo", MODE_PRIVATE).edit()
                .putString("team",(((TeamsEnum) mSpinner.getSelectedItem()).getId())).apply();
        getSharedPreferences("userInfo", MODE_PRIVATE).edit()
                .putString("lang",lang).apply();
        getSharedPreferences("userInfo", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun",false).apply();
        setLocale(lang);
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }
}
