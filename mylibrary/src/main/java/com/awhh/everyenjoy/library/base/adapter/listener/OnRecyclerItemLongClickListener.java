package com.awhh.everyenjoy.library.base.adapter.listener;

import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：王鹏飞
 * 创建时间：2015/12/28 15:49
 * 邮箱：15291967179@163.com
 * 描述：RecyclerView Item 长按事件的监听
 */
public interface OnRecyclerItemLongClickListener
{

    boolean onRecyclerItemLongClick(
            ViewGroup parent, View childView, int position
    );

}
