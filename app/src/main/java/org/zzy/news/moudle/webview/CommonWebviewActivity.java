package org.zzy.news.moudle.webview;


import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.github.mzule.activityrouter.annotation.Router;

import org.zzy.aframwork.base.BaseActivity;
import org.zzy.news.R;

@Router("webview")
public class CommonWebviewActivity extends BaseActivity {

    private TextView tv_title;
    private String url,title;
    private WebView webview;
    private Context context;

    private static final String DEFAULT_URL = "http://www.sohu.com";
/****************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        findViews();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void findViews() {
        setContentView(R.layout.webview);
//        tv_title = (TextView) findViewById(R.id.tv_title);
        url = getIntent().getStringExtra("url");
//        title = getIntent().getStringExtra("title");
//        tv_title.setText(title);
        webview = (WebView) findViewById(R.id.webView);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        WebSettings settings = webview.getSettings();
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webview.addJavascriptInterface(new JavaScriptInterface(), "jsObj");

        webview.loadUrl(url);
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                ToastUtils.showToast(context,"onKey keyCode:"+keyCode);
                finish();
                return false;
            }
        });


    }
    class JavaScriptInterface {
        JavaScriptInterface() {
        }
        @JavascriptInterface
        public void back() {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
