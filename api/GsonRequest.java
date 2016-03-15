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
    if (this.body.length() == 0) {}
    while (this.body.equals("{}")) {
      return null;
    }
    return this.body.getBytes();
  }
  
  public String getBodyContentType()
  {
    return "application/json";
  }
  
  public Map<String, String> getHeaders()
    throws AuthFailureError
  {
    if (this.headers != null) {
      return this.headers;
    }
    return super.getHeaders();
  }
  
  /* Error */
  protected com.android.volley.Response<T> parseNetworkResponse(com.android.volley.NetworkResponse paramNetworkResponse)
  {
    // Byte code:
    //   0: ldc -117
    //   2: astore_2
    //   3: new 107	java/lang/String
    //   6: dup
    //   7: aload_1
    //   8: getfield 145	com/android/volley/NetworkResponse:data	[B
    //   11: aload_1
    //   12: getfield 146	com/android/volley/NetworkResponse:headers	Ljava/util/Map;
    //   15: invokestatic 152	com/android/volley/toolbox/HttpHeaderParser:parseCharset	(Ljava/util/Map;)Ljava/lang/String;
    //   18: invokespecial 155	java/lang/String:<init>	([BLjava/lang/String;)V
    //   21: astore_3
    //   22: aload_3
    //   23: putstatic 160	com/immediasemi/blink/models/BlinkData:mRawResponse	Ljava/lang/String;
    //   26: aload_0
    //   27: getfield 36	com/immediasemi/blink/api/GsonRequest:raw	Z
    //   30: istore 5
    //   32: iload 5
    //   34: ifeq +50 -> 84
    //   37: aload_0
    //   38: getfield 46	com/immediasemi/blink/api/GsonRequest:resultsClass	Ljava/lang/Class;
    //   41: aconst_null
    //   42: checkcast 162	[Ljava/lang/Class;
    //   45: invokevirtual 168	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   48: astore_2
    //   49: aload_3
    //   50: putstatic 160	com/immediasemi/blink/models/BlinkData:mRawResponse	Ljava/lang/String;
    //   53: aload_2
    //   54: iconst_0
    //   55: anewarray 170	java/lang/Object
    //   58: invokevirtual 176	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   61: aload_1
    //   62: invokestatic 180	com/android/volley/toolbox/HttpHeaderParser:parseCacheHeaders	(Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Cache$Entry;
    //   65: invokestatic 186	com/android/volley/Response:success	(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
    //   68: astore_2
    //   69: aload_2
    //   70: areturn
    //   71: astore_2
    //   72: new 188	com/android/volley/ParseError
    //   75: dup
    //   76: aload_2
    //   77: invokespecial 191	com/android/volley/ParseError:<init>	(Ljava/lang/Throwable;)V
    //   80: invokestatic 195	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   83: areturn
    //   84: aload_0
    //   85: getfield 44	com/immediasemi/blink/api/GsonRequest:gson	Lcom/google/gson/Gson;
    //   88: aload_3
    //   89: aload_0
    //   90: getfield 46	com/immediasemi/blink/api/GsonRequest:resultsClass	Ljava/lang/Class;
    //   93: invokevirtual 199	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   96: aload_1
    //   97: invokestatic 180	com/android/volley/toolbox/HttpHeaderParser:parseCacheHeaders	(Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Cache$Entry;
    //   100: invokestatic 186	com/android/volley/Response:success	(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
    //   103: astore_2
    //   104: aload_2
    //   105: areturn
    //   106: astore_1
    //   107: new 188	com/android/volley/ParseError
    //   110: dup
    //   111: aload_1
    //   112: invokespecial 191	com/android/volley/ParseError:<init>	(Ljava/lang/Throwable;)V
    //   115: invokestatic 195	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   118: areturn
    //   119: astore 4
    //   121: aload_2
    //   122: astore_3
    //   123: aload 4
    //   125: astore_2
    //   126: aload_3
    //   127: ifnull +95 -> 222
    //   130: aload_3
    //   131: invokevirtual 111	java/lang/String:length	()I
    //   134: iconst_2
    //   135: if_icmplt +87 -> 222
    //   138: aload_3
    //   139: ldc -55
    //   141: invokevirtual 205	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   144: ifeq +78 -> 222
    //   147: aload_3
    //   148: ldc -49
    //   150: invokevirtual 205	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   153: ifeq +9 -> 162
    //   156: aconst_null
    //   157: aconst_null
    //   158: invokestatic 186	com/android/volley/Response:success	(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
    //   161: areturn
    //   162: new 209	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 210	java/lang/StringBuilder:<init>	()V
    //   169: ldc -44
    //   171: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: aload_3
    //   175: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: ldc -38
    //   180: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: astore_2
    //   187: aload_0
    //   188: getfield 44	com/immediasemi/blink/api/GsonRequest:gson	Lcom/google/gson/Gson;
    //   191: aload_2
    //   192: aload_0
    //   193: getfield 46	com/immediasemi/blink/api/GsonRequest:resultsClass	Ljava/lang/Class;
    //   196: invokevirtual 199	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   199: aload_1
    //   200: invokestatic 180	com/android/volley/toolbox/HttpHeaderParser:parseCacheHeaders	(Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Cache$Entry;
    //   203: invokestatic 186	com/android/volley/Response:success	(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
    //   206: astore_1
    //   207: aload_1
    //   208: areturn
    //   209: astore_1
    //   210: new 188	com/android/volley/ParseError
    //   213: dup
    //   214: aload_1
    //   215: invokespecial 191	com/android/volley/ParseError:<init>	(Ljava/lang/Throwable;)V
    //   218: invokestatic 195	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   221: areturn
    //   222: new 188	com/android/volley/ParseError
    //   225: dup
    //   226: aload_2
    //   227: invokespecial 191	com/android/volley/ParseError:<init>	(Ljava/lang/Throwable;)V
    //   230: invokestatic 195	com/android/volley/Response:error	(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
    //   233: areturn
    //   234: astore_2
    //   235: goto -109 -> 126
    //   238: astore_1
    //   239: goto -132 -> 107
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	242	0	this	GsonRequest
    //   0	242	1	paramNetworkResponse	com.android.volley.NetworkResponse
    //   2	68	2	localObject1	Object
    //   71	6	2	localException	Exception
    //   103	124	2	localObject2	Object
    //   234	1	2	localJsonSyntaxException1	com.google.gson.JsonSyntaxException
    //   21	154	3	localObject3	Object
    //   119	5	4	localJsonSyntaxException2	com.google.gson.JsonSyntaxException
    //   30	3	5	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   37	69	71	java/lang/Exception
    //   3	22	106	java/io/UnsupportedEncodingException
    //   3	22	119	com/google/gson/JsonSyntaxException
    //   130	162	209	java/lang/Exception
    //   162	207	209	java/lang/Exception
    //   22	32	234	com/google/gson/JsonSyntaxException
    //   37	69	234	com/google/gson/JsonSyntaxException
    //   72	84	234	com/google/gson/JsonSyntaxException
    //   84	104	234	com/google/gson/JsonSyntaxException
    //   22	32	238	java/io/UnsupportedEncodingException
    //   37	69	238	java/io/UnsupportedEncodingException
    //   72	84	238	java/io/UnsupportedEncodingException
    //   84	104	238	java/io/UnsupportedEncodingException
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/GsonRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */