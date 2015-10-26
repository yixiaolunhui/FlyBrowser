package www.flybrowser.net.flybrowser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import www.flybrowser.net.flybrowser.controller.BrowserController;
import www.flybrowser.net.flybrowser.view.SearchFrameLayout;
import www.flybrowser.net.flybrowser.view.StickFrameLayout;
import www.flybrowser.net.flybrowser.view.StickyScrollView;
import www.flybrowser.net.flybrowser.view.stickhelp.StickyScrollViewCallbacks;
import www.flybrowser.net.flybrowser.view.stickhelp.StickyScrollViewGlobalLayoutListener;
import www.flybrowser.net.flybrowser.webview.FlyBrowserView;
import www.flybrowser.net.flybrowser.webview.FlyingView;

/**
 * Created by ferris on 2015/10/23.
 */
public abstract class BrowserActivity extends AppCompatActivity implements BrowserController{

    private StickyScrollView browser_scrollview_layout;
    private StickFrameLayout top_titlerbar_layout;
    private View browser_scrollview_head_search_bar;
    private int titlebar_hight=0,head_hight=0,max_scrolly=0;
    private StickyScrollViewCallbacks mCallbacks;

    private FrameLayout main_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
    }

    //初始化配置
    private void init() {
        head_hight=getResources().getDimensionPixelSize(R.dimen.browser_scrollview_head_fl_hight);
        titlebar_hight=getResources().getDimensionPixelSize(R.dimen.titlebar_hight);
        max_scrolly=head_hight-titlebar_hight;
        top_titlerbar_layout.setMaxScroll(max_scrolly);
        browser_scrollview_layout.setMaxScroll(max_scrolly);
    }

    private void findView() {
        browser_scrollview_layout=(StickyScrollView)findViewById(R.id.browser_scrollview_layout);
        top_titlerbar_layout=(StickFrameLayout)findViewById(R.id.top_titlerbar_layout);
        browser_scrollview_head_search_bar=findViewById(R.id.browser_scrollview_head_search_bar);
        mCallbacks = new StickyScrollViewCallbacks(top_titlerbar_layout, browser_scrollview_head_search_bar,
                null, browser_scrollview_layout);
        mCallbacks.setEnableSticky(true);
        browser_scrollview_layout.addCallbacks(mCallbacks);
        browser_scrollview_layout.getViewTreeObserver().addOnGlobalLayoutListener(
                new StickyScrollViewGlobalLayoutListener(mCallbacks));
        top_titlerbar_layout.setScrollView(browser_scrollview_layout);

        //test main containter
        main_container=(FrameLayout)findViewById(R.id.main_container);

        main_container.postDelayed(new Runnable() {
            @Override
            public void run() {

                FlyingView mFlyingView = new FlyingView(BrowserActivity.this, "http://www.baidu.com", false, false, BrowserActivity.this);

                main_container.addView(mFlyingView.getBrowserView());

            }
        }, 2000);
    }
    public abstract boolean isIncognito();
    public abstract void updateCookiePreference();
    public abstract void closeActivity();


    public void openSearch(){
        main_container.addView(SearchFrameLayout.getXml(this));
    }

    public void handleNewIntent(Intent intent) {

        String url = null;
        if (intent != null) {
            url = intent.getDataString();
        }
        int num = 0;
        String source = null;
        if (intent != null && intent.getExtras() != null) {
            num = intent.getExtras().getInt(getPackageName() + ".Origin");
            source = intent.getExtras().getString("SOURCE");
        }
//        if (num == 1) {
//            loadUrlInCurrentView(url);
//        } else if (url != null) {
//            if (url.startsWith(Constants.FILE)) {
//                Utils.showSnackbar(this, R.string.message_blocked_local);
//                url = null;
//            }
//            newTab(url, true);
//            mIsNewIntent = (source == null);
//        }
    }

    @Override
    public void updateUrl(String title, boolean shortUrl) {

    }

    @Override
    public void updateProgress(int n) {

    }

    @Override
    public void updateHistory(String title, String url) {

    }

    @Override
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {

    }

    @Override
    public void updateTabs() {

    }

    @Override
    public void onLongPress() {

    }

    @Override
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {

    }

    @Override
    public void onHideCustomView() {

    }

    @Override
    public Bitmap getDefaultVideoPoster() {
        return null;
    }

    @Override
    public View getVideoLoadingProgressView() {
        return null;
    }

    @Override
    public void onCreateWindow(Message resultMsg) {

    }

    @Override
    public void onCloseWindow(FlyingView view) {

    }

    @Override
    public void hideActionBar() {

    }

    @Override
    public void showActionBar() {

    }

    @Override
    public void longClickPage(String url) {

    }

    @Override
    public void openBookmarkPage(WebView view) {

    }

    @Override
    public void showFileChooser(ValueCallback<Uri[]> filePathCallback) {

    }

    @Override
    public void closeEmptyTab() {

    }

    public void search(String text){
        FlyingView mFlyingView = new FlyingView(BrowserActivity.this, text, false, false, BrowserActivity.this);

        main_container.addView(mFlyingView.getBrowserView());
    }

    @Override
    public boolean proxyIsNotReady() {
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            for(int i=0;i<main_container.getChildCount();i++){
                if(main_container.getChildAt(i) instanceof SearchFrameLayout){
                    main_container.removeViewAt(i);
                    return true;
                }
                if(main_container.getChildAt(i) instanceof FlyBrowserView){
                    main_container.removeViewAt(i);
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
