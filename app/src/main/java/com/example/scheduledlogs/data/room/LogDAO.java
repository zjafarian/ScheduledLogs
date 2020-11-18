package com.example.scheduledlogs.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.scheduledlogs.data.model.LogData;

import java.util.List;

@Dao
public interface LogDAO {
    @Insert
    void insertLog(LogData logData);

    @Update
    void updateLog(LogData logData);

    @Delete
    void deleteLog(LogData logData);

    @Query("SELECT * FROM LogData")
    List<LogData> getLogList();

    @Query("SELECT * FROM LogData WHERE `index`=:indexLog")
    LogData getLogData(int indexLog);

}
