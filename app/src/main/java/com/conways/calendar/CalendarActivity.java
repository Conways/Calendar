package com.conways.calendar;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalendarActivity extends AppCompatActivity  implements CalendarFragment.OnFragmentInteractionListener{

    private ViewPager vp;
    private VpAdapter vpAdapter;

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
        vpAdapter=new VpAdapter(getSupportFragmentManager());
        vp.setAdapter(vpAdapter);
    }



}
