package com.bawei.wangliyang.model;

import com.bawei.wangliyang.contract.IContract;
import com.bawei.wangliyang.model.bean.Bean;
import com.bawei.wangliyang.util.NetUtil;
import com.google.gson.Gson;
/*
 * 功能：M层
 * 作者：王黎杨
 * 时间：2019年12月31日09:43:29
 * */
public class IModel implements IContract.IModel {
    //调用工具类 获取数据 并存入接口
    @Override
    public void onGetData(IModelCallback iModelCallback) {
        NetUtil.getInstance().getDataGet("http://blog.zhaoliang5156.cn/api/news/ranking.json", new NetUtil.MyCallback() {
            @Override
            public void onGetJson(String json) {
                Bean bean = new Gson().fromJson(json, Bean.class);
                iModelCallback.onSuccess(bean);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallback.onFailure(throwable);
            }
        });
    }
}
