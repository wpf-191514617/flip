package com.sports.filip.fragment.home.score;


import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.awhh.everyenjoy.library.widget.SwipeRefreshAndLoadLayout;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.adapter.ScoreListAdapter;
import com.sports.filip.entity.race.ScoreListResponse;
import com.sports.filip.fragment.base.BaseFragment;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by pengfei on 2016/11/17.
 * <p>
 * 足球----即时
 */

public class InstantFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener
{
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshAndLoadLayout refreshLayout;
    @Bind(R.id.layoutContent)
    LinearLayout layoutContent;
    
    private ScoreListAdapter scoreListAdapter;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.fragment_list_bet;
    }


    @Override
    protected void initViewAndData()
    {
        refreshLayout.setOnRefreshListener(this);
        scoreListAdapter = new ScoreListAdapter(getActivity() , null);
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

        OkHttpUtils.getInstance().post()
                .tag(this)
                .url(Constants.BaseUrl + "index.php?g=app&m=score&a=instant")
                .addParams("last_id", "0")
                .addParams("type", "3")
                .addParams("uid", "")
                .addParams("cid", "")
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                refreshLayout.setRefreshing(false);
                showError("");
            }

            @Override
            public void onResponse(String response, int id)
            {
                refreshLayout.setRefreshing(false);
                ScoreListResponse scoreListResponse = GsonUtils.getGson().fromJson(response , ScoreListResponse.class);
                if(scoreListResponse.getStatus()  != 1){
                    showError("");
                    return;
                }
                scoreListAdapter.clearAddData(scoreListResponse.getList());
            }
        });

    }
    
}
