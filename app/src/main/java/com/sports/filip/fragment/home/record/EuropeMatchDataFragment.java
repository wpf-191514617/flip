package com.sports.filip.fragment.home.record;

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
import com.sports.filip.activity.NationsLeagueListActivity;
import com.sports.filip.entity.response.CountryResponse;
import com.sports.filip.fragment.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * author:pengfei
 * date:2017/4/14
 * Email:15291967179@163.com
 */

public class EuropeMatchDataFragment extends BaseFragment
{
    @Bind(R.id.rvRecord)
    RecyclerView rvRecord;
    
    private CountryListAdapter countryListAdapter;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.fragment_record;
    }

    @Override
    protected void initViewAndData()
    {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity() , 4);
        rvRecord.setLayoutManager(layoutManager);
        countryListAdapter = new CountryListAdapter(rvRecord , null);
        rvRecord.setAdapter(countryListAdapter);
        countryListAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener()
        {
            @Override
            public void onRecyclerItemClick(ViewGroup viewGroup, View childView, int position)
            {
                Bundle bundle = new Bundle();
                bundle.putParcelable("key" , countryListAdapter.getItem(position));
                readyGo(NationsLeagueListActivity.class , bundle);
            }
        });
        getData();
    }

    private void getData()
    {
        OkHttpUtils.getInstance().post().tag(this)
        .url(Constants.BASEURL
                + "index.php?g=api&m=footballapi&a=get_match_data")
                .addParams("type", "2")
                .addParams("cont_id" , getContId())
                .build().execute(new StringCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                
            }

            @Override
            public void onResponse(String res, int id)
            {
                List<CountryResponse> responses = GsonUtils.getGson().fromJson(res , 
                        new TypeToken<List<CountryResponse>>(){}.getType());
                countryListAdapter.addMoreDatas(responses);
            }
        });
    }

    protected String getContId()
    {
        return "1";
    }


    class CountryListAdapter extends BaseRecyclerAdapter<CountryResponse>
    {

        public CountryListAdapter(RecyclerView recyclerView, List<CountryResponse> data)
        {
            super(recyclerView, R.layout.item_content_text, data);
        }

        @Override
        protected void fillData(ViewHolderHelper viewHolderHelper, int position, CountryResponse item)
        {
            viewHolderHelper.setText(R.id.tvContent , item.getCountry_name());
            
        }
    }
    
    
}
