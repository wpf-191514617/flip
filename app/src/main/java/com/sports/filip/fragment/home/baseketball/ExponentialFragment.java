package com.sports.filip.fragment.home.baseketball;

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
import com.sports.filip.entity.response.BasketballExponeEntity;
import com.sports.filip.fragment.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

import static com.sports.filip.R.id.layoutCompanyBet;

/**
 * Created by pengfei on 2016/11/17.
 * <p>
 * 足球------指数
 */

public class ExponentialFragment extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.listView)
    protected ListView listView;
    @Bind(R.id.refreshLayout)
    protected SwipeRefreshAndLoadLayout refreshLayout;
    @Bind(R.id.layoutContent)
    LinearLayout layoutContent;
    private int mPageNum = 1;

    private ExponetialListAdapter listAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_list_bet;
    }

    @Override
    protected View getLoadingTargetView() {
        return layoutContent;
    }

    @Override
    protected void initViewAndData() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setLoadingListener(this);
        listView.setDivider(new ColorDrawable(Color.parseColor("#007D2C")));
        listView.setDividerHeight(getActivity().getResources().getDimensionPixelSize(R.dimen.divider_height));
        listAdapter = new ExponetialListAdapter(getActivity(), null);
        listView.setAdapter(listAdapter);
        autoRefresh();
    }

    private void autoRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                onRefresh();
            }
        }, 300);
    }

    private void getData() {
        OkHttpUtils.getInstance().post()
                .url(Constants.BASEURL + "index.php?g=api&m=footballapi&a=get_basketball_index")
                .addParams("page", mPageNum + "")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                if (mPageNum == 1) {
                    showError("");
                    return;
                }
                showToastShort("服务不可用");
            }

            @Override
            public void onResponse(String response, int id) {
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                List<BasketballExponeEntity> list = GsonUtils.getGson()
                        .fromJson(response, new TypeToken<List<BasketballExponeEntity>>() {
                        }.getType());

                if (mPageNum == 1) {
                    listAdapter.clearAddData(list);
                } else {
                    listAdapter.addMoreDatas(list);
                }
                if (list.size() < 10)
                    refreshLayout.setCanLoad(false);

            }
        });

    }


    @Override
    public void onRefresh() {
        mPageNum = 1;
        getData();
    }

    @Override
    public void onLoadMore() {
        mPageNum++;
        getData();
    }


    class ExponetialListAdapter extends BaseListAdapter<BasketballExponeEntity> {

        /**
         * @param context
         * @param mDatas
         */
        public ExponetialListAdapter(Context context, List<BasketballExponeEntity> mDatas) {
            super(context, R.layout.item_exponential_1, mDatas);
        }

        @Override
        protected void fillData(ViewHolderHelper holderHelper, int position, BasketballExponeEntity data) {
            BasketballExponeEntity.OpBean bean = null;
            if (data.getOp() != null && data.getOp().size() > 0) {
                bean = data.getOp().get(0);
            }

            holderHelper.setText(R.id.tvLeague, data.getLeague())
                    .setTextColor(R.id.tvLeague, Color.parseColor("#" + data.getColor()))
                    .setText(R.id.tvHomeTeam, data.getHteam())
                    .setText(R.id.tvVisitTeam, data.getAteam());
            if (bean == null)
                return;

            holderHelper.setText(R.id.tvMatchTime, bean.getMdate());
            LinearLayout layoutCompany = holderHelper.getView(layoutCompanyBet);
            if (layoutCompany.getChildCount() > 0)
                layoutCompany.removeAllViews();

            View view = LayoutInflater.from(mContext).inflate(R.layout.item_company_bet_1 , null);
            TextView tvCS1 = (TextView) view.findViewById(R.id.tvCS1);
            TextView tvCS2 = (TextView) view.findViewById(R.id.tvCS2);
            TextView tvCS3 = (TextView) view.findViewById(R.id.tvCS3);
            TextView tvJS1 = (TextView) view.findViewById(R.id.tvJS1);
            TextView tvJS2 = (TextView) view.findViewById(R.id.tvJS2);
            TextView tvJS3 = (TextView) view.findViewById(R.id.tvJS3);


            tvCS1.setText(bean.getS8_rfsf_sp0());
            tvCS2.setText(bean.getS8_rfsf_bet());
            tvCS3.setText(bean.getS8_rfsf_sp3());

            tvJS1.setText(bean.getS8_dxf_sp0());
            tvJS2.setText(bean.getS8_dxf_bet());
            tvJS3.setText(bean.getS8_dxf_sp3());
            layoutCompany.addView(view);

        }
    }


}
