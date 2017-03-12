package com.sports.filip.fragment.home;

import android.view.View;
import android.widget.TextView;

import com.sports.filip.R;
import com.sports.filip.fragment.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by pengfei on 2016/11/13.
 */

public class LiveFragment extends BaseFragment
{
    @Bind(R.id.tvSelectScore)
    TextView tvSelectScore;
    @Bind(R.id.tvSelectBaseketBall)
    TextView tvSelectBaseketBall;

    @Override
    protected int getContentViewLayoutID()
    {
        return R.layout.fragment_live;
    }

    @Override
    protected void initViewAndData()
    {
        tvSelectScore.setSelected(true);
        tvSelectBaseketBall.setSelected(false);
    }

    @OnClick({R.id.tvSelectScore, R.id.tvSelectBaseketBall})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.tvSelectScore:
                tvSelectScore.setSelected(true);
                tvSelectBaseketBall.setSelected(false);
                break;
            case R.id.tvSelectBaseketBall:
                tvSelectScore.setSelected(false);
                tvSelectBaseketBall.setSelected(true);
                break;
        }
    }
}
