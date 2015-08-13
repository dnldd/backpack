package dnldd.backpack.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtils {
    public static void persistData(Context context, String preference, String key, String data){
        SharedPreferences prefs = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, data);
        editor.apply();
    }

    public static void persistNumericData(Context context, String preference, String key, int data){
        SharedPreferences prefs = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, data);
        editor.apply();
    }

    public static String loadData(Context context, String preference, String key){
        SharedPreferences prefs = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }

    public static int loadNumericData(Context context, String preference, String key){
        SharedPreferences prefs = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return prefs.getInt(key, Integer.MIN_VALUE);
    }

    public static void removeData(Context context, String preference, String key){
        SharedPreferences prefs = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }
}
