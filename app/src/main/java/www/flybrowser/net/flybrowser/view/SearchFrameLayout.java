package www.flybrowser.net.flybrowser.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.TextView;

import www.flybrowser.net.flybrowser.MainActivity;
import www.flybrowser.net.flybrowser.R;

/**
 * Created by 搜索View on 2015/10/26.
 */
public class SearchFrameLayout extends FrameLayout{
    private AutoCompleteTextView tv_input;
    private TextView tv_go;
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
        tv_input=(AutoCompleteTextView)findViewById(R.id.tv_input);
        tv_go=(TextView)findViewById(R.id.tv_go);
        tv_go.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getContext()).search(tv_input.getText().toString());
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
