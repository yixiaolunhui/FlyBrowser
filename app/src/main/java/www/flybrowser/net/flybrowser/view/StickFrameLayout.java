package www.flybrowser.net.flybrowser.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import www.flybrowser.net.flybrowser.R;

/**
 * Created by ferris on 2015/10/24.
 */
public class StickFrameLayout extends FrameLayout{
    private FrameLayout stick_titlebar_one,stick_titlebar_two;
    private int maxScrollY=0;
    private float alphaOffset=0f;
    public void setMaxScroll(int maxScrollY){
        this.maxScrollY=maxScrollY;
        alphaOffset=1f/maxScrollY;
    }
    public StickFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public StickFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickFrameLayout(Context context) {
        this(context, null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        stick_titlebar_one=(FrameLayout)findViewById(R.id.stick_titlebar_one);
        stick_titlebar_two=(FrameLayout)findViewById(R.id.stick_titlebar_two);
    }

    public void scroll(int scrollY){
        Log.d("StickFrameLayout", "scrollY=" + scrollY);
        float alpha=1f-scrollY*alphaOffset;
        stick_titlebar_two.setAlpha(alpha);
    }
}
