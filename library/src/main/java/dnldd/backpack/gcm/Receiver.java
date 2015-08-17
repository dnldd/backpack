package dnldd.backpack.gcm;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.gcm.GcmReceiver;

import dnldd.backpack.utils.LogUtils;


public class Receiver extends GcmReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.log(this.getClass().getSimpleName(), "--- Message received. ---");
        ComponentName componentName = new ComponentName(context.getPackageName(), ListenerService.class.getName());
        startWakefulService(context, (intent.setComponent(componentName)));
        setResultCode(Activity.RESULT_OK);
    }
}
