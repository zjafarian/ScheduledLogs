package com.example.scheduledlogs.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.scheduledlogs.data.model.LogData;
import com.example.scheduledlogs.data.repository.LogRepository;
import com.example.scheduledlogs.work.LogWorker;

import java.util.List;

public class ScheduledLogsViewModel extends AndroidViewModel {
    private LogRepository mRepository;
    private List<LogData> mDataList;

    public ScheduledLogsViewModel(@NonNull Application application) {
        super(application);
        mRepository = LogRepository.getInstance(getApplication());
    }

    public LogRepository getRepository() {
        return mRepository;
    }

    public void setRepository(LogRepository repository) {
        mRepository = repository;
    }

    public List<LogData> getDataList() {
        return mDataList;
    }

    public void setDataList(List<LogData> dataList) {
        mDataList = dataList;
    }

    public boolean isTaskScheduled() {
        return LogWorker.isWorkEnqueued(getApplication());
    }

    public String onStartButtonClicked() {
        toggleScheduledLogs();
        String textLogData="";
        mDataList = mRepository.getLogDataList();
        for (LogData logDataFind: mDataList) {
            StringBuilder stringBuilder = new StringBuilder()
                    .append(logDataFind.getNameLog())
                    .append(logDataFind.getIndex())
                    .append(logDataFind.getCurrentDate())
                    .append(logDataFind.getTimeStampDate())
                    .append(logDataFind.getStateWifi());

            textLogData = textLogData + "\n" + stringBuilder.toString();
        }
        return textLogData;
    }

    public void  onStopButtonClicked(){
        isTaskScheduled();
    }

    public void toggleScheduledLogs() {
        boolean isOn = LogWorker.isWorkEnqueued(getApplication());
        LogWorker.enqueueWork(getApplication(), !isOn);
    }
}
