package com.coder.allen.com.coolaop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cool.coder.allen.com.coolaop.R;

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
