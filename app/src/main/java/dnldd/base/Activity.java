package dnldd.base;

import android.os.Bundle;

public class Activity extends dnldd.backpack.activity.BarcodeActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupBarcodeActivity(dnldd.backpack.R.layout.bp_scanner_layout);
        //setupDrawerActivity(R.layout.bp_drawer_activity_layout, R.menu.bp_drawer_view, R.drawable.ic_menu);
        //setupDrawerActivity(R.layout.bp_drawer_activity_layout, R.menu.bp_drawer_view, R.drawable.ic_menu, R.layout.nav_header);
    }
}