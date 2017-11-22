package com.bwie.framework;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bwie.framework.presenter.LoginPresenter;
import com.bwie.framework.view.LoginView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity  implements LoginView {
    private LoginPresenter presenter;
    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.regName)
    EditText regName;
    @Bind(R.id.regPwd)
    EditText regPwd;
    @Bind(R.id.regNow)
    Button regNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);

        //this 代表implements实现的LoginView类
        presenter = new LoginPresenter(this);
    }

    public void nameEmpty() {
        Toast.makeText(this, "姓名不得为空！", Toast.LENGTH_SHORT).show();
    }

    public void pwdEmpty() {
        Toast.makeText(this, "密码不得为空！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(Object object) {
        //当前为主线程，需建立一个子线程，执行更新UI操作
        /**
         * android Toast提示异常：java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
         错误原因：原来是在子线程弹Toast了，切记，Toast只能在UI线程弹出，如果一定要在子线程弹，那么就通过 new Handler(Looper.getMainLooper()) 来弹出。
         */
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void loginFailed(int code) {
        Toast.makeText(RegActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.back, R.id.regNow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent(RegActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.regNow:
                //点击按钮时调用Presenter层的login登录方法判断输入内容是否为空
                presenter.login(regName.getText().toString(),regPwd.getText().toString());
                break;
        }
    }
}
