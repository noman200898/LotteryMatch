package bd.com.nr13.lotterymatch.addNumber;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import bd.com.nr13.lotterymatch.R;

/**
 * Created by nomanurrashid on 4/7/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class AddActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);

        EditText editText = (EditText)findViewById(R.id.edit_text_add_lottery);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
