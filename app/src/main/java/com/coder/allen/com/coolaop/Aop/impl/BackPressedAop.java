package com.coder.allen.com.coolaop.Aop.impl;

import android.app.Activity;
import android.widget.Toast;

import com.coder.allen.com.coolaop.Aop.OnCreateWrapper;

/**
 * Created by husongzhen on 17/11/28.
 */

public class BackPressedAop extends OnCreateWrapper {
    @Override
    public void excute() {
        Activity activity = (Activity) getRealObj();
        Toast.makeText(activity, "before", Toast.LENGTH_SHORT).show();
        this.proesss();
    }
}
