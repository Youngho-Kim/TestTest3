package com.kwave.android.testtest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by kwave on 2017-07-07.
 */

public class TimerDialog extends Dialog{
    CallBack callBack;

    public TimerDialog(Context context) {
        super(context);
        this.callBack=(CallBack) context;
    }
    ImageButton imageSetTime;
    EditText editWalk, editSprint;

    private String Walk;
    private String Sprint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_dialog);
        setView();
        imageSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempWalk =editWalk.getText().toString();
                String tempSprint = editSprint.getText().toString();
                callBack.timeSet(tempWalk,tempSprint);
                dismiss();
            }
        });
    }
    public void setView(){
        imageSetTime = (ImageButton) findViewById(R.id.imageSetTimeDialog);
        editWalk = (EditText) findViewById(R.id.editWalkDialog);
        editSprint =(EditText) findViewById(R.id.editSprintDialog);
    }

    interface CallBack{
        void timeSet(String walkTime, String sprintTime);
    }
}
