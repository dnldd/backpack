package dnldd.backpack.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class DrawableUtils {
    public static void colorDrawable(Context context, Drawable image, int colorResId){
        image.setColorFilter(ContextCompat.getColor(context, colorResId), PorterDuff.Mode.SRC_ATOP);
    }
}
