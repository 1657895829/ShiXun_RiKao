package com.bwie.secondweek_moni.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bwie.secondweek_moni.R;
import com.bwie.secondweek_moni.presenter.MyPresenter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//注册页
public class Reg_Fragment extends Fragment {
    @Bind(R.id.tel_Reg)
    EditText telReg;
    @Bind(R.id.pwd_Reg)
    EditText pwdReg;
    @Bind(R.id.reg)
    Button reg;
    private MyPresenter myPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reg_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myPresenter = new MyPresenter(getActivity(), new MyPresenter.LoginViewCallBack() {
            @Override
            public void loginTelEmpty() {
            }

            @Override
            public void loginPwdEmpty() {
            }

            @Override
            public void loginSuccess() {
            }

            @Override
            public void loginFailed() {
            }
        }, new MyPresenter.RegViewCallBack() {
            @Override
            public void regTelEmpty() {
                Toast.makeText(getActivity(),"注册时 手机号不能为空",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void regPwdEmpty() {
                Toast.makeText(getActivity(),"注册时 密码不能为空",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void regSuccess() {
                Toast.makeText(getActivity(),"注册成功!请前往登录页面!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void regFailed() {
                Toast.makeText(getActivity(),"注册失败！请重新注册!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.reg)
    public void onViewClicked() {   //点击注册按钮 调用presenter层去逻辑判断非空
        myPresenter.reg_PanDuan(telReg.getText().toString(),pwdReg.getText().toString());
    }
}
