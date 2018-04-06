package bd.com.nr13.lotterymatch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nomanurrashid on 3/2/18.
 * Copyright (c) 2018, nr13.com. All rights reserved.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<String> mDataSet;

    MyAdapter(List<String> mDataSet) {
        this.mDataSet = mDataSet;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_holder, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String number = mDataSet.get(position);
        holder.mTextView.setText(number);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
