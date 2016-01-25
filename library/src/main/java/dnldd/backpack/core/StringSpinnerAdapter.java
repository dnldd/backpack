package dnldd.backpack.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dnldd.backpack.R;
import dnldd.backpack.utils.TypefaceUtils;

public class StringSpinnerAdapter extends BaseAdapter {
    protected Context mContext;
    protected List<String> mItems;
    protected LayoutInflater inflater;

    public StringSpinnerAdapter(Context context, List<String> items){
        mContext = context;
        mItems = items;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }


    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = inflater.inflate(android.R.layout.simple_spinner_item, viewGroup, false);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        /* sets the text, text color, typeface and font size */
        textView.setText(mItems.get(position));
        //noinspection deprecation
        textView.setTextColor(mContext.getResources().getColor(R.color.black_alpha_87));
        textView.setTypeface(TypefaceUtils.getTypeface(TypefaceUtils.CONDENSED_LIGHT));
        TypefaceUtils.setFontSize(textView, 16);
        return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup viewGroup) {
        view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, viewGroup, false);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
         /* sets the text, text color, typeface and font size */
        textView.setText(mItems.get(position));
        //noinspection deprecation
        textView.setTextColor(mContext.getResources().getColor(R.color.black_alpha_87));
        textView.setTypeface(TypefaceUtils.getTypeface(TypefaceUtils.CONDENSED_LIGHT));
        TypefaceUtils.setFontSize(textView, 16);
        return view;
    }
}
