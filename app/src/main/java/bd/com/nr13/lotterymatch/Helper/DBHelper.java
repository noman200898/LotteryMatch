package bd.com.nr13.lotterymatch.Helper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    public boolean addLottery(final Lottery lottery){
        if (lottery != null && appDatabase != null){
            long success = appDatabase.lotteryDao().insertLottery(lottery);
            if (success != -1){
                return true;
            }
            Log.d(AppConstant.LOGTAG, "addNumber "+ success);
        }
        return false;
    }

    public List<Lottery> getAllLottery(int type){
        List<Lottery> lotteryList = new ArrayList<>();
        if (appDatabase != null && type != 0){
            lotteryList = appDatabase.lotteryDao().selectAllLottery();
            Log.d(AppConstant.LOGTAG, "getNumber "+lotteryList.size());
        }
        return lotteryList;
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
