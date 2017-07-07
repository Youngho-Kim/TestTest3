package com.kwave.android.testtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kwave.android.circleprogress.CircleSeekBar;
import com.kwave.android.testtest.domain.UserInformation;

import static com.kwave.android.testtest.MainActivity.key;


public class TimerActivity extends AppCompatActivity implements TimerDialog.CallBack {
    private CircleSeekBar mProgress;
    private ImageButton mIBView,btnTimerDialog;
    private TextView txtSprintTime,txtWalkTime;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    boolean isSprintTime = true;
    int sprintTime,walkingTime;
    UserInformation value;
    String email,name;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("userInfo");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        setView();
        findViewById(R.id.btnGoList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //여기 인텐트 전달좀
                Intent intent = new Intent(TimerActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.imgTimerDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TimerDialog dialog = new TimerDialog(TimerActivity.this);
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {

                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
                dialog.show();
            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
        getUserProfile();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.child(key).getValue(UserInformation.class);
                if(value.timeSprint!=null&&value.timeWalk!=null) {
                    txtSprintTime.setText(value.timeSprint);
                    txtWalkTime.setText(value.timeWalk);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Timer", "Failed to read value.", error.toException());
            }
        });
        mProgress.setOnSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onChanged(CircleSeekBar seekbar, int curValue) {
                if(isSprintTime != true) {
                    mIBView.setImageResource(R.drawable.walking);
                }else{
                    mIBView.setImageResource(R.drawable.runing);
                }
            }
        });

        mProgress.setCurProcess(0);        // start 지점 - 10이면 1초부터  시작

        findViewById(R.id.imagebtnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSprintTime != true) {
                    mIBView.setImageResource(R.drawable.walking);

                    start();
                }else{
                    mIBView.setImageResource(R.drawable.runing);
                    start();
                }

            }
        });
    }
    public void getUserProfile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            name = user.getDisplayName();
            email = user.getEmail();
        }
    }
    public void setView(){
        mProgress = (CircleSeekBar) findViewById(R.id.progress);
        mIBView = (ImageButton) findViewById(R.id.imagebtnStart);
        txtSprintTime = (TextView) findViewById(R.id.txtSprintTime);
        txtWalkTime = (TextView) findViewById(R.id.txtWalkTime);
    }

    @Override
    public void timeSet(String walkTime, String sprintTime) {
        myRef.child(key).child("timeSprint").setValue(sprintTime+"");
        myRef.child(key).child("timeWalk").setValue(walkTime+"");
        txtWalkTime.setText(walkTime);
        txtSprintTime.setText(sprintTime);
        this.sprintTime = Integer.parseInt(sprintTime);
        this.walkingTime = Integer.parseInt(walkTime);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int value = msg.what;
            mProgress.setCurProcess(value);
        }
    };
    private void start() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    mHandler.sendEmptyMessage(i);
                    if(isSprintTime == true) {
                        SystemClock.sleep(sprintTime*10);         // 100 = 10초    // 10 = 1초
                    }else{
                        SystemClock.sleep(walkingTime*10);
                    }
                }
            }
        }.start();
    }
}