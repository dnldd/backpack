package dnldd.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.LinkedHashMap;

import dnldd.backpack.TypefaceSpan;
import dnldd.backpack.utils.ContextUtils;
import dnldd.backpack.utils.TypefaceUtils;

public class TabbedDrawerActivity extends dnldd.backpack.activity.TabbedDrawerActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LinkedHashMap<String, Fragment> fragments = new LinkedHashMap<>();
        fragments.put("Food",  new ListFragment());
        fragments.put("Drinks", new ListFragment());
        fragments.put("Assorted", new ListFragment());

        TypefaceSpan typefaceSpan = new TypefaceSpan(TypefaceUtils.CONDENSED_REGULAR);
        setupDrawerActivity(R.layout.bp_tabbed_drawer_activity_layout, R.menu.drawer_menu, 0, R.layout.nav_header, typefaceSpan);
        setupTabs(fragments);
        styleTitle("Base", TypefaceUtils.CONDENSED_REGULAR, dnldd.backpack.R.color.black_alpha_87, 0);
        styleTabs(dnldd.backpack.R.color.black_alpha_54, dnldd.backpack.R.color.black_alpha_87, 0, TypefaceUtils.CONDENSED_REGULAR);
    }



    @Override
    public void onStart() {
        super.onStart();
        Application application = (Application) ContextUtils.getApp(getApplicationContext());
        application.setupGCMService("143153256358", "http://google.com");
    }
}