package www.flybrowser.net.flybrowser;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import www.flybrowser.net.flybrowser.controller.BrowserController;
import www.flybrowser.net.flybrowser.view.StickFrameLayout;
import www.flybrowser.net.flybrowser.view.StickyScrollView;
import www.flybrowser.net.flybrowser.view.stickhelp.StickyScrollViewCallbacks;
import www.flybrowser.net.flybrowser.view.stickhelp.StickyScrollViewGlobalLayoutListener;
import www.flybrowser.net.flybrowser.webview.LightningView;

/**
 * Created by ferris on 2015/10/23.
 */
public class BrowserActivity extends AppCompatActivity implements BrowserController{

    private StickyScrollView browser_scrollview_layout;
    //顶部stick titlebar
    private StickFrameLayout top_titlerbar_layout;

    private View browser_scrollview_head_search_bar;

    private int titlebar_hight=0,head_hight=0,max_scrolly=0;


    private StickyScrollViewCallbacks mCallbacks;

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
    public void onCloseWindow(LightningView view) {

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

    @Override
    public boolean proxyIsNotReady() {
        return false;
    }
}
