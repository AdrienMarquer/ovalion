package com.ovalion.mongoldorak.ovalion.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ovalion.mongoldorak.ovalion.Activities.Dialogs.MatchDialog;
import com.ovalion.mongoldorak.ovalion.Models.Match;
import com.ovalion.mongoldorak.ovalion.Models.Reservation;
import com.ovalion.mongoldorak.ovalion.R;

import java.util.List;

public class ListTripAdapter extends RecyclerView.Adapter<ListTripAdapter.ListTripViewHolder>{
    private List<Reservation> outputlist;
    private final Context context;
    private final LayoutInflater inflater;
    private int mExpandedPosition = -1;
    boolean isExpanded;


    public ListTripAdapter(Context context, List<Reservation> reservs) {
        this.outputlist = reservs;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListTripViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.list_trip_item,viewGroup,false);
        ListTripAdapter.ListTripViewHolder holder = new ListTripAdapter.ListTripViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ListTripViewHolder viewHolder,final int position) {

        final boolean isExpanded = position==mExpandedPosition;

        viewHolder.trip_info_location.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        viewHolder.trip_info_depart.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        viewHolder.trip_info_return.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        viewHolder.trip_info_hotel.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        viewHolder.trip_info_location_title.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        viewHolder.trip_info_depart_title.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        viewHolder.trip_info_return_title.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        viewHolder.trip_info_hotel_title.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        viewHolder.btnDeleteReserv.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        viewHolder.dateTrip.setText(outputlist.get(position).getMatch().getDate());
        viewHolder.heureTrip.setText(outputlist.get(position).getMatch().getTime());
        viewHolder.homeTrip.setText(outputlist.get(position).getMatch().getCompetitorA().getName());
        viewHolder.awayTrip.setText(outputlist.get(position).getMatch().getCompetitorB().getName());

        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                notifyItemChanged(position);
            }
        });

        viewHolder.btnDeleteReserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete Reservation
            }
        });
    }

    @Override
    public int getItemCount() {
        return outputlist.size();
    }

    public class ListTripViewHolder  extends RecyclerView.ViewHolder
    {
        private Button btnDeleteReserv;
        private TextView dateTrip;
        private TextView heureTrip;
        private TextView homeTrip;
        private TextView awayTrip;
        private TextView trip_info_location;
        private TextView trip_info_depart;
        private TextView trip_info_return;
        private TextView trip_info_hotel;
        private View container;

        private TextView trip_info_location_title;
        private TextView trip_info_depart_title;
        private TextView trip_info_return_title;
        private TextView trip_info_hotel_title;

        public ListTripViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDeleteReserv = (Button) itemView.findViewById(R.id.btnDeleteReserv);
            dateTrip = (TextView) itemView.findViewById(R.id.dateTrip);
            heureTrip = (TextView) itemView.findViewById(R.id.heureTrip);
            homeTrip = (TextView) itemView.findViewById(R.id.homeTrip);
            awayTrip = (TextView) itemView.findViewById(R.id.awayTrip);
            trip_info_location = (TextView) itemView.findViewById(R.id.trip_info_location);
            trip_info_depart = (TextView) itemView.findViewById(R.id.trip_info_depart);
            trip_info_return = (TextView) itemView.findViewById(R.id.trip_info_return);
            trip_info_hotel = (TextView) itemView.findViewById(R.id.trip_info_hotel);
            container = itemView.findViewById(R.id.card_view);
            trip_info_location_title = (TextView) itemView.findViewById(R.id.trip_info_location_title);
            trip_info_depart_title = (TextView) itemView.findViewById(R.id.trip_info_depart_title);
            trip_info_return_title = (TextView) itemView.findViewById(R.id.trip_info_return_title);
            trip_info_hotel_title = (TextView) itemView.findViewById(R.id.trip_info_hotel_title);
        }
    }
}
