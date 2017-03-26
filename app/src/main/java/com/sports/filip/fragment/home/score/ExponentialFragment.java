package com.sports.filip.fragment.home.score;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.awhh.everyenjoy.library.base.adapter.BaseListAdapter;
import com.awhh.everyenjoy.library.base.adapter.ViewHolderHelper;
import com.awhh.everyenjoy.library.http.OkHttpUtils;
import com.awhh.everyenjoy.library.http.callback.StringCallback;
import com.awhh.everyenjoy.library.http.utils.GsonUtils;
import com.awhh.everyenjoy.library.widget.SwipeRefreshAndLoadLayout;
import com.awhh.everyenjoy.library.widget.listener.OnLoadMoreListener;
import com.google.gson.reflect.TypeToken;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.entity.response.ExponentialEntity;
import com.sports.filip.fragment.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by pengfei on 2016/11/17.
 * 
 *  足球------指数
 * 
 */

public class ExponentialFragment extends BaseFragment implements 
        SwipeRefreshLayout.OnRefreshListener , OnLoadMoreListener
{

    @Bind(R.id.listView)
    protected ListView listView;
    @Bind(R.id.refreshLayout)
    protected SwipeRefreshAndLoadLayout refreshLayout;
    @Bind(R.id.layoutContent)
    LinearLayout layoutContent;
    
    private int mPageNum = 1;
    
    private ExponentialAdapter adapter;

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
        listView.setDivider(new ColorDrawable(Color.parseColor("#007D2C")));
        listView.setDividerHeight(getActivity().getResources().getDimensionPixelSize(R.dimen.divider_height));
        adapter = new ExponentialAdapter(getActivity() , null);
        listView.setAdapter(adapter);
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
    public void onRefresh()
    {
        mPageNum = 1;
        getData();
    }

    protected void getData()
    {

        OkHttpUtils.getInstance().post()
                .url(Constants.BASEURL + "index.php?g=api&m=footballapi&a=get_football_index")
                .addParams("page" , mPageNum + "")
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
                    return;
                }
            }

            @Override
            public void onResponse(String response, int id)
            {
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                List<ExponentialEntity> list =GsonUtils.getGson()
                        .fromJson(response , new TypeToken<List<ExponentialEntity>>(){}.getType());
                if (mPageNum == 1)
                {
                    adapter.clearAddData(list);
                }else{
                    adapter.addMoreDatas(list);
                }
                if (list.size() < 1)
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
        mPageNum = 1;
        getData();
        reStoreView();
    }

    @Override
    public void onLoadMore()
    {
        mPageNum++;
        getData();
    }

    class ExponentialAdapter extends BaseListAdapter<ExponentialEntity>{

        /**
         * @param context
         * @param mDatas
         */
        public ExponentialAdapter(Context context, List<ExponentialEntity> mDatas)
        {
            super(context, R.layout.item_exponential, mDatas);
        }

        @Override
        protected void fillData(ViewHolderHelper holderHelper, int position, ExponentialEntity data)
        {
            holderHelper.setText(R.id.tvLeague , data.getLeague_name());
            holderHelper.setText(R.id.tvMatchTime , data.getMtime());
            holderHelper.setText(R.id.tvHomeTeam , data.getH_name());
            holderHelper.setText(R.id.tvVisitTeam , data.getA_name());
            LinearLayout layoutCompanyBet = holderHelper.getView(R.id.layoutCompanyBet);
            if(layoutCompanyBet.getChildCount() > 0)
                layoutCompanyBet.removeAllViews();
            for (int i = 0;i < data.getYp().size();i++)
            {
                ExponentialEntity.YpBean bean = data.getYp().get(i);
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_company_bet , null);
                TextView tvBetCompany = (TextView) view.findViewById(R.id.tvBetCompany);
                TextView tvCS1 = (TextView) view.findViewById(R.id.tvCS1);
                TextView tvCS2 = (TextView) view.findViewById(R.id.tvCS2);
                TextView tvCS3 = (TextView) view.findViewById(R.id.tvCS3);
                TextView tvJS1 = (TextView) view.findViewById(R.id.tvJS1);
                TextView tvJS2 = (TextView) view.findViewById(R.id.tvJS2);
                TextView tvJS3 = (TextView) view.findViewById(R.id.tvJS3);

                tvBetCompany.setText(bean.getD1());
                tvCS1.setText(bean.getD3());
                tvCS2.setText(bean.getD4());
                tvCS3.setText(bean.getD5());
                
                tvJS1.setText(bean.getD6());
                tvJS2.setText(bean.getD7());
                tvJS3.setText(bean.getD8());
                layoutCompanyBet.addView(view);
            }
        }
    }

}
