package com.conways.calendar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.WindowDecorActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;


public class CalendarFragment extends Fragment implements RvAdapter.RvItemClickListenter {
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
        rv = (RecyclerView) getView().findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 7));
        rv.addItemDecoration(new GridItemDecoration(getActivity()));
        myAdapter = new RvAdapter(getActivity(), dates);
        myAdapter.setClickListenter(this);
        rv.setAdapter(myAdapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        init();
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

    private void initData() {
        dates = new ArrayList<>();
        int firstweekDay = TimeUtil.getWeekDayOfFirstDayInOneMonthByTimeStamp(date);
        int lastweekDay = TimeUtil.getWeekDayOfLastDayInOneMonthByTimeStamp(date);
        int maxDay = TimeUtil.getDayCountsOfOneMonthByTimestamp(date);
        int now = TimeUtil.getDayofMonthByTimeStamp(date);

        for (int i = firstweekDay - 1; i > 0; i--) {
            DateEntity entity = new DateEntity();
            entity.setData(TimeUtil.getTimeStampbyTimeStampAndDistance(date, -i - now + 1));
            entity.setInMonth(false);
            dates.add(entity);
        }
        for (int i = 0; i < maxDay; i++) {
            DateEntity entity = new DateEntity();
            entity.setData(TimeUtil.getTimeStampbyTimeStampAndDistance(date, i - now + 1));
            entity.setInMonth(true);
            dates.add(entity);
        }
        for (int i = 0; i < (7 - lastweekDay); i++) {
            DateEntity entity = new DateEntity();
            entity.setData(TimeUtil.getTimeStampbyTimeStampAndDistance(date, maxDay - now + i + 1));
            entity.setInMonth(false);
            dates.add(entity);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void itemClick(long timeStamp) {
        if (mListener != null) {
            mListener.onFragmentInteraction(timeStamp);
        }
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(long timeStamp);
    }
}
