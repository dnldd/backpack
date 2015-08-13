package dnldd.backpack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import dnldd.backpack.R;
import dnldd.backpack.view.progressbar.SmoothProgressBar;

public class SansDrawerActivity extends BaseActivity {
    protected Toolbar toolbar;
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

    public void setupSansDrawerActivity(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        smoothProgressBar = (SmoothProgressBar) findViewById(R.id.progress);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
    }

    @Override
    public void onNewIntent(Intent intent) {
        /* consume notification intents here in onCreate() */
    }
}
