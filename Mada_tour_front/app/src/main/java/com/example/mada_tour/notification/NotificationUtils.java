package com.example.mada_tour.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.app.NotificationManager;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.mada_tour.MainActivity;
import com.example.mada_tour.R;

public class NotificationUtils {
    public static void showNotification(Context context) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)//"@mipmap/ic_launcher"
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
    /*public static void showSignUpNotification(Context context){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Inscription")
                        .setContentText("Bienvenue dans la famille des grands voyageurs de la Grande Ile!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NotificationConstants.NOTIFICATION_ID, builder.build());
    }*/
    public static void showSignUpNotification(Context context) {
        // Créer une intention pour ouvrir votre activité lorsque la notification est cliquée
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Texte avec sauts de ligne pour afficher sur plusieurs lignes
        String notificationText = "Bienvenue dans la famille des grands voyageurs  de la Grande Ile!";

        // Créer la notification avec le style Heads-Up
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Inscription")
                .setContentText(notificationText)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationText)) //  BigTextStyle pour gérer le texte long
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setFullScreenIntent(pendingIntent, true)
                .setAutoCancel(true);

        // Afficher la notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NotificationConstants.NOTIFICATION_ID, builder.build());

        Log.d("Notification", "Notification Signup debug !");
    }

}
