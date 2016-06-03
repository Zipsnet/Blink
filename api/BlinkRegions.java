package com.immediasemi.blink.api;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.requests.BlinkRegionsRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.QuickRegionInfo;
import com.immediasemi.blink.models.Region;
import com.immediasemi.blink.models.RegionManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class BlinkRegions
{
  public static final String BLINKREGIONSDIDFAILUPDATE = "BlinkRegionsDidFailUpdate";
  public static final String BLINKREGIONSDIDUPDATE = "BlinkRegionsDidUpdate";
  public RegionManager regionManager = new RegionManager();
  
  private void regionsDidFailUpdate()
  {
    Intent localIntent = new Intent("BlinkRegionsDidFailUpdate");
    LocalBroadcastManager.getInstance(BlinkApp.getApp().getBaseContext()).sendBroadcast(localIntent);
  }
  
  private void regionsDidUpdate()
  {
    Intent localIntent = new Intent("BlinkRegionsDidUpdate");
    LocalBroadcastManager.getInstance(BlinkApp.getApp().getBaseContext()).sendBroadcast(localIntent);
  }
  
  public void getRegions()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new BlinkRegionsRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        BlinkRegions.this.regionManager.setGetRegionsSuceeded(false);
        BlinkRegions.this.regionsDidFailUpdate();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        paramAnonymousBlinkData = new Gson();
        new QuickRegionInfo();
        try
        {
          paramAnonymousBlinkData = (QuickRegionInfo)paramAnonymousBlinkData.fromJson(BlinkData.mRawResponse, QuickRegionInfo.class);
          if (paramAnonymousBlinkData != null) {
            if ((paramAnonymousBlinkData.getRegions() == null) || (paramAnonymousBlinkData.getRegions().size() == 0))
            {
              BlinkRegions.this.regionManager.setGetRegionsSuceeded(false);
              BlinkRegions.this.regionsDidUpdate();
              return;
            }
          }
        }
        catch (JsonSyntaxException paramAnonymousBlinkData)
        {
          for (;;)
          {
            paramAnonymousBlinkData = null;
            continue;
            BlinkRegions.this.regionManager.setPreferred(paramAnonymousBlinkData.getPreferred());
            ArrayList localArrayList = new ArrayList();
            HashMap localHashMap = paramAnonymousBlinkData.getRegions();
            Iterator localIterator = localHashMap.keySet().iterator();
            while (localIterator.hasNext())
            {
              paramAnonymousBlinkData = (String)localIterator.next();
              Region localRegion = new Region();
              localRegion.setRegionCode(paramAnonymousBlinkData);
              localRegion.setDnsSubdomain((String)((HashMap)localHashMap.get(paramAnonymousBlinkData)).get("dns"));
              localRegion.setFriendlyName((String)((HashMap)localHashMap.get(paramAnonymousBlinkData)).get("friendly_name"));
              localArrayList.add(localRegion);
              Log.i("/regions", String.format("regionCode = %s, friendlyName = %s, dns = %s", new Object[] { localRegion.getRegionCode(), localRegion.getFriendlyName(), localRegion.getDnsSubdomain() }));
            }
            BlinkRegions.this.regionManager.setRegions(localArrayList);
            for (paramAnonymousBlinkData = Integer.valueOf(0); paramAnonymousBlinkData.intValue() < localArrayList.size(); paramAnonymousBlinkData = Integer.valueOf(paramAnonymousBlinkData.intValue() + 1)) {
              if (BlinkRegions.this.regionManager.getPreferred().equals(((Region)localArrayList.get(paramAnonymousBlinkData.intValue())).getRegionCode())) {
                BlinkRegions.this.regionManager.setIndexOfPreferredRegion(paramAnonymousBlinkData);
              }
            }
            BlinkRegions.this.regionManager.setGetRegionsSuceeded(true);
            BlinkRegions.this.regionsDidUpdate();
            continue;
            BlinkRegions.this.regionManager.setGetRegionsSuceeded(false);
            BlinkRegions.this.regionsDidUpdate();
          }
        }
      }
    }, false);
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/BlinkRegions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */