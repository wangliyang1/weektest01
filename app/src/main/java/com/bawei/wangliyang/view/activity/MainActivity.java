package com.bawei.wangliyang.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.wangliyang.R;
import com.bawei.wangliyang.base.BaseActivity;
import com.bawei.wangliyang.contract.IContract;
import com.bawei.wangliyang.model.bean.Bean;
import com.bawei.wangliyang.presenter.IPresenter;
import com.bawei.wangliyang.util.NetUtil;
import com.bawei.wangliyang.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
 * 功能：首页
 * 作者：王黎杨
 * 时间：2019年12月31日09:43:29
 * */
public class MainActivity extends BaseActivity<IPresenter> implements IContract.IView {


    @BindView(R.id.main_dian)
    TextView mainDian;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void initData() {
       if (NetUtil.getInstance().isWang(this)){
           mPresenter.onGetData();
       }else {
           Toast.makeText(this, "鱼死网破了", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected IPresenter provitePresenter() {
        return new IPresenter();
    }

    @Override
    protected int layoutId() {
        //绑定布局
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Bean bean) {
        //获取成功后 设置适配器
        List<Bean.RankingBean> ranking = bean.getRanking();
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        MyAdapter myAdapter = new MyAdapter(ranking);
        recycler.setAdapter(myAdapter);
        //点击吐司名称
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(String s) {
                Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFailure(Throwable throwable) {
        //失败后调用此方法
        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.main_dian)
    public void onViewClicked() {
        //点击跳转页面
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }
}
