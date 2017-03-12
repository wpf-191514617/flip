package com.awhh.everyenjoy.library.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awhh.everyenjoy.autolayout.utils.AutoUtils;

/**
 * 作者：王鹏飞
 * 创建时间：2015/12/17 14:03
 * 邮箱：15291967179@163.com
 * 描述： ListView ，GridView 通用 ViewHolder
 * 使用链式编程
 */
public class BaseListViewHolder
{

    private View mConvertView;
    
    private ViewHolderHelper holderHelper;

    private BaseListViewHolder(Context context, ViewGroup parent,
                               int layoutId, int position)
    {
        
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
        holderHelper = new ViewHolderHelper(parent , mConvertView);
        AutoUtils.autoSize(mConvertView);
    }


    public static BaseListViewHolder dequeReusableListViewHolder(Context context,
                                                                 View convertView, ViewGroup parent, int layoutId, int position)
    {
        if (convertView == null)
        {
            return new BaseListViewHolder(context, parent, layoutId, position);
        }

        BaseListViewHolder holder = (BaseListViewHolder) convertView.getTag();

        return holder;

    }

    public ViewHolderHelper getViewHolderHelper() {
        return holderHelper;
    }

    public View getConvertView() {
        return mConvertView;
    }
   


}
