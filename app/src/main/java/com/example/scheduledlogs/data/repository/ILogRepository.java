package com.example.scheduledlogs.data.repository;

import com.example.scheduledlogs.data.model.LogData;

import java.util.List;

public interface ILogRepository {
    void insertLogData (LogData logData);
    LogData getLogData (int logId);
    void updateLogData (LogData logData);
    void deleteLogData (LogData logData);
    int getPosition (int logId);
    List<LogData> getTaskList();
}
