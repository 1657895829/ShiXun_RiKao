package com.bwie.ShiXun_RiKao;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.bwie.ShiXun_RiKao.Activity.LoginActivity;
import com.bwie.ShiXun_RiKao.Activity.SubActivity;

public class MainActivity extends AppCompatActivity {
    private EditText tel;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tel = (EditText) findViewById(R.id.tel);
        pwd = (EditText) findViewById(R.id.pwd);
    }

    public void login(View view) {
        //判断是否为手机号,本地使用正则表达式校验手机号为合法手机号
        String number = tel.getText().toString();
        boolean mobile = isMobile(number);
        if(TextUtils.isEmpty(tel.getText().toString()) || TextUtils.isEmpty(pwd.getText().toString())){
            Toast.makeText(MainActivity.this, "手机号或者密码不能为空！", Toast.LENGTH_SHORT).show();
        }else if (mobile) {
            Toast.makeText(MainActivity.this, "手机号合法！", Toast.LENGTH_SHORT).show();
            //本地校验密码不能少于六位数
            if (pwd.getText().toString().length() < 6) {
                Toast.makeText(MainActivity.this, "密码长度不得少于6位！", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }else if (!mobile) {
            Toast.makeText(MainActivity.this, "手机号不合法！请重新输入", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 验证手机格式
     */
    private static boolean  isMobile(String number) {
            /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][358]\\d{9}";//表示手机号一共11位，"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    public void sub(View view) {
        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        startActivity(intent);
    }
}
