package dnldd.backpack.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.gson.JsonObject;

import java.io.IOException;

import dnldd.backpack.R;
import dnldd.backpack.activity.BaseActivity;
import dnldd.backpack.contract.LifecycleInterface;
import dnldd.backpack.gcm.GCMServiceBuilder;
import dnldd.backpack.utils.DeviceIDUtils;
import dnldd.backpack.utils.LogUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class ServiceCalls {
    protected Context context;

    public ServiceCalls(Context context) {
        this.context = context;
    }

    public void giveFeedback(LifecycleInterface ui, final String message){
        if (ui instanceof BaseActivity){
            final BaseActivity activity =  ((BaseActivity) ui);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(ui instanceof Fragment){
            final Fragment fragment = ((Fragment) ui);
            if (fragment.getActivity() != null) { //paranoia
                fragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(fragment.getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else { LogUtils.log(LogUtils.INFO_LOG_TYPE, "Parent activity is null."); }
        }
    }

    public void registerForGCM(LifecycleInterface ui, GCMServiceBuilder serviceClient){
        SharedPreferences prefs = context.getSharedPreferences(BaseApplication.PREF, Context.MODE_PRIVATE);

        try {
            if(!prefs.getBoolean(context.getResources().getString(R.string.gcm_registered), false)) {
                InstanceID instanceID = InstanceID.getInstance(context);
                String token = instanceID.getToken(context.getResources().getString(R.string.gcm_sender_id), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                String id = DeviceIDUtils.getDeviceID(context);
                /* sends token to the third party server */
                serviceClient.getGCMService().register("", token, id).observeOn(AndroidSchedulers.mainThread())
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

                /* persists in the registration in shared prefs */
                prefs.edit().putBoolean(context.getResources().getString(R.string.gcm_registered), true);
            }
        }
        catch (IOException e){
            e.printStackTrace();
            prefs.edit().putBoolean(context.getResources().getString(R.string.gcm_registered), false);
        }
    }

}
