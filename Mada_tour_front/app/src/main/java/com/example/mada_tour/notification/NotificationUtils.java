package com.example.mada_tour.notification;

import android.content.Context;
import android.app.NotificationManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.mada_tour.R;

public class NotificationUtils {
    public static void showNotification(Context context) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(context.getString(R.string.wlcm_title))
                        .setContentText("Akory e")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NotificationConstants.NOTIFICATION_ID, builder.build());
    }
    public void cancelNotification(Context mContext) {
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancel(NotificationConstants.NOTIFICATION_ID);
        }
    }
    //---
    public static void showSignUpNotification(Context context){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("Inscription")
                        .setContentText("Bienvenue dans la famille des grands voyageurs de la Grande Ile!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NotificationConstants.NOTIFICATION_ID, builder.build());
    }
}
