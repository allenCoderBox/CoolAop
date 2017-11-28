package com.coder.allen.com.coolaop.Aop;

import android.support.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by husongzhen on 17/11/28.
 */

public abstract class OnCreateWrapper {
    private static final String TAG = "proxy";
    private Object realObj;
    private String methodName;
    private Object[] objects;

    public OnCreateWrapper setParams(Object readObj, String method) {
        realObj = readObj;
        this.methodName = method;
        return this;
    }


    public OnCreateWrapper setObjects(Object... objects) {
        this.objects = objects;
        return this;
    }

    public abstract void excute();

    protected Object proesss() {
        try {
            int size = objects.length;
            Class[] classes = getTypeClasses(size);
            Method method = realObj.getClass().getMethod(methodName + "$impl", classes);
            return method.invoke(realObj, objects);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    private Class[] getTypeClasses(int size) {
        Class[] classes = new Class[size];
        int i = 0;
        for (i = 0; i < size; i++) {
            classes[i] = objects[i].getClass();
        }
        return classes;
    }


    public Object getRealObj() {
        return realObj;
    }
}
