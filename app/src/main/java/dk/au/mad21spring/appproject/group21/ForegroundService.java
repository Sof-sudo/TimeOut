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
        if (!started) { // sørger for at den kun starter, hvis den ikke allerede er startet
            started = true;
            doNotification();
        }

    }

    private void doNotification() {
        if (execService == null) { // denne gør at det er muligt at kører på en anden tråd end main thread.
            execService = Executors.newSingleThreadExecutor();
        }

        execService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    Log.e(TAG, "Run Eroor", e);
                }
                if (started) {
                    NBANotification(repository.getRandomTeam());//RandomTeam()
                    doNotification(); // starter igen
                }
            }
        });
    }

    private void NBANotification(Team team) {
        Log.i(TAG, "Notification NBA");
               Notification notification = new NotificationCompat.Builder(this, SERVICE_CHANNEL)
                .setContentTitle("Team data available")
                .setContentText("Check out data from " + team.getFullname())//+ team.getName()
                .setSmallIcon(R.drawable.ic_basketball_24)
                .build();
        startForeground(NOTIFICATION_id, notification);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}