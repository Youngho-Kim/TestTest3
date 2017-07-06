package com.kwave.android.testtest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
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
    EditText editId, editPw;



    private String mode;

    private String text;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.timer_dialog);




        imageSetTime.setOnTouchListener(new View.OnTouchListener() {

            @Override

            public boolean onTouch(View v, MotionEvent event) {

                text = editId.getText().toString();

                mode = editPw.getText().toString();

                dismiss();

                return false;

            }

        });

    }



    public String getMode() {

        return mode;

    }



    public void setMode(String mode) {

        this.mode = mode;

        editPw.setText(mode);
        editPw.setFocusable(true);

    }



    public String getText() {

        return text;

    }



    public void setText(String text) {

        this.text = text;

        editId.setText(text);

        editId.setFocusable(true);

    }
    interface CallBack{
        void signUp(String email,String password);
    }
}

