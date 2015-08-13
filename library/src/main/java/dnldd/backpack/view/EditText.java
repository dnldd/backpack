package dnldd.backpack.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.utils.TypefaceUtils;

public class EditText extends AppCompatEditText {
    public EditText(Context context) { super(context); }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    public EditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }
    
    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, dnldd.backpack.R.styleable.EditText);
        int fontStyle = values.getInt(dnldd.backpack.R.styleable.EditText_fontStyle, 0);
        BaseApplication app = (BaseApplication) context.getApplicationContext();
        TypefaceUtils.setWidgetTypeface(fontStyle, this);
        values.recycle();
    }
}
