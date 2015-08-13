package dnldd.backpack.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.util.List;

import dnldd.backpack.core.BaseApplication;


public class NotificationUtils {
    public static void createNotification(BaseApplication app,Class intentClass, List<NotificationExtra> extras, int notificationID,
                                          int notificationIcon, String title, String contentText, String group, Uri notificationSound, boolean isAutoCancellable) {
        NotificationManager notificationManager = (NotificationManager) app.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(app.getApplicationContext(), intentClass);

        for (NotificationExtra extra : extras){ notificationIntent.putExtra(extra.getKey(), extra.getValue()); }
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(app.getApplicationContext(), notificationID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(app.getApplicationContext())
                .setSmallIcon(notificationIcon)
                .setContentTitle(title)
                .setContentText(contentText)
                //.setStyle(new NotificationCompat.InboxStyle())
                //Uri.parse("android.resource://" + context.getApplicationContext().getPackageName() + "/" + R.raw.notification)
                .setDefaults(NotificationCompat.DEFAULT_LIGHTS)
                .setContentIntent(pendingIntent)
                .setGroup(group);
        if (isAutoCancellable){ notifyBuilder.setAutoCancel(true); }
        if (notificationSound != null){ notifyBuilder.setSound(notificationSound); }
        notificationManager.notify(notificationID, notifyBuilder.build());
    }
}
