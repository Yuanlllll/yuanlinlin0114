package com.example.dell.yuanlinlin0114.presenter;

import com.example.dell.yuanlinlin0114.MainActivity;
import com.example.dell.yuanlinlin0114.api.Api;
import com.example.dell.yuanlinlin0114.modle.Imodel;
import com.example.dell.yuanlinlin0114.modle.LoginModel;

public class LoginPresenter implements Ipresenter
{
    MainActivity mview;
    private final LoginModel loginModel;

    public LoginPresenter(MainActivity mview) {
        this.mview = mview;
        loginModel = new LoginModel();
    }

    @Override
    public void getPdata(String name, String pwd)
    {
        loginModel.getMdata(Api.LOGIN, name, pwd, new Imodel.LoginCallBack() {
            @Override
            public void getSuccess(String data) {
                mview.getVdata(data);
            }

            @Override
            public void getFail() {
                mview.getVdata("加载失败");

            }
        });

    }

    public void destory()
    {
        if(mview!=null)
        {
            mview=null;
        }
    }
}
