package dnldd.backpack.utils;

import android.content.Context;

import dnldd.backpack.core.AppData;
import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.core.ServiceCalls;

public class ContextUtils {
    public static final BaseApplication getApp(Context context){
        return (BaseApplication) context.getApplicationContext();
    }

    public static final AppData getAppData(Context context){
        return getApp(context).getAppData();
    }

    public static final ServiceCalls getServiceCalls(Context context){
        return getApp(context).getServiceCalls();
    }
}
