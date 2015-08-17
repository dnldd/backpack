package dnldd.backpack.gcm;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

public class ListenerService extends GcmListenerService {

    public ListenerService() {}

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        /* override this in an extended class to process the received message */
    }

    @Override
    public void onMessageSent(String msgId) {
        super.onMessageSent(msgId);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onSendError(String msgId, String error) {
        super.onSendError(msgId, error);
    }
}