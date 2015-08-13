package dnldd.backpack.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dnldd.backpack.activity.BaseActivity;
import dnldd.backpack.core.BaseApplication;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.QueryMap;
import rx.Observable;
import rx.Subscriber;

public class GCMServiceUtils {
    protected static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final  String GCM_REGISTRATION_ID = "gcm_reg_id";
    public static final String REGISTERED = "registered";
    public static final String APP_VERSION = "version";
    public static final String TAG = "GCMServiceUtils";

    protected static final String SENDER_ID = "143153256358";
    protected static final String GCM_URL = "http://52.6.5.127:8080";
    protected static RestAdapter.Builder builder;
    protected static RestAdapter adapter;
    protected static GoogleCloudMessaging client;
    protected static Context appContext;
    protected static GCMServiceInterface service;
    protected static Activity currentActivity;
    public static Callback<JsonObject> registerClientCallback;

    public interface  GCMServiceInterface {
        @POST("/register")
        void registerClient(@Body String body, @QueryMap Map<String, String> params, Callback<JsonObject> callback); //adding body to get around a retrofit bug
    }

    public static void initialize(Context context, Gson gson){
        client = GoogleCloudMessaging.getInstance(context);
        appContext = context;
        builder = new RestAdapter.Builder().setEndpoint(GCM_URL).setConverter(new GsonConverter(gson));
        adapter = builder.build();
        service = adapter.create(GCMServiceInterface.class);


        registerClientCallback = new Callback<JsonObject>() {
            @Override
            public void success(JsonObject obj, Response response) {
                log(TAG, obj.toString());
                persistData(appContext, BaseApplication.PREF, GCMServiceUtils.REGISTERED, "true");
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                log(TAG, error.toString());

                if (currentActivity != null){
                    currentActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(appContext, "Could not register the device.", Toast.LENGTH_SHORT).show();
                            Handler handler = new Handler();
                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(appContext, "Retrying device registration.", Toast.LENGTH_SHORT).show();
                                    GCMServiceUtils.registerGCMService(currentActivity, BaseApplication.PREF);
                                }
                            };

                            handler.postDelayed(runnable, 8000);
                        }
                    });
                }
            }
        };
    }


    public static void log(String type, String entry){
        Log.d(type, "-- " + entry + " --");
    }

    public static void activate(BaseActivity activity){
        if (GCMServiceUtils.checkPlayServices(activity)) {
            currentActivity = activity;
            GCMServiceUtils.registerGCMService(activity, BaseApplication.PREF);
        }
    }

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

    public static boolean checkPlayServices(Activity activity) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            else {
                log(TAG, "This device is not supported.");
                activity.finish();
            }
            return false;
        }
        return true;
    }

    public static void registerGCMService(Activity activity, String preference) {
        if (checkPlayServices(activity)) {
            client = GoogleCloudMessaging.getInstance(activity);
            String regID = getRegistrationID(appContext, preference);
            String registered =loadData(appContext, preference, REGISTERED);
            log(TAG, "GCM registration ID: " + regID);

            if ((registered == null) || (regID.equals(""))){ registerInBackground(appContext); }
        }
        else { log(TAG, "No valid Google Play Services APK found."); }
    }

    private static String getRegistrationID(Context context, String preference) {
        String registrationID = loadData(context, preference, GCM_REGISTRATION_ID);
        if (registrationID == null) {
            log(TAG, "Registration ID not found.");
            return "";
        }

        int registeredVersion = loadNumericData(context, preference, APP_VERSION);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            log(TAG, "App version changed.");
            return "";
        }
        return registrationID;
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        }
        catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    private static void registerInBackground(final Context context) {
        final BaseApplication application  = (BaseApplication) context;
        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                String preference = (String) params[0];
                String message;
                try {
                    InstanceID instanceID = InstanceID.getInstance(context);
                    String regID = instanceID.getToken(SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                    message = "Device registered, registration ID=" + regID;

                    HashMap<String, String> data = new HashMap<String, String>();
                    data.put("app_id", application.getPackageName());
                    data.put("reg_id", regID);
                    data.put("device_id", DeviceIDUtils.getDeviceID(application));
                    service.registerClient("", data, registerClientCallback);

                    persistData(context, preference, GCM_REGISTRATION_ID, regID);
                    persistNumericData(context, preference, APP_VERSION, getAppVersion(context));
                }
                catch (IOException ex) { message = "Error : " + ex.getMessage(); }

                return message;
            }

            @Override
            protected void onPostExecute(String msg) {
                log(TAG, msg);
                if (msg.equals("Error : SERVICE_NOT_AVAILABLE")){ registerInBackground(context); }
            }
        }.execute(BaseApplication.PREF);
    }


    public static void subscribeToTopic(Context context, String registrationToken, String topic){
        String full_topic = "/topics/" + context.getPackageName() + "." + topic;
        try {
            GcmPubSub.getInstance(context).subscribe(registrationToken, full_topic, null);
            LogUtils.log(LogUtils.INFO_LOG_TYPE, "--- registered to topic. ---");
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static Observable<Object> subscribeToTopicObservable(final Context context, final String registeredToken, final String topic){
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscribeToTopic(context, registeredToken, topic);
            }
        });
    }

    public static void unsubscribeFromTopic(Context context, String registrationToken, String topic){
        String full_topic = "/topics/" + context.getPackageName() + "." + topic;
        try {
            GcmPubSub.getInstance(context).unsubscribe(registrationToken, full_topic);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static Observable<Object> unsubscribeFromTopicObservable(final Context context, final String registeredToken, final String topic){
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                unsubscribeFromTopic(context, registeredToken, topic);
            }
        });
    }
}
