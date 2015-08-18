package dnldd.base;

import android.os.Bundle;

import dnldd.backpack.utils.ContextUtils;

public class Activity extends dnldd.backpack.activity.BarcodeActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupBarcodeActivity(dnldd.backpack.R.layout.bp_scanner_layout);
        //setupDrawerActivity(R.layout.bp_drawer_activity_layout, R.menu.bp_drawer_view, R.drawable.ic_menu);
        //setupDrawerActivity(R.layout.bp_drawer_activity_layout, R.menu.bp_drawer_view, R.drawable.ic_menu, R.layout.nav_header);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ContextUtils.getApp(getApplicationContext()).setupGCMService("143153256358", "http://google.com");
    }
}