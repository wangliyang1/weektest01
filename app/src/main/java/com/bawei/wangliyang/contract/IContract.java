package com.bawei.wangliyang.contract;

import com.bawei.wangliyang.model.bean.Bean;
/*
 * 功能：契约类
 * 作者：王黎杨
 * 时间：2019年12月31日09:43:29
 * */
public interface IContract {
    interface IView{
        void onSuccess(Bean bean);
        void onFailure(Throwable throwable);
    }
    interface IPresenter{
        void onGetData();
    }
    interface IModel{
        void onGetData(IModelCallback iModelCallback);
        interface IModelCallback{
            void onSuccess(Bean bean);
            void onFailure(Throwable throwable);
        }
    }
}
