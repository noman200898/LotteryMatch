package bd.com.nr13.lotterymatch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bd.com.nr13.lotterymatch.Helper.AppConstant;
import bd.com.nr13.lotterymatch.Helper.DBHelper;
import bd.com.nr13.lotterymatch.dbmanger.AppDatabase;
import bd.com.nr13.lotterymatch.dbmanger.Lottery;

/**
 * Created by nomanurrashid on 2/2/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<String> myDataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        prepareData();

    }

    @Override
    protected void onResume() {
        super.onResume();
       // testDBMethods();
    }

    private void prepareData(){
        for (int i=1; i < 21; i++) {
            myDataset.add("000000"+i);
        }
        mAdapter.notifyDataSetChanged();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_my_number);
                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_win_number);
                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
            }
            return false;
        }
    };

    private void testDBMethods(){
        final Lottery lottery = new Lottery();
        lottery.setNumber("43534");
        lottery.setType(AppConstant.LOTTERY_TYPE_MY_NUM);

        new Thread(new Runnable() {
            @Override
            public void run() {
                DBHelper.on().addNumber(lottery);
                DBHelper.on().getNumber();
            }
        }).start();


    }
}
