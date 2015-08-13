package dnldd.backpack.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class TypefaceUtils {
    public static String LIGHT = "Roboto-Light.ttf";
    public static String REGULAR ="Roboto-Regular.ttf";
    public static String THE_SANS_LIGHT_PLAIN = "TheSansLightPlain.ttf";
    public static String THE_SANS_PLAIN = "TheSansPlain.ttf";
    public static String THE_SANS_SEMI_LIGHT_PLAIN = "TheSansSemiLightPlain.ttf";
    public static String THE_SANS_SEMI_BOLD_PLAIN = "TheSansSemiBoldPlain.ttf";
    public static String THE_SANS_B2_EXTRA_LIGHT = "TheSansB2ExtraLight.otf";
    public static String THE_SANS_B6_SEMI_BOLD = "TheSansB6SemiBold.otf";
    public static String THE_SERIF_BLACK_PLAIN = "TheSerifBlackPlain.ttf";
    public static String THE_SERIF_EXTRA_BOLD_PLAIN = "TheSerifExtraBoldPlain.ttf";
    public static String THE_SERIF_PLAIN = "TheSerifPlain.ttf";
    public static String THE_SERIF_SEMI_BOLD_PLAIN = "TheSerifSemiBoldPlain.ttf";


    protected static HashMap<String, Typeface> fontsCache;

    public static void create(List<String> assets,Context context) {
        if (fontsCache == null){
            fontsCache = new HashMap<String, Typeface>();
            createFontsCache(assets, context.getAssets());
        }
    }

    public static HashMap<String, Typeface> getFontsCache(){
        return fontsCache;
    }

    protected static void createFontsCache(List<String> assets, AssetManager manager){;
        loadFont(assets,manager, LIGHT);
        loadFont(assets,manager, REGULAR);
        loadFont(assets,manager, THE_SANS_LIGHT_PLAIN);
        loadFont(assets,manager, THE_SANS_PLAIN);
        loadFont(assets,manager, THE_SANS_SEMI_LIGHT_PLAIN);
        loadFont(assets,manager, THE_SANS_SEMI_BOLD_PLAIN);
        loadFont(assets,manager, THE_SANS_B2_EXTRA_LIGHT);
        loadFont(assets,manager, THE_SANS_B6_SEMI_BOLD);
        loadFont(assets,manager, THE_SERIF_BLACK_PLAIN);
        loadFont(assets,manager, THE_SERIF_EXTRA_BOLD_PLAIN);
        loadFont(assets,manager, THE_SERIF_PLAIN);
        loadFont(assets,manager, THE_SERIF_SEMI_BOLD_PLAIN);
    }

    public static void setWidgetTypeface(int fontStyle, TextView view) {
        switch (fontStyle){
            case 1: { view.setTypeface(fontsCache.get(LIGHT)); } break;
            case 2: { view.setTypeface(fontsCache.get(REGULAR)); } break;
            case 3: { view.setTypeface(fontsCache.get(THE_SANS_LIGHT_PLAIN)); } break;
            case 4: { view.setTypeface(fontsCache.get(THE_SANS_PLAIN)); } break;
            case 5: { view.setTypeface(fontsCache.get(THE_SANS_SEMI_LIGHT_PLAIN)); } break;
            case 6: { view.setTypeface(fontsCache.get(THE_SANS_SEMI_BOLD_PLAIN)); } break;
            case 7: { view.setTypeface(fontsCache.get(THE_SANS_B2_EXTRA_LIGHT)); } break;
            case 8: { view.setTypeface(fontsCache.get(THE_SANS_B6_SEMI_BOLD)); } break;
            case 9: { view.setTypeface(fontsCache.get(THE_SERIF_BLACK_PLAIN)); } break;
            case 10: { view.setTypeface(fontsCache.get(THE_SERIF_EXTRA_BOLD_PLAIN)); } break;
            case 11: { view.setTypeface(fontsCache.get(THE_SERIF_PLAIN)); } break;
            case 12: { view.setTypeface(fontsCache.get(THE_SERIF_SEMI_BOLD_PLAIN)); } break;

        }
    }

    protected static void loadFont(List<String> assets, AssetManager manager, String filename){
        if(assetAvailable(assets, filename)) { fontsCache.put(filename, Typeface.createFromAsset(manager, filename)); }
    }

    public static boolean assetAvailable(List<String> assets, String asset) {
        return assets.contains(asset);
    }

    public static Typeface getTypeface (String key) { return fontsCache.get(key); }

    public static void setFontSize(Context context, TextView view, int fontSize){
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
    }
}
