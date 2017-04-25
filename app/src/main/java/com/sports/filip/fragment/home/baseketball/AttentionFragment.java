package com.sports.filip.fragment.home.baseketball;

import android.widget.ListView;

import com.awhh.everyenjoy.library.base.eventbus.EventCenter;
import com.awhh.everyenjoy.library.base.util.StringUtils;
import com.awhh.everyenjoy.library.db.NiceDB;
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

/**
 * Created by pengfei on 2016/11/17.
 * <p>
 * 足球------关注
 */

public class AttentionFragment extends BaseFragment implements OnAttentionListener
{

    @Bind(R.id.listView)
    ListView listView;
    private BasketballListAdapter listAdapter;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.listview;
    }

    @Override
    protected void initViewAndData()
    {
        listAdapter = new BasketballListAdapter(getActivity(), null);
        listAdapter.setAttention(true);
        listAdapter.setOnAttentionListener(this);
        listView.setAdapter(listAdapter);

        NiceDB niceDB = NiceDB.create(getActivity(), Constants.DBName.FOLLOW_BASKETBALL);
        List<BasketballMatchEntity> entities = niceDB.findAll(BasketballMatchEntity.class);
        listAdapter.addMoreDatas(entities);
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


        if (entities.contains(entity))
        {
            entities.remove(entity);
            niceDB.deleteByWhere(BasketballMatchEntity.class, "xid = '" + entity.getXid() + "'");
        } else
        {
            entities.add(entity);
            niceDB.save(entity);
        }
        listAdapter.clearAddData(entities);
        listAdapter.setMatchEntities(entities);
        EventCenter center = new EventCenter(EventCode.CODE_BASKETBALL_FOLLOW);
        EventBus.getDefault().post(center);
        
    }

}
