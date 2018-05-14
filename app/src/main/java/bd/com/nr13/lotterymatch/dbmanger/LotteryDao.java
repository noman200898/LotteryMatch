package bd.com.nr13.lotterymatch.dbmanger;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by nomanurrashid on 4/2/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

@Dao
public interface LotteryDao {

    @Insert
    long insertLottery(Lottery lottery);

    @Query("SELECT * FROM lottery WHERE type LIKE:type")
    List<Lottery> selectAllLottery(int type);

    @Update
    int updateLottery(Lottery lottery);

    @Delete
    int deleteLottery(Lottery lottery);
}
