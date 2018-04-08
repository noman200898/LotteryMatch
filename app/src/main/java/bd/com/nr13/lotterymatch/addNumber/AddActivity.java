package bd.com.nr13.lotterymatch.addNumber;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import bd.com.nr13.lotterymatch.Helper.AppConstant;
import bd.com.nr13.lotterymatch.Helper.DBHelper;
import bd.com.nr13.lotterymatch.R;
import bd.com.nr13.lotterymatch.dbmanger.Lottery;

/**
 * Created by nomanurrashid on 4/7/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextAdd;
    Button buttonSave;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);

        editTextAdd = findViewById(R.id.edit_text_add_lottery);
        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case (R.id.button_save):
                Log.d(AppConstant.LOGTAG, "onClick "+ "save button");
                saveInputNumber();
                break;
        }
    }

    private void saveInputNumber(){
        final String inputText = String.valueOf(editTextAdd.getText());
        if (inputText == null || inputText.equals("")){
            Toast.makeText(this,"Empty text found",Toast.LENGTH_LONG).show();
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Lottery lottery = new Lottery();
                    lottery.setNumber(inputText);
                    lottery.setType(AppConstant.LOTTERY_TYPE_MY_NUM);
                    boolean success = DBHelper.on().addLottery(lottery);
                    if (success){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                editTextAdd.setText("");
                                Toast.makeText(AddActivity.this,"Number Added successfully.",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();

        }
    }
}
