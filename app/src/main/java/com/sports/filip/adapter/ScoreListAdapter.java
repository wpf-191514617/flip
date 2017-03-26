package com.sports.filip.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.awhh.everyenjoy.library.base.adapter.BaseListAdapter;
import com.awhh.everyenjoy.library.base.adapter.ViewHolderHelper;
import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.db.NiceDB;
import com.sports.filip.Constants;
import com.sports.filip.EventCode;
import com.sports.filip.R;
import com.sports.filip.activity.account.LoginActivity;
import com.sports.filip.adapter.callback.OnFollowScoreMatchCallBack;
import com.sports.filip.entity.race.ScoreEntity;
import com.sports.filip.util.CacheHelper;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * author:pengfei
 * date:2017/3/12
 * Email:15291967179@163.com
 */

public class ScoreListAdapter extends BaseListAdapter<ScoreEntity>
{
    
    private OnFollowScoreMatchCallBack followScoreMatchCallBack;
    
    private NiceDB niceDB;
    
    private List<ScoreEntity> fromDB ;
    
    private boolean isDelData = false;

    public void setFollowScoreMatchCallBack(OnFollowScoreMatchCallBack followScoreMatchCallBack)
    {
        this.followScoreMatchCallBack = followScoreMatchCallBack;
    }

    public void setDelData(boolean delData)
    {
        isDelData = delData;
    }

    /**
     * @param context
     * @param mDatas
     */
    public ScoreListAdapter(Context context, List<ScoreEntity> mDatas)
    {
        super(context, R.layout.item_match_1, mDatas);
        niceDB = NiceDB.create(context , Constants.DBName.FOLLOW_SCORE);
        fromDB = niceDB.findAll(ScoreEntity.class);
    }
    
    public void onRefreshDBData(ScoreEntity entity){
        niceDB = NiceDB.create(mContext , Constants.DBName.FOLLOW_SCORE);
        fromDB = niceDB.findAllByWhere(ScoreEntity.class , "id='" + entity.getId() + "'");
        if (fromDB != null && fromDB.size() > 0)
        {
            niceDB.delete(entity);
            if (isDelData)
                mDatas.remove(entity);
        }else{
            niceDB.save(entity);
        }
        fromDB = niceDB.findAll(ScoreEntity.class);
        EventCenter center = new EventCenter(EventCode.CODE_SCORE_FOLLOW);
        EventBus.getDefault().post(center);
        notifyDataSetChanged();
    }
    
    public void onRefresh(){
        niceDB = NiceDB.create(mContext , Constants.DBName.FOLLOW_SCORE);
        fromDB = niceDB.findAll(ScoreEntity.class);
        notifyDataSetChanged();
    }
    
    

    @Override
    protected void fillData(ViewHolderHelper holderHelper, int position, final ScoreEntity data)
    {
        holderHelper.setText(R.id.tvMatchType, data.getLeague());
        holderHelper.setTextColor(R.id.tvMatchType, Color.parseColor(data.getColor()));
        holderHelper.setText(R.id.tvMatchTime, data.getTime());
        
        holderHelper.getView(R.id.linearLayout2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (StringUtils.isEmpty(CacheHelper.getUserId()))
                {
                    mContext.startActivity(new Intent(mContext , LoginActivity.class));
                    return;
                }
                onRefreshDBData(data);
            }
        });

        if (!StringUtils.isEmpty(data.getH_yellow()))
        {
            if (data.getH_yellow().equals("0"))
            {
                holderHelper.setText(R.id.tvHomeyellow, "");
                holderHelper.getView(R.id.tvHomeyellow).setVisibility(View.GONE);
            } else
            {
                holderHelper.setText(R.id.tvHomeyellow, data.getH_yellow());
                holderHelper.getView(R.id.tvHomeyellow).setVisibility(View.VISIBLE);
            }
        } else
        {
            holderHelper.setText(R.id.tvHomeyellow, "");
            holderHelper.getView(R.id.tvHomeyellow).setVisibility(View.GONE);
        }

        ImageView ivStarMatch = holderHelper.getView(R.id.ivStarMatch);
        if (isContain(data)){
            ivStarMatch.setImageResource(R.drawable.star_yellow);
        }else{
            ivStarMatch.setImageResource(R.drawable.start);
        }
        if (!StringUtils.isEmpty(data.getH_red()))
        {
            if (data.getH_red().equals("0"))
            {
                holderHelper.setText(R.id.tvHomeRed, "");
                holderHelper.getView(R.id.tvHomeRed).setVisibility(View.GONE);
            } else
            {
                holderHelper.setText(R.id.tvHomeRed, data.getH_red());
                holderHelper.getView(R.id.tvHomeRed).setVisibility(View.VISIBLE);
            }
        } else
        {
            holderHelper.setText(R.id.tvHomeRed, "");
            holderHelper.getView(R.id.tvHomeRed).setVisibility(View.GONE);
        }

        holderHelper.setText(R.id.tvHomeName, data.getH_name().trim().toString());
        if (!isBegin(data))
        {
            holderHelper.setText(R.id.tvHomeScore, "");
        } else
        {
            holderHelper.setText(R.id.tvHomeScore, data.getH_score());
        }

        if (!isBegin(data))
        {
            holderHelper.setText(R.id.tvTeamScore, "vs");
        } else
        {
            holderHelper.setText(R.id.tvTeamScore, " - ");
        }

        if (!StringUtils.isEmpty(data.getA_yellow()))
        {
            if (data.getA_yellow().equals("0"))
            {
                holderHelper.setText(R.id.tvVisityellow, "");
                holderHelper.getView(R.id.tvVisityellow).setVisibility(View.GONE);
            } else
            {
                holderHelper.setText(R.id.tvVisityellow, data.getA_yellow());
                holderHelper.getView(R.id.tvVisityellow).setVisibility(View.VISIBLE);
            }
        } else
        {
            holderHelper.setText(R.id.tvVisityellow, "");
            holderHelper.getView(R.id.tvVisityellow).setVisibility(View.GONE);
        }


        //holderHelper.setText(R.id.tvVisityellow , data.getA_yellow());


        //holderHelper.setText(R.id.tvVisitRed , data.getA_red());

        if (!StringUtils.isEmpty(data.getA_red()))
        {
            if (data.getA_red().equals("0"))
            {
                holderHelper.setText(R.id.tvVisitRed, "");
                holderHelper.getView(R.id.tvVisitRed).setVisibility(View.GONE);
            } else
            {
                holderHelper.setText(R.id.tvVisitRed, data.getA_red());
                holderHelper.getView(R.id.tvVisitRed).setVisibility(View.VISIBLE);
            }
        } else
        {
            holderHelper.setText(R.id.tvVisitRed, "");
            holderHelper.getView(R.id.tvVisitRed).setVisibility(View.GONE);
        }

        holderHelper.setText(R.id.tvVisitName, data.getA_name().trim().toString());

        if (!isBegin(data))
        {
            holderHelper.setText(R.id.tvVisitScore, "");
        } else
        {
            holderHelper.setText(R.id.tvVisitScore, data.getA_score());
        }
        holderHelper.setText(R.id.tvHomeTeamRanking, "[" + data.getH_rank() + "]");

        holderHelper.setText(R.id.tvVisitTeamRanking, "[" + data.getA_rank() + "]");

        if (!isBegin(data))
        {
            holderHelper.setText(R.id.tvHalfScore, "");
        } else
        {
            holderHelper.setText(R.id.tvHalfScore, "(" + data.getH_half() + "-" + data.getA_half() + ")");
        }

        if (!isBegin(data))
        {
            holderHelper.setText(R.id.tvDateTime, "");
        } else
        {
            if (!StringUtils.isEmpty(data.getDatetime()))
            {
                if (data.getDatetime().equals("中"))
                {
                    holderHelper.setText(R.id.tvDateTime, "中");
                                           } else
                {
                    holderHelper.setText(R.id.tvDateTime, data.getDatetime() + "'");
                }
            }else{
                holderHelper.setText(R.id.tvDateTime,  "");
            }
        }

        holderHelper.setText(R.id.tvD1, data.getD1());
        holderHelper.setText(R.id.tvD2, data.getD2());
        holderHelper.setText(R.id.tvD3, data.getD3());
        holderHelper.setText(R.id.tvD4, data.getD4());
        holderHelper.setText(R.id.tvD5, data.getD5());
        holderHelper.setText(R.id.tvD6, data.getD6());

    }

    private boolean isContain(ScoreEntity data)
    {
        if (fromDB == null || fromDB.size() < 1)
            return false;
        
        for (ScoreEntity entity : fromDB)
        {
            if (entity.getId().equals(data.getId()))
                return true;
        }
        return false;
    }

    private boolean isBegin(ScoreEntity data)
    {
        if (StringUtils.isEmpty(data.getDatetime()))
        {
            return true;
        }
        if (data.getDatetime().length() > 3)
        {
            return false;
        } else
        {
            return true;
        }
    }

}
