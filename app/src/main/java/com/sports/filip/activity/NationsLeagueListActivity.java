package com.sports.filip.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.awhh.everyenjoy.library.base.adapter.BaseRecyclerAdapter;
import com.awhh.everyenjoy.library.base.adapter.ViewHolderHelper;
import com.awhh.everyenjoy.library.base.adapter.listener.OnRecyclerItemClickListener;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.activity.base.BaseActivity;
import com.sports.filip.entity.response.CountryResponse;
import com.sports.filip.entity.response.RemarkResponse;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * author:pengfei
 * date:2017/4/14
 * Email:15291967179@163.com
 */

public class NationsLeagueListActivity extends BaseActivity
{

    @Bind(R.id.rvRecord)
    RecyclerView rvRecord;
    private CountryResponse countryResponse;

    private CountryListAdapter listAdapter;

    @Override
    protected String getTitleString()
    {
        return countryResponse.getCountry_name();
    }

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.activity_nation;
    }

    @Override
    protected void getBundleExtras(Bundle extras)
    {
        countryResponse = extras.getParcelable("key");
    }

    @Override
    protected void initViewAndData()
    {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        rvRecord.setLayoutManager(layoutManager);
        listAdapter = new CountryListAdapter(rvRecord, null);
        rvRecord.setAdapter(listAdapter);
        listAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener()
        {
            @Override
            public void onRecyclerItemClick(ViewGroup viewGroup, View childView, int position)
            {
                Bundle bundle = new Bundle();
                bundle.putParcelable("key" , listAdapter.getItem(position));
                readyGo(LeagueMatchListActivity.class , bundle);
            }
        });
        getData();
    }

    private void getData()
    {
        OkHttpUtils.getInstance().post().tag(this)
                .url(Constants.BASEURL
                        + "index.php?g=api&m=footballapi&a=get_match_data")
                .addParams("type", "3")
                .addParams("country_id" , countryResponse.getCountry_id())
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {

            }

            @Override
            public void onResponse(String res, int id)
            {
                List<RemarkResponse> responses = GsonUtils.getGson().fromJson(res ,
                        new TypeToken<List<RemarkResponse>>(){}.getType());
                listAdapter.addMoreDatas(responses);
            }
        });
    }


    class CountryListAdapter extends BaseRecyclerAdapter<RemarkResponse>
    {

        public CountryListAdapter(RecyclerView recyclerView, List<RemarkResponse> data)
        {
            super(recyclerView, R.layout.item_content_text, data);
        }

        @Override
        protected void fillData(ViewHolderHelper viewHolderHelper, int position, RemarkResponse item)
        {
            viewHolderHelper.setText(R.id.tvContent, item.getMatch_name());

        }
    }


}
