package com.example.scheduledlogs.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.util.Date;

@Entity(tableName = "LogData")
public class LogData {

    @ColumnInfo(name = "index")
    @PrimaryKey(autoGenerate = true)
    private int mIndex;

    @ColumnInfo(name = "currentDate")
    private Date mCurrentDate;

    @ColumnInfo (name = "timeStampDate")
    private String mTimeStampDate;

    @ColumnInfo(name = "nameLog")
    private String mNameLog;

    @ColumnInfo(name = "stateWifi")
    private String mStateWifi;


    @Ignore
    public LogData() {
        mCurrentDate = new Date();
        mTimeStampDate = "";
        mNameLog ="";
        mStateWifi = "";

    }

    public LogData(Date currentDate, String timeStampDate, String nameLog, String stateWifi) {
        mCurrentDate = currentDate;
        mTimeStampDate = timeStampDate;
        mNameLog = nameLog;
        mStateWifi = stateWifi;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public Date getCurrentDate() {
        return mCurrentDate;
    }

    public void setCurrentDate(Date currentDate) {
        mCurrentDate = currentDate;
    }

    public String getTimeStampDate() {
        return mTimeStampDate;
    }

    public void setTimeStampDate(String timeStampDate) {
        mTimeStampDate = timeStampDate;
    }

    public String getNameLog() {
        return mNameLog;
    }

    public void setNameLog(String nameLog) {
        mNameLog = nameLog;
    }

    public String getStateWifi() {
        return mStateWifi;
    }

    public void setStateWifi(String stateWifi) {
        mStateWifi = stateWifi;
    }

}
