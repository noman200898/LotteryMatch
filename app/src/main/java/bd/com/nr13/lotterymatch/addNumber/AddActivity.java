package bd.com.nr13.lotterymatch.addNumber;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import bd.com.nr13.lotterymatch.Helper.AppConstant;
import bd.com.nr13.lotterymatch.Helper.CommonHelper;
import bd.com.nr13.lotterymatch.Helper.DBHelper;
import bd.com.nr13.lotterymatch.R;
import bd.com.nr13.lotterymatch.dbmanger.Lottery;

/**
 * Created by nomanurrashid on 4/7/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextAdd;
    Button buttonSave;
    ImageButton buttonInfo;
    private boolean isWinNumberTabSelected;
    private Lottery editingLottery;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);

        Toolbar toolbar = findViewById(R.id.toolbarAddActivity);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        editTextAdd = findViewById(R.id.edit_text_add_lottery);
        buttonSave = findViewById(R.id.button_save);
        buttonInfo = findViewById(R.id.button_info);
        buttonSave.setOnClickListener(this);
        buttonInfo.setOnClickListener(this);

        //Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            if (intent.hasExtra(AppConstant.WIN_NUMBER_SELECTED_KEY)) {
                isWinNumberTabSelected = intent.getExtras().getBoolean(AppConstant.WIN_NUMBER_SELECTED_KEY);
                buttonSave.setText(R.string.title_save);
            } else if (intent.hasExtra(AppConstant.LOTTERY_OBJECT)) {
                editingLottery = (Lottery) intent.getExtras().get(AppConstant.LOTTERY_OBJECT);
                if (editingLottery != null)
                editTextAdd.setText(editingLottery.getNumber());
                buttonSave.setText(R.string.title_update);
            }
        }
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
        switch (view.getId()) {
            case (R.id.button_save):
                if (editingLottery == null){
                    saveInputNumber();
                }else {
                    updateInputNumber();
                }
                break;
            case (R.id.button_info):
                Log.d(AppConstant.LOGTAG, "onClick " + "info button");
                showHint();
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void saveInputNumber() {
        final String inputText = String.valueOf(editTextAdd.getText());
        if (inputText == null || inputText.equals("")) {
            Toast.makeText(this, AppConstant.TOAST_FOR_EMPTY_NUMBER, Toast.LENGTH_LONG).show();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean success = false;
                    List<String> numberList = CommonHelper.splitText(inputText);
                    for (String number : numberList){
                        Lottery lottery = new Lottery();
                        lottery.setNumber(number);
                        if (isWinNumberTabSelected) {
                            lottery.setType(AppConstant.LOTTERY_TYPE_WIN_NUM);
                        } else {
                            lottery.setType(AppConstant.LOTTERY_TYPE_MY_NUM);
                        }
                        success = DBHelper.on().addLottery(lottery);
                    }

                    if (success) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                editTextAdd.setText("");
                                Toast.makeText(AddActivity.this,AppConstant.TOAST_FOR_NUMBER_ADDED, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();

        }
    }

    private void updateInputNumber() {
        final String inputText = String.valueOf(editTextAdd.getText());
        if (inputText == null || inputText.equals("")) {
            Toast.makeText(this, AppConstant.TOAST_FOR_EMPTY_NUMBER, Toast.LENGTH_LONG).show();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    editingLottery.setNumber(inputText);
                    int success = DBHelper.on().updateNumber(editingLottery);
                    if (success > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                editTextAdd.setText("");
                                Toast.makeText(AddActivity.this, AppConstant.TOAST_FOR_NUMBER_UPDATED, Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }
                        });
                    }
                }
            }).start();

        }
    }

    private void showHint(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.title_add_number_hint)
                .setMessage(R.string.message_add_number_hint)
                .setNegativeButton(R.string.title_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }
}
