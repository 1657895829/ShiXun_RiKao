package com.bwie.framework;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bwie.framework.presenter.LoginPresenter;
import com.bwie.framework.view.LoginView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoginView {
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.pwd)
    EditText pwd;
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.reg)
    Button reg;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Toast.makeText(MainActivity.this, "数据正确，跳转至下一页面！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void loginFailed(int code) {
        Toast.makeText(MainActivity.this, "数据获取失败！", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当前页面关闭，销毁 Presenter层 中所持有的LoginView 对象
        presenter.detach();
    }

    @OnClick({R.id.btn, R.id.reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                //点击按钮时调用Presenter层的login登录方法判断输入内容是否为空
                presenter.login(name.getText().toString(),pwd.getText().toString());
                break;
            case R.id.reg:
                Intent intent = new Intent(MainActivity.this, RegActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
