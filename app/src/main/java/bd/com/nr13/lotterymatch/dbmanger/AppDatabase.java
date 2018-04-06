package bd.com.nr13.lotterymatch.dbmanger;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by nomanurrashid on 4/2/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

@Database(entities = {Lottery.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANT;
    public abstract LotteryDao lotteryDao();

    public static AppDatabase getAppDatabase(Context context){
        if (INSTANT == null){
          INSTANT = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"lottery_db").build();
        }

        return INSTANT;
    }

    public static void destroyInstant(){
        INSTANT = null;
    }
}
