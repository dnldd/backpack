package dnldd.base;

import android.os.Bundle;

import dnldd.backpack.utils.ContextUtils;
import dnldd.backpack.utils.TransitionUtils;
import dnldd.backpack.utils.TypefaceUtils;

public class SingleFragmentActivity extends dnldd.backpack.activity.TabbedSansDrawerActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupSansDrawerActivity(R.layout.bp_paralax_toolbar_layout, 0);
        setupCollapsingToolbar();
        setupCollapsingToolbarTitle("Base", TypefaceUtils.CONDENSED_REGULAR, dnldd.backpack.R.color.black_alpha_87, dnldd.backpack.R.color.pink_500);
    }

    @Override
    public void onStart() {
        super.onStart();
        Application application = (Application) ContextUtils.getApp(getApplicationContext());
        application.setupGCMService("143153256358", "http://google.com");

        /* ShowcaseFragment fragment = (ShowcaseFragment) application.getFragment(ShowcaseFragment.class.getName());
        TransitionUtils.transitionFragmentIn(application.getCurrentActivity().getSupportFragmentManager(), fragment); */

        /* FABListFragment fragment = (FABListFragment) application.getFragment(FABListFragment.class.getName());
        TransitionUtils.transitionFragmentIn(application.getCurrentActivity().getSupportFragmentManager(), fragment); */

        ListFragment fragment = (ListFragment) application.getFragment(ListFragment.class.getName());
        TransitionUtils.transitionFragmentIn(application.getCurrentActivity().getSupportFragmentManager(), fragment);

        /* FormFragment fragment = (FormFragment) application.getFragment(FormFragment.class.getName());
        TransitionUtils.transitionFragmentIn(application.getCurrentActivity().getSupportFragmentManager(), fragment); */
    }
}