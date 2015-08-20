package dnldd.backpack.core;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import dnldd.backpack.R;
import dnldd.backpack.utils.TypefaceUtils;

public class SpinnerAdapter extends ArrayAdapter<String> {
    protected Context context;

    public SpinnerAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.context = context;
    }

    public SpinnerAdapter(Context context, List<String> items){
        this(context, android.R.layout.simple_spinner_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        TextView view = (TextView) v;
        view.setTypeface(TypefaceUtils.getTypeface(TypefaceUtils.LIGHT));
        view.setTextColor(context.getResources().getColor(R.color.black_alpha_87));
        view.setPadding(0, 4, 0,8);
        view.setGravity(Gravity.LEFT);
        TypefaceUtils.setFontSize(view, 14);
        return view;
    }


    @Override
    public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
        View v = super.getDropDownView(position, convertView, parent);
        TextView view = (TextView) v;
        view.setTypeface(TypefaceUtils.getTypeface(TypefaceUtils.LIGHT));
        view.setTextColor(context.getResources().getColor(R.color.black_alpha_87));
        view.setPadding(8, 16, 8,16);
        view.setGravity(Gravity.CENTER);
        TypefaceUtils.setFontSize(view, 14);
        return view;
    }
}
