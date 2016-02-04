package com.immediasemi.blink.gcm;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;

public class BlinkGcmIdListenerService
  extends InstanceIDListenerService
{
  private static final String TAG = BlinkGcmIdListenerService.class.getSimpleName();
  
  public void onTokenRefresh()
  {
    startService(new Intent(this, BlinkGcmRegistrationIntentService.class));
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/gcm/BlinkGcmIdListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */