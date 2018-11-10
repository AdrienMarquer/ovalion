package com.ovalion.mongoldorak.ovalion.Activities.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ovalion.mongoldorak.ovalion.API.BDD.DatabaseManager;
import com.ovalion.mongoldorak.ovalion.Adapters.ListCalendarAdapter;
import com.ovalion.mongoldorak.ovalion.Adapters.ListTripAdapter;
import com.ovalion.mongoldorak.ovalion.Models.Match;
import com.ovalion.mongoldorak.ovalion.Models.Reservation;
import com.ovalion.mongoldorak.ovalion.Models.ReservationBDD;
import com.ovalion.mongoldorak.ovalion.R;

import java.util.ArrayList;
import java.util.List;

public class TripFragment extends Fragment
{
    private RecyclerView rv;
    List<ReservationBDD> reservs = new ArrayList<>();
    private DatabaseManager db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_trip, container, false);
        db = new DatabaseManager(inflater.getContext());
        reservs = db.getReservs();

        //REFERENCE
        rv= (RecyclerView) rootView.findViewById(R.id.trip_recycler_view);

        //LAYOUT MANAGER
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        rv.setAdapter(new ListTripAdapter(getContext(),reservs));

        return rootView;
    }

}
