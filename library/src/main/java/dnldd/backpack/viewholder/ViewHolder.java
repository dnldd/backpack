package dnldd.backpack.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewHolder extends RecyclerView.ViewHolder {
    protected Context context;
    protected View view;

    public ViewHolder(View view) {
        super(view);
        this.context = view.getContext().getApplicationContext();
        this.view = view;
    }

    public View getView(){
        return view;
    }
}
