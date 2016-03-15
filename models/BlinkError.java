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
    //   29: ifnull +63 -> 92
    //   32: aload_0
    //   33: new 49	java/lang/String
    //   36: dup
    //   37: aload_0
    //   38: getfield 47	com/immediasemi/blink/models/BlinkError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   41: getfield 55	com/android/volley/NetworkResponse:data	[B
    //   44: aload_0
    //   45: getfield 47	com/immediasemi/blink/models/BlinkError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   48: getfield 59	com/android/volley/NetworkResponse:headers	Ljava/util/Map;
    //   51: invokestatic 65	com/android/volley/toolbox/HttpHeaderParser:parseCharset	(Ljava/util/Map;)Ljava/lang/String;
    //   54: invokespecial 68	java/lang/String:<init>	([BLjava/lang/String;)V
    //   57: putfield 70	com/immediasemi/blink/models/BlinkError:responseString	Ljava/lang/String;
    //   60: aload_0
    //   61: getfield 70	com/immediasemi/blink/models/BlinkError:responseString	Ljava/lang/String;
    //   64: putstatic 75	com/immediasemi/blink/models/BlinkData:mRawResponse	Ljava/lang/String;
    //   67: new 77	com/google/gson/Gson
    //   70: dup
    //   71: invokespecial 78	com/google/gson/Gson:<init>	()V
    //   74: astore_3
    //   75: aload_0
    //   76: aload_3
    //   77: aload_0
    //   78: getfield 70	com/immediasemi/blink/models/BlinkError:responseString	Ljava/lang/String;
    //   81: ldc 80
    //   83: invokevirtual 84	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   86: checkcast 80	java/util/HashMap
    //   89: putfield 86	com/immediasemi/blink/models/BlinkError:response	Ljava/util/HashMap;
    //   92: aload_0
    //   93: new 49	java/lang/String
    //   96: dup
    //   97: aload_0
    //   98: getfield 47	com/immediasemi/blink/models/BlinkError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   101: getfield 55	com/android/volley/NetworkResponse:data	[B
    //   104: ldc 88
    //   106: invokespecial 68	java/lang/String:<init>	([BLjava/lang/String;)V
    //   109: putfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   112: aload_0
    //   113: getfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   116: ifnonnull +71 -> 187
    //   119: aload_1
    //   120: getfield 46	com/android/volley/VolleyError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   123: ifnull +58 -> 181
    //   126: aload_0
    //   127: new 90	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   134: aload_2
    //   135: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: ldc 97
    //   140: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload_1
    //   144: getfield 46	com/android/volley/VolleyError:networkResponse	Lcom/android/volley/NetworkResponse;
    //   147: getfield 101	com/android/volley/NetworkResponse:statusCode	I
    //   150: invokestatic 105	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   153: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: putfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   162: return
    //   163: astore_3
    //   164: aload_0
    //   165: aconst_null
    //   166: putfield 86	com/immediasemi/blink/models/BlinkError:response	Ljava/util/HashMap;
    //   169: goto -77 -> 92
    //   172: astore_3
    //   173: aload_0
    //   174: aconst_null
    //   175: putfield 86	com/immediasemi/blink/models/BlinkError:response	Ljava/util/HashMap;
    //   178: goto -86 -> 92
    //   181: aload_0
    //   182: aload_2
    //   183: putfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   186: return
    //   187: aload_0
    //   188: getfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   191: ldc 110
    //   193: invokevirtual 114	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   196: ifeq -34 -> 162
    //   199: new 77	com/google/gson/Gson
    //   202: dup
    //   203: invokespecial 78	com/google/gson/Gson:<init>	()V
    //   206: aload_0
    //   207: getfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   210: ldc 116
    //   212: invokevirtual 84	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   215: checkcast 116	com/immediasemi/blink/models/MessageResponse
    //   218: astore_1
    //   219: aload_1
    //   220: ifnull -58 -> 162
    //   223: aload_1
    //   224: invokevirtual 119	com/immediasemi/blink/models/MessageResponse:getMessage	()Ljava/lang/String;
    //   227: ifnull -65 -> 162
    //   230: aload_0
    //   231: aload_1
    //   232: invokevirtual 119	com/immediasemi/blink/models/MessageResponse:getMessage	()Ljava/lang/String;
    //   235: putfield 24	com/immediasemi/blink/models/BlinkError:errorMessage	Ljava/lang/String;
    //   238: return
    //   239: astore_3
    //   240: goto -128 -> 112
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	243	0	this	BlinkError
    //   0	243	1	paramVolleyError	com.android.volley.VolleyError
    //   16	167	2	str	String
    //   74	3	3	localGson	com.google.gson.Gson
    //   163	1	3	localJsonSyntaxException	com.google.gson.JsonSyntaxException
    //   172	1	3	localUnsupportedEncodingException	java.io.UnsupportedEncodingException
    //   239	1	3	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   75	92	163	com/google/gson/JsonSyntaxException
    //   32	75	172	java/io/UnsupportedEncodingException
    //   75	92	172	java/io/UnsupportedEncodingException
    //   164	169	172	java/io/UnsupportedEncodingException
    //   92	112	239	java/lang/Exception
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


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/models/BlinkError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */