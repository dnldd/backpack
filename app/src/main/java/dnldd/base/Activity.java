package dnldd.base;

import android.os.Bundle;

public class Activity extends dnldd.backpack.activity.DrawerActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //setupDrawerActivity(R.layout.bp_drawer_activity_layout, R.menu.bp_drawer_view, R.drawable.ic_menu);
        setupDrawerActivity(R.layout.bp_drawer_activity_layout, R.menu.bp_drawer_view, R.drawable.ic_menu, R.layout.nav_header);
    }
}
