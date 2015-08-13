package dnldd.backpack.utils;


import android.content.Context;
import android.provider.Settings;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DeviceIDUtils {
    public static String getDeviceID(Context context){
        String deviceID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (deviceID == null || deviceID.equals("9774d56d682e549c") || deviceID.length() < 15 ) { /* please don't happen :S */
            /* if ANDROID_ID is null, or it's equals to the GalaxyTab generic ANDROID_ID or bad, generates a new one */
            final SecureRandom random = new SecureRandom();
            deviceID = new BigInteger(64, random).toString(16);
        }

        return deviceID;
    }
}
