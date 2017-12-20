package com.coder.allen.com.coolaop.rx;

/**
 * 作者：husongzhen on 17/12/20 11:01
 * 邮箱：husongzhen@musikid.com
 */

public class RxFlow<T> {
    private RxInput<T> base;

    public RxFlow(RxInput<T> base) {
        this.base = base;
    }

    public <M> RxFlow<M> map(Func<T, M> func) {
        return new RxFlow<>(output -> base.excute(t -> output.excute(func.action(t))));
    }


    public void bind(RxOutput<T> output) {
        base.excute(output);
    }
}
