package com.example.dell.yuanlinlin0114.network;

import android.util.Log;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttp
{

    private final OkHttpClient client;
    private static volatile OKHttp instance;
    private static Request request;

    private Interceptor getInterceptor()
    {
        Interceptor interceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.e("+++++++","拦截前");
                Response response = chain.proceed(request);
                Log.e("+++++++","拦截后");
                return response;
            }
        };
        return interceptor;
    }

    public OKHttp()
    {
        client = new OkHttpClient().newBuilder()
                .readTimeout(3000,TimeUnit.SECONDS)
                .connectTimeout(3000,TimeUnit.SECONDS)
                .addInterceptor(getInterceptor())
                .build();
    }
    public static OKHttp getInstance()
    {
        if (instance==null)
        {
            synchronized (OKHttp.class)
            {
                if (instance==null)
                {
                    instance=new OKHttp();
                }
            }

        }
        return instance;
    }

//post请求
    public static void DoPost(String url, String name, String pwd, final ListCallBack listCallBack)
    {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody builder=new FormBody.Builder()
                .add("mobile",name)
                .add("password",pwd)
                .build();
        request = new Request.Builder().url(url).post(builder).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                listCallBack.onSuccess(response.body().string());

            }
        });

    }
    public interface ListCallBack
    {
        void onSuccess(String s);
    }











}
