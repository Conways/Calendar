package com.conways.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Conways on 2017/3/2.
 */

public class MyHolder extends RecyclerView.ViewHolder {

    public ImageView ivState;
    public TextView tvGregorian;
    public TextView tvLunar;
    public MyHolder(View itemView) {
        super(itemView);
        ivState=(ImageView)itemView.findViewById(R.id.state);
        tvGregorian=(TextView)itemView.findViewById(R.id.gregorian);
        tvLunar=(TextView) itemView.findViewById(R.id.lunar);
    }
}
