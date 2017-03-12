package com.awhh.everyenjoy.library.base.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.awhh.everyenjoy.library.base.adapter.listener.OnItemChildCheckedChangeListener;
import com.awhh.everyenjoy.library.base.adapter.listener.OnItemChildClickListener;
import com.awhh.everyenjoy.library.base.adapter.listener.OnItemChildLongClickListener;
import com.awhh.everyenjoy.library.base.util.StringUtils;


/**
 * 作者：王鹏飞
 * 创建时间：2015/12/28 16:05
 * 邮箱：15291967179@163.com
 * 描述： AdapterView 和 RecyclerView 的Item 设置常见属性
 */
public class ViewHolderHelper implements View.OnClickListener, View.OnLongClickListener, CompoundButton.OnCheckedChangeListener
{

    protected SparseArray<View> mViews;
    protected OnItemChildCheckedChangeListener childCheckedChangeListener;
    protected OnItemChildClickListener childClickListener;
    protected OnItemChildLongClickListener childLongClickListener;

    protected View mConvertView;
    protected Context mContext;
    protected int mPosition;


    protected ViewGroup mAdapterView;

    protected BaseRecyclerViewHolder baseRecyclerViewHolder;
    protected RecyclerView mRecyclerView;

    
    
    public BaseRecyclerViewHolder getRecyclerViewHolder()
    {
        return baseRecyclerViewHolder;
    }

    public void setRecyclerViewHolder(BaseRecyclerViewHolder baseRecyclerViewHolder)
    {
        this.baseRecyclerViewHolder = baseRecyclerViewHolder;
    }

    public ViewHolderHelper(ViewGroup viewGroup, View convertView)
    {
        mViews = new SparseArray<>();
        mAdapterView = viewGroup;
        mConvertView = convertView;
        mContext = convertView.getContext();
    }

    public ViewHolderHelper(RecyclerView recyclerView, View convertView)
    {
        mViews = new SparseArray<>();
        mRecyclerView = recyclerView;
        mConvertView = convertView;
        mContext = convertView.getContext();
    }

    public int getPosition()
    {
        if (baseRecyclerViewHolder != null)
        {
            return baseRecyclerViewHolder.getLayoutPosition();
        }
        return mPosition;
    }

    public void setPosition(int mPosition)
    {
        this.mPosition = mPosition;
    }

    /**
     * 为ItemView的子控件 设置点击事件
     *
     * @param itemChildClickListener
     */
    public void setOnItemChildClickListener(OnItemChildClickListener itemChildClickListener)
    {
        this.childClickListener = itemChildClickListener;
    }


    public void setItemChildClickListener(int viewId)
    {

        getView(viewId).setOnClickListener(this);

    }


    /**
     * 为ItemView的子控件  设置长按事件
     *
     * @param itemChildLongClickListener
     */
    public void setOnItemChildLongClickListener(OnItemChildLongClickListener itemChildLongClickListener)
    {
        this.childLongClickListener = itemChildLongClickListener;
    }


    public void setItemChildLongClickListener(int viewId)
    {
        getView(viewId).setOnLongClickListener(this);
    }

    /**
     * 为ItemView的子控件 设置选中状态
     *
     * @param itemChildCheckedChangeListener
     */
    public void setOnItemChildCheckedChangeListener(OnItemChildCheckedChangeListener itemChildCheckedChangeListener)
    {
        this.childCheckedChangeListener = itemChildCheckedChangeListener;
    }

    public void setItemChildCheckedChangeListener(int viewId)
    {
        if (getView(viewId) instanceof CompoundButton)
        {
            ((CompoundButton) getView(viewId)).setOnCheckedChangeListener(this);
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        if (childCheckedChangeListener != null)
        {
            if (mRecyclerView != null)
            {
                childCheckedChangeListener.onItemChildCheckedChanged(mRecyclerView, buttonView, getPosition(), isChecked);
            } else if (mAdapterView != null)
            {
                childCheckedChangeListener.onItemChildCheckedChanged(mAdapterView, buttonView, getPosition(), isChecked);
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        if (childClickListener != null)
        {
            if (mRecyclerView != null)
            {
                childClickListener.onItemChildClick(mRecyclerView, v, getPosition());
            } else if (mAdapterView != null)
            {
                childClickListener.onItemChildClick(mAdapterView, v, getPosition());
            }
        }
    }

    @Override
    public boolean onLongClick(View v)
    {
        if (childLongClickListener != null)
        {
            if (mRecyclerView != null)
            {
                return childLongClickListener.onItemChildLongClick(mRecyclerView, v, getPosition());
            } else if (mAdapterView != null)
            {
                return childLongClickListener.onItemChildLongClick(mAdapterView, v, getPosition());
            }
        }
        return false;
    }

    public <T extends View> T getView(int viewId)
    {

        View view = mViews.get(viewId);

        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;

    }

    public ViewHolderHelper setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        if (StringUtils.isEmpty(text)){
            tv.setText("");   
        }else {
            tv.setText(text);
        }return this;
    }

 
    public View getConvertView(){
        return mConvertView;
    }

    public ViewHolderHelper setImageBitmap(int viewId, Bitmap bmp)
    {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bmp);
        return this;
    }

    /**
     * 设置对应id的控件是否选中
     *
     * @param viewId
     * @param checked
     * @return
     */
    public ViewHolderHelper setChecked(@IdRes int viewId, boolean checked)
    {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 设置View是否可见
     * @param viewId
     * @param visibility
     * @return
     */
    public ViewHolderHelper setVisibility(@IdRes int viewId, int visibility)
    {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * @param viewId
     * @param textColorResId 颜色资源id
     * @return
     */
    public ViewHolderHelper setTextColorRes(@IdRes int viewId, @ColorRes int textColorResId)
    {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorResId));
        return this;
    }

    /**
     * @param viewId
     * @param textColor 颜色值
     * @return
     */
    public ViewHolderHelper setTextColor(@IdRes int viewId, int textColor)
    {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * @param viewId
     * @param backgroundResId 背景资源id
     * @return
     */
    public ViewHolderHelper setBackgroundRes(@IdRes int viewId, int backgroundResId)
    {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundResId);
        return this;
    }

    /**
     * @param viewId
     * @param color  颜色值
     * @return
     */
    public ViewHolderHelper setBackgroundColor(@IdRes int viewId, int color)
    {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * @param viewId
     * @param colorResId 颜色值资源id
     * @return
     */
    public ViewHolderHelper setBackgroundColorRes(@IdRes int viewId, @ColorRes int colorResId)
    {
        View view = getView(viewId);
        view.setBackgroundColor(mContext.getResources().getColor(colorResId));
        return this;
    }

    /**
     * @param viewId
     * @param imageResId 图像资源id
     * @return
     */
    public ViewHolderHelper setImageResource(@IdRes int viewId, @DrawableRes int imageResId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }
    
}
