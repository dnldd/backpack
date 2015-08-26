package dnldd.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dnldd.backpack.core.RecyclerAdapter;
import dnldd.backpack.view.TextView;
import dnldd.backpack.viewholder.ViewHolder;


public class StringRecyclerAdapter extends RecyclerAdapter {
    public StringRecyclerAdapter(Context context, List<?> items){
        super(context, items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_layout, viewGroup, false);
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        StringViewHolder holder = (StringViewHolder) viewHolder;
        holder.getTextView().setText((String) items.get(position));
    }

    public void togglePrompt(TextView prompt){
        prompt.setVisibility(getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
