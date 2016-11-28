package android.marshon.likequanmintv.utils;

import android.app.Activity;
import android.content.Intent;
import android.marshon.likequanmintv.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;


public class WebContainerActivity extends AppCompatActivity implements View.OnClickListener {


    private String title;
    private String url;

    private WebView mWebView;
    private ImageView imgBack;
    private boolean isAd;
    private ImageView imgForward;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_web_container);
        SystemBarUtils.setStatusBarTranslate(this,R.color.colorPrimary);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        imgForward=(ImageView)findViewById(R.id.imgForward);
        tvTitle=(TextView)findViewById(R.id.title);
        imgBack.setOnClickListener(this);
        imgForward.setOnClickListener(this);

        mWebView= (WebView) findViewById(R.id.mWebView);
        Bundle bundle = getIntent().getExtras();
        if (null!=bundle){
            title=bundle.getString(Intent.EXTRA_TITLE,"全民tv");
            url=bundle.getString(Intent.EXTRA_TEXT,"");
            isAd=bundle.getBoolean("isAd",false);

            tvTitle.setText(""+title);
        }
        if (isAd){
            imgForward.setVisibility(View.VISIBLE);
            imgBack.setVisibility(View.GONE);
        }

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
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:
                setResult(Activity.RESULT_OK);
                finish();
                break;
            case R.id.imgForward:
                setResult(Activity.RESULT_OK);
                finish();
                break;
        }

    }
}
