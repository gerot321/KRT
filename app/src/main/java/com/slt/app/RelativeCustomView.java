package com.slt.app;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;

public class RelativeCustomView extends RecyclerView {
    private int mMaxHeight;

    public RelativeCustomView(Context context) {
        super(context);
    }

    public RelativeCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public RelativeCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightScrollView);
        mMaxHeight = arr.getLayoutDimension(R.styleable.MaxHeightScrollView_maxHeight, mMaxHeight);
        arr.recycle();
    }
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        if(getHeight()>dpToPx(225)){
            heightSpec = MeasureSpec.makeMeasureSpec(dpToPx(225), MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthSpec, heightSpec);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}