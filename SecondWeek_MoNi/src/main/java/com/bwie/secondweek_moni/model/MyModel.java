package com.bwie.secondweek_moni.model;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.bwie.secondweek_moni.sqlite.MyHelper;
import com.bwie.secondweek_moni.view_jiekou.LoginModelCallBack;
import com.bwie.secondweek_moni.view_jiekou.RegModelCallBack;

//model层模块,即对数据库操作的类
public class MyModel {
    //注册数据时的 访问数据库方法
    public void reg(Context context, String tel, String pwd, RegModelCallBack regModelCallBack){
        //注册时添加数据库数据
        MyHelper myHelper = new MyHelper(context);
        SQLiteDatabase database = myHelper.getWritableDatabase();
        database.execSQL("insert into user(tel,pwd) values(?,?)",new String[]{tel,pwd});
        database.close();
        //注册成功的回调
        regModelCallBack.regSuccess();
    }

    //登录时的 访问数据库方法
    public void login(Context context, String tel, String pwd, LoginModelCallBack loginModelCallBack){
        //登录时查询数据库数据
        MyHelper myHelper = new MyHelper(context);
        SQLiteDatabase database = myHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from user where tel = ? and pwd = ?", new String[]{tel, pwd});
        if (cursor.moveToNext()){
            //数据查询到时就成功回调方法
            loginModelCallBack.loginSuccess();
        }else {
            loginModelCallBack.loginFailed();
        }
    }
}
