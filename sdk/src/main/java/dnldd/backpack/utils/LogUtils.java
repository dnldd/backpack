package dnldd.backpack.utils;

import android.util.Log;

public class LogUtils {
    public static String INFO_LOG_TYPE = "INFO";
    public static String ASSERT_LOG_TYPE = "ASSERT";

    public static void log(String type, String entry){
        Log.d(type, "-- " + entry + " --");
    }
}
