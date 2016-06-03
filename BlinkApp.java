package com.immediasemi.blink;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.immediasemi.blink.models.Video;
import com.immediasemi.blink.utils.DiskLruCache;
import com.immediasemi.blink.utils.Util;
import java.io.File;
import java.util.ArrayList;

public class BlinkApp
  extends Application
{
  private static final String DISK_DATA_CACHE_DIR = "/image_cache";
  private static final String DISK_DATA_DIR = "/Android/data/";
  public static final String ONBOARDING_URL = "http://172.16.97.199";
  private static final String PREF_DNS_SUBDOMAIN = "pref_dns_subdomain";
  private static final String PREF_GCM_TOKEN = "pref_gcm_token";
  private static final String PREF_LAST_CAMERA_ACCESSED = "pref_last_camera_accessed";
  private static final String PREF_LAST_CAMERA_NAME = "pref_last_camera_name";
  private static final String PREF_LAST_COMMAND_ACCESSED = "pref_last_command_accessed";
  private static final String PREF_LAST_NETWORK_ACCESSED = "pref_last_network_accesssed";
  private static final String PREF_LAST_NETWORK_INDEX = "pref_last_network_index";
  private static final String PREF_LAST_NETWORK_NAME = "pref_last_network_name";
  private static final String PREF_LAST_PAGE_ACCESSED = "pref_last_page_accessed";
  private static final String PREF_LAST_SYNCMODULE_ACCESSED = "pref_last_syncmodule_accessed";
  private static final String PREF_LOGIN_PASSWORD = "pref_login_password";
  private static final String PREF_LOGIN_TIMESTAMP = "pref_login_timestamp";
  private static final String PREF_LOGIN_USER_NAME = "pref_login_user_name";
  private static final String PREF_LOGIN_USER_TOKEN = "pref_login_user_token";
  private static final String PREF_REGION_FRIENLY_NAME = "pref_region_friendly_name";
  private static final String PREF_SERVER_URL = "pref_server_url";
  private static final String PREF_TEMP_UNITS = "pref_temp_units";
  private static final String PREF_WRITE_EXTERNAL_STORAGE = "pre_write_external_storage";
  private static BlinkApp mApp;
  private static RequestQueue mRequestQueue;
  private ArrayList<String> RegionDNSSubdomains;
  private ArrayList<String> RegionFriendlyNames;
  private String dnsSubdomain;
  private boolean isDebug;
  private String mAndroidId;
  private String mDeviceToken;
  private DiskLruCache mDiskLruCache;
  private String mLastCameraId;
  private String mLastCameraName;
  private String mLastCommand;
  private String mLastNetworkId;
  private Integer mLastNetworkIndex;
  private String mLastNetworkName;
  private String mLastPage;
  private String mLastSyncModuleId;
  private String mLastVideo;
  private Video[] mLastVideoList;
  private boolean mLoggedIn;
  private String mLoginAuthToken;
  private Integer mNetworkCount;
  private String mOnboardingUrl;
  private String mPassword;
  private boolean mPermissionsGranted;
  private DisplayMetrics mScreenMetrics;
  private String mServerUrl;
  private boolean mTempUnits;
  private String mUserName;
  private String regionFriendlyName;
  
  public BlinkApp()
  {
    mApp = this;
  }
  
  public static BlinkApp getApp()
  {
    return mApp;
  }
  
  public static RequestQueue getVollyRequestQueue()
  {
    return mRequestQueue;
  }
  
  public String getDeviceToken()
  {
    return this.mDeviceToken;
  }
  
  public DiskLruCache getDiskLruCache()
  {
    return this.mDiskLruCache;
  }
  
  public String getDnsSubdomain()
  {
    this.dnsSubdomain = PreferenceManager.getDefaultSharedPreferences(this).getString("pref_dns_subdomain", getString(2131099800));
    return this.dnsSubdomain;
  }
  
  public String getLastCameraId()
  {
    return this.mLastCameraId;
  }
  
  public String getLastCameraName()
  {
    return this.mLastCameraName;
  }
  
  public String getLastCommand()
  {
    return this.mLastCommand;
  }
  
  public String getLastNetworkId()
  {
    return this.mLastNetworkId;
  }
  
  public Integer getLastNetworkIndex()
  {
    return this.mLastNetworkIndex;
  }
  
  public String getLastNetworkName()
  {
    return this.mLastNetworkName;
  }
  
  public String getLastPage()
  {
    return this.mLastPage;
  }
  
  public String getLastSyncModuleId()
  {
    return this.mLastSyncModuleId;
  }
  
  public String getLastVideoId()
  {
    return this.mLastVideo;
  }
  
  public Video[] getLastVideoList()
  {
    return this.mLastVideoList;
  }
  
  public boolean getLoggedIn()
  {
    return this.mLoggedIn;
  }
  
  public String getLoginAuthToken()
  {
    return this.mLoginAuthToken;
  }
  
  public Integer getNetworkCount()
  {
    return this.mNetworkCount;
  }
  
  public String getOnboardingUrl()
  {
    return this.mOnboardingUrl;
  }
  
  public String getPassword()
  {
    return this.mPassword;
  }
  
  public boolean getPermissionsGranted()
  {
    if (!this.mPermissionsGranted) {
      this.mPermissionsGranted = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pre_write_external_storage", false);
    }
    return this.mPermissionsGranted;
  }
  
  public String getRegionFriendlyName()
  {
    this.regionFriendlyName = PreferenceManager.getDefaultSharedPreferences(this).getString("pref_region_friendly_name", "United States");
    return this.regionFriendlyName;
  }
  
  public DisplayMetrics getScreenMetrics()
  {
    return this.mScreenMetrics;
  }
  
  public String getServerUrl()
  {
    if ((getOnboardingUrl() != null) && (!getOnboardingUrl().isEmpty())) {}
    for (String str = getOnboardingUrl();; str = this.mServerUrl) {
      return str;
    }
  }
  
  public String getUserName()
  {
    return this.mUserName;
  }
  
  public boolean hasLoginExpired()
  {
    Object localObject = PreferenceManager.getDefaultSharedPreferences(this);
    long l = System.currentTimeMillis() - ((SharedPreferences)localObject).getLong("pref_login_timestamp", 0L);
    if ((l < 0L) || (l > 64800000L))
    {
      String str1 = ((SharedPreferences)localObject).getString("pref_login_user_name", "");
      String str2 = ((SharedPreferences)localObject).getString("pref_login_password", "");
      localObject = ((SharedPreferences)localObject).getString("pref_login_user_token", "");
      if ((str1.isEmpty()) || (str2.isEmpty()) || (((String)localObject).isEmpty())) {}
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isDebug()
  {
    if ((getApplicationInfo().flags & 0x2) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      this.isDebug = bool;
      return this.isDebug;
    }
  }
  
  public boolean isTempUnits()
  {
    return this.mTempUnits;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public void onCreate()
  {
    super.onCreate();
    try
    {
      this.mAndroidId = Util.SHA1(Settings.Secure.getString(getContentResolver(), "android_id"));
      while (this.mAndroidId.length() < 80)
      {
        StringBuilder localStringBuilder = new java/lang/StringBuilder;
        localStringBuilder.<init>();
        this.mAndroidId += this.mAndroidId;
        continue;
        mRequestQueue = Volley.newRequestQueue(mApp, null);
      }
    }
    catch (Exception localException1)
    {
      this.mAndroidId = "12345678901234567890123456789012345678901234567890123456789012345678901234567890";
    }
    for (;;)
    {
      Object localObject = PreferenceManager.getDefaultSharedPreferences(this);
      this.mLoginAuthToken = ((SharedPreferences)localObject).getString("pref_login_user_token", "");
      String str = ((SharedPreferences)localObject).getString("pref_login_user_name", "");
      if (str.length() < 40)
      {
        this.mUserName = str;
        label128:
        str = ((SharedPreferences)localObject).getString("pref_login_password", "");
        if (str.length() >= 40) {
          break label501;
        }
        this.mPassword = str;
        if ((!this.mLoginAuthToken.isEmpty()) && (!this.mUserName.isEmpty()) && (!this.mPassword.isEmpty())) {
          this.mLoggedIn = true;
        }
        this.mLastNetworkIndex = Integer.valueOf(((SharedPreferences)localObject).getInt("pref_last_network_index", 0));
        this.mLastNetworkId = ((SharedPreferences)localObject).getString("pref_last_network_accesssed", "");
        this.mLastNetworkName = ((SharedPreferences)localObject).getString("pref_last_network_name", "");
        this.mLastCameraId = ((SharedPreferences)localObject).getString("pref_last_camera_accessed", "");
        this.mLastCameraName = ((SharedPreferences)localObject).getString("pref_last_camera_name", "");
        this.mLastSyncModuleId = ((SharedPreferences)localObject).getString("pref_last_syncmodule_accessed", "");
        this.mLastPage = ((SharedPreferences)localObject).getString("pref_last_page_accessed", "");
        this.mLastCommand = ((SharedPreferences)localObject).getString("pref_last_command_accessed", "");
        this.mTempUnits = ((SharedPreferences)localObject).getBoolean("pref_temp_units", true);
        this.mServerUrl = ((SharedPreferences)localObject).getString("pref_server_url", "");
        if (this.mServerUrl.length() == 0) {
          this.mServerUrl = getResources().getStringArray(2131427333)[0];
        }
        this.mScreenMetrics = getResources().getDisplayMetrics();
        Util.mWeekdays = getApp().getApplicationContext().getResources().getStringArray(2131427334);
        localObject = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getPackageName() + "/image_cache");
        if ((localObject == null) || (!((File)localObject).exists()) || (!((File)localObject).isDirectory())) {
          ((File)localObject).mkdir();
        }
      }
      try
      {
        this.mDiskLruCache = DiskLruCache.open((File)localObject, 0, 2, 20000000L);
        return;
        this.mAndroidId = this.mAndroidId.substring(this.mAndroidId.length() - 80);
        continue;
        this.mUserName = Util.removeHash(this.mAndroidId, str);
        break label128;
        label501:
        this.mPassword = Util.removeHash(this.mAndroidId, str);
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          localException2.printStackTrace();
        }
      }
    }
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
  }
  
  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
  }
  
  public void setDeviceToken(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_gcm_token", paramString).commit();
    this.mDeviceToken = paramString;
  }
  
  public void setDnsSubdomain(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_dns_subdomain", paramString).commit();
    this.dnsSubdomain = paramString;
  }
  
  public void setIsDebug(boolean paramBoolean)
  {
    this.isDebug = paramBoolean;
  }
  
  public void setLastCameraId(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_last_camera_accessed", paramString).commit();
    this.mLastCameraId = paramString;
  }
  
  public void setLastCameraName(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_last_camera_name", paramString).commit();
    this.mLastCameraName = paramString;
  }
  
  public void setLastCommand(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_last_command_accessed", paramString).commit();
    this.mLastCommand = paramString;
  }
  
  public void setLastNetworkId(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_last_network_accesssed", paramString).commit();
    this.mLastNetworkId = paramString;
  }
  
  public void setLastNetworkIndex(Integer paramInteger)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("pref_last_network_index", paramInteger.intValue()).commit();
    this.mLastNetworkIndex = paramInteger;
  }
  
  public void setLastNetworkName(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_last_network_name", paramString).commit();
    this.mLastNetworkName = paramString;
  }
  
  public void setLastPage(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_last_page_accessed", paramString).commit();
    this.mLastPage = paramString;
  }
  
  public void setLastVideoId(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_last_command_accessed", paramString).commit();
    this.mLastVideo = paramString;
  }
  
  public void setLastVideoList(Video[] paramArrayOfVideo)
  {
    this.mLastVideoList = paramArrayOfVideo;
  }
  
  public void setLoggedIn(boolean paramBoolean)
  {
    boolean bool = paramBoolean;
    if (paramBoolean) {
      if ((!this.mLoginAuthToken.isEmpty()) && (!this.mUserName.isEmpty()))
      {
        bool = paramBoolean;
        if (!this.mPassword.isEmpty()) {}
      }
      else
      {
        bool = false;
      }
    }
    this.mLoggedIn = bool;
  }
  
  public void setLoginAuthToken(String paramString)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    localSharedPreferences.edit().putString("pref_login_user_token", paramString).commit();
    this.mLoginAuthToken = paramString;
    if (paramString.length() > 0) {}
    for (boolean bool = true;; bool = false)
    {
      setLoggedIn(bool);
      if (bool) {
        localSharedPreferences.edit().putLong("pref_login_timestamp", System.currentTimeMillis()).commit();
      }
      return;
    }
  }
  
  public void setNetworkCount(Integer paramInteger)
  {
    this.mNetworkCount = paramInteger;
  }
  
  public void setOnboardingUrl(String paramString)
  {
    this.mOnboardingUrl = paramString;
  }
  
  public void setPassword(String paramString)
  {
    String str = Util.applyHash(this.mAndroidId, paramString);
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_login_password", str).commit();
    this.mPassword = paramString;
  }
  
  public void setPermissionsGranted(boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("pre_write_external_storage", paramBoolean).commit();
    this.mPermissionsGranted = paramBoolean;
  }
  
  public void setRegionFriendlyName(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_region_friendly_name", paramString);
    this.regionFriendlyName = paramString;
  }
  
  public void setRegionFriendlyNames(ArrayList<String> paramArrayList)
  {
    PreferenceManager.getDefaultSharedPreferences(this);
  }
  
  public void setServerUrl(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_server_url", paramString).commit();
    this.mServerUrl = paramString;
  }
  
  public void setServerUrlForDNSSubdomain(String paramString)
  {
    setDnsSubdomain(paramString);
    setServerUrl(String.format("https://%s.immedia-semi.com", new Object[] { paramString }));
  }
  
  public void setTempUnits(boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("pref_temp_units", paramBoolean).commit();
    this.mTempUnits = paramBoolean;
  }
  
  public void setUserName(String paramString)
  {
    String str = Util.applyHash(this.mAndroidId, paramString);
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_login_user_name", str).commit();
    this.mUserName = paramString;
  }
  
  public void setlastSyncModuleId(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_last_syncmodule_accessed", paramString).commit();
    this.mLastSyncModuleId = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/BlinkApp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */