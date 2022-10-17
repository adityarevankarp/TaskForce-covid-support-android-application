package com.revankars.taskforce;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        getFirebaseMessage(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public void getFirebaseMessage(String title, String msg)

    {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"myFirebasechannel")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setAutoCancel(true)
                .setContentText(msg);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(101,builder.build());

    }

}
