package com.bawei.wangliyang.presenter;

import com.bawei.wangliyang.base.BasePresenter;
import com.bawei.wangliyang.contract.IContract;
import com.bawei.wangliyang.model.IModel;
import com.bawei.wangliyang.model.bean.Bean;
/*
 * 功能：P层
 * 作者：王黎杨
 * 时间：2019年12月31日09:43:29
 * */
public class IPresenter extends BasePresenter<IContract.IView>implements IContract.IPresenter {
    public IModel iModel;
    @Override
    protected void initModel() {
        //实例化M层
        iModel = new IModel();
    }

    @Override
    public void onGetData() {
        //调用M层数据传入V层
        iModel.onGetData(new IContract.IModel.IModelCallback() {
            @Override
            public void onSuccess(Bean bean) {
                view.onSuccess(bean);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onFailure(throwable);
            }
        });
    }
}
