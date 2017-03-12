package com.sports.filip.fragment.home.score;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.awhh.everyenjoy.library.widget.SwipeRefreshAndLoadLayout;
import com.sports.filip.R;
import com.sports.filip.adapter.ScoreListAdapter;
import com.sports.filip.fragment.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pengfei on 2016/11/17.
 * <p>
 * 足球-----赛事
 */

public class CompetitionFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener
{

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshAndLoadLayout refreshLayout;
    @Bind(R.id.layoutContent)
    LinearLayout layoutContent;
    @Bind(R.id.tvDate1)
    TextView tvDate1;
    @Bind(R.id.tvDate2)
    TextView tvDate2;
    @Bind(R.id.tvDate3)
    TextView tvDate3;
    @Bind(R.id.tvDate4)
    TextView tvDate4;
    @Bind(R.id.tvDate5)
    TextView tvDate5;
    @Bind(R.id.tvDate6)
    TextView tvDate6;
    @Bind(R.id.tvDate7)
    TextView tvDate7;

    private ScoreListAdapter scoreListAdapter;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.fragment_commpetition;
    }


    @Override
    protected void initViewAndData()
    {
        refreshLayout.setOnRefreshListener(this);
        scoreListAdapter = new ScoreListAdapter(getActivity(), null);
        listView.setAdapter(scoreListAdapter);
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
    protected void onReloadThe()
    {
        super.onReloadThe();
        getData();
        reStoreView();
    }


    @Override
    protected View getLoadingTargetView()
    {
        return layoutContent;
    }

    @Override
    public void onRefresh()
    {
        getData();
    }

    private void getData()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tvDate1, R.id.tvDate2, R.id.tvDate3, R.id.tvDate4, R.id.tvDate5, R.id.tvDate6, R.id.tvDate7})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tvDate1:
                tvDate1.setSelected(true);
                tvDate2.setSelected(false);
                tvDate3.setSelected(false);
                tvDate4.setSelected(false);
                tvDate5.setSelected(false);
                tvDate6.setSelected(false);
                tvDate7.setSelected(false);
                break;
            case R.id.tvDate2:
                tvDate2.setSelected(true);
                tvDate1.setSelected(false);
                tvDate3.setSelected(false);
                tvDate4.setSelected(false);
                tvDate5.setSelected(false);
                tvDate6.setSelected(false);
                tvDate7.setSelected(false);
                break;
            case R.id.tvDate3:
                tvDate3.setSelected(true);
                tvDate1.setSelected(false);
                tvDate2.setSelected(false);
                tvDate4.setSelected(false);
                tvDate5.setSelected(false);
                tvDate6.setSelected(false);
                tvDate7.setSelected(false);
                break;
            case R.id.tvDate4:
                tvDate4.setSelected(true);
                tvDate1.setSelected(false);
                tvDate2.setSelected(false);
                tvDate3.setSelected(false);
                tvDate5.setSelected(false);
                tvDate6.setSelected(false);
                tvDate7.setSelected(false);
                break;
            case R.id.tvDate5:
                tvDate5.setSelected(true);
                tvDate1.setSelected(false);
                tvDate2.setSelected(false);
                tvDate3.setSelected(false);
                tvDate4.setSelected(false);
                tvDate6.setSelected(false);
                tvDate7.setSelected(false);
                break;
            case R.id.tvDate6:
                tvDate6.setSelected(true);
                tvDate1.setSelected(false);
                tvDate2.setSelected(false);
                tvDate3.setSelected(false);
                tvDate4.setSelected(false);
                tvDate5.setSelected(false);
                tvDate7.setSelected(false);
                break;
            case R.id.tvDate7:
                tvDate7.setSelected(true);
                tvDate1.setSelected(false);
                tvDate2.setSelected(false);
                tvDate3.setSelected(false);
                tvDate4.setSelected(false);
                tvDate5.setSelected(false);
                tvDate6.setSelected(false);
                break;
        }
    }
}
