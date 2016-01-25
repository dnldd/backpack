package dnldd.backpack.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.text.SpannableString;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/* this structure to load more custom fonts if needed and load in BaseApplication */
public class TypefaceUtils {
    public static String BLACK = "Roboto-Black.ttf";
    public static String BLACK_ITALIC ="Roboto-BlackItalic.ttf";
    public static String BOLD ="Roboto-Bold.ttf";
    public static String BOLD_ITALIC ="Roboto-BoldItalic.ttf";
    public static String ITALIC ="Roboto-Italic.ttf";
    public static String LIGHT ="Roboto-Light.ttf";
    public static String LIGHT_ITALIC ="Roboto-LightItalic.ttf";
    public static String MEDIUM ="Roboto-Medium.ttf";
    public static String MEDIUM_ITALIC ="Roboto-MediumItalic.ttf";
    public static String REGULAR ="Roboto-Regular.ttf";
    public static String THIN ="Roboto-Thin.ttf";
    public static String THIN_ITALIC ="Roboto-ThinItalic.ttf";
    public static String CONDENSED_BOLD ="RobotoCondensed-Bold.ttf";
    public static String CONDENSED_BOLD_ITALIC ="RobotoCondensed-BoldItalic.ttf";
    public static String CONDENSED_ITALIC ="RobotoCondensed-Italic.ttf";
    public static String CONDENSED_LIGHT ="RobotoCondensed-Light.ttf";
    public static String CONDENSED_LIGHT_ITALIC ="RobotoCondensed-LightItalic.ttf";
    public static String CONDENSED_REGULAR ="RobotoCondensed-Regular.ttf";



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
        loadFont(assets,manager, BLACK);
        loadFont(assets,manager, BLACK_ITALIC);
        loadFont(assets,manager, BOLD);
        loadFont(assets,manager, BOLD_ITALIC);
        loadFont(assets,manager, ITALIC);
        loadFont(assets,manager, LIGHT);
        loadFont(assets,manager, LIGHT_ITALIC);
        loadFont(assets,manager, MEDIUM);
        loadFont(assets,manager, MEDIUM_ITALIC);
        loadFont(assets,manager, REGULAR);
        loadFont(assets,manager, THIN);
        loadFont(assets,manager, THIN_ITALIC);
        loadFont(assets,manager, CONDENSED_BOLD);
        loadFont(assets,manager, CONDENSED_BOLD_ITALIC);
        loadFont(assets,manager, CONDENSED_ITALIC);
        loadFont(assets,manager, CONDENSED_LIGHT);
        loadFont(assets,manager, CONDENSED_LIGHT_ITALIC);
        loadFont(assets,manager, CONDENSED_REGULAR);
    }

    public static void setWidgetTypeface(int fontStyle, TextView view) {
        switch (fontStyle){
            case 1: { view.setTypeface(fontsCache.get(BLACK)); } break;
            case 2: { view.setTypeface(fontsCache.get(BLACK_ITALIC)); } break;
            case 3: { view.setTypeface(fontsCache.get(BOLD)); } break;
            case 4: { view.setTypeface(fontsCache.get(BOLD_ITALIC)); } break;
            case 5: { view.setTypeface(fontsCache.get(ITALIC)); } break;
            case 6: { view.setTypeface(fontsCache.get(LIGHT)); } break;
            case 7: { view.setTypeface(fontsCache.get(LIGHT_ITALIC)); } break;
            case 8: { view.setTypeface(fontsCache.get(MEDIUM)); } break;
            case 9: { view.setTypeface(fontsCache.get(MEDIUM_ITALIC)); } break;
            case 10: { view.setTypeface(fontsCache.get(REGULAR)); } break;
            case 11: { view.setTypeface(fontsCache.get(THIN)); } break;
            case 12: { view.setTypeface(fontsCache.get(THIN_ITALIC)); } break;
            case 13: { view.setTypeface(fontsCache.get(CONDENSED_BOLD)); } break;
            case 14: { view.setTypeface(fontsCache.get(CONDENSED_BOLD_ITALIC)); } break;
            case 15: { view.setTypeface(fontsCache.get(CONDENSED_ITALIC)); } break;
            case 16: { view.setTypeface(fontsCache.get(CONDENSED_LIGHT)); } break;
            case 17: { view.setTypeface(fontsCache.get(CONDENSED_LIGHT_ITALIC)); } break;
            case 18: { view.setTypeface(fontsCache.get(CONDENSED_REGULAR)); } break;
        }
    }

    protected static void loadFont(List<String> assets, AssetManager manager, String filename){
        if(assetAvailable(assets, filename)) { fontsCache.put(filename, Typeface.createFromAsset(manager, filename)); }
    }

    public static boolean assetAvailable(List<String> assets, String asset) {
        return assets.contains(asset);
    }

    public static Typeface getTypeface (String key) { return fontsCache.get(key); }

    public static void setFontSize(TextView view, int fontSize){
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
    }

    public static void modifyTabs(TabLayout tabLayout){
        Typeface typeface = TypefaceUtils.getTypeface(TypefaceUtils.CONDENSED_LIGHT);

        for(int index = 0; index < tabLayout.getTabCount(); ++index) {
            TabLayout.Tab tab  = tabLayout.getTabAt(index);
            if(tab != null) {
                CharSequence tabText = tab.getText();

                if(tabText != null && tabText.length() > 0) {
                    SpannableString spannableString = new SpannableString(tabText);
                    spannableString.setSpan(typeface, 0, tabText.length(), 0);
                    tab.setText(spannableString);
                }
            }
        }
    }
}
