package bd.com.nr13.lotterymatch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bd.com.nr13.lotterymatch.Helper.AppConstant;
import bd.com.nr13.lotterymatch.Helper.CommonHelper;
import bd.com.nr13.lotterymatch.Helper.DBHelper;
import bd.com.nr13.lotterymatch.addNumber.AddActivity;
import bd.com.nr13.lotterymatch.dbmanger.AppDatabase;
import bd.com.nr13.lotterymatch.dbmanger.Lottery;

/**
 * Created by nomanurrashid on 2/2/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<Lottery> myDataset = new ArrayList<>();
    private boolean isWinNumberTabSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset, this);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra(AppConstant.WIN_NUMBER_SELECTED_KEY, isWinNumberTabSelected);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_top_menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.menuSearch);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) searchViewItem.getActionView();

        searchView.setQueryHint(AppConstant.SEARCH_BAR_HINT);
        if (searchManager != null)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuAbout:
                break;
            case R.id.menuSettings:
                break;
            case R.id.menuLogout:
                break;
        }
        return true;
    }

    private void prepareData(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                myDataset.clear();
                if (isWinNumberTabSelected){
                    myDataset.addAll(DBHelper.on().getAllLottery(AppConstant.LOTTERY_TYPE_WIN_NUM));
                }else {
                    myDataset.addAll(DBHelper.on().getAllLottery(AppConstant.LOTTERY_TYPE_MY_NUM));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        mAdapter.fullDataSet = new ArrayList<>();
                        mAdapter.fullDataSet.addAll(myDataset);
                    }
                });
            }
        }).start();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_my_number:
                    item.setChecked(true);
                    isWinNumberTabSelected = false;
                    break;
                case R.id.navigation_win_number:
                    item.setChecked(true);
                    isWinNumberTabSelected = true;
                    break;
            }
            prepareData();
            return true;
        }
    };

    private void testDBMethods(){
        final Lottery lottery = new Lottery();
        lottery.setNumber("43534");
        lottery.setType(AppConstant.LOTTERY_TYPE_MY_NUM);

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();


    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        mAdapter.filter(text);
        return false;
    }
}
