package com.conways.calendar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class CalendarFragment extends Fragment {
    private static final String DATE = "date";
    private static final String DATAS = "datas";

    private RecyclerView rv;
    private RvAdapter myAdapter;

    private long date;
    private ArrayList<DataEntity> datas;
    private ArrayList<DateEntity> dates;

    private OnFragmentInteractionListener mListener;

    public CalendarFragment() {
    }

    public static CalendarFragment newInstance(long date, ArrayList<DataEntity> datas) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putLong(DATE, date);
        args.putParcelableArrayList(DATAS, datas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = getArguments().getLong(DATE);
            datas = getArguments().getParcelableArrayList(DATAS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }



    private void init() {
        rv=(RecyclerView)getView().findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),7));
        rv.addItemDecoration(new GridItemDecoration(getActivity()));
        myAdapter=new RvAdapter(getActivity(),dates);
        rv.setAdapter(myAdapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        init();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void initData(){
        dates=new ArrayList<>();
        int firstweekDay=TimeUtil.getWeekDayOfFirstDayInOneMonthByTimeStamp(date);
        int lastweekDay=TimeUtil.getWeekDayOfLastDayInOneMonthByTimeStamp(date);
        int maxDay=TimeUtil.getDayCountsOfOneMonthByTimestamp(date);

        for (int i = 1; i < firstweekDay; i++) {
            DateEntity entity=new DateEntity();
            entity.setData(0);
            entity.setInMonth(false);
            dates.add(entity);
        }
        for (int i = 0; i < maxDay; i++) {
            DateEntity entity=new DateEntity();
            entity.setData(i+1);
            entity.setInMonth(true);
            dates.add(entity);
        }
        for (int i = 0; i < (7-lastweekDay); i++) {
            DateEntity entity=new DateEntity();
            entity.setData(0);
            entity.setInMonth(false);
            dates.add(entity);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
