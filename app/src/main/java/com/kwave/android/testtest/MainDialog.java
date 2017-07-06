package com.kwave.android.testtest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by user on 2017-07-07.
 */

public class MainDialog extends Dialog {
    CallBack callBack;
    EditText editEmail,editPassword;
    ImageButton btnSignUp;
    String email,password;
    public MainDialog(@NonNull Context context) {
        super(context);
        this.callBack=(CallBack) context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dialog);
        setView();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSignUpOnclick();
            }
        });
    }
    public void dialogSignUpOnclick(){
        email = editEmail.getText().toString();
        password = editPassword.getText().toString();
        callBack.signUp(email,password);
        dismiss();
    }
    private void setView(){
        editEmail = (EditText)findViewById(R.id.editSignUpEmail);
        editPassword = (EditText)findViewById(R.id.editSignUpPassword);
        btnSignUp = (ImageButton) findViewById(R.id.btnSignupDialog);
    }
    interface CallBack{
        void signUp(String email,String password);
    }
}