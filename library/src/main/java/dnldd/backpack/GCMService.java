package dnldd.backpack;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.JsonParser;

public class GCMService extends GcmListenerService {
    protected JsonParser parser;

    public GCMService() {}

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
    }


}