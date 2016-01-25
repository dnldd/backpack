package dnldd.backpack.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public final class SquaredImageView extends ImageView {
    public SquaredImageView(Context context) {
		super(context);
	}

    public SquaredImageView(Context context, AttributeSet attrs) { 
    	super(context, attrs);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //noinspection SuspiciousNameCombination
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
       setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
    }
}