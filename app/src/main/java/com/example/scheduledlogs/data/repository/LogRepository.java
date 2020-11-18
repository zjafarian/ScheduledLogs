package com.example.scheduledlogs.data.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Room;

import java.util.List;

import com.example.scheduledlogs.data.model.LogData;
import com.example.scheduledlogs.data.room.LogDAO;
import com.example.scheduledlogs.data.room.ScheduledLogsDatabase;

public class LogRepository implements ILogRepository {
    private List<LogData> mLogDataList;
    private static LogRepository sInstance;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private LogDAO mLogDAO;


    private LogRepository(Context context) {
        mContext = context;


        ScheduledLogsDatabase scheduledLogsDatabase = Room.databaseBuilder(mContext,
                ScheduledLogsDatabase.class, "scheduledLogs.db")
                .allowMainThreadQueries()
                .build();

        mLogDAO = scheduledLogsDatabase.getLogTable();


    }

    public static LogRepository getInstance(Context context) {
        if (sInstance==null){
            sInstance = new LogRepository(context);

        }
        return sInstance;
    }

    public List<LogData> getLogDataList() {
        return mLogDataList;
    }

    public void setLogDataList(List<LogData> logDataList) {
        mLogDataList = logDataList;
    }


    @Override
    public void insertLogData(LogData logData) {
        mLogDAO.insertLog(logData);

    }

    @Override
    public LogData getLogData(int logId) {
        return mLogDAO.getLogData(logId);
    }

    @Override
    public void updateLogData(LogData logData) {
        mLogDAO.updateLog(logData);
    }

    @Override
    public void deleteLogData(LogData logData) {
        mLogDAO.deleteLog(logData);
    }

    @Override
    public int getPosition(int logId) {
        for (int i = 0; i <mLogDataList.size() ; i++) {
            if (mLogDataList.get(i).getIndex()==logId) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public List<LogData> getTaskList() {
        mLogDataList = mLogDAO.getLogList();
        return mLogDAO.getLogList();
    }
}
