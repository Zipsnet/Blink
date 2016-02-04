package com.immediasemi.blink.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.BlinkImageRequest;
import com.immediasemi.blink.models.BlinkBitmap;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

public class ImageLoader
  implements Closeable
{
  boolean closed;
  ImageView imgView;
  boolean roundedCorners;
  
  public ImageLoader(String paramString, ImageView paramImageView, boolean paramBoolean, int paramInt)
  {
    this.roundedCorners = paramBoolean;
    loadImage(paramString, paramImageView, paramInt);
  }
  
  public static Bitmap getRoundedCornerBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, Context paramContext)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramInt3 = (int)TypedValue.applyDimension(1, paramInt3, paramContext.getResources().getDisplayMetrics());
    paramInt2 = (int)TypedValue.applyDimension(1, paramInt2, paramContext.getResources().getDisplayMetrics());
    paramContext = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    paramContext.setAntiAlias(true);
    paramContext.setColor(-1);
    paramContext.setStyle(Paint.Style.FILL);
    localCanvas.drawARGB(0, 0, 0, 0);
    localCanvas.drawRoundRect(localRectF, paramInt2, paramInt2, paramContext);
    paramContext.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, paramContext);
    paramContext.setColor(paramInt1);
    paramContext.setStyle(Paint.Style.STROKE);
    paramContext.setStrokeWidth(paramInt3);
    localCanvas.drawRoundRect(localRectF, paramInt2, paramInt2, paramContext);
    return localBitmap;
  }
  
  private void loadImage(final String paramString, ImageView paramImageView, int paramInt)
  {
    switch (paramInt)
    {
    }
    final String str;
    for (;;)
    {
      str = Integer.toHexString(paramString.hashCode());
      this.imgView = paramImageView;
      Object localObject = null;
      try
      {
        DiskLruCache.Snapshot localSnapshot = BlinkApp.getApp().getDiskLruCache().get(str);
        paramImageView = (ImageView)localObject;
        if (localSnapshot != null) {}
        try
        {
          paramImageView = BitmapFactory.decodeStream(localSnapshot.getInputStream(1));
          if ((paramImageView != null) && (!paramImageView.isRecycled()))
          {
            this.imgView.setVisibility(0);
            setImageBitmap(this.imgView, paramImageView);
            return;
            paramString = paramString + "_s";
            continue;
            paramString = paramString + "_m";
          }
        }
        catch (Exception paramImageView)
        {
          for (;;)
          {
            paramImageView.printStackTrace();
            paramImageView = (ImageView)localObject;
          }
        }
        new BlinkImageRequest(paramString, new BlinkAPI.BlinkAPICallback()
        {
          public void onError(BlinkError paramAnonymousBlinkError)
          {
            if (ImageLoader.this.closed) {}
          }
          
          public void onResult(BlinkData paramAnonymousBlinkData)
          {
            paramAnonymousBlinkData = ((BlinkBitmap)paramAnonymousBlinkData).getBitmap();
            if (ImageLoader.this.closed) {
              return;
            }
            try
            {
              DiskLruCache.Editor localEditor = BlinkApp.getApp().getDiskLruCache().edit(str);
              localEditor.set(0, paramString);
              OutputStream localOutputStream = localEditor.newOutputStream(1);
              paramAnonymousBlinkData.compress(Bitmap.CompressFormat.PNG, 100, localOutputStream);
              localOutputStream.close();
              localEditor.commit();
              ImageLoader.this.imgView.setVisibility(0);
              ImageLoader.this.setImageBitmap(ImageLoader.this.imgView, paramAnonymousBlinkData);
              return;
            }
            catch (IOException localIOException)
            {
              for (;;)
              {
                localIOException.printStackTrace();
              }
            }
          }
        }, 0, 0, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.ARGB_8888);
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
    }
  }
  
  private void setImageBitmap(ImageView paramImageView, Bitmap paramBitmap)
  {
    if (this.roundedCorners)
    {
      paramBitmap = getRoundedCornerBitmap(paramBitmap, BlinkApp.getApp().getResources().getColor(2131492875), 8, 8, BlinkApp.getApp().getApplicationContext());
      paramImageView.setAdjustViewBounds(true);
      paramImageView.setImageBitmap(paramBitmap);
      return;
    }
    paramImageView.setImageBitmap(paramBitmap);
  }
  
  public void close()
    throws IOException
  {
    this.closed = true;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/utils/ImageLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */