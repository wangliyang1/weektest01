package com.bawei.wangliyang.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.wangliyang.R;
import com.bawei.wangliyang.base.BaseActivity;
import com.bawei.wangliyang.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
 * 功能：第二个页面
 * 作者：王黎杨
 * 时间：2019年12月31日09:43:29
 * */
public class SecondActivity extends BaseActivity {

    @BindView(R.id.second_image)
    ImageView secondImage;
    @BindView(R.id.second_wei)
    Button secondWei;
    @BindView(R.id.second_qq)
    Button secondQq;

    @Override
    protected void initData() {
        //创建二维码
        Bitmap bitmap = CodeUtils.createImage("王黎杨", 400, 400, null);
        secondImage.setImageBitmap(bitmap);
        //设置长按事件
        secondImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(secondImage, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondActivity.this, "成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SecondActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected void initView() {
        CodeUtils.init(this);
    }

    @Override
    protected BasePresenter provitePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }

    //点击事件
    @OnClick({R.id.second_wei, R.id.second_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.second_wei:
                EventBus.getDefault().post("微信");
                break;
            case R.id.second_qq:
                EventBus.getDefault().post("QQ");
                break;
        }
    }
    //绑定
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    //解绑
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    //接收数据并吐司
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onEvent(String s){
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onEvent1(String s){
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }
}
