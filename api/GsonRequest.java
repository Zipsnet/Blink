package com.immediasemi.blink.api;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.gson.Gson;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.requests.BlinkRequest;
import java.util.HashMap;
import java.util.Map;

public class GsonRequest<T>
  extends Request<T>
{
  private String body;
  private Gson gson;
  private Map<String, String> headers;
  private Response.Listener<T> listener;
  private Map<String, String> params;
  private boolean raw;
  private Class<T> resultsClass;
  
  public GsonRequest(int paramInt1, String paramString, Class<T> paramClass, Map<String, String> paramMap1, Map<String, String> paramMap2, BlinkRequest paramBlinkRequest, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener, int paramInt2)
  {
    super(paramInt1, paramString, paramErrorListener);
    init(paramClass, paramMap1, paramMap2, paramBlinkRequest, paramListener, paramErrorListener, paramInt2);
  }
  
  public GsonRequest(int paramInt, String paramString, Class<T> paramClass, Map<String, String> paramMap1, Map<String, String> paramMap2, BlinkRequest paramBlinkRequest, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener, boolean paramBoolean)
  {
    super(paramInt, paramString, paramErrorListener);
    this.raw = paramBoolean;
    init(paramClass, paramMap1, paramMap2, paramBlinkRequest, paramListener, paramErrorListener, 25000);
  }
  
  private void init(Class<T> paramClass, Map<String, String> paramMap1, Map<String, String> paramMap2, BlinkRequest paramBlinkRequest, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener, int paramInt)
  {
    this.gson = new Gson();
    this.resultsClass = paramClass;
    this.body = new Gson().toJson(paramBlinkRequest);
    this.params = paramMap1;
    paramClass = paramMap2;
    if (paramMap2 == null) {
      paramClass = new HashMap();
    }
    if (BlinkApp.getApp().getLoggedIn()) {
      paramClass.put("TOKEN_AUTH", BlinkApp.getApp().getLoginAuthToken());
    }
    this.headers = paramClass;
    this.listener = paramListener;
    setRetryPolicy(new DefaultRetryPolicy(paramInt, 0, 1.0F));
  }
  
  protected void deliverResponse(T paramT)
  {
    this.listener.onResponse(paramT);
  }
  
  public byte[] getBody()
    throws AuthFailureError
  {
    byte[] arrayOfByte = null;
    if (this.body.length() == 0) {}
    for (;;)
    {
      return arrayOfByte;
      if (!this.body.equals("{}")) {
        arrayOfByte = this.body.getBytes();
      }
    }
  }
  
  public String getBodyContentType()
  {
    return "application/json";
  }
  
  public Map<String, String> getHeaders()
    throws AuthFailureError
  {
    if (this.headers != null) {}
    for (Map localMap = this.headers;; localMap = super.getHeaders()) {
      return localMap;
    }
  }
  
  /* Error */
  protected com.android.volley.Response<T> parseNetworkResponse(com.android.volley.NetworkResponse paramNetworkResponse)
  {
    // Byte code:
    //   0: ldc -117
    //   2: astore 5
    //   4: new 107	java/lang/String
    //   7: astore_3
    //   8: aload_3
    //   9: aload_1
    //   10: getfield 145	com/android/volley/NetworkResponse:data	[B
    //   13: aload_1
    //   14: getfield 146	com/android/volley/NetworkResponse:headers	Ljava/util/Map;
    //   17: invokestatic 152	com/android/volley/toolbox/HttpHeaderParser:parseCharset	(Ljava/util/Map;)Ljava/lang/String;
    //   20: invokespecial 155	java/lang/String:<init>	([BLjava/lang/String;)V
    //   23: aload_3
    //   24: putstatic 160	com/immediasemi/blink/models/BlinkData:mRawResponse	Ljava/lang/String;
    //   27: aload_0
    //   28: getfield 36	com/immediasemi/blink/api/GsonRequest:raw	Z
    //   31: istore_2
    //   32: iload_2
    //   33: ifeq +70 -> 103
    //   36: aload_0
    //   37: getfield 46	com/immediasemi/blink/api/GsonRequest:resultsClass	Ljava/lang/Class;
    //   40: aconst_null
    //   41: checkcast 162	[Ljava/lang/Class;
    //   44: invokevirtual 168	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   47: astore 4
    //   49: aload_3
    //   50: putstatic 160	com/immediasemi/blink/models/BlinkData:mRawResponse	Ljava/lang/String;
    //   53: aload 4
    //   55: iconst_0
    //   56: anewarray 170	java/lang/Object
    //   59: invokevirtual 176	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   62: aload_1
    //   63: invokestatic 180	com/android/volley/toolbox/HttpHeaderParser:parseCacheHeaders	(Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Cache$Entry;
    //   66: invokestatic 186	com/android/volley/Response:success	(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
    //   69: astore 4
    //   71: aload 4
    //   73: astore_1
    //   74: aload_1
    //   75: areturn
    //   76: astore 4
    //   78: new 188	com/android/volley/ParseError
    //   81: astore 5
    //   83: aload 5
    //   85: aload 4
    //   87: invokespecial 191	com/android/volley/ParseError:<init>	(Ljava/lang/Throwable;)V
    //   90: aload 5
    //   92: invokestatic 195	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   95: astore 4
    //   97: aload 4
    //   99: astore_1
    //   100: goto -26 -> 74
    //   103: aload_0
    //   104: getfield 44	com/immediasemi/blink/api/GsonRequest:gson	Lcom/google/gson/Gson;
    //   107: aload_3
    //   108: aload_0
    //   109: getfield 46	com/immediasemi/blink/api/GsonRequest:resultsClass	Ljava/lang/Class;
    //   112: invokevirtual 199	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   115: aload_1
    //   116: invokestatic 180	com/android/volley/toolbox/HttpHeaderParser:parseCacheHeaders	(Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Cache$Entry;
    //   119: invokestatic 186	com/android/volley/Response:success	(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
    //   122: astore 4
    //   124: aload 4
    //   126: astore_1
    //   127: goto -53 -> 74
    //   130: astore_1
    //   131: new 188	com/android/volley/ParseError
    //   134: dup
    //   135: aload_1
    //   136: invokespecial 191	com/android/volley/ParseError:<init>	(Ljava/lang/Throwable;)V
    //   139: invokestatic 195	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   142: astore_1
    //   143: goto -69 -> 74
    //   146: astore 4
    //   148: aload 5
    //   150: astore_3
    //   151: aload_3
    //   152: ifnull +107 -> 259
    //   155: aload_3
    //   156: invokevirtual 111	java/lang/String:length	()I
    //   159: iconst_2
    //   160: if_icmplt +99 -> 259
    //   163: aload_3
    //   164: ldc -55
    //   166: invokevirtual 205	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   169: ifeq +90 -> 259
    //   172: aload_3
    //   173: ldc -49
    //   175: invokevirtual 205	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   178: ifeq +12 -> 190
    //   181: aconst_null
    //   182: aconst_null
    //   183: invokestatic 186	com/android/volley/Response:success	(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
    //   186: astore_1
    //   187: goto -113 -> 74
    //   190: new 209	java/lang/StringBuilder
    //   193: astore 4
    //   195: aload 4
    //   197: invokespecial 210	java/lang/StringBuilder:<init>	()V
    //   200: aload 4
    //   202: ldc -44
    //   204: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: aload_3
    //   208: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: ldc -38
    //   213: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   219: astore_3
    //   220: aload_0
    //   221: getfield 44	com/immediasemi/blink/api/GsonRequest:gson	Lcom/google/gson/Gson;
    //   224: aload_3
    //   225: aload_0
    //   226: getfield 46	com/immediasemi/blink/api/GsonRequest:resultsClass	Ljava/lang/Class;
    //   229: invokevirtual 199	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   232: aload_1
    //   233: invokestatic 180	com/android/volley/toolbox/HttpHeaderParser:parseCacheHeaders	(Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Cache$Entry;
    //   236: invokestatic 186	com/android/volley/Response:success	(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
    //   239: astore_1
    //   240: goto -166 -> 74
    //   243: astore_1
    //   244: new 188	com/android/volley/ParseError
    //   247: dup
    //   248: aload_1
    //   249: invokespecial 191	com/android/volley/ParseError:<init>	(Ljava/lang/Throwable;)V
    //   252: invokestatic 195	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   255: astore_1
    //   256: goto -182 -> 74
    //   259: new 188	com/android/volley/ParseError
    //   262: dup
    //   263: aload 4
    //   265: invokespecial 191	com/android/volley/ParseError:<init>	(Ljava/lang/Throwable;)V
    //   268: invokestatic 195	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   271: astore_1
    //   272: goto -198 -> 74
    //   275: astore 4
    //   277: goto -126 -> 151
    //   280: astore_1
    //   281: goto -150 -> 131
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	284	0	this	GsonRequest
    //   0	284	1	paramNetworkResponse	com.android.volley.NetworkResponse
    //   31	2	2	bool	boolean
    //   7	218	3	localObject1	Object
    //   47	25	4	localObject2	Object
    //   76	10	4	localException	Exception
    //   95	30	4	localResponse	com.android.volley.Response
    //   146	1	4	localJsonSyntaxException1	com.google.gson.JsonSyntaxException
    //   193	71	4	localStringBuilder	StringBuilder
    //   275	1	4	localJsonSyntaxException2	com.google.gson.JsonSyntaxException
    //   2	147	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   36	71	76	java/lang/Exception
    //   4	23	130	java/io/UnsupportedEncodingException
    //   4	23	146	com/google/gson/JsonSyntaxException
    //   155	187	243	java/lang/Exception
    //   190	240	243	java/lang/Exception
    //   23	32	275	com/google/gson/JsonSyntaxException
    //   36	71	275	com/google/gson/JsonSyntaxException
    //   78	97	275	com/google/gson/JsonSyntaxException
    //   103	124	275	com/google/gson/JsonSyntaxException
    //   23	32	280	java/io/UnsupportedEncodingException
    //   36	71	280	java/io/UnsupportedEncodingException
    //   78	97	280	java/io/UnsupportedEncodingException
    //   103	124	280	java/io/UnsupportedEncodingException
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/GsonRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */