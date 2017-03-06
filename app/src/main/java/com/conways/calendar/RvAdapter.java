package com.conways.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Conways on 2017/3/2.
 */

public class RvAdapter extends RecyclerView.Adapter<MyHolder> {

    private Context context;
    private ArrayList<DateEntity> list;
    private RvItemClickListenter clickListenter;
    private int positionNow = -1;


    public RvAdapter(Context context, ArrayList<DateEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_calendar_pager,
                parent, false));
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.tvGregorian.setText(TimeUtil.getDayofMonthByTimeStamp(list.get(position).getData()) + "");
        if (position % 7 == 0 || (position - 6) % 7 == 0) {
            holder.tvGregorian.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.tvLunar.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.ivState.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.tvGregorian.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.tvLunar.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.ivState.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
        holder.itemView.setBackgroundColor(list.get(position).isInMonth() ? 0xffffffff : 0xaae8e8e8);

        if (!list.get(position).isInMonth()) {
            holder.tvGregorian.setTextColor(0x88000000);
            holder.tvLunar.setTextColor(0x88000000);
            holder.ivState.setBackgroundColor(0x88000000);
        }
        if (this.positionNow == position) {
            holder.itemView.setBackgroundColor(0x6600ff00);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListenter != null) {
                    clickListenter.itemClick(list.get(position).getData());
                }

                RvAdapter.this.positionNow = position;
                RvAdapter.this.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListenter(RvItemClickListenter clickListenter) {
        this.clickListenter = clickListenter;
    }

    public interface RvItemClickListenter {
        void itemClick(long timeStimp);
    }
}
