package com.ovalion.mongoldorak.ovalion.Adapters;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ovalion.mongoldorak.ovalion.Activities.Dialogs.MatchDialog;
import com.ovalion.mongoldorak.ovalion.Activities.MainActivity;
import com.ovalion.mongoldorak.ovalion.Models.Match;
import com.ovalion.mongoldorak.ovalion.R;


public class ListCalendarAdapter extends RecyclerView.Adapter<ListCalendarAdapter.ListCalendarViewHolder> {

    private List<Match> outputlist;
    private final Context context;
    private final LayoutInflater inflater;
    Dialog myDialog;

    public ListCalendarAdapter(Context context, List<Match> matchs) {
        this.outputlist = matchs;
        this.context = context;
        myDialog = new Dialog(context);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.list_calendar_item,viewGroup,false);
        ListCalendarViewHolder holder = new ListCalendarViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder( ListCalendarViewHolder viewHolder, int position) {

        viewHolder.dateCalendar.setText(outputlist.get(position).getDate());
        viewHolder.heureCalendar.setText(outputlist.get(position).getTime());
        viewHolder.homeCalendar.setText(outputlist.get(position).getCompetitorA().getName());
        viewHolder.awayCalendar.setText(outputlist.get(position).getCompetitorB().getName());

        //set on click listener for each element
        viewHolder.container.setOnClickListener(onClickListener(position));

    }

    @Override
    public int getItemCount() {
        return outputlist.size();
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchDialog matchDialog = new MatchDialog(context, outputlist.get(position));
                matchDialog.show();
            }
        };
    }

    public class ListCalendarViewHolder extends RecyclerView.ViewHolder
    {
        private TextView dateCalendar;
        private TextView heureCalendar;
        private TextView homeCalendar;
        private TextView awayCalendar;
        private View container;

        public ListCalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            dateCalendar = (TextView) itemView.findViewById(R.id.dateCalendar);
            heureCalendar = (TextView) itemView.findViewById(R.id.heureCalendar);
            homeCalendar = (TextView) itemView.findViewById(R.id.homeCalendar);
            awayCalendar = (TextView) itemView.findViewById(R.id.awayCalendar);
            container = itemView.findViewById(R.id.card_view);
        }
    }
}
