package bd.com.nr13.lotterymatch.dbmanger;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by nomanurrashid on 4/2/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

@Entity(tableName = "lottery")
public class Lottery {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "row_id")
    private int id;

    @ColumnInfo(name = "number")
    private String number;

    @ColumnInfo(name = "type")
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
