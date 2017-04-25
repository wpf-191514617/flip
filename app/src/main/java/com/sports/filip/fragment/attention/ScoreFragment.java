package com.sports.filip.fragment.attention;

import android.widget.ListView;

import com.sports.filip.R;
import com.sports.filip.fragment.base.BaseFragment;

import butterknife.Bind;

/**
 * author:pengfei
 * date:2017/4/5
 * Email:15291967179@163.com
 */

public class ScoreFragment extends BaseFragment
{
    @Bind(R.id.listView)
    ListView listView;
    
    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.listview;
    }

    @Override
    protected void initViewAndData()
    {

    }
    

}
