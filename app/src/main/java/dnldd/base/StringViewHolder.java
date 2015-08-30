package dnldd.base;

import android.view.View;

import dnldd.backpack.R;
import dnldd.backpack.view.TextView;
import dnldd.backpack.viewholder.ViewHolder;

public class StringViewHolder extends ViewHolder {
    protected TextView text;

    public StringViewHolder(View view) {
        super(view);
        text = (TextView) view.findViewById(R.id.text);
    }

    public TextView getTextView(){ return text; }
}
