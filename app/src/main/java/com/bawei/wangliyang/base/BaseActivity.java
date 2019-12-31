package com.bawei.wangliyang.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei.wangliyang.R;

import butterknife.ButterKnife;
/*
* 功能：activity基类
* 作者：王黎杨
* 时间：2019年12月31日09:43:29
* */
public abstract class BaseActivity <P extends BasePresenter>extends AppCompatActivity {
    public P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ButterKnife.bind(this);
        mPresenter = provitePresenter();
        if (mPresenter!=null){
            mPresenter.actach(this);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P provitePresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detach();
        }
    }
}
