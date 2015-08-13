package dnldd.backpack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import dnldd.backpack.R;
import dnldd.backpack.view.progressbar.SmoothProgressBar;

public class DrawerActivity extends BaseActivity {
    protected DrawerLayout drawerLayout;
    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected ActionBar actionBar;
    protected SmoothProgressBar smoothProgressBar;


    public void showProgress(){
        if (smoothProgressBar != null){
            smoothProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgress(){
        if (smoothProgressBar != null){
            smoothProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
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

    public void setupDrawerActivity(int menu){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        smoothProgressBar = (SmoothProgressBar) findViewById(R.id.progress);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        setupNavigationMenu(menu);
    }


    public void setupNavigationMenu(int menu){
        navigationView.inflateMenu(menu);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }
}
