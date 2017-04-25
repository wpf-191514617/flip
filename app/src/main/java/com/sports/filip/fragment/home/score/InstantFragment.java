package com.sports.filip.fragment.home.score;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.awhh.everyenjoy.library.base.activity.BaseWebActivity;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.awhh.everyenjoy.library.widget.SwipeRefreshAndLoadLayout;
import com.awhh.everyenjoy.library.widget.listener.OnLoadMoreListener;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.activity.ScoreDetailActivity;
import com.sports.filip.adapter.ScoreListAdapter;
import com.sports.filip.adapter.callback.OnFollowScoreMatchCallBack;
import com.sports.filip.entity.race.ScoreEntity;
import com.sports.filip.entity.race.ScoreListResponse;
import com.sports.filip.fragment.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by pengfei on 2016/11/17.
 * <p>
 * 足球----即时
 */

public class InstantFragment extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, OnFollowScoreMatchCallBack , OnLoadMoreListener
{
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshAndLoadLayout refreshLayout;
    @Bind(R.id.layoutContent)
    LinearLayout layoutContent;
    
    private String last_id = "0";

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
        refreshLayout.setLoadingListener(this);
        scoreListAdapter = new ScoreListAdapter(getActivity(), null);
        listView.setAdapter(scoreListAdapter);
        scoreListAdapter.setFollowScoreMatchCallBack(this);
        autoRefresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                ScoreEntity scoreEntity = scoreListAdapter.getItem(i);
                Bundle bundle = new Bundle();
                bundle.putBoolean(BaseWebActivity.BUNDLE_KEY_SHOW_BOTTOM_BAR , false);
                bundle.putString(BaseWebActivity.BUNDLE_KEY_TITLE , scoreEntity.getLeague());
                bundle.putString(BaseWebActivity.BUNDLE_KEY_URL , scoreEntity.getId());
                readyGo(ScoreDetailActivity.class , bundle);
            }
        });
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
        last_id = "0";
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
        last_id = "0";
        getData();
    }

    private void getData()
    {

        OkHttpUtils.getInstance().post()
                .tag(this)
                .url(Constants.BASEURL + "index.php?g=app&m=score&a=instant")
                .addParams("last_id", last_id)
                .addParams("type", "3")
                .addParams("uid", "")
                .addParams("cid", "")
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                showError("");
            }

            @Override
            public void onResponse(String response, int id)
            {
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                ScoreListResponse scoreListResponse = GsonUtils.getGson().fromJson(response, ScoreListResponse.class);
                if (scoreListResponse.getStatus() != 1 && last_id.equals("0"))
                {
                    showError("");
                    return;
                }
                if (last_id.equals("0"))
                    scoreListAdapter.clearAddData(scoreListResponse.getList());
                else
                    scoreListAdapter.addMoreDatas(scoreListResponse.getList());
                last_id = scoreListResponse.getLast_id();
                if (scoreListResponse.getList().size() < 1)
                    refreshLayout.setCanLoad(false);
            }
        });
    }
    
    private void refreshListAdapter(final List<ScoreEntity> list){
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                scoreListAdapter.clearAddData(list);
            }
        }, 1000);
    }

    @Override
    public void onFollow(ScoreEntity entity)
    {

    }

    @Override
    public void onLoadMore()
    {
        getData();
    }
}
