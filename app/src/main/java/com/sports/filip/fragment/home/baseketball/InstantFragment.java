package com.sports.filip.fragment.home.baseketball;

import android.widget.LinearLayout;
import android.widget.ListView;

import com.awhh.everyenjoy.library.widget.SwipeRefreshAndLoadLayout;
import com.sports.filip.R;
import com.sports.filip.fragment.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by pengfei on 2016/11/17.
 * <p>
 * 足球----即时
 */

public class InstantFragment extends BaseFragment
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
        autoRefresh();
    }

    private void autoRefresh()
    {
        
    }

}
