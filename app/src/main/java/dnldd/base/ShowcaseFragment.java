package dnldd.base;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import dnldd.backpack.core.StringSpinnerAdapter;
import dnldd.backpack.fragment.ParentFragment;

public class ShowcaseFragment extends ParentFragment {
    public @Bind(R.id.spinner) AppCompatSpinner spinner;
    public @Bind(R.id.spinnerr) AppCompatSpinner secondSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inject(inflater,R.layout.showcase_layout, container);

        ArrayList<String> items = new ArrayList<>();
        items.add(0, "Array Item One");
        items.add(0, "Array Item Two");
        items.add(0, "Array Item Three");
        items.add(0, "Array Item Four");

        StringSpinnerAdapter adapter = new StringSpinnerAdapter(context, items);
        StringSpinnerAdapter secondAdapter = new StringSpinnerAdapter(context, items);
        spinner.setAdapter(adapter);
        secondSpinner.setAdapter(secondAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}