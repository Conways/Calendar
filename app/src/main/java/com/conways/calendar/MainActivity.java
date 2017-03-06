package com.conways.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btNormal;
    private Button btDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        btNormal = (Button) this.findViewById(R.id.normalCalendar);
        btDialog = (Button) this.findViewById(R.id.dialogCalendar);
        btNormal.setOnClickListener(this);
        btDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normalCalendar:
                toTarget(CalendarActivity.class);
                break;
            case R.id.dialogCalendar:
                Toast.makeText(this,"建设中",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }


    private void toTarget(Class<?> c) {
        Intent intent = new Intent();
        intent.setClass(this, c);
        startActivity(intent);
    }



}
