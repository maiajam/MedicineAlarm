package com.example.maiajam.medcinealram.util;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.maiajam.medcinealram.helper.HelperMethodes;

import static com.example.maiajam.medcinealram.helper.HelperMethodes.notifyMyAboutTheMedcine;

public class NotifyMeWorker extends Worker {

    private Data medcineInfo;
    private Context mContext;

    public NotifyMeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {

        getMedcineInfo();
        notifyMyAboutTheMedcine(mContext,
                medcineInfo.getString("med_mame")
                , medcineInfo.getString("med_not"),
                medcineInfo.getString("med_dose"));
        return Result.retry();
    }

    private void getMedcineInfo() {
        medcineInfo = getInputData();
    }
}
