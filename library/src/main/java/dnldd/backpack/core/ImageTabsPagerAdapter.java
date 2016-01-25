package dnldd.backpack.core;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.util.ArrayList;
import java.util.List;

public class ImageTabsPagerAdapter extends FragmentPagerAdapter {
    protected List<String> mFragmentTitles;
    protected List<Fragment> mFragments;
    protected FragmentManager mFragmentManager;
    protected List<Integer> mTabIconResIds;
    protected int mImageColor;
    protected Context mContext;

    public List<Fragment> getFragments(){ return mFragments; }

    public ImageTabsPagerAdapter(FragmentManager manager, Context context, int imageColor) {
        super(manager);
        mFragments = new ArrayList<>();
        mFragmentTitles = new ArrayList<>();
        mTabIconResIds = new ArrayList<>();
        mFragmentManager = manager;
        mContext = context;
        mImageColor = imageColor;
    }

    public void addFragment(Fragment fragment, String title, int tabIconResId) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
        mTabIconResIds.add(tabIconResId);
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
        Drawable image = ContextCompat.getDrawable(mContext, mTabIconResIds.get(position));
        image.setBounds(0, 0, 36, 36); /* ideal bound size for tabs */
        image.setColorFilter(ContextCompat.getColor(mContext, mImageColor), PorterDuff.Mode.SRC_ATOP);
        SpannableString spannableString = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}