package dnldd.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dnldd.backpack.fragment.ParentFragment;

public class BPFragment extends ParentFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inject(inflater, R.layout.fragment_layout,  container);
    }
}
