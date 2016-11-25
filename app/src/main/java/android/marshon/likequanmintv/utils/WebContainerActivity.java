package android.marshon.likequanmintv.utils;

import android.app.Activity;
import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.marshon.likequanmintv.mvp.main.MainActivity;
import android.marshon.likequanmintv.start.SplashActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;




public class WebContainerActivity extends BaseActivity {


    private String title;
    private String url;

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_container);
        mWebView= (WebView) findViewById(R.id.mWebView);
        Bundle bundle = getIntent().getExtras();
        if (null!=bundle){
            title=bundle.getString(Intent.EXTRA_TITLE,"全民tv");
            url=bundle.getString(Intent.EXTRA_TEXT,"");
        }
        setToolBarTitle(title);

        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings settings = mWebView.getSettings();
        //允许使用js
        settings.setJavaScriptEnabled(true);
        //支持缩放
        settings.setSupportZoom(true);
//        //出现缩放工具
        settings.setBuiltInZoomControls(true);
        //
        settings.setUseWideViewPort(true);
        //
        settings.setLoadWithOverviewMode(true);
        //让网页自适应屏幕宽度
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.loadUrl(url);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (null!=mWebView&&mWebView.canGoBack()){
                mWebView.goBack();
                return true;
            }

            setResult(Activity.RESULT_OK);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
