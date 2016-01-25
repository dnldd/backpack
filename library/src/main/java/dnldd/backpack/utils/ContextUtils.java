package dnldd.backpack.utils;

import android.content.Context;

import dnldd.backpack.contract.GCMServiceInterface;
import dnldd.backpack.core.AppData;
import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.core.ServiceCalls;

public class ContextUtils {
    public static BaseApplication getApp(Context context){
        return (BaseApplication) context.getApplicationContext();
    }

    public static AppData getAppData(Context context){
        return getApp(context).getAppData();
    }

    public static ServiceCalls getServiceCalls(Context context){
        return getApp(context).getServiceCalls();
    }

    public static GCMServiceInterface getGSMservice(Context context){
        return getApp(context).getGcmService();
    }
}
