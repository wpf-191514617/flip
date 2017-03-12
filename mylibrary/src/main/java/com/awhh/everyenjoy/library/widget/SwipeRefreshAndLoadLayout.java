package com.awhh.everyenjoy.library.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.awhh.everyenjoy.library.R;
import com.awhh.everyenjoy.library.widget.listener.OnLoadMoreListener;


/**
 * Created by Eyey on 16/2/23. just support ListView
 */
public class SwipeRefreshAndLoadLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {

    private boolean isLoading = false;

    private int mDownY;

    private int mLastY;

    private ListView mListView;

    private OnLoadMoreListener loadingListener;

    private int mTouchSlop;

    private View mListViewFooter;

    private boolean canLoad = true;

    private boolean isRefresh = true;

    public void setIsRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    public SwipeRefreshAndLoadLayout(Context context) {
        super(context);
        ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, null, false);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (canLoad())
            loadData();
    }

    public SwipeRefreshAndLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mListView == null)
            getListView();
    }

    private void getListView() {
        for (int i = 0; i < getChildCount(); i++)
            if (getChildAt(i) instanceof ListView)
                mListView = (ListView) getChildAt(i);
        if (mListView != null)
            mListView.setOnScrollListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                mLastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                if (canLoad()) {
                    loadData();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public void setLoadingListener(OnLoadMoreListener loadingListener) {
        this.loadingListener = loadingListener;
    }

    private boolean isBottom() {
        if (mListView != null && mListView != null)
            if (mListView.getAdapter() != null)
                return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        return false;

    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            if (mListView.getFooterViewsCount() < 1)
                mListView.addFooterView(mListViewFooter);
        } else {
            if(mListView.getFooterViewsCount() > 0){
                mListView.removeFooterView(mListViewFooter);
                mDownY = 0;
                mLastY = 0;
            }
        }
    }

    private void loadData() {
        if (loadingListener != null) {
            setLoading(true);
            loadingListener.onLoadMore();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isRefresh)
            return super.onTouchEvent(ev);
        return false;
    }


    public void setCanLoad(boolean canLoad) {
        this.canLoad = canLoad;
    }

    public boolean canLoad() {
        return isBottom() && !isLoading && isPullup() && canLoad;
    }

    public boolean isPullup() {
        return (mDownY - mLastY) > mTouchSlop;
    }
}
