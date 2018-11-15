package com.viktor.listtest;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AboutInfo extends AppCompatActivity {

    Button downloadButton;
    DownloadManager downloadManager;
    final ArrayList<Long> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info);

        TextView textView = findViewById(R.id.linktoicons);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView1 = findViewById(R.id.PetCareRx);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView2 = findViewById(R.id.vetwest);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textView3 = findViewById(R.id.DogSlim);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());

        downloadButton = findViewById(R.id.downloadPDFbtn);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("http://dels.nas.edu/resources/static-assets/banr/miscellaneous/dog_nutrition_final_fix.pdf");
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);

                list.add(reference);

                Toast.makeText(getApplicationContext(), "Downloading", Toast.LENGTH_SHORT).show();
            }
        });
        registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            // get the refid from the download manager
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            // remove it from our list
            list.remove(referenceId);

            // if list is empty means all downloads completed
            if (list.isEmpty())
            {
                // show a notification
                String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
                Log.e("INSIDE", "" + referenceId);
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(AboutInfo.this, NOTIFICATION_CHANNEL_ID)
                                .setSmallIcon(R.mipmap.ic_launcher_round)
                                .setContentTitle("RawPaw")
                                .setContentText("Your download is complete");

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(455, mBuilder.build());
            }

        }
    };

    @Override
    public void onDestroy(){
        super.onDestroy();

        unregisterReceiver(onComplete);
    }
}
