package com.sports.filip.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.awhh.everyenjoy.library.base.adapter.BaseRecyclerAdapter;
import com.awhh.everyenjoy.library.base.adapter.ViewHolderHelper;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.base.widget.Divider;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.entity.response.MatchResponse;
import com.sports.filip.entity.response.RemarkResponse;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * author:pengfei
 * date:2017/4/14
 * Email:15291967179@163.com
 */

public class LeagueMatchListActivity extends BaseActivity
{

    @Bind(R.id.rvRecord)
    RecyclerView rvRecord;
    private RemarkResponse remarkResponse;
    private MatchListAdapter matchListAdapter;

    @Override
    protected String getTitleString()
    {
        return remarkResponse.getMatch_name() + "当前轮比赛";
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_nation;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {
        remarkResponse = extras.getParcelable("key");
    }

    @Override
    protected void initViewAndData()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvRecord.setLayoutManager(layoutManager);
        rvRecord.addItemDecoration(new Divider(this));
        matchListAdapter = new MatchListAdapter(rvRecord , null);
        rvRecord.setAdapter(matchListAdapter);
        getData();
    }

    private void getData()
    {
        OkHttpUtils.getInstance().post().tag(this)
                .url(Constants.BASEURL
                        + "index.php?g=api&m=footballapi&a=get_match_data")
                .addParams("type", "4")
                .addParams("match_id" , remarkResponse.getMatch_id())
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {

            }

            @Override
            public void onResponse(String res, int id)
            {
                List<MatchResponse> responses = GsonUtils.getGson().fromJson(res ,
                        new TypeToken<List<MatchResponse>>(){}.getType());
                matchListAdapter.addMoreDatas(responses);
            }
        });
    }


    class MatchListAdapter extends BaseRecyclerAdapter<MatchResponse>
    {
        
        public MatchListAdapter(RecyclerView recyclerView, List<MatchResponse> data)
        {
            super(recyclerView, R.layout.item_match, data);
        }

        @Override
        protected void fillData(ViewHolderHelper holderHelper, int position, MatchResponse data)
        {
            String[] dates = data.getMatch_time().split(" ");
            holderHelper.setText(R.id.tvMatchDate , dates[0]);
            holderHelper.setText(R.id.tvMatchTime , dates[1]);
            
            if (StringUtils.isEmpty(data.getScore()))
            {
                holderHelper.setText(R.id.tvMatchStatus , "未开始");
                holderHelper.setText(R.id.tvScore , " - ");
            } else{
                holderHelper.setText(R.id.tvMatchStatus , "已结束");
                holderHelper.setText(R.id.tvScore , data.getScore());
            }
            
            holderHelper.setText(R.id.tvHome , data.getH_name());
            holderHelper.setText(R.id.tvVisit , data.getA_name());
        }
    }
    
}
