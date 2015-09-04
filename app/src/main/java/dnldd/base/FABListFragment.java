package dnldd.base;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import dnldd.backpack.fragment.ParentFragment;
import dnldd.backpack.view.TextView;

public class FABListFragment extends ParentFragment {
    protected  @Bind(R.id.recycler) RecyclerView recyclerView;
    protected  @Bind(R.id.prompt) TextView prompt;
    protected  @Bind(R.id.add) FloatingActionButton add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inject(inflater, R.layout.fab_list_layout,  container);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> items = new ArrayList<>();

        StringRecyclerAdapter adapter = new StringRecyclerAdapter(context, items);
        adapter.togglePrompt(prompt);


        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "Add Clicked.", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
