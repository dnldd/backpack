package dnldd.backpack.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import dnldd.backpack.R;
import dnldd.backpack.utils.TypefaceUtils;

public class CheckBox extends AppCompatCheckBox {
    public CheckBox(Context context) { super(context); }

    public CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    public CheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.CheckBox);
        int fontStyle = values.getInt(R.styleable.CheckBox_fontStyle, 0);
        TypefaceUtils.setWidgetTypeface(fontStyle, this);
        values.recycle();
    }
}
