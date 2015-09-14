package dnldd.backpack.core;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import dnldd.backpack.model.Entity;
import dnldd.backpack.viewholder.ViewHolder;


public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{
    protected List<?> items;
    protected BaseApplication app;
    protected LayoutInflater layoutInflater;

    public RecyclerAdapter(Context context, List<?> items){
        this.items = items;
        this.app = (BaseApplication) context.getApplicationContext();
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return null; /* override this every time after extending */
    }

    @Override
    public int getItemViewType(int position) {
        return 0; /* override this every time after extending */
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Entity item = (Entity) items.get(position);
        item.bindItem(app, viewHolder);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
