package com.ovalion.mongoldorak.ovalion.Activities.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ovalion.mongoldorak.ovalion.Activities.MainActivity;
import com.ovalion.mongoldorak.ovalion.Models.TeamsEnum;
import com.ovalion.mongoldorak.ovalion.R;

import static android.content.Context.MODE_PRIVATE;

public class ParamFragment extends Fragment implements View.OnClickListener
{
    TeamsEnum team;
    String lang;
    String lang_choose;
    boolean all_team;
    boolean all_team_choose;

    Spinner mSpinner;
    private RadioGroup radioLangGroup;
    private RadioButton radioFr;
    private RadioButton radioEng;
    private Button button;
    private RadioGroup param_all_team;
    ArrayAdapter<TeamsEnum> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_param, container, false);

        radioLangGroup = (RadioGroup) view.findViewById(R.id.param_radio_lang_groupe);
        radioFr = (RadioButton) view.findViewById(R.id.param_radioFr);
        radioEng = (RadioButton) view.findViewById(R.id.param_radioEng);
        mSpinner = (Spinner) view.findViewById(R.id.param_team_spinner);
        button =  (Button) view.findViewById(R.id.param_validate);
        param_all_team = (RadioGroup) view.findViewById(R.id.param_all_team);


        adapter = new ArrayAdapter<TeamsEnum>(view.getContext(),android.R.layout.simple_list_item_1, TeamsEnum.values());
        mSpinner.setAdapter(adapter);

        setSpinnerAndRadio();

        button.setOnClickListener(this);

        return view;
    }

    public void setSpinnerAndRadio()
    {
        //Setting spinner
        team = TeamsEnum.fromId(this.getActivity().getSharedPreferences("userInfo", MODE_PRIVATE)
                .getString("team","All"));

        int spinnerPosition = adapter.getPosition(team);
        mSpinner.setSelection(spinnerPosition);

        //Setting radio group
        lang = this.getActivity().getSharedPreferences("userInfo", MODE_PRIVATE)
                .getString("lang","fr");

        switch (lang)
        {
            case "fr":
                radioLangGroup.check(R.id.param_radioFr);
                break;
            case "en":
                radioLangGroup.check(R.id.param_radioEng);
                break;
        }

        all_team = this.getActivity().getSharedPreferences("userInfo", MODE_PRIVATE)
                .getBoolean("all_team",false);

        if (all_team) {
            param_all_team.check(R.id.param_all_yes);
        }else {
            param_all_team.check(R.id.param_all_no);
        }
    }

    @Override
    public void onClick(View v)
    {
        int radioButtonId_lang = radioLangGroup.getCheckedRadioButtonId();
        int radioButtonId_all_team = param_all_team.getCheckedRadioButtonId();

        switch (radioButtonId_lang)
        {
            case R.id.param_radioFr:
                lang_choose = "fr";
                break;
            case R.id.param_radioEng:
                lang_choose = "en";
                break;
        }

        switch (radioButtonId_all_team)
        {
            case R.id.param_all_yes:
                all_team_choose = true;
                break;
            case R.id.param_all_no:
                all_team_choose = false;
                break;
        }

        this.getActivity().getSharedPreferences("userInfo", MODE_PRIVATE).edit()
                .putString("team",(((TeamsEnum) mSpinner.getSelectedItem()).getId())).apply();
        this.getActivity().getSharedPreferences("userInfo", MODE_PRIVATE).edit()
                .putString("lang",lang_choose).apply();
        this.getActivity().getSharedPreferences("userInfo", MODE_PRIVATE).edit()
                .putBoolean("all_team",all_team_choose).apply();

        ((MainActivity)this.getActivity()).setLocale(lang_choose);

        switch (lang_choose)
        {
            case "fr":
                Toast.makeText(this.getActivity(),"Paramètres sauvegardés",Toast.LENGTH_SHORT).show();
                break;
            case "en":
                Toast.makeText(this.getActivity(),"Settings saved",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
