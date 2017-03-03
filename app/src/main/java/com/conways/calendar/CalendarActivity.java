package com.conways.calendar;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity  implements CalendarFragment
        .OnFragmentInteractionListener,ViewPager.OnPageChangeListener{

    private ViewPager vp;
    private VpAdapter vpAdapter;
    private String tag;
    private TextView tvDate;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        init();
    }


    private void init(){
        vp=(ViewPager)this.findViewById(R.id.vp);
        tvDate=(TextView)this.findViewById(R.id.date);
        vpAdapter=new VpAdapter(getSupportFragmentManager());
        vp.setAdapter(vpAdapter);
        vp.addOnPageChangeListener(this);
        vp.setCurrentItem(vpAdapter.getCount()/2,false);
    }

    private void update(int position){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH,position-Integer.MAX_VALUE/2);
        tvDate.setText(TimeUtil.getTimeFromTimeStamp(calendar.getTimeInMillis(),"yyyy-MM-dd"));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            update(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}
