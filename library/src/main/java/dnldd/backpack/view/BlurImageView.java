package dnldd.backpack.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import dnldd.backpack.utils.BlurUtils;

public class BlurImageView  extends ImageView {
    public BlurImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Bitmap bitmap = BlurUtils.blurBitmap(getContext(), ((BitmapDrawable) getDrawable()).getBitmap());
        setImageBitmap(bitmap);
    }
}
