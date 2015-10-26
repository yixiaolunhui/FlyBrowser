package www.flybrowser.net.flybrowser.webview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class TitleBarWebView extends WebView {
	private View mTitleBar;
    private LayoutParams mTitleBarLayoutParams;
    private Matrix mMatrix = new Matrix();
    private Rect mClipBounds = new Rect();

    public TitleBarWebView(Context context) {
        super(context);

    }

    public TitleBarWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleBarWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setEmbeddedTitleBar(View v) {
        if(mTitleBar == v) return;
        if(mTitleBar != null) {
            removeView(mTitleBar);
        }
        if(null != v) {
            mTitleBarLayoutParams = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0);
            addView(v, mTitleBarLayoutParams);
        }
        mTitleBar = v;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        if(mTitleBar != null) {
            final int sy = getScrollY();
            int titleBarOffs = mTitleBar.getHeight() - sy;
            if(titleBarOffs < 0) titleBarOffs = 0;
            canvas.translate(0, titleBarOffs);
        }

        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if(child == mTitleBar) {
            mTitleBar.offsetLeftAndRight((int) (getScrollX() - mTitleBar.getLeft()));
        }

        return super.drawChild(canvas, child, drawingTime);
    }
}
