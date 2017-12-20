package com.coder.allen.com.coolaop.rx;

import org.w3c.dom.Text;


/**
 * 作者：husongzhen on 17/12/20 09:50
 * 邮箱：husongzhen@musikid.com
 */

public class RxSender<T> {

    private RxOutput<T> output;


    public void sender(RxInput<T> input) {
        input.excute(output);
    }


    public RxSender<T> bindOutPut(RxOutput<T> output) {
        this.output = output;
        return this;
    }


}
