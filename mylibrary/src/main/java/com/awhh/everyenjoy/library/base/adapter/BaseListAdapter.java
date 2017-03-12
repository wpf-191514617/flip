package com.awhh.everyenjoy.library.base.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.awhh.everyenjoy.library.base.adapter.listener.OnItemChildCheckedChangeListener;
import com.awhh.everyenjoy.library.base.adapter.listener.OnItemChildClickListener;
import com.awhh.everyenjoy.library.base.adapter.listener.OnItemChildLongClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：王鹏飞
 * 创建时间：2015/12/17 15:31
 * 邮箱：15291967179@163.com
 * 描述：ListView, GridView 通用适配器
 */
public abstract class BaseListAdapter<T> extends BaseAdapter
{

    protected Context mContext;

    protected final int mItemLayoutId;
    
    protected List<T> mDatas;

    protected OnItemChildClickListener mOnItemChildClickListener;
    protected OnItemChildLongClickListener mOnItemChildLongClickListener;
    protected OnItemChildCheckedChangeListener mOnItemChildCheckedChangeListener;

//    protected ImageLoader imageLoader;
//
//    protected ImageLoaderHelper loaderHelper;
    protected Drawable drawable;
    /**
     * 
     * @param context
     * @param itemLayoutId
     * @param mDatas
     */
    public BaseListAdapter(Context context, int itemLayoutId , List<T> mDatas)
    {
        if (context == null)
        {
            throw new NullPointerException("在BaseListAdapter中传入的Context为空");

        } else
        {
            this.mContext = context;
        }
        this.mItemLayoutId = itemLayoutId;
        setDatas(mDatas);
        
//        imageLoader = ImageLoader.getInstance();
//        
//        loaderHelper = ImageLoaderHelper.getInstance(context);
//        drawable = context.getResources().getDrawable(R.drawable.icon_default_img);
    }
    
    public void setDatas(List<T> datas)
    {
        if (datas != null && datas.size() > 0)
        {
            this.mDatas = datas;
        } else
        {
            this.mDatas = new ArrayList<>();
        }
    }

    public void insertData(T data)
    {
        this.mDatas.add(data);
        notifyDataSetChanged();
    }

    public void insertData(int position, T data)
    {
        this.mDatas.add(position, data);
        notifyDataSetChanged();
    }

    /**
     * @param datas
     */
    public void addMoreDatas(List<T> datas)
    {
        if (this.mDatas != null)
        {
            this.mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }   

    public void addFirstData(T data){
        this.mDatas.add(0, data);
        notifyDataSetChanged();
    }
    
    /**
     * 重新设置当前列表数据
     *
     * @param datas 新数据
     */
    public void clearAddData(List<T> datas)
    {
        if (datas != null)
        {
            mDatas = datas;
        } else
        {
            mDatas.clear();
        }
        notifyDataSetChanged();
    }


    /**
     * 清空列表数据
     */
    public void clear()
    {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取数据集
     *
     * @return
     */
    public List<T> getDatas()
    {
        return mDatas;
    }

    /**
     * 删除指定索引数据条目
     *
     * @param position
     */
    public void removeItem(int position)
    {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 删除指定数据条目
     *
     * @param data
     */
    public void removeItem(T data)
    {
        mDatas.remove(data);
        notifyDataSetChanged();
    }

    /**
     * 替换指定索引的数据条目
     *
     * @param position
     * @param newData
     */
    public void setItem(int position, T newData)
    {
        mDatas.set(position, newData);
        notifyDataSetChanged();
    }

    /**
     * 替换指定数据条目
     *
     * @param oldData
     * @param newData
     */
    public void setItem(T oldData, T newData)
    {
        setItem(mDatas.indexOf(oldData), newData);
    }

    /**                  
     * 交换俩个数据条目的位置
     *
     * @param fromPosition
     * @param toPosition
     */
    public void moveItem(int fromPosition, int toPosition)
    {
        Collections.swap(mDatas, fromPosition, toPosition);
        notifyDataSetChanged();

    }

    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    @Override
    public T getItem(int position)
    {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        BaseListViewHolder viewHolder = BaseListViewHolder.dequeReusableListViewHolder(
                mContext, convertView, parent, mItemLayoutId, position);

        viewHolder.getViewHolderHelper().setPosition(position);
        viewHolder.getViewHolderHelper().setOnItemChildClickListener(mOnItemChildClickListener);
        viewHolder.getViewHolderHelper().setOnItemChildLongClickListener(mOnItemChildLongClickListener);
        viewHolder.getViewHolderHelper().setOnItemChildCheckedChangeListener(mOnItemChildCheckedChangeListener);
        setItemChildListener(viewHolder.getViewHolderHelper());

        fillData(viewHolder.getViewHolderHelper() , position, getItem(position));

        return viewHolder.getConvertView();
    }

    /**
     * 为item的孩子节点设置监听器，并不是每一个数据列表都要为item的子控件添加事件监听器，所以这里采用了空实现，需要设置事件监听器时重写该方法即可
     *
     * @param viewHolderHelper
     */
    protected void setItemChildListener(ViewHolderHelper viewHolderHelper) {
    }

    /**
     * 设置item中的子控件点击事件监听器
     *
     * @param onItemChildClickListener
     */
    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    /**
     * 设置item中的子控件长按事件监听器
     *
     * @param onItemChildLongClickListener
     */
    public void setOnItemChildLongClickListener(OnItemChildLongClickListener onItemChildLongClickListener) {
        mOnItemChildLongClickListener = onItemChildLongClickListener;
    }

    /**
     * 设置item子控件选中状态变化事件监听器
     *
     * @param onItemChildCheckedChangeListener
     */
    public void setOnItemChildCheckedChangeListener(OnItemChildCheckedChangeListener onItemChildCheckedChangeListener) {
        mOnItemChildCheckedChangeListener = onItemChildCheckedChangeListener;
    }
    

    protected abstract void fillData(ViewHolderHelper holderHelper , int position, T data);

}
