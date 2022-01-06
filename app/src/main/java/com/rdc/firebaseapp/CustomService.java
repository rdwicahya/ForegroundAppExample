package com.rdc.firebaseapp;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.annotations.Nullable;

public class CustomService extends Service {
    static private String TAG = CustomService.class.getSimpleName();
    static private CustomService instance;
    static public CustomService getInstance(){
        if (instance == null){
            instance = new CustomService();
        }
        return instance;
    }
    static public Intent newIntent(Context context){
        return new Intent(context, CustomService.class);
    }

    private NotificationManagerCompat notificationManagerCompat;

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.e(TAG, "onCreate()");
        notificationManagerCompat = NotificationManagerCompat.from(this);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent, flags, startId);
        Log.e(TAG, "onStartCommand()");
        Notification notification = createNotification(this);

        startForeground(App.CHANNEL_ID1, notification);

        return START_STICKY;
    }
    public Notification createNotification(Context context){
        return new NotificationCompat.Builder(context, App.CHANNEL_ID_1)
                .setOngoing(true)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(NotificationCompat.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .build();
    }
    public void createNotification2(Context context, String title, String message) {
        Log.e(TAG, "createNotification()");
        if (notificationManagerCompat == null)
            notificationManagerCompat = NotificationManagerCompat.from(context);

        Notification notification = new NotificationCompat.Builder(context, App.CHANNEL_ID_2)
                .setOngoing(false)
                .setAutoCancel(false)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(message)
                .setSubText(message)
                .setOnlyAlertOnce(false)
                .build();
        notificationManagerCompat.notify(App.CHANNEL_ID2, notification);
    }

    public void createNotification3(Context context, String title, String message) {
        Log.e(TAG, "createNotification()");
        if (notificationManagerCompat == null)
            notificationManagerCompat = NotificationManagerCompat.from(context);

        Notification notification = new NotificationCompat.Builder(context, App.CHANNEL_ID_3)
                .setOngoing(false)
                .setAutoCancel(false)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(message)
                .setSubText(message)
                .build();
        notificationManagerCompat.notify(App.CHANNEL_ID3, notification);
    }
}
