package dnldd.backpack;

    import android.content.Context;
    import android.graphics.Paint;
    import android.graphics.Typeface;
    import android.support.v4.util.LruCache;
    import android.text.TextPaint;
    import android.text.style.MetricAffectingSpan;

public class TypefaceSpan extends MetricAffectingSpan {
    private static LruCache<String, Typeface> typefaceCache = new LruCache<String, Typeface>(6);
    private Typeface typeface;

    public TypefaceSpan(Context context, String typefaceName, String typefaceFormat) {
        typeface = typefaceCache.get(typefaceName);

        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(), String.format("%s.%s", typefaceName, typefaceFormat));
            typefaceCache.put(typefaceName, typeface);
        }
    }

    @Override
    public void updateMeasureState(TextPaint p) {
        p.setTypeface(typeface);
        // Note: This flag is required for proper typeface rendering
        p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setTypeface(typeface);
        // Note: This flag is required for proper typeface rendering
        tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
}
