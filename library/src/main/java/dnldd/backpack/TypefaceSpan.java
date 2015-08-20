package dnldd.backpack;

    import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

import dnldd.backpack.utils.TypefaceUtils;

public class TypefaceSpan extends MetricAffectingSpan {
    private Typeface mTypeface;

    public TypefaceSpan(String typefaceName){
        mTypeface = TypefaceUtils.getTypeface(typefaceName);
    }

    public TypefaceSpan(Typeface typeface){
        mTypeface = typeface;
    }

    @Override
    public void updateMeasureState(TextPaint p) {
        p.setTypeface(mTypeface);
        // Note: This flag is required for proper typeface rendering
        p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setTypeface(mTypeface);
        // Note: This flag is required for proper typeface rendering
        tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
}
