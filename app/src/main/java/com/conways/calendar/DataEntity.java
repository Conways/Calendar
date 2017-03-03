package com.conways.calendar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Conways on 2017/3/2.
 */

public class DataEntity implements Parcelable {


    private long date;


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.date);
    }

    public DataEntity() {
    }

    protected DataEntity(Parcel in) {
        this.date = in.readLong();
    }

    public static final Parcelable.Creator<DataEntity> CREATOR = new Parcelable.Creator<DataEntity>() {
        @Override
        public DataEntity createFromParcel(Parcel source) {
            return new DataEntity(source);
        }

        @Override
        public DataEntity[] newArray(int size) {
            return new DataEntity[size];
        }
    };
}
