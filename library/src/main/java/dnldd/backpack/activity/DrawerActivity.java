package dnldd.backpack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import dnldd.backpack.R;

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

    public void setupDrawerActivity(int layoutResID, int navigationResID, int homeIconResID){
        setContentView(layoutResID);
        bindComponents();
        inflateNavigation(navigationResID);
        if(homeIconResID != 0) {
            actionBar.setHomeAsUpIndicator(homeIconResID);
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void setupDrawerActivity(int activityResID, int navigationResID, int homeIconResID, int headerResID){
        setupDrawerActivity(activityResID, navigationResID, homeIconResID);
        navigationView.inflateHeaderView(headerResID);
    }

    private void bindComponents(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
    }

    private void inflateNavigation(int menu){
        navigationView.inflateMenu(menu);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }
}
