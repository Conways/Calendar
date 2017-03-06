package com.conways.calendar;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity  implements CalendarFragment
        .OnFragmentInteractionListener,ViewPager.OnPageChangeListener,View.OnClickListener{

    private ViewPager vp;
    private VpAdapter vpAdapter;
    private String tag;
    private TextView tvDate;
    private ImageView btPrevious;
    private ImageView btNext;

    @Override
    public void onFragmentInteraction(long timeStamp) {
        tvDate.setText(TimeUtil.getTimeFromTimeStamp(timeStamp,"yyyy-MM-dd"));
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
        btPrevious=(ImageView)this.findViewById(R.id.previous);
        btNext=(ImageView)this.findViewById(R.id.next);
        btPrevious.setOnClickListener(this);
        btNext.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previous:
                if (vp.getCurrentItem()<=0){
                    break;
                }
                vp.setCurrentItem(vp.getCurrentItem()-1);
                break;

            case R.id.next:
                if (vp.getCurrentItem()>=Integer.MAX_VALUE){
                    break;
                }
                vp.setCurrentItem(vp.getCurrentItem()+1);
            default:
                break;


        }
    }
}
