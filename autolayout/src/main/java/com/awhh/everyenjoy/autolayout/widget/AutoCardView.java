package com.awhh.everyenjoy.autolayout.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.awhh.everyenjoy.autolayout.AutoFrameLayout;
import com.awhh.everyenjoy.autolayout.utils.AutoLayoutHelper;

/**
 * Created by admin on 2016/5/3.
 */
public class AutoCardView extends CardView{
    
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);
    
    public AutoCardView(Context context) {
        super(context);
    }

    public AutoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoFrameLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!isInEditMode()){
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
