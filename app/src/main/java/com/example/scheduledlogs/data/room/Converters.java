package com.example.scheduledlogs.data.room;

import androidx.room.TypeConverter;

import java.sql.Timestamp;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date timestampToDate(long timeStamp) {
        return new Date(timeStamp);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Integer stringToInt (String index){
        return Integer.valueOf(index);
    }

    @TypeConverter
    public static String intToString (int index){
        return String.valueOf(index);
    }

}
