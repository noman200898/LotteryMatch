package bd.com.nr13.lotterymatch;

import android.app.Application;
import android.content.Context;

/**
 * Created by nomanurrashid on 4/2/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class LotteryMatch extends Application {
     private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LotteryMatch.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return LotteryMatch.context;
    }
}
