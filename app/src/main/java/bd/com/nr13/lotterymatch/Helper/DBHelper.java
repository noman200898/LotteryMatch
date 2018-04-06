package bd.com.nr13.lotterymatch.Helper;

import android.util.Log;

import bd.com.nr13.lotterymatch.LotteryMatch;
import bd.com.nr13.lotterymatch.dbmanger.AppDatabase;
import bd.com.nr13.lotterymatch.dbmanger.Lottery;

/**
 * Created by nomanurrashid on 4/2/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class DBHelper {

    private static DBHelper INSTANT;
    private static AppDatabase appDatabase;

    public static DBHelper on() {
        if (INSTANT == null){
            INSTANT = new DBHelper();
            appDatabase = AppDatabase.getAppDatabase(LotteryMatch.getAppContext());
        }
        return INSTANT;
    }

    public void addNumber(final Lottery lottery){
        if (lottery != null && appDatabase != null){
            long success = appDatabase.lotteryDao().insertLottery(lottery);
            Log.d(AppConstant.LOGTAG, "addNumber "+ success);
        }
    }

    public void getNumber(){
        if (appDatabase != null){
            Log.d(AppConstant.LOGTAG, "getNumber "+appDatabase.lotteryDao().selectAllLottery().size());
        }
    }

    public void updateNumber(final Lottery lottery){
        if (lottery != null && appDatabase != null){
            int success = appDatabase.lotteryDao().updateLottery(lottery);
            Log.d(AppConstant.LOGTAG, "updateNumber "+ success);
        }
    }

    public void deleteNumber(final Lottery lottery){
        if (lottery != null && appDatabase != null){
            long success = appDatabase.lotteryDao().deleteLottery(lottery);
            Log.d(AppConstant.LOGTAG, "deleteNumber "+ success);
        }
    }
}
