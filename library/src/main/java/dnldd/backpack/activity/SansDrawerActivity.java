package dnldd.backpack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import dnldd.backpack.R;
import dnldd.backpack.TypefaceSpan;
import dnldd.backpack.utils.ContextUtils;

public class SansDrawerActivity extends BaseActivity {
    protected Toolbar toolbar;
    protected CollapsingToolbarLayout collapsingToolbarLayout;
    protected ImageView backdrop;
    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    private void bindComponents(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
    }

    public void setupSansDrawerActivity(int layoutResID, int homeIconResID){
        setContentView(layoutResID);
        bindComponents();
        if(homeIconResID > 0) {
            actionBar.setHomeAsUpIndicator(homeIconResID);
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void setupCollapsingToolbar(int backdropResID){
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        backdrop = (ImageView) findViewById(R.id.backdrop);
        ContextUtils.getApp(getApplicationContext()).getPicasso().load(backdropResID).into(backdrop);
    }

    public void setupCollapsingTitle(String title, String typefaceName, int baseColorResID, int expandedColorResID){
        SpannableString titleSpan = new SpannableString(title);
        titleSpan.setSpan(new TypefaceSpan(typefaceName), 0, title.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //noinspection deprecation
        titleSpan.setSpan(new ForegroundColorSpan(getResources().getColor(baseColorResID)), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        collapsingToolbarLayout.setTitle(titleSpan);
        //noinspection deprecation
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(baseColorResID));
        //noinspection deprecation
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(expandedColorResID));
    }

    @Override
    public void onNewIntent(Intent intent) {
        /* consume notification intents here in onCreate() */
    }
}
