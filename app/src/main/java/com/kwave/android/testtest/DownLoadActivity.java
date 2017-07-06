package com.kwave.android.testtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kwave.android.circleprogress.CircleSeekBar;

public class DownLoadActivity extends AppCompatActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int value = msg.what;
            mProgress.setCurProcess(value);
        }
    };

    private CircleSeekBar mProgress;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);

        mProgress = (CircleSeekBar) findViewById(R.id.progress);
        mTextView = (TextView) findViewById(R.id.textview);

        mProgress.setOnSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onChanged(CircleSeekBar seekbar, int curValue) {    // progresss가 진행 되면서 변경되는 사항들 넣기
                mTextView.setText((curValue/10)+" 초");     // 원에 들어가는 글자.
            }
        });

        mProgress.setCurProcess(0);        // start 지점 - 10이면 1초부터  시작

//        mProgress.setPointerColor(R.color.colorAccent);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }


    private void start() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    mHandler.sendEmptyMessage(i);
                    SystemClock.sleep(100);         // 100 = 10초    // 10 = 1초
                }
            }
        }.start();
    }

}
