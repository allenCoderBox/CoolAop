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


@PointCut(pointName = "onBackPressed", action = AopAction.M_REPLACE, importsPool = {})
public class BackPressedAop extends OnCreateWrapper {
    @Override
    public void excute() {
        Activity activity = (Activity) getRealObj();
        if (LockUtils.isOpen()){
            Toast.makeText(activity, "before", Toast.LENGTH_SHORT).show();
        }
        this.proesss();
    }
}
