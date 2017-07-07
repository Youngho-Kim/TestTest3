package com.kwave.android.testtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kwave.android.testtest.domain.UserInformation;

public class MainActivity extends AppCompatActivity implements MainDialog.CallBack,View.OnClickListener{


    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("userInfo");
    ImageButton btnSignIn;
    ImageButton btnSignUp;
    EditText editId, editPw;
    String email,password;
    public static String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Toast.makeText(MainActivity.this, "환영합니다", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "뭔가잘못됬습니다", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignInLogin:
                signInOnClick();
                break;
            case R.id.btnSignupLogin:
                signUpOnClick();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authStateListener);
    }

    private void setView(){
        btnSignIn = (ImageButton) findViewById(R.id.btnSignInLogin);
        editId = (EditText) findViewById(R.id.id);
        editPw = (EditText) findViewById(R.id.pw);
        btnSignUp = (ImageButton) findViewById(R.id.btnSignupLogin);
        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }
    private void signIn(String id, String pw){
        auth.signInWithEmailAndPassword(id,pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "잘못되었습니다", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "로그인 되었습니다", Toast.LENGTH_SHORT).show();
                            UserInformation userInformation = new UserInformation("10","10","10","10");
                            String childKey = myRef.push().getKey();
                            key = childKey;
                            myRef.child(childKey).setValue(userInformation);
                            goTimerActivity();
                        }
                    }
                });
    }
    private void goTimerActivity(){
        Intent intent = new Intent(this,TimerActivity.class);
        startActivity(intent);
        finish();
    }

    private void signInOnClick(){
        email = editId.getText().toString();
        password = editPw.getText().toString();
        signIn(email,password);
    }

    private void signUpOnClick(){
        final MainDialog dialog = new MainDialog(this);
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

    @Override
    public void signUp(String email,String password) {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "잘못됌", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "등록되었음", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}