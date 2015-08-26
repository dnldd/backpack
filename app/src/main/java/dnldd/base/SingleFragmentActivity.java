package dnldd.base;

import android.os.Bundle;

import dnldd.backpack.utils.ContextUtils;
import dnldd.backpack.utils.TransitionUtils;
import dnldd.backpack.utils.TypefaceUtils;

public class SingleFragmentActivity extends dnldd.backpack.activity.TabbedSansDrawerActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupSansDrawerActivity(R.layout.bp_activity_layout, 0);
        styleTitle("Base", TypefaceUtils.CONDENSED_REGULAR, dnldd.backpack.R.color.black_alpha_87, 0);
    }


    @Override
    public void onStart() {
        super.onStart();
        Application application = (Application) ContextUtils.getApp(getApplicationContext());
        application.setupGCMService("143153256358", "http://google.com");

        ShowcaseFragment fragment = (ShowcaseFragment) application.getFragment(ShowcaseFragment.class.getName());
        TransitionUtils.transitionFragmentIn(application.getCurrentActivity().getSupportFragmentManager(), fragment);
    }
}