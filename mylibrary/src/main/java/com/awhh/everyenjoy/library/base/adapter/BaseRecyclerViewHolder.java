package com.awhh.everyenjoy.library.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.awhh.everyenjoy.autolayout.utils.AutoUtils;
import com.awhh.everyenjoy.library.base.adapter.listener.OnRecyclerItemClickListener;
import com.awhh.everyenjoy.library.base.adapter.listener.OnRecyclerItemLongClickListener;


/**
 * 作者：王鹏飞
 * 创建时间：2015/12/28 14:19
 * 邮箱：15291967179@163.com
 * 描述：
 */
public class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
{

    private Context mContext;

    private RecyclerView recyclerView;

    private ViewHolderHelper mHolderHelper;

    private OnRecyclerItemClickListener mRecyclerItemClickListener;

    private OnRecyclerItemLongClickListener mRecyclerItemLongClickListener;
    

    public BaseRecyclerViewHolder(RecyclerView recyclerView, View itemView, OnRecyclerItemClickListener itemClickListener
            , OnRecyclerItemLongClickListener itemLongClickListener)
    {
        super(itemView);
        AutoUtils.autoSize(itemView);
        this.recyclerView = recyclerView;
        mContext = recyclerView.getContext();
        this.mRecyclerItemClickListener = itemClickListener;
        this.mRecyclerItemLongClickListener = itemLongClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        mHolderHelper = new ViewHolderHelper(recyclerView, this.itemView);
        mHolderHelper.setRecyclerViewHolder(this);
    }

    

    public ViewHolderHelper getViewHolderHelper()
    {
        return mHolderHelper;
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == this.itemView.getId()
                && mRecyclerItemClickListener != null){
            
            mRecyclerItemClickListener.onRecyclerItemClick(recyclerView , v , getLayoutPosition());
            
        }
    }

    @Override
    public boolean onLongClick(View v)
    {
        if (v.getId() == this.itemView.getId() && null != mRecyclerItemLongClickListener)
        {
            return mRecyclerItemLongClickListener.onRecyclerItemLongClick(recyclerView, v, getLayoutPosition());
        }
        return false;
    }
}
