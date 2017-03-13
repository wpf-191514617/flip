package com.sports.filip.fragment.home.score;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.awhh.everyenjoy.library.widget.SwipeRefreshAndLoadLayout;
import com.awhh.everyenjoy.library.widget.listener.OnLoadMoreListener;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.adapter.ScoreListAdapter;
import com.sports.filip.entity.race.ScoreListResponse;
import com.sports.filip.fragment.base.BaseFragment;
import com.sports.filip.util.ScoreUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by pengfei on 2016/11/17.
 * <p>
 * 足球-----赛事
 */

public class CompetitionFragment extends BaseFragment implements 
        SwipeRefreshLayout.OnRefreshListener , OnLoadMoreListener
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

    private List<String> dates;
    
    private List<String> showDates;
    
    private ScoreListAdapter scoreListAdapter;

    private int currentIndex = 0;
    private String last_id = "0";
    
    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.fragment_commpetition;
    }


    @Override
    protected void initViewAndData()
    {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setLoadingListener(this);
        scoreListAdapter = new ScoreListAdapter(getActivity(), null);
        listView.setAdapter(scoreListAdapter);
        dates = ScoreUtil.getNextServenDays();
        showDates = new ArrayList<>();
        for (int i = 0;i<dates.size();i++)
        {
            showDates.add(dates.get(i).substring(dates.get(i).length() - 5 , dates.get(i).length()));
        }
        tvDate1.setText(showDates.get(0));
        tvDate2.setText(showDates.get(1));
        tvDate3.setText(showDates.get(2));
        tvDate4.setText(showDates.get(3));
        tvDate5.setText(showDates.get(4));
        tvDate6.setText(showDates.get(5));
        tvDate7.setText(showDates.get(6));
        tvDate1.setSelected(true);
        tvDate2.setSelected(false);
        tvDate3.setSelected(false);
        tvDate4.setSelected(false);
        tvDate5.setSelected(false);
        tvDate6.setSelected(false);
        tvDate7.setSelected(false);
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
        last_id = "0";
        getData();
    }

    private void getData()
    {
        OkHttpUtils.getInstance().post()
                .url(Constants.BaseUrl + "index.php?g=app&m=score&a=next")
                .addParams("last_id" , last_id)
                .addParams("date" , dates.get(currentIndex))
                .addParams("uid" , "")
                .addParams("type" , "3")
                .addParams("cid" , "")
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                if(last_id.equals("0")){
                    showError("");
                }else{
                    showToastShort("加载数据失败");
                }
            }

            @Override
            public void onResponse(String response, int id)
            {
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                ScoreListResponse listResponse = GsonUtils.getGson().fromJson(response , 
                        ScoreListResponse.class);
                if(listResponse.getStatus() != 1){
                    if(last_id.equals("0")){
                        showError("");
                    }else{
                        showToastShort(listResponse.getError());
                    }
                    return;
                }

                if(listResponse.getList().size() < 1){
                    refreshLayout.setCanLoad(false);
                }
                if(last_id.equals("0")){
                    scoreListAdapter.clearAddData(listResponse.getList());
                }else{
                    scoreListAdapter.addMoreDatas(listResponse.getList());
                }
                last_id = listResponse.getLast_id();
                
            }
        });
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
                currentIndex = 0;
                autoRefresh();
                break;
            case R.id.tvDate2:
                tvDate2.setSelected(true);
                tvDate1.setSelected(false);
                tvDate3.setSelected(false);
                tvDate4.setSelected(false);
                tvDate5.setSelected(false);
                tvDate6.setSelected(false);
                tvDate7.setSelected(false);
                currentIndex = 1;
                autoRefresh();
                break;
            case R.id.tvDate3:
                tvDate3.setSelected(true);
                tvDate1.setSelected(false);
                tvDate2.setSelected(false);
                tvDate4.setSelected(false);
                tvDate5.setSelected(false);
                tvDate6.setSelected(false);
                tvDate7.setSelected(false);
                currentIndex = 2;
                autoRefresh();
                break;
            case R.id.tvDate4:
                tvDate4.setSelected(true);
                tvDate1.setSelected(false);
                tvDate2.setSelected(false);
                tvDate3.setSelected(false);
                tvDate5.setSelected(false);
                tvDate6.setSelected(false);
                tvDate7.setSelected(false);
                currentIndex = 3;
                autoRefresh();
                break;
            case R.id.tvDate5:
                tvDate5.setSelected(true);
                tvDate1.setSelected(false);
                tvDate2.setSelected(false);
                tvDate3.setSelected(false);
                tvDate4.setSelected(false);
                tvDate6.setSelected(false);
                tvDate7.setSelected(false);
                currentIndex = 4;
                autoRefresh();
                break;
            case R.id.tvDate6:
                tvDate6.setSelected(true);
                tvDate1.setSelected(false);
                tvDate2.setSelected(false);
                tvDate3.setSelected(false);
                tvDate4.setSelected(false);
                tvDate5.setSelected(false);
                tvDate7.setSelected(false);
                currentIndex = 5;
                autoRefresh();
                break;
            case R.id.tvDate7:
                tvDate7.setSelected(true);
                tvDate1.setSelected(false);
                tvDate2.setSelected(false);
                tvDate3.setSelected(false);
                tvDate4.setSelected(false);
                tvDate5.setSelected(false);
                tvDate6.setSelected(false);
                currentIndex = 6;
                autoRefresh();
                break;
        }
    }

    @Override
    public void onLoadMore()
    {
        getData();
    }
}
