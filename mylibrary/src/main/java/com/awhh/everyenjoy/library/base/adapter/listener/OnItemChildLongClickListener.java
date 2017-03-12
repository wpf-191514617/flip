package com.awhh.everyenjoy.library.base.adapter.listener;

import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：王鹏飞
 * 创建时间：2015/12/28 15:43
 * 邮箱：15291967179@163.com
 * 描述：AdapterView 和 RecyclerView 中Item 子控件的长按时间的监听
 */
public interface OnItemChildLongClickListener
{
    
    boolean onItemChildLongClick(ViewGroup parent, View childView, int position);
    
}
