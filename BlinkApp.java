package com.immediasemi.blink;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class BlinkApp
  extends Application
{
  private static final String DISK_DATA_CACHE_DIR = "/image_cache";
  private static final String DISK_DATA_DIR = "/Android/data/";
  public static final String ONBOARDING_URL = "http://172.16.97.199";
  private static final String PREF_GCM_TOKEN = "pref_gcm_token";
  private static final String PREF_LAST_CAMERA_ACCESSED = "pref_last_camera_accessed";
  private static final String PREF_LAST_CAMERA_NAME = "pref_last_camera_name";
  private static final String PREF_LAST_COMMAND_ACCESSED = "pref_last_command_accessed";
  private static final String PREF_LAST_NETWORK_ACCESSED = "pref_last_network_accesssed";
  private static final String PREF_LAST_PAGE_ACCESSED = "pref_last_page_accessed";
  private static final String PREF_LAST_SYNCMODULE_ACCESSED = "pref_last_syncmodule_accessed";
  private static final String PREF_LOGIN_PASSWORD = "pref_login_password";
  private static final String PREF_LOGIN_TIMESTAMP = "pref_login_timestamp";
  private static final String PREF_LOGIN_USER_NAME = "pref_login_user_name";
  private static final String PREF_LOGIN_USER_TOKEN = "pref_login_user_token";
  private static final String PREF_SERVER_URL = "pref_server_url";
  private static final String PREF_TEMP_UNITS = "pref_temp_units";
  private static final String PREF_WRITE_EXTERNAL_STORAGE = "pre_write_external_storage";
  private static BlinkApp mApp;
  private static RequestQueue mRequestQueue;
  private String mAndroidId;
  private String mDeviceToken;
  private DiskLruCache mDiskLruCache;
  private String mLastCameraId;
  private String mLastCameraName;
  private String mLastCommand;
  private String mLastNetworkId;
  private String mLastPage;
  private String mLastSyncModuleId;
  private String mLastVideo;
  private Video[] mLastVideoList;
  private boolean mLoggedIn;
  private String mLoginAuthToken;
  private String mOnboardingUrl;
  private String mPassword;
  private boolean mPermissionsGranted;
  private DisplayMetrics mScreenMetrics;
  private String mServerUrl;
  private boolean mTempUnits;
  private String mUserName;
  
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
  
  public DisplayMetrics getScreenMetrics()
  {
    return this.mScreenMetrics;
  }
  
  public String getServerUrl()
  {
    if ((getOnboardingUrl() != null) && (!getOnboardingUrl().isEmpty())) {
      return getOnboardingUrl();
    }
    return this.mServerUrl;
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
      if ((!str1.isEmpty()) && (!str2.isEmpty()) && (!((String)localObject).isEmpty())) {
        return true;
      }
    }
    return false;
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
      for (this.mAndroidId = Util.SHA1(Settings.Secure.getString(getContentResolver(), "android_id")); this.mAndroidId.length() < 80; this.mAndroidId += this.mAndroidId) {}
      Object localObject;
      String str;
      label124:
      label466:
      return;
    }
    catch (Exception localException1)
    {
      this.mAndroidId = "12345678901234567890123456789012345678901234567890123456789012345678901234567890";
      mRequestQueue = Volley.newRequestQueue(mApp, null);
      localObject = PreferenceManager.getDefaultSharedPreferences(this);
      this.mLoginAuthToken = ((SharedPreferences)localObject).getString("pref_login_user_token", "");
      str = ((SharedPreferences)localObject).getString("pref_login_user_name", "");
      if (str.length() < 40)
      {
        this.mUserName = str;
        str = ((SharedPreferences)localObject).getString("pref_login_password", "");
        if (str.length() >= 40) {
          break label466;
        }
      }
      for (this.mPassword = str;; this.mPassword = Util.removeHash(this.mAndroidId, str))
      {
        if ((!this.mLoginAuthToken.isEmpty()) && (!this.mUserName.isEmpty()) && (!this.mPassword.isEmpty())) {
          this.mLoggedIn = true;
        }
        this.mLastNetworkId = ((SharedPreferences)localObject).getString("pref_last_network_accesssed", "");
        this.mLastCameraId = ((SharedPreferences)localObject).getString("pref_last_camera_accessed", "");
        this.mLastCameraName = ((SharedPreferences)localObject).getString("pref_last_camera_name", "");
        this.mLastSyncModuleId = ((SharedPreferences)localObject).getString("pref_last_syncmodule_accessed", "");
        this.mLastPage = ((SharedPreferences)localObject).getString("pref_last_page_accessed", "");
        this.mLastCommand = ((SharedPreferences)localObject).getString("pref_last_command_accessed", "");
        this.mTempUnits = ((SharedPreferences)localObject).getBoolean("pref_temp_units", true);
        this.mServerUrl = ((SharedPreferences)localObject).getString("pref_server_url", "");
        if (this.mServerUrl.length() == 0) {
          this.mServerUrl = getResources().getStringArray(2131427332)[0];
        }
        this.mScreenMetrics = getResources().getDisplayMetrics();
        Util.mWeekdays = getApp().getApplicationContext().getResources().getStringArray(2131427333);
        localObject = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getPackageName() + "/image_cache");
        if ((localObject == null) || (!((File)localObject).exists()) || (!((File)localObject).isDirectory())) {
          ((File)localObject).mkdir();
        }
        try
        {
          this.mDiskLruCache = DiskLruCache.open((File)localObject, 0, 2, 20000000L);
          return;
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
        this.mAndroidId = this.mAndroidId.substring(this.mAndroidId.length() - 80);
        break;
        this.mUserName = Util.removeHash(this.mAndroidId, str);
        break label124;
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
  
  public void setServerUrl(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pref_server_url", paramString).commit();
    this.mServerUrl = paramString;
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


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/BlinkApp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */