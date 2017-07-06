package com.kwave.android.testtest;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-07-07.
 */

public class CustomAdapter extends RecyclerView.ViewHolder {
    List<Food> data = new ArrayList<>();


    public CustomAdapter(View itemView) {
        super(itemView);
    }



}
