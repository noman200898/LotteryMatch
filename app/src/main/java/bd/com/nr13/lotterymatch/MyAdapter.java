package bd.com.nr13.lotterymatch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import bd.com.nr13.lotterymatch.Helper.AppConstant;
import bd.com.nr13.lotterymatch.Helper.DBHelper;
import bd.com.nr13.lotterymatch.addNumber.AddActivity;
import bd.com.nr13.lotterymatch.dbmanger.Lottery;

/**
 * Created by nomanurrashid on 3/2/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Lottery> mDataSet, mainDataSet;

    MyAdapter(List<Lottery> mDataSet) {
        this.mDataSet = mDataSet;
        this.mainDataSet = mDataSet;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        ImageButton editButton;
        ImageButton deleteButton;
        ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
            editButton = itemView.findViewById(R.id.imageButton_edit);
            deleteButton = itemView.findViewById(R.id.imageButton_delete);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =  getAdapterPosition();
                    Log.d(AppConstant.LOGTAG, "editButton.setOnClickListener "+position);
                }
            });

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_holder, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Lottery lottery =  mDataSet.get(position);
        String number = lottery.getNumber();
        holder.mTextView.setText(number);
        final int index = position;
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(AppConstant.LOGTAG, "deleteButton.setOnClickListener "+ index);
                removeDataAt(index, lottery);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private void removeDataAt(final int position, final  Lottery lottery) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataSet.size());

        new Thread(new Runnable() {
            @Override
            public void run() {
                DBHelper.on().deleteLottery(lottery);
            }
        }).start();
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mainDataSet.clear();
        if (charText.length() == 0) {
            mainDataSet.addAll(mainDataSet);
        } else {
            for (Lottery wp : mainDataSet) {
                if (wp.getNumber().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mainDataSet.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
