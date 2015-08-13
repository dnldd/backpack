package dnldd.backpack.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.R;
import dnldd.backpack.utils.TypefaceUtils;

public class TextView extends AppCompatTextView {
    public TextView(Context context) {
        super(context);
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    public TextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }
    
    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.TextView);
       int fontStyle =  values.getInt(R.styleable.TextView_fontStyle, 0);
       BaseApplication app = (BaseApplication) context.getApplicationContext();
       TypefaceUtils.setWidgetTypeface(fontStyle, this);
       values.recycle();
    }
}
