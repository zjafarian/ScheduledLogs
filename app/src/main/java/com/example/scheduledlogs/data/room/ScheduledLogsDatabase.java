package com.example.scheduledlogs.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.scheduledlogs.data.model.LogData;

@Database(entities = {LogData.class},version = 1)
@TypeConverters(Converters.class)
public abstract class ScheduledLogsDatabase extends RoomDatabase {

    public abstract LogDAO getLogTable();
}
