package com.bwie.jpush;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {
    //声明变量
    private MainActivity    context ;
    private NotificationManager Manager ;
    private Bitmap LargeIcon ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置显示通知主界面的内容视图
        setContentView(R.layout.activity_main);

        //当前界面对象
        context = MainActivity.this;

        //查找发送和关闭通知按钮控件，并且为其设置点击监听事件，实现OnClickListener接口
        findViewById(R.id.Send).setOnClickListener(this);
        findViewById(R.id.Send).setOnClickListener(this);

        //获取通知管理类 NotificationManager 对象
        Manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        /**
         * BitmapFactory：工厂函数
         * decodeResource：解析资源（decode：解析；Resource：资源）
         */
        //获取大图标
        LargeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
    }

    //点击监听事件
    @Override
    public void onClick(View v) {
        //判断选择的通知按钮
        switch (v.getId()) {
            case R.id.Send:     //选择 发送通知 按钮时，执行 发送通知 方法
                send();
                break;

            case R.id.Close:
                Manager.cancel(6);    		 //选择 关闭通知 按钮时，执行通知管理类 NotificationManager 对象的.cancel()方法
                break;

            default:
                break;
        }

    }

    //发送通知 方法
    private void send() {
        /**
         * PendingIntent：等待执行的意图，对Intent进行包装，可以实现远程通信
         * Intent(Context packageContext, Class<?> cls)
         * packageContext：当前包名中的上下文对象
         * Class<?> cls：要跳转发送的类
         */
        Intent intent = new Intent(context, ReceiveActivity.class);
        /**
         * PendingIntent.getActivity(Context context,int requestCode,Intent intent , int flags) 方法
         * Context context：当前的上下文对象
         * int requestCode：发送的请求值
         * Intent intent ：跳转执行的对象
         * int flags：int类型的标志位数
         */
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        //构建一个 通知 Builder 类对象
        Notification.Builder builder = new Notification.Builder(context);

        //1. 设置标题
        builder.setContentTitle("今天下午放假哦 ~ ~ ~")
                //2. 设置内容
                .setContentText("太好啦，做自己想做的事情吧")
                //3. 设置大图标
                .setLargeIcon(LargeIcon)
                //4. 设置小图标
                .setSmallIcon(R.drawable.ku)
                //5. 设置时间(setWhen)  System.currentTimeMillis()：查看时间
                .setWhen(System.currentTimeMillis())
                //设置提示内容
                .setTicker("主人，您有一条新消息 . . .")
                //设置自动取消显示
                .setAutoCancel(true)
                //设置默认信息：Notification.DEFAULT_ALL:默认都有，声音、振动、闪光（必须 Notification.出来）
                .setDefaults(Notification.DEFAULT_ALL)
                /**
                 * 设置声音：需在 res目录下新建文件夹raw，内放音频文件
                 * 固定导入文件写法：
                 * Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.音频文件名称)
                 */
                .setSound(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sent_message))
                //设置通知内容实现的通信功能
                .setContentIntent(pendingIntent);

        //获取 通知 类对象（格式如同AlertDialog对话框）
        Notification notification = builder.build();
        /**
         * 使用 通知管理类 NotificationManager 对象发送通知
         * 【方法：Manager.notify(int id , Notification notification)】
         *  int id：这个id和点击监听事件的关闭通知 按钮的cancel()方法的id必须一致
         */
        Manager.notify(6, notification);
    }
}
