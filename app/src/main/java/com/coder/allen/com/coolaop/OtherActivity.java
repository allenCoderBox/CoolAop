package com.coder.allen.com.coolaop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.coder.allen.com.coolaop.Aop.impl.BackPressedAop;

import cool.coder.allen.com.coolaop.R;

public class OtherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(v -> {
            wraaper("husonghzne");
        });
    }


    private void wraaper(String name) {
        new BackPressedAop().setParams(this, "wraaper").setObjects(name).excute();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
