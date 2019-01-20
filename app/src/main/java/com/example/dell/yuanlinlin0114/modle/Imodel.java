package com.example.dell.yuanlinlin0114.modle;

public interface Imodel
{
    void getMdata(String url,String name,String pwd,LoginCallBack loginCallBack);
    interface LoginCallBack
    {
        void getSuccess(String data);
        void getFail();
    }
}
