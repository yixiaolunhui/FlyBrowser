/**
 * StickyScrollView.java
 * StickyScrollView
 * <p/>
 * Created by likebamboo on 2014-4-21
 * Copyright (c) 1998-2014 https://github.com/likebamboo All rights reserved.
 */

package www.flybrowser.net.flybrowser.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * 459821731@qq.com
 * Created by ferris on 2015/10/24.
 */
public class WebViewContainter extends ScrollView {
    private Callbacks mCallbacks;
    private int mTouchSlop = 0;
    private int maxScrollY = 0;//一半
    public void setMaxScroll(int maxScrollY) {
        this.maxScrollY = maxScrollY;

    }

    public WebViewContainter(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        // 滚动时回调
        if (mCallbacks != null) {
            mCallbacks.onScrollChanged();
        }
    }

    public void addCallbacks(Callbacks listener) {
        this.mCallbacks = listener;
    }

    public static interface Callbacks {
        public void onScrollChanged();
    }
}
