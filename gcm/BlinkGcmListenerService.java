package com.immediasemi.blink.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.gcm.GcmListenerService;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.MainActivity;
import com.immediasemi.blink.activities.VideoPlayerActivity;

public class BlinkGcmListenerService
  extends GcmListenerService
{
  public static final String NEW_VIDEO_NOTIFICATION = "new_video_notification";
  private static final String TAG = BlinkGcmListenerService.class.getSimpleName();
  private static final int notifyID = 1;
  
  private void processPayload(Bundle paramBundle)
  {
    if (!BlinkApp.getApp().getLoggedIn()) {}
    Bundle localBundle;
    do
    {
      return;
      localBundle = new Bundle();
    } while (TextUtils.isEmpty(paramBundle.getString("thumbnail")));
    localBundle.putString("network", paramBundle.getString("network"));
    localBundle.putString("camera", paramBundle.getString("camera"));
    localBundle.putString("video", paramBundle.getString("video"));
    localBundle.putString("thumbnail", paramBundle.getString("thumbnail"));
    if (paramBundle.containsKey("created_at")) {
      localBundle.putString("created_at", paramBundle.getString("created_at"));
    }
    if (paramBundle.containsKey("camera_name")) {
      localBundle.putString("camera_name", paramBundle.getString("camera_name"));
    }
    Object localObject = new Intent(this, VideoPlayerActivity.class);
    ((Intent)localObject).putExtra("video_dictionary", localBundle);
    ((Intent)localObject).putExtra("start_alert_view", true);
    localObject = TaskStackBuilder.create(this).addNextIntent((Intent)localObject).getPendingIntent(0, 134217728);
    Uri localUri = RingtoneManager.getDefaultUri(2);
    paramBundle = new NotificationCompat.Builder(this).setSmallIcon(2130837683).setContentTitle(paramBundle.getString("title")).setContentText(paramBundle.getString("message")).setAutoCancel(true).setSound(localUri).setContentIntent((PendingIntent)localObject);
    ((NotificationManager)getSystemService("notification")).notify(1, paramBundle.build());
    paramBundle = LocalBroadcastManager.getInstance(BlinkApp.getApp().getApplicationContext());
    localObject = new Intent();
    ((Intent)localObject).setAction("new_video_notification");
    ((Intent)localObject).putExtras(localBundle);
    paramBundle.sendBroadcast((Intent)localObject);
  }
  
  private void sendNotification(String paramString)
  {
    Object localObject = new Intent(this, MainActivity.class);
    ((Intent)localObject).addFlags(67108864);
    localObject = PendingIntent.getActivity(this, 0, (Intent)localObject, 1073741824);
    Uri localUri = RingtoneManager.getDefaultUri(2);
    paramString = new NotificationCompat.Builder(this).setSmallIcon(2130837714).setContentTitle("GCM Message").setContentText(paramString).setAutoCancel(true).setSound(localUri).setContentIntent((PendingIntent)localObject);
    ((NotificationManager)getSystemService("notification")).notify(0, paramString.build());
  }
  
  public void onMessageReceived(String paramString, Bundle paramBundle)
  {
    String str = paramBundle.getString("message");
    Log.d(TAG, "From: " + paramString);
    Log.d(TAG, "Message: " + str);
    processPayload(paramBundle);
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/gcm/BlinkGcmListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */