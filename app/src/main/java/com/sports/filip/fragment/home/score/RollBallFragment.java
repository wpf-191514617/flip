package com.sports.filip.fragment.home.score;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.entity.response.MatchContentResponse;
import com.sports.filip.entity.response.RollBallResponse;
import com.sports.filip.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by pengfei on 2016/11/17.
 * 
 *  足球-----滚球
 * 
 */

public class RollBallFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener
{

    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshAndLoadLayout refreshLayout;
    @Bind(R.id.layoutContent)
    LinearLayout layoutContent;
    
    private RollBallListAdapter rollBallListAdapter;
    
    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.fragment_list_bet;
    }

    @Override
    protected void initViewAndData()
    {
        refreshLayout.setOnRefreshListener(this);
        rollBallListAdapter = new RollBallListAdapter(getActivity() , null);
        listView.setAdapter(rollBallListAdapter);
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
            public void onResponse(String string, int id)
            {
                refreshLayout.setRefreshing(false);
                RollBallResponse response = GsonUtils
                        .gsonToBean(string, RollBallResponse.class);
                if (response.getStatus() != 1) {
                    showError("");
                    return;
                }
                reStoreView();
                rollBallListAdapter.clearAddData(response.getList());
            }
        });
        
    }











    class RollBallListAdapter extends BaseListAdapter<RollBallResponse.ListBean>
    {

        private List<MatchContentResponse.ListBean> listBeen = new ArrayList<>();

        public List<MatchContentResponse.ListBean> getListBeen() {
            return listBeen;
        }

        /**
         * @param context
         * @param mDatas
         */
        public RollBallListAdapter(Context context, List<RollBallResponse.ListBean> mDatas) {
            super(context, R.layout.item_rollball, mDatas);
        }

        @Override
        protected void fillData(ViewHolderHelper holderHelper, int position, final RollBallResponse.ListBean data) {
            holderHelper.setText(R.id.tvLeague, data.getLeague());
            holderHelper.setTextColor(R.id.tvLeague, Color.parseColor(data.getColor()));
            holderHelper.setText(R.id.tvTime, data.getMinute()+" '");
            holderHelper.setText(R.id.tvHomeName, data.getH_name());
            holderHelper.setText(R.id.tvVisitName, data.getA_name());
            holderHelper.setText(R.id.tvHomeScore, data.getH_score());
            holderHelper.setText(R.id.tvVisitScore, data.getA_score());

            holderHelper.setText(R.id.tvRangFen, data.getRangfen());
            final TextView tvHomeRang = holderHelper.getView(R.id.tvHomeRang);
            tvHomeRang.setText(data.getH_rang() + " " + data.getH_rang1());
            final TextView tvVisitRang = holderHelper.getView(R.id.tvVisitRang);
            tvVisitRang.setText(data.getA_rang() + " " + data.getA_rang1());
            holderHelper.setText(R.id.tvHomeDa1, data.getDa() + " " + data.getDa1());
            holderHelper.setText(R.id.tvVisitDa, data.getXiao() + "" + data.getXiao1());

            final TextView tvHomeDX = holderHelper.getView(R.id.tvHomeDX);
            tvHomeDX.setText(data.getH_dx() + "" + data.getH_dx1());

            final TextView tvVisitDX = holderHelper.getView(R.id.tvVisitDX);
            tvVisitDX.setText(data.getA_dx() + "" + data.getA_dx1());
           

            final View layoutHomeRang = holderHelper.getView(R.id.layoutHomeRang);

            if (isContains(data.getId() + "-" + "全场让球-主")) {
                layoutHomeRang.setSelected(true);
                tvHomeRang.setTextColor(Color.parseColor("#ffffff"));
            } else {
                tvHomeRang.setTextColor(Color.parseColor("#ff8800"));
                layoutHomeRang.setSelected(false);
            }

          

            final View layoutVisitRang = holderHelper.getView(R.id.layoutVisitRang);

            if (isContains(data.getId() + "-" + "全场让球-客")) {
                layoutVisitRang.setSelected(true);
                tvVisitRang.setTextColor(Color.parseColor("#ffffff"));
            } else {
                layoutVisitRang.setSelected(false);
                tvVisitRang.setTextColor(Color.parseColor("#ff8800"));
            }

          
            final View layoutHomeDX = holderHelper.getView(R.id.layoutHomeDX);

            if (isContains(data.getId() + "-" + "全场大小-主")) {
                layoutHomeDX.setSelected(true);
                tvHomeDX.setTextColor(Color.parseColor("#ffffff"));
            } else {
                layoutHomeDX.setSelected(false);
                tvHomeDX.setTextColor(Color.parseColor("#ff8800"));
            }

            final View layoutVisitDX = holderHelper.getView(R.id.layoutVisitDX);

            if (isContains(data.getId() + "-" + "全场大小-客")) {
                layoutVisitDX.setSelected(true);
                tvVisitDX.setTextColor(Color.parseColor("#ffffff"));
            } else {
                layoutVisitDX.setSelected(false);
                tvVisitDX.setTextColor(Color.parseColor("#ff8800"));
            }

        }

        private boolean isContains(String beanStr) {
            for (int i = 0; i < listBeen.size(); i++) {
                String liststr = listBeen.get(i).getId() + "-" + listBeen.get(i).getType();
                if (beanStr.equals(liststr)) {
                    return true;
                }
            }
            return false;
        }

        private void removeData(MatchContentResponse.ListBean listBean) {
            String beanStr = listBean.getId() + "-" + listBean.getType();
            for (int i = 0; i < listBeen.size(); i++) {
                String liststr = listBeen.get(i).getId() + "-" + listBean.getType();
                if (beanStr.equals(liststr)) {
                    listBeen.remove(i);
                }
            }

        }


    }
    
    
    
    
    

}
