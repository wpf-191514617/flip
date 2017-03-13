package com.sports.filip.fragment.home.score;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.awhh.everyenjoy.library.widget.SwipeRefreshAndLoadLayout;
import com.sports.filip.R;
import com.sports.filip.fragment.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by pengfei on 2016/11/17.
 * 
 *  足球------指数
 * 
 */

public class ExponentialFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener
{

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshAndLoadLayout refreshLayout;
    @Bind(R.id.layoutContent)
    LinearLayout layoutContent;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.fragment_list_bet;
    }

    @Override
    protected void initViewAndData()
    {
        refreshLayout.setOnRefreshListener(this);
        autoRefresh();
    }

    private void autoRefresh()
    {
        refreshLayout.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                refreshLayout.setRefreshing(true);
                onRefresh();
            }
        }, 300);
    }

    @Override
    public void onRefresh()
    {
        getData();
    }

    protected void getData()
    {
        mHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                showError("");
            }
        } , 3000);
    }

    @Override
    protected View getLoadingTargetView()
    {
        return layoutContent;
    }
    
    protected Handler mHandler = new Handler();


    @Override
    protected void onReloadThe()
    {
        super.onReloadThe();
        getData();
        reStoreView();
    }


}
