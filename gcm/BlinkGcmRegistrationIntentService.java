package com.immediasemi.blink.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.iid.InstanceID;
import com.immediasemi.blink.BlinkApp;
import java.io.IOException;

public class BlinkGcmRegistrationIntentService
  extends IntentService
{
  public static final String REGISTRATION_COMPLETE = "registrationComplete";
  public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
  private static final String TAG = BlinkGcmRegistrationIntentService.class.getSimpleName();
  private static final String[] TOPICS = { "global" };
  
  public BlinkGcmRegistrationIntentService()
  {
    super(TAG);
  }
  
  private void sendRegistrationToServer(String paramString) {}
  
  private void subscribeTopics(String paramString)
    throws IOException
  {
    String[] arrayOfString = TOPICS;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      GcmPubSub.getInstance(this).subscribe(paramString, "/topics/" + str, null);
      i += 1;
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    paramIntent = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext());
    try
    {
      synchronized (TAG)
      {
        String str2 = InstanceID.getInstance(this).getToken(getString(2131099750), "GCM", null);
        Log.i(TAG, "GCM Registration Token: " + str2);
        BlinkApp.getApp().setDeviceToken(str2);
        subscribeTopics(str2);
        paramIntent.edit().putBoolean("sentTokenToServer", true).commit();
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      Log.d(TAG, "Failed to complete token refresh", localException);
      paramIntent.edit().putBoolean("sentTokenToServer", false).commit();
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/gcm/BlinkGcmRegistrationIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */