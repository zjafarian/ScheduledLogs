package com.example.scheduledlogs.work;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.scheduledlogs.data.model.LogData;
import com.example.scheduledlogs.data.repository.LogRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LogWorker extends Worker {
    public static final String TAG = "LogWorker";
    private static final String LOG_WORKER_NAME = "LogWorkerName";

    private LogRepository mRepository;
    private List<LogData> mLogDataList;


    public LogWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        mRepository = LogRepository.getInstance(getApplicationContext());
        mLogDataList = mRepository.getTaskList();
        createLog();
        return Result.success();
    }
    private void createLog() {
        Date dateCurrent = new Date();
        Timestamp dateTimeStamp = new Timestamp(dateCurrent.getTime());
        String name = "logData";
        String stateWifi = "";
        WifiManager wifiManager = (WifiManager)
                getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING)
            stateWifi = "ON";
        else if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLING) {
            stateWifi = "OFF";
        }
        LogData logData = new LogData(dateCurrent, dateTimeStamp.toString(), name, stateWifi);
        mRepository.insertLogData(logData);
        StringBuilder stringBuilder = new StringBuilder()
                .append("Log Name:" + logData.getNameLog())
                .append("Index: " + logData.getIndex())
                .append("DataCurrent: " + logData.getCurrentDate().toString())
                .append("TimeStamp: " + logData.getTimeStampDate())
                .append("Wifi State: " + logData.getStateWifi());
        String message = stringBuilder.toString();

        Log.d(TAG, message);
    }

    public static void enqueueWork(Context context, boolean isOn){

        Log.d(TAG, "enqueueWork");
        WorkManager workManager = WorkManager.getInstance(context);

        if (isOn) {
            Log.d(TAG, "enqueued");
            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.UNMETERED)
                    .build();

            PeriodicWorkRequest periodicWorkRequest =
                    new PeriodicWorkRequest.Builder(LogWorker.class,
                            15, TimeUnit.MINUTES)
                            .setConstraints(constraints)
                            .build();

            workManager.enqueueUniquePeriodicWork(
                    LOG_WORKER_NAME,
                    ExistingPeriodicWorkPolicy.KEEP,
                    periodicWorkRequest);
        } else {
            Log.d(TAG, "canceled");
            workManager.cancelUniqueWork(LOG_WORKER_NAME);
        }

    }


    public static boolean isWorkEnqueued(Context context) {
        WorkManager workManager = WorkManager.getInstance(context);
        try {
            List<WorkInfo> workInfoList =
                    workManager.getWorkInfosForUniqueWork(LOG_WORKER_NAME).get();

            for (WorkInfo workInfo: workInfoList) {
                if (workInfo.getState() == WorkInfo.State.ENQUEUED ||
                        workInfo.getState() == WorkInfo.State.RUNNING)
                    return true;
            }

            return false;
        } catch (ExecutionException e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage(), e);
            return false;
        }
    }
}
