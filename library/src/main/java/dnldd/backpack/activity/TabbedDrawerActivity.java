package dnldd.backpack.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Set;

import dnldd.backpack.R;
import dnldd.backpack.core.TabsPagerAdapter;
import dnldd.backpack.utils.TypefaceUtils;
import dnldd.backpack.view.SlidingTabLayout;

public class TabbedDrawerActivity extends DrawerActivity {
    protected SlidingTabLayout tabLayout;
    protected TabsPagerAdapter tabsPagerAdapter;
    protected ViewPager viewPager;

    @Override
    public void onNewIntent(Intent intent) {
        /* consume notification intents here in onCreate() */
    }

    public void styleTabs( final int indicatorColorResID, int fontColorResID, int fontSize, String typefaceName){
        tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                //noinspection deprecation,deprecation
                return getResources().getColor(indicatorColorResID);
            }
        });

        LinearLayout tabsLinearLayout = ((LinearLayout) tabLayout.getChildAt(0));
        for (int index = 0; index < tabsLinearLayout.getChildCount(); index++) {
            TextView textView = (TextView) tabsLinearLayout.getChildAt(index);
            if(fontColorResID > 0) {
                //noinspection deprecation,deprecation
                textView.setTextColor(getResources().getColor(fontColorResID));
            }

            if(typefaceName != null) {
                textView.setTypeface(TypefaceUtils.getTypeface(TypefaceUtils.CONDENSED_REGULAR));
            }

            if(fontSize > 0) {
                TypefaceUtils.setFontSize(textView, fontSize);
            }

            textView.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    public void setupTabs(LinkedHashMap<String, Fragment> fragments) {
        tabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        tabLayout.setDistributeEvenly(true);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        tabsPagerAdapter = new TabsPagerAdapter(this.getSupportFragmentManager());
        Set<String> titles = fragments.keySet();

        for (String title : titles){
            tabsPagerAdapter.addFragment(fragments.get(title), title);
        }

        viewPager.setAdapter(tabsPagerAdapter);
        tabLayout.setViewPager(viewPager);
    }
}
