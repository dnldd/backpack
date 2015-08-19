package dnldd.backpack.core;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    protected List<String> mFragmentTitles;
    protected List<Fragment> mFragments;
    protected FragmentManager mFragmentManager;

    public List<Fragment> getFragments(){ return mFragments; }

    public TabsPagerAdapter(FragmentManager manager) {
        super(manager);
        mFragments = new ArrayList<>();
        mFragmentTitles = new ArrayList<>();
        mFragmentManager = manager;
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}