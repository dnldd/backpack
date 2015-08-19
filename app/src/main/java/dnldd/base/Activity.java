package dnldd.base;

import android.os.Bundle;

import dnldd.backpack.utils.ContextUtils;
import dnldd.backpack.utils.TransitionUtils;

public class Activity extends dnldd.backpack.activity.DrawerActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Application application = (Application) ContextUtils.getApp(getApplicationContext());
        setupDrawerActivity(R.layout.bp_drawer_activity_layout, R.menu.bp_drawer_view, 0);
        BPFragment fragment = (BPFragment) application.getFragment(BPFragment.class.getName());
        TransitionUtils.transitionFragmentIn(application.getCurrentActivity().getSupportFragmentManager(), fragment);
        //setupDrawerActivity(R.layout.bp_drawer_activity_layout, R.menu.bp_drawer_view, R.drawable.ic_menu, R.layout.nav_header);
    }

    @Override
    public void onStart() {
        super.onStart();
        Application application = (Application) ContextUtils.getApp(getApplicationContext());
        application.setupGCMService("143153256358", "http://google.com");

    }
}