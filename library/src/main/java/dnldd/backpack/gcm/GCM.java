package dnldd.backpack.gcm;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.gson.JsonObject;

import java.io.IOException;

import dnldd.backpack.R;
import dnldd.backpack.activity.BaseActivity;
import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.utils.ContextUtils;
import dnldd.backpack.utils.DeviceIDUtils;
import dnldd.backpack.utils.LogUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class GCM {
    public static String TOPICS = "/topics/";
    public static int PLAY_SERVICES_REQUEST_TIME = 9000;

    protected Context context;

    public GCM(Context context){
        this.context = context;
    }

    public void register(Context context){
        SharedPreferences prefs = context.getSharedPreferences(BaseApplication.PREF, Context.MODE_PRIVATE);
        if(!prefs.getBoolean(context.getResources().getString(R.string.gcm_registered), false)) {
            try {
                synchronized (RegistrationIntentService.class.getSimpleName()) {
                    InstanceID instanceID = InstanceID.getInstance(context);
                    String token = instanceID.getToken(context.getResources().getString(R.string.gcm_sender_id), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                    String id = DeviceIDUtils.getDeviceID(context);
                    LogUtils.log(RegistrationIntentService.class.getSimpleName(), "GCM Registration Token: " + token);

                    /* sends registration to third party server */
                    sendRegistrationToServer(token, id);
                    prefs.edit().putBoolean(context.getResources().getString(R.string.gcm_registered), true);
                }
            } catch (Exception e) {
                LogUtils.log(RegistrationIntentService.class.getSimpleName(), "Failed to complete token refresh");
                prefs.edit().putBoolean(context.getResources().getString(R.string.gcm_registered), true);
            }
        }
        else {
            LogUtils.log(RegistrationIntentService.class.getSimpleName(), "Registered for GCM");
        }
    }

    public  boolean hasPlayServices(Context context) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                BaseActivity activity = ContextUtils.getApp(context).getCurrentActivity();
                if(activity != null) {
                    GooglePlayServicesUtil.getErrorDialog(resultCode, activity, PLAY_SERVICES_REQUEST_TIME).show();
                }
            }
            else {
                LogUtils.log(GCM.class.getSimpleName(), "--- this device is not supported. ---");
            }
            return false;
        }
        return true;
    }

    protected void sendRegistrationToServer(final String token, final String id) {
        ContextUtils.getApp(context).getGcmService().register("", token, id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonObject>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                /* check on the error response  */
            }

            @Override
            public void onNext(JsonObject jsonObject) {
                /* check on the response here  */
            }
        });
    }
    private void subscribeToTopic(Context context, String registrationToken, String topic){
        String full_topic = TOPICS + context.getPackageName() + "." + topic;
        try {
            GcmPubSub.getInstance(context).subscribe(registrationToken, full_topic, null);
            LogUtils.log(LogUtils.INFO_LOG_TYPE, "--- registered to topic. ---");
        }
        catch (IOException e) {
            e.printStackTrace();
            /* give UI feedback */
        }
    }

    private void unsubscribeFromTopic(Context context, String registrationToken, String topic){
        String full_topic = TOPICS + context.getPackageName() + "." + topic;
        try {
            GcmPubSub.getInstance(context).unsubscribe(registrationToken, full_topic);
        }
        catch (IOException e) {
            e.printStackTrace();
            /* give UI feedback */
        }
    }

    public  Observable<Object> callUnsubscribeFromTopic(final Context context, final String registeredToken, final String topic){
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                unsubscribeFromTopic(context, registeredToken, topic);
            }
        });
    }

    public Observable<Object> callSubscribeToTopic(final Context context, final String registeredToken, final String topic){
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscribeToTopic(context, registeredToken, topic);
            }
        });
    }
}
