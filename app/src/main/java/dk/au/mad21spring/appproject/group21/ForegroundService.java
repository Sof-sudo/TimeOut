package dk.au.mad21spring.appproject.group21;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwner;
//import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dk.au.mad21spring.appproject.group21.Database.Team;

public class ForegroundService extends Service {//Service LifecycleService


    private static final String TAG = "ForegroundService";  // Tag til af log
    public static final String SERVICE_CHANNEL = "serviceChannel";  //Notifikations kanal navn
    public static final int NOTIFICATION_id = 47; // Notifikations id

    private Repository repository;

    ExecutorService execService;
    boolean started = false;
    int count = 0;

    public ForegroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        repository = Repository.getInstance(getApplication());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "ForegroundService started");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(SERVICE_CHANNEL, "Foreground Service", NotificationManager.IMPORTANCE_LOW);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        doBackgroundStuff();
        return START_STICKY;
    }

    private void doBackgroundStuff() {
        Log.d(TAG, "doBackgroundStuff started");
        if (!started) { // s??rger for at den kun starter, hvis den ikke allerede er startet
            started = true;
            doNotification();
        }

    }

    private void doNotification() {
        if (execService == null) { // denne g??r at det er muligt at k??re p?? en anden tr??d end main thread.
            execService = Executors.newSingleThreadExecutor();
        }

        execService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000); //10min
                } catch (InterruptedException e) {
                    Log.e(TAG, "Run Error", e);
                }
                if (started) {
                    NBANotification(repository.getRandomTeam());//RandomTeam()
                    doNotification(); // start again
                }
            }
        });
    }

    private void NBANotification(Team team) {
        Log.i(TAG, "Notification NBA");
               Notification notification = new NotificationCompat.Builder(this, SERVICE_CHANNEL)
                .setContentTitle(getResources().getString(R.string.Toast_Notification_Title))
                .setContentText(getResources().getString(R.string.Toast_Notification_ContextText) +" "+ team.getFullname())//+ team.getName()
                .setSmallIcon(R.drawable.ic_basketball_24)
                .build();
        startForeground(NOTIFICATION_id, notification);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}