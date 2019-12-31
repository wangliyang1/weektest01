package com.bawei.wangliyang.base;
/*
 * 功能：p层基类
 * 作者：王黎杨
 * 时间：2019年12月31日09:43:29
 * */
public abstract class BasePresenter <V> {
    protected V view;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void actach(V view) {
        this.view = view;
    }

    public void detach(){
        view = null;
    }
}
