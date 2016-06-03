package com.immediasemi.blink.gcm;

import android.app.IntentService;
import com.google.android.gms.gcm.GcmPubSub;
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
    for (String str : TOPICS) {
      GcmPubSub.getInstance(this).subscribe(paramString, "/topics/" + str, null);
    }
  }
  
  /* Error */
  protected void onHandleIntent(android.content.Intent paramIntent)
  {
    // Byte code:
    //   0: invokestatic 73	com/immediasemi/blink/BlinkApp:getApp	()Lcom/immediasemi/blink/BlinkApp;
    //   3: invokevirtual 77	com/immediasemi/blink/BlinkApp:getApplicationContext	()Landroid/content/Context;
    //   6: invokestatic 83	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   9: astore_1
    //   10: getstatic 24	com/immediasemi/blink/gcm/BlinkGcmRegistrationIntentService:TAG	Ljava/lang/String;
    //   13: astore_2
    //   14: aload_2
    //   15: monitorenter
    //   16: aload_0
    //   17: invokestatic 88	com/google/android/gms/iid/InstanceID:getInstance	(Landroid/content/Context;)Lcom/google/android/gms/iid/InstanceID;
    //   20: aload_0
    //   21: ldc 89
    //   23: invokevirtual 93	com/immediasemi/blink/gcm/BlinkGcmRegistrationIntentService:getString	(I)Ljava/lang/String;
    //   26: ldc 95
    //   28: aconst_null
    //   29: invokevirtual 99	com/google/android/gms/iid/InstanceID:getToken	(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Ljava/lang/String;
    //   32: astore_3
    //   33: invokestatic 73	com/immediasemi/blink/BlinkApp:getApp	()Lcom/immediasemi/blink/BlinkApp;
    //   36: aload_3
    //   37: invokevirtual 102	com/immediasemi/blink/BlinkApp:setDeviceToken	(Ljava/lang/String;)V
    //   40: aload_0
    //   41: aload_3
    //   42: invokespecial 104	com/immediasemi/blink/gcm/BlinkGcmRegistrationIntentService:subscribeTopics	(Ljava/lang/String;)V
    //   45: aload_1
    //   46: invokeinterface 110 1 0
    //   51: ldc 11
    //   53: iconst_1
    //   54: invokeinterface 116 3 0
    //   59: invokeinterface 120 1 0
    //   64: pop
    //   65: aload_2
    //   66: monitorexit
    //   67: return
    //   68: astore_3
    //   69: aload_2
    //   70: monitorexit
    //   71: aload_3
    //   72: athrow
    //   73: astore_2
    //   74: getstatic 24	com/immediasemi/blink/gcm/BlinkGcmRegistrationIntentService:TAG	Ljava/lang/String;
    //   77: ldc 122
    //   79: aload_2
    //   80: invokestatic 128	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   83: pop
    //   84: aload_1
    //   85: invokeinterface 110 1 0
    //   90: ldc 11
    //   92: iconst_0
    //   93: invokeinterface 116 3 0
    //   98: invokeinterface 120 1 0
    //   103: pop
    //   104: goto -37 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	this	BlinkGcmRegistrationIntentService
    //   0	107	1	paramIntent	android.content.Intent
    //   73	7	2	localException	Exception
    //   32	10	3	str2	String
    //   68	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   16	67	68	finally
    //   69	71	68	finally
    //   10	16	73	java/lang/Exception
    //   71	73	73	java/lang/Exception
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/gcm/BlinkGcmRegistrationIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */