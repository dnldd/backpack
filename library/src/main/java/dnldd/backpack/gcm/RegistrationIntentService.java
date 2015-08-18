/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dnldd.backpack.gcm;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.gson.JsonObject;

import dnldd.backpack.core.BaseApplication;
import dnldd.backpack.utils.ContextUtils;
import dnldd.backpack.utils.DeviceIDUtils;
import dnldd.backpack.utils.LogUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class RegistrationIntentService extends IntentService {
    public RegistrationIntentService() {
        super(RegistrationIntentService.class.getSimpleName());
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences(BaseApplication.PREF, Context.MODE_PRIVATE);
        try {
            synchronized (RegistrationIntentService.class.getSimpleName()) {
                InstanceID instanceID = InstanceID.getInstance(this);
                String token = instanceID.getToken(ContextUtils.getApp(getApplicationContext()).getGcm().getSenderID(), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                String id = DeviceIDUtils.getDeviceID(getApplicationContext());
                LogUtils.log(RegistrationIntentService.class.getSimpleName(), "GCM Registration Token: " + token);

                /* sends registration to third party server */
                sendRegistrationToServer(token, id);
                prefs.edit().putString(GCM.GCM_TOKEN, token).apply();
                prefs.edit().putBoolean(GCM.IS_REGISTERED_KEY, true).apply();
            }
        } catch (Exception e) {
            Log.d(RegistrationIntentService.class.getSimpleName(), "--- Failed to complete token refresh ---", e);
            prefs.edit().putBoolean(GCM.IS_REGISTERED_KEY, true).apply();
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
    }

    /**
     * Persist registration to third-party servers.
     *
     * Modify this method to associate the user's GCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    protected void sendRegistrationToServer(final String token, final String id) {
        ContextUtils.getApp(getApplicationContext()).getGcmService()
                .register("", token, id)
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

    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */
    /*protected void subscribeTopics(String token) throws IOException {
        for (String topic : TOPICS) {
            GcmPubSub pubSub = GcmPubSub.getInstance(this);
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }*/
}