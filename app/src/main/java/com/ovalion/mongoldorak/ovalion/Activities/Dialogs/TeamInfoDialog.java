package com.ovalion.mongoldorak.ovalion.Activities.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.ovalion.mongoldorak.ovalion.API.SportRadar.apiSportRadarTeamInfo;
import com.ovalion.mongoldorak.ovalion.Models.Competitor;
import com.ovalion.mongoldorak.ovalion.Models.TeamInfo;
import com.ovalion.mongoldorak.ovalion.Models.TeamsEnum;
import com.ovalion.mongoldorak.ovalion.R;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class TeamInfoDialog extends Dialog implements
        View.OnClickListener {

    private Context context;
    private TeamInfo teaminfo;

    TextView closeTeamInfoDialog;
    TextView info_played;
    TextView info_won;
    TextView info_lost;
    TextView info_scored;
    TextView info_conceded;


    public TeamInfoDialog(Context context, TeamInfo info) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.teaminfo = info;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.team_info_dialog);

        closeTeamInfoDialog = (TextView) findViewById(R.id.closeTeamInfoDialog);
        info_played = (TextView) findViewById(R.id.info_played);
        info_won = (TextView) findViewById(R.id.info_won);
        info_lost = (TextView) findViewById(R.id.info_lost);
        info_scored = (TextView) findViewById(R.id.info_scored);
        info_conceded = (TextView) findViewById(R.id.info_conceded);

        fireInfoTask();

        closeTeamInfoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public void setInfo(TeamInfo teaminfo) {
        if(teaminfo != null){
            info_played.setText("" + teaminfo.getMatches_played());
            info_won.setText("" +teaminfo.getMatches_won());
            info_lost.setText(""+teaminfo.getMatches_lost());
            info_scored.setText(""+teaminfo.getPoints_scored());
            info_conceded.setText(""+teaminfo.getPoints_conceded());
        }
    }


    private void fireInfoTask(){
        String id = TeamsEnum.fromId(context.getSharedPreferences("userInfo", MODE_PRIVATE)
                .getString("team","sr:competitor:5758")).getId();
        new apiSportRadarTeamInfo(this).execute(id);
    }

    @Override
    public void onClick(View v) {

    }
}
