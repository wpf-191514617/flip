package com.sports.filip.fragment.home.baseketball;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.db.NiceDB;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.awhh.everyenjoy.library.widget.SwipeRefreshAndLoadLayout;
import com.awhh.everyenjoy.library.widget.listener.OnLoadMoreListener;
import com.google.gson.reflect.TypeToken;
import com.sports.filip.Constants;
import com.sports.filip.EventCode;
import com.sports.filip.R;
import com.sports.filip.activity.account.LoginActivity;
import com.sports.filip.adapter.BasketballListAdapter;
import com.sports.filip.entity.response.BasketballMatchEntity;
import com.sports.filip.fragment.base.BaseFragment;
import com.sports.filip.fragment.callback.OnAttentionListener;
import com.sports.filip.util.CacheHelper;

import java.util.List;

import butterknife.Bind;
import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * Created by pengfei on 2016/11/17.
 * <p>
 * 篮球----即时
 */

public class InstantFragment extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener, OnAttentionListener
{
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshAndLoadLayout refreshLayout;
    @Bind(R.id.layoutContent)
    LinearLayout layoutContent;

    private BasketballListAdapter listAdapter;

    private int mPageNum = 1;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.fragment_list_bet;
    }

    @Override
    protected void initViewAndData()
    {
        listAdapter = new BasketballListAdapter(getActivity(), null);
        listAdapter.setAttention(true);
        listAdapter.setOnAttentionListener(this);
        listView.setAdapter(listAdapter);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setLoadingListener(this);
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


    private void getData()
    {
        OkHttpUtils.getInstance().post().url(Constants.BASEURL
                + "index.php?g=api&m=footballapi&a=get_basketball_info")
                .addParams("type", "1")
                .addParams("page", mPageNum + "")
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                if (mPageNum == 1)
                {
                    showError("");
                } else
                {
                    showToastShort("服务不可用");
                }
            }

            @Override
            public void onResponse(String response, int id)
            {
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                List<BasketballMatchEntity> entities =
                        GsonUtils.getGson().fromJson(response, new TypeToken<List<BasketballMatchEntity>>()
                        {
                        }.getType());
                if (mPageNum == 1)
                {
                    listAdapter.clearAddData(entities);
                } else
                {
                    listAdapter.addMoreDatas(entities);
                }
                if (entities.size() < 10)
                    refreshLayout.setCanLoad(false);

            }
        });
    }

    @Override
    protected View getLoadingTargetView()
    {
        return layoutContent;
    }

    @Override
    protected void onReloadThe()
    {
        super.onReloadThe();
        reStoreView();
        onRefresh();
    }

    @Override
    public void onRefresh()
    {
        mPageNum = 1;
        getData();
    }

    @Override
    public void onLoadMore()
    {
        mPageNum++;
        getData();
    }


    @Override
    public void onAttentionMatch(BasketballMatchEntity entity)
    {
        if (StringUtils.isEmpty(CacheHelper.getUserId()))
        {
            readyGo(LoginActivity.class);
            return;
        }
        NiceDB niceDB = NiceDB.create(getActivity(), Constants.DBName.FOLLOW_BASKETBALL);
        List<BasketballMatchEntity> entities = niceDB.findAll(BasketballMatchEntity.class);
        if (entities.size() < 1)
        {
            niceDB.save(entity);
            entities.add(entity);
            notificationAdapter(entities);
            return;
        }
        queryfilterData(entity, entities, niceDB);
    }

    private void queryfilterData(BasketballMatchEntity entity, List<BasketballMatchEntity> entities,
                                 NiceDB niceDB)
    {
        if (entities.contains(entity))
        {
            entities.remove(entity);
            niceDB.deleteByWhere(BasketballMatchEntity.class, "xid = '" + entity.getXid() + "'");
        } else
        {
            entities.add(entity);
            niceDB.save(entity);
        }
        notificationAdapter(entities);
    }


    void notificationAdapter(List<BasketballMatchEntity> entities)
    {
        listAdapter.setMatchEntities(entities);
        EventCenter center = new EventCenter(EventCode.CODE_BASKETBALL_FOLLOW);
        EventBus.getDefault().post(center);
    }
}
