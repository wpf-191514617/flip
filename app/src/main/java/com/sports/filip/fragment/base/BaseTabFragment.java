package com.sports.filip.fragment.base;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.sports.filip.R;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pengfei on 2016/11/16.
 */

public abstract class BaseTabFragment extends BaseFragment {
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    
    private VPAndTLAdapter vpAndTLAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.layout_tab;
    }

    @Override
    protected void initViewAndData() {
        vpAndTLAdapter = new VPAndTLAdapter(getChildFragmentManager());
        viewPager.setAdapter(vpAndTLAdapter);
        tablayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(getFragments().size()-1);
    }

   
    class VPAndTLAdapter extends FragmentStatePagerAdapter
    {

        private String[] tabNames;
        private List<BaseFragment> fragments;

        public VPAndTLAdapter(FragmentManager fm) {
            super(fm);
            fragments= getFragments();
            tabNames = getTabNames();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }

    protected abstract String[] getTabNames();

    protected abstract List<BaseFragment> getFragments();

}
