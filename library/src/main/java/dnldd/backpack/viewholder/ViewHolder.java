package dnldd.backpack.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewHolder extends RecyclerView.ViewHolder {
    protected Context context;
    protected View view;
    protected int option;

    public ViewHolder(View view, int option) {
        super(view);
        this.context = view.getContext().getApplicationContext();
        this.view = view;
        this.option = option;
    }

    public View getView(){
        return view;
    }
    public int getOption(){ return option; }
}
