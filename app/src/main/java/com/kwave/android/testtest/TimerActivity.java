package com.kwave.android.testtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwave.android.circleprogress.CircleSeekBar;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener{

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int value = msg.what;
            mProgress.setCurProcess(value);
        }
    };

    private CircleSeekBar mProgress;
    ImageView btnGoInfo;
    TextView textRest, textPlay;
    private ImageButton mIBView;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        btnGoInfo = (ImageView) findViewById(R.id.btnGoInfo);
        textRest = (TextView) findViewById(R.id.textFood);
        textPlay = (TextView) findViewById(R.id.textPlay);
        mIBView = (ImageButton) findViewById(R.id.image);
        mProgress = (CircleSeekBar) findViewById(R.id.progress);
        mProgress.setOnSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onChanged(CircleSeekBar seekbar, int curValue) {    // progresss가 진행 되면서 변경되는 사항들 넣기
                textPlay.setText((curValue/10)+" 초");     // 원에 들어가는 글자.
//                if(curValue == mMaxProcess)
                mIBView.setBackground(null);
                mIBView.setImageResource(R.drawable.run);
            }

        });

        mProgress.setCurProcess(0);        // start 지점 - 10이면 1초부터  시작
        mIBView.setBackground(null);
        mIBView.setImageResource(R.drawable.walk);
//        mProgress.setPointerColor(R.color.colorAccent);
        btnGoInfo.setOnClickListener(this);
        mIBView.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image :
                start();
                break;
            case R.id.btnGoInfo :
                Intent intent = new Intent(TimerActivity.this, InformationActivity.class);
                startActivity(intent);
            case R.id.imageRunning:
            case R.id.imageWalk :
                setTimerDialog();
                break;
        }
    }


    private void start() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    mHandler.sendEmptyMessage(i);
                    SystemClock.sleep(100);         // 100 = 10초    // 10 = 1초
//                    mIBView.setImageResource(R.drawable.walking);
                }
            }
        }.start();
    }

    private void setTimerDialog() {

        final TimerDialog dialog = new TimerDialog(this);



        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override

            public void onShow(DialogInterface dia) {

                dialog.setMode("닉네임");

                dialog.setText("예제");

            }

        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override

            public void onDismiss(DialogInterface dia) {

                textRest.setText(dialog.getMode());
                textPlay.setText(dialog.getText());

                mIBView.setImageResource(R.drawable.walk);

            }

        });



        dialog.show();

    }




}
