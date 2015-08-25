package dnldd.backpack.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import dnldd.backpack.R;
import dnldd.backpack.utils.TypefaceUtils;

public class Switch extends SwitchCompat {
    public Switch(Context context) { super(context); }

    public Switch(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    public Switch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }
    
    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.Switch);
        int fontStyle = values.getInt(dnldd.backpack.R.styleable.Switch_fontStyle, 0);
        TypefaceUtils.setWidgetTypeface(fontStyle, this);
        values.recycle();
    }
}
