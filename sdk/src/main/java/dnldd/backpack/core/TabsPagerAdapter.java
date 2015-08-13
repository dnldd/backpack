package dnldd.backpack.core;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;

import dnldd.backpack.TabPagerItem;
import dnldd.backpack.fragment.BaseFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    protected String[] tabNames;
    protected SparseArray<android.support.v4.app.Fragment> tabsCache;
    protected ArrayList<TabPagerItem> tabs;

    public SparseArray<android.support.v4.app.Fragment> getTabsCache(){ return tabsCache; }

    public TabsPagerAdapter(FragmentManager manager, String [] tabNames, ArrayList<TabPagerItem> tabs) {
        super(manager);
        this.tabNames = tabNames;
        this.tabsCache = new SparseArray<>();
        this.tabs = tabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int i) {
        android.support.v4.app.Fragment fragment = tabsCache.get(i);
        if (fragment == null){
            fragment = BaseFragment.createFragment(tabNames[i]);
            tabsCache.put(i, fragment);
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        tabsCache.remove(position);
    }

    @Override
    public int getCount() { return tabs.size(); }

    @Override
    public CharSequence getPageTitle(int position) { return tabs.get(position).getTitle(); }
}