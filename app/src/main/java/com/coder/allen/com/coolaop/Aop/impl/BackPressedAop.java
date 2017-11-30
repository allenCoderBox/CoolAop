package com.coder.allen.com.coolaop.Aop.impl;

import android.app.Activity;
import android.widget.Toast;

import com.coder.allen.com.coolaop.Aop.OnCreateWrapper;
import com.coder.allen.com.coolaop.LockUtils;

import bg.coder.allen.com.aopanimation.AopAction;
import bg.coder.allen.com.aopanimation.PointCut;

/**
 * Created by husongzhen on 17/11/28.
 */


@PointCut(point = "onBackPressed", action = AopAction.M_REPLACE,
        imports = {BackPressedAop.class, LockUtils.class, OnCreateWrapper.class})
public class BackPressedAop extends OnCreateWrapper {
    @Override
    public void excute() {
        Activity activity = (Activity) getRealObj();
        Toast.makeText(activity, "before", Toast.LENGTH_SHORT).show();
        this.proesss();
    }
}
