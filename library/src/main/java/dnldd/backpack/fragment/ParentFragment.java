package dnldd.backpack.fragment;


import android.os.Bundle;

import dnldd.backpack.utils.ContextUtils;

public class ParentFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ContextUtils.getApp(context).setCurrentFragment(this);
    }
}