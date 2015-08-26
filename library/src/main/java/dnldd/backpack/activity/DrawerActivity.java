package dnldd.backpack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import dnldd.backpack.R;
import dnldd.backpack.TypefaceSpan;

public class DrawerActivity extends BaseActivity {
    protected DrawerLayout drawerLayout;
    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNewIntent(Intent intent) {
        /* consume notification intents here in onCreate() */
    }

    /* handle menu item click events here */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public void setupDrawerActivity(int layoutResID, int navigationResID, int homeIconResID, TypefaceSpan typefaceSpan){
        setContentView(layoutResID);
        bindComponents();
        inflateNavigation(navigationResID, typefaceSpan);
        if(homeIconResID != 0) {
            actionBar.setHomeAsUpIndicator(homeIconResID);
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void setupDrawerActivity(int activityResID, int navigationResID, int homeIconResID, int headerResID, TypefaceSpan typefaceSpan){
        setupDrawerActivity(activityResID, navigationResID, homeIconResID, typefaceSpan);
        navigationView.inflateHeaderView(headerResID);
    }

    private void bindComponents(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
    }

    private void inflateNavigation(int menu, TypefaceSpan typefaceSpan){
        navigationView.inflateMenu(menu);
        Menu items = navigationView.getMenu();


        for (int index = 0; index < items.size(); ++index){
            MenuItem item = items.getItem(index);
            SpannableString spannableString = new SpannableString(item.getTitle());
            spannableString.setSpan(typefaceSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            item.setTitle(spannableString);

            if(item.hasSubMenu()){
                SubMenu subMenu = item.getSubMenu();

                for (int subIndex = 0; subIndex < subMenu.size(); ++subIndex) {
                    MenuItem subItem = subMenu.getItem(subIndex);
                    SpannableString spannableSubString = new SpannableString(subItem.getTitle());
                    spannableSubString.setSpan(typefaceSpan, 0, spannableSubString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    subItem.setTitle(spannableSubString);
                }
            }
        }

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }
}
