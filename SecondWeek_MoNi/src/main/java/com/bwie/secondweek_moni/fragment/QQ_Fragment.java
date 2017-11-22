package com.bwie.secondweek_moni.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.secondweek_moni.MainActivity;
import com.bwie.secondweek_moni.R;
import com.bwie.secondweek_moni.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
//个人中心页
public class QQ_Fragment extends Fragment {
    @Bind(R.id.headPhoto)
    ImageView headPhoto;
    @Bind(R.id.userName)
    TextView userName;
    @Bind(R.id.gener)
    TextView gener;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.outLogin)
    Button outLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qq_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取传递信息
        Intent intent = getActivity().getIntent();
        String icon = intent.getStringExtra("headPhoto");
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("gender");

        //设置用户信息
        String addRess = intent.getStringExtra("address");
        ImageLoader.getInstance().displayImage(icon,headPhoto, ImageLoaderUtil.getDefaultOption());
        /*Glide.with(this).load(icon).into(headPhoto);*/
        userName.setText("      "+name);
        gener.setText("      "+sex);
        address.setText("      "+addRess);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //退出登录到主页
    @OnClick(R.id.outLogin)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MainActivity.class));
    }
}
