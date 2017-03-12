package com.awhh.everyenjoy.library.base.adapter.listener;

import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：王鹏飞
 * 创建时间：2015/12/28 15:46
 * 邮箱：15291967179@163.com
 * 描述：RecyclerView item的点击时间监听 
 */
public interface OnRecyclerItemClickListener
{
    
    void onRecyclerItemClick(ViewGroup viewGroup, View childView, int position);
    
}
