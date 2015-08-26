package dnldd.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.LinkedHashMap;

import dnldd.backpack.fragment.ChoiceDialog;
import dnldd.backpack.fragment.TitledChoiceDialog;
import dnldd.backpack.utils.ContextUtils;
import dnldd.backpack.utils.TypefaceUtils;

public class SansDrawerActivity extends dnldd.backpack.activity.TabbedSansDrawerActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LinkedHashMap<String, Fragment> fragments = new LinkedHashMap<>();
        fragments.put("Food",  new ListFragment());
        fragments.put("Drinks", new ListFragment());
        fragments.put("Assorted", new ListFragment());

        setupSansDrawerActivity(R.layout.bp_tabbed_activity_layout, 0);
        setupTabs(fragments);
        styleTitle("Base", TypefaceUtils.CONDENSED_REGULAR, dnldd.backpack.R.color.black_alpha_87, 0);
        styleTabs(dnldd.backpack.R.color.black_alpha_54, dnldd.backpack.R.color.black_alpha_87, 0, TypefaceUtils.CONDENSED_REGULAR);
    }


    @Override
    public void onStart() {
        super.onStart();
        Application application = (Application) ContextUtils.getApp(getApplicationContext());
        application.setupGCMService("143153256358", "http://google.com");

        TitledChoiceDialog dialog = new TitledChoiceDialog()
                .setMessage("Want to kick it this Friday? With the homies, " +
                        "some games, Sprite and coke. and some nice girls? Come on come on," +
                        " it is going to be really fun my G.")
                .setTitle("Permissions");
        dialog.show(this.getSupportFragmentManager(), ChoiceDialog.class.getName());

        /*ChoiceDialog dialog = new ChoiceDialog().setMessage("Want to kick it this Friday with the homies?");
        dialog.show(this.getSupportFragmentManager(), ChoiceDialog.class.getName());*/
    }
}