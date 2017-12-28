package com.coder.allen.com.coolaop.Aop.impl;

import android.app.Activity;
import android.widget.Toast;

import com.coder.allen.com.coolaop.Aop.OnCreateWrapper;
import com.coder.allen.com.coolaop.LockUtils;

import bg.coder.allen.com.aopanimation.PointCut;

/**
 * Created by husongzhen on 17/11/28.
 */



public class BackPressedAop extends OnCreateWrapper {

    @PointCut(pointName = "onBackPressed")
    @Override
    public void excute() {
        Activity activity = (Activity) getRealObj();
        if (LockUtils.isOpen()) {
            Toast.makeText(activity, "before", Toast.LENGTH_SHORT).show();
        }
        this.proesss();
    }
}
