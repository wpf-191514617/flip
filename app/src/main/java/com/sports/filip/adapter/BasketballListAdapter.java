package com.sports.filip.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.awhh.everyenjoy.library.base.adapter.BaseListAdapter;
import com.awhh.everyenjoy.library.base.adapter.ViewHolderHelper;
import com.awhh.everyenjoy.library.db.NiceDB;
import com.sports.filip.Constants;
import com.sports.filip.R;
import com.sports.filip.entity.response.BasketballMatchEntity;
import com.sports.filip.fragment.callback.OnAttentionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pk on 2017/3/27.
 */

public class BasketballListAdapter extends BaseListAdapter<BasketballMatchEntity>
{

    private boolean isAttention = false;
    
    private List<BasketballMatchEntity> fromDB;

    public void setAttention(boolean attention)
    {
        isAttention = attention;
    }
    
    public void queryListFromDB(){
        NiceDB niceDB = NiceDB.create(mContext, Constants.DBName.FOLLOW_BASKETBALL);
        fromDB = niceDB.findAll(BasketballMatchEntity.class);
    }

    /**
     * @param context
     * @param mDatas
     */
    public BasketballListAdapter(Context context, List<BasketballMatchEntity> mDatas)
    {
        super(context, R.layout.item_match_2, mDatas);
    }

    @Override
    protected void fillData(ViewHolderHelper holderHelper, int position, final BasketballMatchEntity data)
    {
        BasketballMatchEntity.MatchInfoBean infoBean = null;
        if (data.getMatch_info() != null && data.getMatch_info().size() > 0)
        {
            infoBean = data.getMatch_info().get(0);
        }

        holderHelper.setText(R.id.tvMatchType, data.getLeague())
                .setTextColor(R.id.tvMatchType, Color.parseColor("#" + data.getColor()))
                .setText(R.id.tvHomeTeamRanking, "[" + data.getHrank() + "]")
                .setText(R.id.tvHomeName, data.getHteam())
                .setText(R.id.tvHomeScore, data.getHscore())
                .setText(R.id.tvVisitScore, data.getAscore())
                .setText(R.id.tvVisitName, data.getAteam())
                .setText(R.id.tvVisitTeamRanking, "[" + data.getArank() + "]");
        if (infoBean == null)
            return;
        holderHelper.setText(R.id.tvMatchTime, infoBean.getMdate());

        holderHelper.setText(R.id.tvD1, infoBean.getS8_rfsf_sp0())
                .setText(R.id.tvD2, infoBean.getS8_rfsf_bet())
                .setText(R.id.tvD3, infoBean.getS8_rfsf_sp3())
                .setText(R.id.tvD4, infoBean.getS8_dxf_sp0())
                .setText(R.id.tvD5, infoBean.getS8_dxf_bet())
                .setText(R.id.tvD6, infoBean.getS8_dxf_sp3());

        ImageView ivStarMatch = holderHelper.getView(R.id.ivStarMatch);
        if (isAttention)
            ivStarMatch.setVisibility(View.VISIBLE);
        else
            ivStarMatch.setVisibility(View.GONE);
        
        if (matchEntities != null && matchEntities.contains(data))
            ivStarMatch.setImageResource(R.drawable.star_yellow);
        else
            ivStarMatch.setImageResource(R.drawable.start);
        
        ivStarMatch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (onAttentionListener != null)
                    onAttentionListener.onAttentionMatch(data);
            }
        });
    }

    private OnAttentionListener onAttentionListener;

    public void setOnAttentionListener(OnAttentionListener onAttentionListener)
    {
        this.onAttentionListener = onAttentionListener;
    }

    private List<BasketballMatchEntity> matchEntities;

    public void setMatchEntities(List<BasketballMatchEntity> matchEntities)
    {
        if (matchEntities != null || matchEntities.size() > 0)
            this.matchEntities = matchEntities;
        else
            this.matchEntities = new ArrayList<>();

        notifyDataSetChanged();
    }
}
