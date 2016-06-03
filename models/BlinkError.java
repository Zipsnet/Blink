package com.immediasemi.blink.models;

import com.android.volley.NetworkResponse;
import java.io.Serializable;
import java.util.HashMap;

public class BlinkError
  implements Serializable
{
  private static final long serialVersionUID = -4620063504420699924L;
  protected String errorMessage = null;
  private NetworkResponse networkResponse;
  public HashMap<String, String> response;
  private String responseString;
  
  public BlinkError() {}
  
  /* Error */
  public BlinkError(com.android.volley.VolleyError paramVolleyError)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 22	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: aconst_null
    //   6: putfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   9: aload_1
    //   10: invokevirtual 36	java/lang/Object:getClass	()Ljava/lang/Class;
    //   13: invokevirtual 42	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   16: astore_2
    //   17: aload_0
    //   18: aload_1
    //   19: getfield 46	com/android/volley/VolleyError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   22: putfield 47	com/immediasemi/blink/models/BlinkError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   25: aload_0
    //   26: getfield 47	com/immediasemi/blink/models/BlinkError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   29: ifnull +65 -> 94
    //   32: new 49	java/lang/String
    //   35: astore_3
    //   36: aload_3
    //   37: aload_0
    //   38: getfield 47	com/immediasemi/blink/models/BlinkError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   41: getfield 55	com/android/volley/NetworkResponse:data	[B
    //   44: aload_0
    //   45: getfield 47	com/immediasemi/blink/models/BlinkError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   48: getfield 59	com/android/volley/NetworkResponse:headers	Ljava/util/Map;
    //   51: invokestatic 65	com/android/volley/toolbox/HttpHeaderParser:parseCharset	(Ljava/util/Map;)Ljava/lang/String;
    //   54: invokespecial 68	java/lang/String:<init>	([BLjava/lang/String;)V
    //   57: aload_0
    //   58: aload_3
    //   59: putfield 70	com/immediasemi/blink/models/BlinkError:responseString	Ljava/lang/String;
    //   62: aload_0
    //   63: getfield 70	com/immediasemi/blink/models/BlinkError:responseString	Ljava/lang/String;
    //   66: putstatic 75	com/immediasemi/blink/models/BlinkData:mRawResponse	Ljava/lang/String;
    //   69: new 77	com/google/gson/Gson
    //   72: astore_3
    //   73: aload_3
    //   74: invokespecial 78	com/google/gson/Gson:<init>	()V
    //   77: aload_0
    //   78: aload_3
    //   79: aload_0
    //   80: getfield 70	com/immediasemi/blink/models/BlinkError:responseString	Ljava/lang/String;
    //   83: ldc 80
    //   85: invokevirtual 84	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   88: checkcast 80	java/util/HashMap
    //   91: putfield 86	com/immediasemi/blink/models/BlinkError:response	Ljava/util/HashMap;
    //   94: new 49	java/lang/String
    //   97: astore_3
    //   98: aload_3
    //   99: aload_0
    //   100: getfield 47	com/immediasemi/blink/models/BlinkError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   103: getfield 55	com/android/volley/NetworkResponse:data	[B
    //   106: ldc 88
    //   108: invokespecial 68	java/lang/String:<init>	([BLjava/lang/String;)V
    //   111: aload_0
    //   112: aload_3
    //   113: putfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   116: aload_0
    //   117: getfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   120: ifnonnull +73 -> 193
    //   123: aload_1
    //   124: getfield 46	com/android/volley/VolleyError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   127: ifnull +58 -> 185
    //   130: aload_0
    //   131: new 90	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   138: aload_2
    //   139: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: ldc 97
    //   144: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: aload_1
    //   148: getfield 46	com/android/volley/VolleyError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   151: getfield 101	com/android/volley/NetworkResponse:statusCode	I
    //   154: invokestatic 105	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   157: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: putfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   166: return
    //   167: astore_3
    //   168: aload_0
    //   169: aconst_null
    //   170: putfield 86	com/immediasemi/blink/models/BlinkError:response	Ljava/util/HashMap;
    //   173: goto -79 -> 94
    //   176: astore_3
    //   177: aload_0
    //   178: aconst_null
    //   179: putfield 86	com/immediasemi/blink/models/BlinkError:response	Ljava/util/HashMap;
    //   182: goto -88 -> 94
    //   185: aload_0
    //   186: aload_2
    //   187: putfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   190: goto -24 -> 166
    //   193: aload_0
    //   194: getfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   197: ldc 110
    //   199: invokevirtual 114	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   202: ifeq -36 -> 166
    //   205: new 77	com/google/gson/Gson
    //   208: dup
    //   209: invokespecial 78	com/google/gson/Gson:<init>	()V
    //   212: aload_0
    //   213: getfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   216: ldc 116
    //   218: invokevirtual 84	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   221: checkcast 116	com/immediasemi/blink/models/MessageResponse
    //   224: astore_1
    //   225: aload_1
    //   226: ifnull -60 -> 166
    //   229: aload_1
    //   230: invokevirtual 119	com/immediasemi/blink/models/MessageResponse:getMessage	()Ljava/lang/String;
    //   233: ifnull -67 -> 166
    //   236: aload_0
    //   237: aload_1
    //   238: invokevirtual 119	com/immediasemi/blink/models/MessageResponse:getMessage	()Ljava/lang/String;
    //   241: putfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   244: goto -78 -> 166
    //   247: astore_3
    //   248: goto -132 -> 116
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	251	0	this	BlinkError
    //   0	251	1	paramVolleyError	com.android.volley.VolleyError
    //   16	171	2	str	String
    //   35	78	3	localObject	Object
    //   167	1	3	localJsonSyntaxException	com.google.gson.JsonSyntaxException
    //   176	1	3	localUnsupportedEncodingException	java.io.UnsupportedEncodingException
    //   247	1	3	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   77	94	167	com/google/gson/JsonSyntaxException
    //   32	77	176	java/io/UnsupportedEncodingException
    //   77	94	176	java/io/UnsupportedEncodingException
    //   168	173	176	java/io/UnsupportedEncodingException
    //   94	116	247	java/lang/Exception
  }
  
  public String getErrorMessage()
  {
    return this.errorMessage;
  }
  
  public NetworkResponse getNetworkResponse()
  {
    return this.networkResponse;
  }
  
  public void setErrorMessage(String paramString)
  {
    this.errorMessage = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/BlinkError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */