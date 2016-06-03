package com.immediasemi.blink.api.requests;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import com.immediasemi.blink.BlinkApp;
import java.io.File;

public class BlinkDownloadVideoClip
{
  public static final String DOWNLOAD_FILEPATH = "video_clip.mp4";
  private static final String FAILED_TO_DOWNLOAD_MESSAGE = "Unknown failure to download clip";
  private DownloadManager mDownloadManager;
  private long mEnqueue;
  private Error mError;
  private Result mResult;
  private Uri mUri;
  
  public BlinkDownloadVideoClip(Uri paramUri, Result paramResult, Error paramError)
  {
    this.mUri = paramUri;
    this.mResult = paramResult;
    this.mError = paramError;
    paramUri = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if ("android.intent.action.DOWNLOAD_COMPLETE".equals(paramAnonymousIntent.getAction()))
        {
          paramAnonymousIntent.getLongExtra("extra_download_id", 0L);
          paramAnonymousContext = new DownloadManager.Query();
          paramAnonymousContext.setFilterById(new long[] { BlinkDownloadVideoClip.this.mEnqueue });
          paramAnonymousContext = BlinkDownloadVideoClip.this.mDownloadManager.query(paramAnonymousContext);
          if (paramAnonymousContext.moveToFirst())
          {
            if (8 != paramAnonymousContext.getInt(paramAnonymousContext.getColumnIndex("status"))) {
              break label125;
            }
            paramAnonymousContext = paramAnonymousContext.getString(paramAnonymousContext.getColumnIndex("local_filename"));
            if (BlinkDownloadVideoClip.this.mResult != null) {
              BlinkDownloadVideoClip.this.mResult.result(paramAnonymousContext);
            }
          }
        }
        for (;;)
        {
          return;
          label125:
          int i = paramAnonymousContext.getColumnIndex("reason");
          if (BlinkDownloadVideoClip.this.mError != null)
          {
            paramAnonymousIntent = paramAnonymousContext.getString(i);
            if (paramAnonymousIntent.length() != 0)
            {
              paramAnonymousContext = paramAnonymousIntent;
              if (!paramAnonymousIntent.equals("placeholder")) {}
            }
            else
            {
              paramAnonymousContext = "Unknown failure to download clip";
            }
            BlinkDownloadVideoClip.this.mError.error(paramAnonymousContext);
          }
        }
      }
    };
    BlinkApp.getApp().registerReceiver(paramUri, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
    this.mDownloadManager = ((DownloadManager)BlinkApp.getApp().getSystemService("download"));
    paramError = new DownloadManager.Request(this.mUri);
    paramUri = null;
    paramResult = BlinkApp.getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
    if (paramResult != null)
    {
      paramResult = new File(paramResult.getPath() + "/" + "video_clip.mp4");
      Log.i("BlinkDownloadVideoClip", "abs path to video clip: " + paramResult.getPath());
      paramUri = paramResult;
      if (paramResult != null)
      {
        paramUri = paramResult;
        if (paramResult.exists())
        {
          paramResult.delete();
          Log.i("BlinkDownloadVideoClip", "deleted clip: " + paramResult.getPath());
          paramUri = paramResult;
        }
      }
    }
    if (paramUri != null)
    {
      paramResult = new DownloadManager.Query();
      paramResult.setFilterByStatus(8);
      paramResult = this.mDownloadManager.query(paramResult);
      if (paramResult.moveToFirst())
      {
        if (!paramResult.getString(paramResult.getColumnIndex("local_filename")).equals(paramUri.getPath())) {
          break label340;
        }
        int i = paramResult.getInt(paramResult.getColumnIndex("_id"));
        paramResult.close();
        this.mDownloadManager.remove(new long[] { i });
      }
    }
    for (;;)
    {
      paramError.setDestinationInExternalFilesDir(BlinkApp.getApp(), Environment.DIRECTORY_DOWNLOADS, "video_clip.mp4");
      paramError.addRequestHeader("TOKEN_AUTH", BlinkApp.getApp().getLoginAuthToken());
      this.mEnqueue = this.mDownloadManager.enqueue(paramError);
      return;
      label340:
      if (paramResult.moveToNext()) {
        break;
      }
    }
  }
  
  public static abstract interface Error
  {
    public abstract void error(String paramString);
  }
  
  public static abstract interface Result
  {
    public abstract void result(String paramString);
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/BlinkDownloadVideoClip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */