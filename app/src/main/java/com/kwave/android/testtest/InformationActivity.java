package com.kwave.android.testtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

public class InformationActivity extends AppCompatActivity {

    ImageView sun,mon,tue,wen,thi,fri,sat;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setView();

    }

    public void setView(){
        sun = (ImageView) findViewById(R.id.imageSun);
        mon = (ImageView) findViewById(R.id.imageMon);
        tue = (ImageView) findViewById(R.id.imageTue);
        wen = (ImageView) findViewById(R.id.imageWen);
        thi = (ImageView) findViewById(R.id.imageThi);
        fri = (ImageView) findViewById(R.id.imageFri);
        sat = (ImageView) findViewById(R.id.imageSat);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }




}
