package dnldd.backpack.fragment;


import android.os.Bundle;

import dnldd.backpack.contract.ChildFragmentInterface;

public class ChildFragment extends BaseFragment implements ChildFragmentInterface {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onChildResume() {}
}