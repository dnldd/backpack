package dnldd.backpack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import dnldd.backpack.R;

public class SansDrawerActivity extends BaseActivity {
    protected Toolbar toolbar;
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

    @Override
    public void onNewIntent(Intent intent) {
        /* consume notification intents here in onCreate() */
    }
}
