package www.flybrowser.net.flybrowser.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import www.flybrowser.net.flybrowser.R;

/**
 * 带titlebar 的webView
 * Created by ferris on 2015/10/24.
 */
public class FlyBrowserView extends FrameLayout{

    private WebView mWebView;

    public FlyBrowserView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlyBrowserView(Context context) {
        this(context, null);
    }
    public FlyBrowserView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public static FlyBrowserView getXml(Context mContext){
        return (FlyBrowserView)LayoutInflater.from(mContext).inflate(R.layout.browser_view,null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mWebView=(WebView)findViewById(R.id.mWebView);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www.baidu.com/");
    }
}
