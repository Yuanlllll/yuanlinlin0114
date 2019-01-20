package com.example.dell.yuanlinlin0114.modle;

import com.example.dell.yuanlinlin0114.network.OKHttp;

public class LoginModel implements Imodel
{
    @Override
    public void getMdata(String url, String name, String pwd, final LoginCallBack loginCallBack)
    {
        OKHttp.DoPost(url, name, pwd, new OKHttp.ListCallBack() {
            @Override
            public void onSuccess(String s) {
                loginCallBack.getSuccess(s);

            }
        });


    }
}
