package www.flybrowser.net.flybrowser.webview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.nineoldandroids.view.ViewPropertyAnimator;

import www.flybrowser.net.flybrowser.R;
import www.flybrowser.net.flybrowser.constant.Constants;
import www.flybrowser.net.flybrowser.utils.IntentUtils;
import www.flybrowser.net.flybrowser.view.WebViewContainter;
import www.flybrowser.net.flybrowser.view.stickhelp.ViewHelper;

/**
 * 带titlebar 的webView
 * Created by ferris on 2015/10/24.
 */
public class FlyBrowserView extends FrameLayout implements WebViewContainter.WebViewScrollCallbacks{

    private WebView mWebView;
    private WebViewContainter mWebViewContainter;
    private FrameLayout webview_titlebar;
    private int titlebar_hight=0;
    private  IntentUtils mIntentUtils;

    public FlyBrowserView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public FlyBrowserView(Context context) {
        this(context, null);
        init();
    }
    public FlyBrowserView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init(){
        titlebar_hight=getResources().getDimensionPixelSize(R.dimen.titlebar_hight);
    }
    public static FlyBrowserView getXml(Context mContext){
        return (FlyBrowserView)LayoutInflater.from(mContext).inflate(R.layout.browser_view,null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mWebViewContainter=(WebViewContainter)findViewById(R.id.webview_containter);
        webview_titlebar=(FrameLayout)findViewById(R.id.webview_titlebar);
        mWebView=(WebView)findViewById(R.id.mWebView);
        mIntentUtils = new IntentUtils((Activity)getContext());
        mWebView.setWebViewClient(new LightningChromeClient());

        WebSettings settings = mWebView.getSettings();
        mWebView.getSettings().setUserAgentString(Constants.DESKTOP_USER_AGENT);

        mWebView.getSettings().setJavaScriptEnabled(true);
// 设置可以支持缩放
        mWebView.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true);
//扩大比例的缩放
        mWebView.getSettings().setUseWideViewPort(true);
//自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);


        mWebView.loadUrl("http://www.sina.com.cn/");
        mWebViewContainter.addCallbacks(this);
    }

    @Override
    public void onScrollChanged() {
        int scrollY=Math.min(mWebViewContainter.getScrollY(),titlebar_hight);
        ViewHelper.setTranslationY(webview_titlebar, -scrollY);
    }

    @Override
    public void onScrollUp() {
            startAnimation(webview_titlebar, -titlebar_hight);
    }

    @Override
    public void onScrollDown() {
            startAnimation(webview_titlebar, 0);
    }

    private void startAnimation(View view, int translationY) {
        ViewPropertyAnimator.animate(view).cancel();

        ViewPropertyAnimator.animate(view).translationY(translationY);
    }

    public class LightningChromeClient extends WebViewClient {


        LightningChromeClient() {

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return mIntentUtils.startActivityForUrl(mWebView, url);
        }
    }
}
