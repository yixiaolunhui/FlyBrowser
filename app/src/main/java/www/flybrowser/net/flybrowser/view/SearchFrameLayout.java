package www.flybrowser.net.flybrowser.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import www.flybrowser.net.flybrowser.R;

/**
 * Created by 搜索View on 2015/10/26.
 */
public class SearchFrameLayout extends FrameLayout{

    public SearchFrameLayout(Context context) {
        super(context);
    }

    public SearchFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public static SearchFrameLayout getXml(Context mContext){
        return (SearchFrameLayout) LayoutInflater.from(mContext).inflate(R.layout.search_view,null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
