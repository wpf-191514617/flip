package com.awhh.everyenjoy.library.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.awhh.everyenjoy.library.base.adapter.listener.OnItemChildCheckedChangeListener;
import com.awhh.everyenjoy.library.base.adapter.listener.OnItemChildClickListener;
import com.awhh.everyenjoy.library.base.adapter.listener.OnItemChildLongClickListener;
import com.awhh.everyenjoy.library.base.adapter.listener.OnRecyclerItemClickListener;
import com.awhh.everyenjoy.library.base.adapter.listener.OnRecyclerItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王鹏飞
 * 创建时间：2015/12/28 10:18
 * 邮箱：15291967179@163.com
 * 描述：
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T>>
{

    protected final int mItemLayoutId;

    protected Context mContext;
    protected List<T> mDatas;


    protected OnItemChildClickListener mOnItemChildClickListener;
    protected OnItemChildLongClickListener mOnItemChildLongClickListener;
    protected OnItemChildCheckedChangeListener mOnItemChildCheckedChangeListener;

//    protected ImageLoader imageLoader;

//    protected ImageLoaderHelper loaderHelper;

    public void setOnRecyclerItemLongClickListener(OnRecyclerItemLongClickListener mOnRVItemLongClickListener)
    {
        this.mOnRVItemLongClickListener = mOnRVItemLongClickListener;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener mOnRVItemClickListener)
    {
        this.mOnRVItemClickListener = mOnRVItemClickListener;
    }

    protected OnRecyclerItemClickListener mOnRVItemClickListener;
    protected OnRecyclerItemLongClickListener mOnRVItemLongClickListener;

    protected RecyclerView mRecyclerView;

    public BaseRecyclerAdapter(RecyclerView recyclerView, int mItemLayoutId, List<T> data)
    {
        this.mRecyclerView = recyclerView;
        this.mItemLayoutId = mItemLayoutId;
        this.mContext = recyclerView.getContext();
        setDatas(data);
//        imageLoader = ImageLoader.getInstance();
//        loaderHelper = ImageLoaderHelper.getInstance(MyApplication.getApplication());
    }


    /**
     * 设置数据集
     *
     * @param data
     */
    public void setDatas(List<T> data)
    {
        if (data != null && data.size() > 0)
        {
            this.mDatas = data;
        } else
        {
            if (this.mDatas != null && this.mDatas.size() > 0)
            {
                this.mDatas.clear();
            } else
            {
                this.mDatas = new ArrayList<>();
            }
        }
    }


    @Override
    public BaseRecyclerViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType)
    {
        BaseRecyclerViewHolder viewHolder = new BaseRecyclerViewHolder(mRecyclerView, LayoutInflater.from(mContext).
                inflate(mItemLayoutId, parent, false), mOnRVItemClickListener, mOnRVItemLongClickListener);

        viewHolder.getViewHolderHelper().setOnItemChildClickListener(mOnItemChildClickListener);
        viewHolder.getViewHolderHelper().setOnItemChildLongClickListener(mOnItemChildLongClickListener);
        viewHolder.getViewHolderHelper().setOnItemChildCheckedChangeListener(mOnItemChildCheckedChangeListener);
        setItemChildListener(viewHolder.getViewHolderHelper());
        return viewHolder;
    }

    /**
     * 为item的孩子节点设置监听器，并不是每一个数据列表都要为item的子控件添加事件监听器，所以这里采用了空实现，需要设置事件监听器时重写该方法即可
     *
     * @param viewHolderHelper
     */
    protected void setItemChildListener(ViewHolderHelper viewHolderHelper)
    {
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder<T> holder, int position)
    {
        fillData(holder.getViewHolderHelper(), position, getItem(position));
    }

    /**
     * 填充数据的方法
     *
     * @param viewHolderHelper
     * @param position
     * @param item
     */
    protected abstract void fillData(ViewHolderHelper viewHolderHelper, int position, T item);


    @Override
    public int getItemCount()
    {
        return mDatas == null ? 0 : mDatas.size();
    }

    public T getItem(int position)
    {
        return mDatas.get(position);
    }

    /**
     * 在集合头部添加新的数据集合（下拉从服务器获取最新的数据集合，例如新浪微博加载最新的几条微博数据）
     *
     * @param datas
     */
    public void addNewDatas(List<T> datas)
    {
        if (datas != null)
        {
            mDatas.addAll(0, datas);
            notifyItemRangeInserted(0, datas.size());
        }
    }

    /**
     * 在集合尾部添加更多数据集合（上拉从服务器获取更多的数据集合，例如新浪微博列表上拉加载更晚时间发布的微博数据）
     *
     * @param datas
     */
    public void addMoreDatas(List<T> datas)
    {
        if (datas != null)
        {
            mDatas.addAll(mDatas.size(), datas);
            notifyItemRangeInserted(mDatas.size(), datas.size());
        }
    }


    /**
     * 清空数据列表
     */
    public void clear()
    {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除指定索引数据条目
     *
     * @param position
     */
    public void removeItem(int position)
    {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 删除指定数据条目
     *
     * @param model
     */
    public void removeItem(T model)
    {
        removeItem(mDatas.indexOf(model));
    }

    /**
     * 在指定位置添加数据条目
     *
     * @param position
     * @param model
     */
    public void addItem(int position, T model)
    {
        mDatas.add(position, model);
        notifyItemInserted(position);
    }

    /**
     * 在集合头部添加数据条目
     *
     * @param model
     */
    public void addFirstItem(T model)
    {
        addItem(0, model);
    }

    /**
     * 在集合末尾添加数据条目
     *
     * @param model
     */
    public void addLastItem(T model)
    {
        addItem(mDatas.size(), model);
    }

    /**
     * 替换指定索引的数据条目
     *
     * @param location
     * @param newModel
     */
    public void setItem(int location, T newModel)
    {
        mDatas.set(location, newModel);
        notifyItemChanged(location);
    }

    /**
     * 替换指定数据条目
     *
     * @param oldModel
     * @param newModel
     */
    public void setItem(T oldModel, T newModel)
    {
        setItem(mDatas.indexOf(oldModel), newModel);
    }

    /**
     * 移动数据条目的位置
     *
     * @param fromPosition
     * @param toPosition
     */
    public void moveItem(int fromPosition, int toPosition)
    {
        mDatas.add(toPosition, mDatas.remove(fromPosition));
        notifyItemMoved(fromPosition, toPosition);
    }

}
