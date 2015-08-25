package dnldd.backpack.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import dnldd.backpack.R;
import dnldd.backpack.utils.TypefaceUtils;

public class RadioButton extends AppCompatRadioButton {
    public RadioButton(Context context) { super(context); }

    public RadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    public RadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.RadioButton);
        int fontStyle = values.getInt(R.styleable.RadioButton_fontStyle, 0);
        TypefaceUtils.setWidgetTypeface(fontStyle, this);
        values.recycle();
    }
}
