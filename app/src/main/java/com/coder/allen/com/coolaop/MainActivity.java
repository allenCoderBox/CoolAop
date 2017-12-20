package com.coder.allen.com.coolaop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.coder.allen.com.coolaop.rx.Func;
import com.coder.allen.com.coolaop.rx.RxFlow;
import com.coder.allen.com.coolaop.rx.RxInput;
import com.coder.allen.com.coolaop.rx.RxOutput;
import com.coder.allen.com.coolaop.rx.RxSender;

import cool.coder.allen.com.coolaop.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class MainActivity extends AppCompatActivity {


    private String name;

    public String getName() {
        return name;
    }

    public MainActivity setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(v -> {
            startActivity(new Intent(this, OtherActivity.class));
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void onBackPressed(String name) {
        super.onBackPressed();
    }

}
