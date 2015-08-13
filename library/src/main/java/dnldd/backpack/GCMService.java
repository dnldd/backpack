package dnldd.backpack;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.JsonParser;

import dnldd.backpack.core.BaseApplication;

public class GCMService extends GcmListenerService {
    JsonParser parser = new JsonParser();

    public BaseApplication getAppObject(){
        return (BaseApplication) getBaseContext().getApplicationContext();
    }

    public GCMService() {}

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
    }


}