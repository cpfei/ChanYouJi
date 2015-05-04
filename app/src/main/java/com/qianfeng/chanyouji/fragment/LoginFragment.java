package com.qianfeng.chanyouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.qianfeng.chanyouji.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by aaa on 15-5-3.
 */
public class LoginFragment extends Fragment implements PlatformActionListener {
    private Button weibo_login, qq_login;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_login_fragment, container, false);
        init();
        return view;


        //获取第三方登录授权


    }

    private void init() {
        weibo_login = ((Button) view.findViewById(R.id.weibo_login));
        weibo_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //微博登录
                getRoot();

            }
        });
        qq_login = (Button) view.findViewById(R.id.qq_login);

        qq_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //qq登录
                getQqRoot();
            }
        });
    }

    public void getRoot() {
        //微博登录

        ShareSDK.initSDK(getActivity());
        Platform sinaWeibo = ShareSDK.getPlatform(getActivity(), SinaWeibo.NAME);
        sinaWeibo.setPlatformActionListener(this);
        sinaWeibo.showUser(null);//执行登录，登录后在回调里面获取用户资料
    }

    public void getQqRoot(){
        //qq登录
        ShareSDK.initSDK(getActivity());
        Platform qQ = ShareSDK.getPlatform(getActivity(), QQ.NAME);
        qQ.setPlatformActionListener(this);
        qQ.showUser(null);//执行登录，登录后在回调里面获取用户资料
    }




    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> stringObjectHashMap) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });




    }

    @Override
    public void onError(Platform platform, int i, final Throwable throwable) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "错误信息" + throwable.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCancel(Platform platform, int i) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "登录取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
