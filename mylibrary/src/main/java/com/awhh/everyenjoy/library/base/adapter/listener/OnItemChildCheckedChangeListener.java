package com.awhh.everyenjoy.library.base.adapter.listener;

import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：王鹏飞
 * 创建时间：2015/12/28 15:38
 * 邮箱：15291967179@163.com
 * 描述：AdapterView 和 RecyclerView 中item子控件 选中状态是事件的监听
 */
public interface OnItemChildCheckedChangeListener
{

    void onItemChildCheckedChanged(ViewGroup parent,
                                   View childView, int position, boolean isChecked);

}
