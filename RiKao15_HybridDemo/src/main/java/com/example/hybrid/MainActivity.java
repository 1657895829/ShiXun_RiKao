package com.example.hybrid;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;
//主页面
public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button jsJava = (Button) findViewById(R.id.jsJava);
        Button javaJs = (Button) findViewById(R.id.javaJs);
        Button loadBaiDu = (Button) findViewById(R.id.loadBaiDu);
        webView = (WebView) findViewById(R.id.webView);

        // 启用javascript,设置web页面
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        //点击：加载百度页面，跳转能成功加载百度网页；
        loadBaiDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,WebActivity.class));
            }
        });

        //js调用了java
        jsJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionFromJs();
            }
        });

        //java调用js
        javaJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 无参数调用  android端调用js 方法
                webView.loadUrl("file:///android_asset/js_java.html");
                Toast.makeText(MainActivity.this, "java调用了js弹窗", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * js调用此方法
     */
    @android.webkit.JavascriptInterface
    public void actionFromJs() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/js_java.html");
                Toast.makeText(MainActivity.this, "js调用了Android函数，我是你旺哥", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * js调用此方法. 并且将参数传递过来
     *
     * @param str js  传递过来的参数
     */
    @android.webkit.JavascriptInterface
    public void actionFromJsWithParam(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "js调用了Android函数传递的参数：" + str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
