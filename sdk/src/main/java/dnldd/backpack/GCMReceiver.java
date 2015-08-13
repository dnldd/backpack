package dnldd.backpack;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.gcm.GcmReceiver;

import dnldd.backpack.utils.LogUtils;


public class GCMReceiver extends GcmReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.log(LogUtils.INFO_LOG_TYPE, "Received GCM message.");
        ComponentName comp = new ComponentName(context.getPackageName(), GCMService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
