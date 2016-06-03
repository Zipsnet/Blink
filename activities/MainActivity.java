package com.immediasemi.blink.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Accounts.LoginRequest;
import com.immediasemi.blink.api.requests.Accounts.LogoutRequest;
import com.immediasemi.blink.api.requests.Accounts.ResetPasswordRequest;
import com.immediasemi.blink.fragments.BaseFragment;
import com.immediasemi.blink.fragments.BaseFragment.OnFragmentInteractionListener.InteractionAction;
import com.immediasemi.blink.fragments.CameraSettingsFragment;
import com.immediasemi.blink.fragments.HomeScreenFragment;
import com.immediasemi.blink.fragments.NavigationDrawerFragment;
import com.immediasemi.blink.fragments.NavigationDrawerFragment.NavigationDrawerCallbacks;
import com.immediasemi.blink.models.AuthToken;
import com.immediasemi.blink.models.AuthToken.AuthTokenContents;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.MessageResponse;
import com.immediasemi.blink.utils.ChooseDialog;
import com.immediasemi.blink.utils.ChooseDialog.NoticeDialogListener;
import com.immediasemi.blink.utils.DiskLruCache;
import com.immediasemi.blink.utils.OnClick;
import java.util.HashMap;
import java.util.List;

public class MainActivity
  extends BaseActivity
  implements NavigationDrawerFragment.NavigationDrawerCallbacks, ChooseDialog.NoticeDialogListener
{
  public static final int ABOUT = 102;
  public static final int ADDCAMERA = 111;
  public static final int ADVANCEDSETTINGS = 112;
  public static final int APITEST = 114;
  public static final String ARG_APP_IS_BOOTING = "arg_app_is_bootin";
  public static final String ARG_CAMERA_IDS = "arg_camera_ids";
  public static final String ARG_CAMERA_NAMES = "arg_camera_name";
  public static final String ARG_SECTION_NUMBER = "arg_section_number";
  public static final int CREATEACCOUNT = 110;
  public static final int EVENTLIST = 108;
  public static final int HOME = 0;
  public static final int LIVEVIDEO = 109;
  public static final int LI_MENU_ABOUT = 3;
  public static final int LI_MENU_APITEST = 7;
  public static final int LI_MENU_LOGOUT = 6;
  public static final int LI_MENU_MANAGEACCOUNT = 1;
  public static final int LI_MENU_MANAGESYSTEMS = 2;
  public static final int LI_MENU_SENDFEEDBACK = 4;
  public static final int LI_MENU_VISITBLINK = 5;
  public static final int LOGIN = 106;
  public static final int LOGOUT = 105;
  public static final int LO_MENU_ABOUT = 3;
  public static final int LO_MENU_APITEST = 6;
  public static final int LO_MENU_CREATEACCOUNT = 2;
  public static final int LO_MENU_LOGIN = 1;
  public static final int LO_MENU_SENDFEEDBACK = 4;
  public static final int LO_MENU_VISITBLINK = 5;
  public static final int MANAGEACCOUNT = 100;
  public static final int MANAGESYSTEMS = 101;
  private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
  public static final int RECONFIGURESYNC = 113;
  public static final int SENDFEEDBACK = 103;
  public static final int VIDEOLIST = 107;
  public static final int VISITBLINK = 104;
  private View mActionBarView;
  private boolean mBooting;
  private Menu mMenu;
  private NavigationDrawerFragment mNavigationDrawerFragment;
  private CharSequence mTitle;
  
  private boolean checkPlayServices()
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i != 0) {
      if (GooglePlayServicesUtil.isUserRecoverableError(i)) {
        GooglePlayServicesUtil.getErrorDialog(i, this, 9000).show();
      }
    }
    for (boolean bool = false;; bool = true)
    {
      return bool;
      Log.i("BlinkApp", "This device is not supported for Google Play Services");
      break;
    }
  }
  
  private void logout()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new LogoutRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        MainActivity.this.logMeOut();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        MainActivity.this.logMeOut();
      }
    }, false);
  }
  
  private void sendFeedback()
  {
    Object localObject2 = Build.BRAND;
    String str3 = Build.MODEL;
    Object localObject4 = Build.DEVICE;
    String str2 = Build.PRODUCT;
    str1 = "";
    Object localObject1 = str1;
    if (localObject2 != null)
    {
      localObject1 = str1;
      if (((String)localObject2).length() > 0) {
        localObject1 = localObject2;
      }
    }
    localObject2 = localObject1;
    if (str3 != null)
    {
      localObject2 = localObject1;
      if (str3.length() > 0) {
        localObject2 = (String)localObject1 + " " + str3;
      }
    }
    localObject1 = localObject2;
    if (localObject4 != null)
    {
      localObject1 = localObject2;
      if (((String)localObject4).length() > 0) {
        localObject1 = (String)localObject2 + " " + (String)localObject4;
      }
    }
    localObject2 = localObject1;
    if (str2 != null)
    {
      localObject2 = localObject1;
      if (str2.length() > 0) {
        localObject2 = (String)localObject1 + " " + str2;
      }
    }
    localObject1 = "Android " + Build.VERSION.RELEASE + " - API " + String.valueOf(Build.VERSION.SDK_INT);
    str2 = getString(2131099933).replace("$1", BlinkApp.getApp().getUserName()).replace("$2", (CharSequence)localObject2).replace("$3", (CharSequence)localObject1);
    localObject2 = "";
    str1 = "";
    localObject1 = localObject2;
    try
    {
      localObject4 = getPackageManager().getPackageInfo(getPackageName(), 0);
      localObject1 = localObject2;
      localObject2 = String.valueOf(((PackageInfo)localObject4).versionName);
      localObject1 = localObject2;
      int i = ((PackageInfo)localObject4).versionCode;
      str1 = String.valueOf(i);
      localObject1 = localObject2;
      localObject2 = str1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
        Object localObject3 = str1;
      }
    }
    localObject1 = new Intent("android.intent.action.VIEW", Uri.parse(str2.replace("$4", (CharSequence)localObject1).replace("$5", (CharSequence)localObject2)));
    if (((Intent)localObject1).resolveActivity(getPackageManager()) != null) {
      startActivity((Intent)localObject1);
    }
  }
  
  private boolean validateInputs(String paramString1, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString1 != null)
    {
      if (paramString1.length() != 0) {
        break label22;
      }
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      label22:
      bool1 = bool2;
      if (paramString2 != null)
      {
        bool1 = bool2;
        if (paramString2.length() != 0) {
          bool1 = true;
        }
      }
    }
  }
  
  public void logMeOut()
  {
    this.mNavigationDrawerFragment.updateListView();
    BlinkApp.getApp().setLoginAuthToken("");
    BlinkApp.getApp().setUserName("");
    BlinkApp.getApp().setPassword("");
    Intent localIntent = new Intent(this, SplashActivity.class);
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    finish();
  }
  
  protected void manageAccount()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("Title", getString(2131099915));
    localBundle.putStringArray("List", null);
    localBundle.putInt("Layout_id_key", 2130903144);
    localBundle.putString("Ok_Button_Label", getResources().getString(2131099944));
    localBundle.putBoolean("Has_extra_button_key", true);
    ChooseDialog localChooseDialog = new ChooseDialog();
    localChooseDialog.setArguments(localBundle);
    localChooseDialog.show(getSupportFragmentManager(), "ChooseDialog");
  }
  
  public void navigationDrawerDidClose()
  {
    ((HomeScreenFragment)getSupportFragmentManager().findFragmentByTag("home_screen_tag")).updateHomeScreenImmediately();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (BlinkApp.getApp().getLoggedIn()) {
      switch (paramInt1 & 0xFFFF)
      {
      }
    }
    for (;;)
    {
      return;
      this.mNavigationDrawerFragment.updateListView();
      getSupportFragmentManager().beginTransaction().replace(2131558643, HomeScreenFragment.newInstance(-1, this.mBooting)).commit();
      this.mBooting = false;
      continue;
      switch (paramInt1 & 0xFFFF)
      {
      case 104: 
      case 108: 
      default: 
        break;
      case 0: 
        this.mNavigationDrawerFragment.updateListView();
        getSupportFragmentManager().beginTransaction().replace(2131558643, HomeScreenFragment.newInstance(-1, this.mBooting)).commit();
        this.mBooting = false;
      }
    }
  }
  
  public void onBackPressed()
  {
    if (this.mNavigationDrawerFragment.isDrawerOpen()) {
      this.mNavigationDrawerFragment.closeDrawer();
    }
    for (;;)
    {
      return;
      FragmentManager localFragmentManager = getSupportFragmentManager();
      if ((localFragmentManager.getFragments() != null) && (localFragmentManager.getFragments().size() > 1) && (localFragmentManager.getBackStackEntryCount() > 0))
      {
        super.onBackPressed();
        refreshScreen();
        pickActionBar(0);
      }
      else
      {
        super.onBackPressed();
      }
    }
  }
  
  public void onCancelClicked(View paramView)
  {
    getSupportFragmentManager().popBackStack();
    paramView.setVisibility(4);
    pickActionBar(0);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903120);
    paramBundle = getIntent().getExtras();
    if (paramBundle != null) {
      this.mBooting = paramBundle.getBoolean("arg_app_is_bootin", false);
    }
    this.mNavigationDrawerFragment = ((NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(2131558694));
    this.mTitle = getTitle();
    pickActionBar(0);
    refreshScreen();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if (!this.mNavigationDrawerFragment.isDrawerOpen())
    {
      this.mMenu = paramMenu;
      getMenuInflater().inflate(2131623937, paramMenu);
      ((HomeScreenFragment)getSupportFragmentManager().findFragmentByTag("home_screen_tag")).updateHomeScreenImmediately();
    }
    for (;;)
    {
      return super.onCreateOptionsMenu(paramMenu);
      restoreActionBar();
      ((HomeScreenFragment)getSupportFragmentManager().findFragmentByTag("home_screen_tag")).cleanUpHandler();
    }
  }
  
  public void onDialogExtraButtonClick(DialogFragment paramDialogFragment)
  {
    paramDialogFragment = new ResetPasswordRequest();
    paramDialogFragment.setEmail(BlinkApp.getApp().getUserName());
    BlinkAPI.BlinkAPIRequest(null, null, paramDialogFragment, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (paramAnonymousBlinkError.response != null) {}
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = "Reset failed")
        {
          new AlertDialog.Builder(MainActivity.this).setTitle("Password Reset").setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).create().show();
          return;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        paramAnonymousBlinkData = ((MessageResponse)paramAnonymousBlinkData).getMessage();
        new AlertDialog.Builder(MainActivity.this).setTitle("Password Reset").setMessage(paramAnonymousBlinkData).setPositiveButton(2131099891, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            MainActivity.this.logMeOut();
          }
        }).create().show();
      }
    }, false);
  }
  
  public void onDialogListClick(DialogFragment paramDialogFragment, int paramInt) {}
  
  public void onDialogNegativeClick(DialogFragment paramDialogFragment) {}
  
  public void onDialogPositiveClick(DialogFragment paramDialogFragment)
  {
    paramDialogFragment = (TextView)((ChooseDialog)paramDialogFragment).getCustomView().findViewById(2131558756);
    final String str2 = BlinkApp.getApp().getUserName();
    final String str3 = paramDialogFragment.getText().toString();
    LoginRequest localLoginRequest;
    String str1;
    int i;
    if (validateInputs(str2, str3))
    {
      localLoginRequest = new LoginRequest();
      localLoginRequest.setEmail(str2);
      localLoginRequest.setPassword(str3);
      str1 = "?";
      i = 0;
      paramDialogFragment = str1;
    }
    try
    {
      PackageInfo localPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
      paramDialogFragment = str1;
      str1 = localPackageInfo.versionName;
      paramDialogFragment = str1;
      int j = localPackageInfo.versionCode;
      paramDialogFragment = str1;
      i = j;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    localLoginRequest.setClient_specifier(Build.MANUFACTURER + " " + Build.MODEL + " | " + String.valueOf(Build.VERSION.SDK_INT) + " | " + paramDialogFragment + " | " + i);
    localLoginRequest.setClient_version(paramDialogFragment);
    localLoginRequest.setClient_type("android");
    localLoginRequest.setClient_name(Build.MANUFACTURER + " " + Build.MODEL);
    if ((BlinkApp.getApp().getDeviceToken() != null) && (BlinkApp.getApp().getDeviceToken().length() > 0)) {
      localLoginRequest.setNotification_key(BlinkApp.getApp().getDeviceToken());
    }
    BlinkAPI.BlinkAPIRequest(null, null, localLoginRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (paramAnonymousBlinkError.response != null) {}
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = "Login error")
        {
          new AlertDialog.Builder(MainActivity.this).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, null).create().show();
          return;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        BlinkApp.getApp().setLoginAuthToken(((AuthToken)paramAnonymousBlinkData).getAuthtoken().getAuthtoken());
        BlinkApp.getApp().setUserName(str2);
        BlinkApp.getApp().setPassword(str3);
        BlinkApp.getApp().setLoggedIn(true);
        BlinkApp.getApp().setLastNetworkId("");
        BlinkApp.getApp().setLastCameraId("");
        paramAnonymousBlinkData = new Intent(MainActivity.this, ManageAccountActivity.class);
        MainActivity.this.startActivityForResult(paramAnonymousBlinkData, 100);
      }
    }, false);
    for (;;)
    {
      return;
      new AlertDialog.Builder(this).setTitle(getString(2131099861)).setMessage(getString(2131100009)).setPositiveButton(2131099891, null).create().show();
    }
  }
  
  public void onDoneClicked(View paramView)
  {
    final FragmentManager localFragmentManager = getSupportFragmentManager();
    final Fragment localFragment = localFragmentManager.findFragmentByTag("camera_settings_tag");
    paramView.setVisibility(4);
    this.mActionBarView.findViewById(2131558541).setVisibility(0);
    ((CameraSettingsFragment)localFragment).saveCameraSettings(new Runnable()
    {
      public void run()
      {
        localFragmentManager.popBackStack();
        localFragmentManager.beginTransaction().remove(localFragment).commit();
        MainActivity.this.pickActionBar(0);
        ((HomeScreenFragment)localFragmentManager.findFragmentByTag("home_screen_tag")).refresh();
      }
    });
  }
  
  public void onFragmentInteraction(int paramInt, BaseFragment.OnFragmentInteractionListener.InteractionAction paramInteractionAction, Object paramObject)
  {
    switch (paramInteractionAction)
    {
    default: 
      super.onFragmentInteraction(paramInt, paramInteractionAction, paramObject);
    }
    for (;;)
    {
      return;
      this.mNavigationDrawerFragment.updateListView();
      getSupportFragmentManager().beginTransaction().replace(2131558643, HomeScreenFragment.newInstance(-1, this.mBooting)).commit();
      this.mBooting = false;
    }
  }
  
  public void onLowMemory()
  {
    try
    {
      BlinkApp.getApp().getDiskLruCache().delete();
      super.onLowMemory();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void onNavigationDrawerItemSelected(int paramInt)
  {
    if (this.mNavigationDrawerFragment.isDrawerOpen()) {
      this.mNavigationDrawerFragment.closeDrawer();
    }
    Object localObject = getSupportFragmentManager();
    if (BlinkApp.getApp().getLoggedIn()) {
      switch (paramInt)
      {
      default: 
        if (!this.mBooting) {
          ((FragmentManager)localObject).beginTransaction().replace(2131558643, HomeScreenFragment.newInstance(-1, this.mBooting), "home_screen_tag").commit();
        }
        break;
      }
    }
    for (;;)
    {
      return;
      manageAccount();
      continue;
      startActivityForResult(new Intent(this, ManageSystemsActivity.class), 101);
      continue;
      startActivityForResult(new Intent(this, AboutBlinkActivity.class), 102);
      continue;
      sendFeedback();
      continue;
      localObject = new Intent("android.intent.action.VIEW", Uri.parse(getString(2131100017)));
      if (((Intent)localObject).resolveActivity(getPackageManager()) != null)
      {
        startActivity((Intent)localObject);
        continue;
        new AlertDialog.Builder(this).setTitle(getString(2131099875)).setMessage(getString(2131099746)).setPositiveButton(2131099891, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            MainActivity.this.logout();
          }
        }).setNegativeButton(2131099778, null).create().show();
        continue;
        startActivityForResult(new Intent(this, APITestActivity.class), 114);
        continue;
        switch (paramInt)
        {
        default: 
          break;
        case 1: 
          startActivityForResult(new Intent(this, LoginActivity.class), 106);
          break;
        case 2: 
          startActivityForResult(new Intent(this, CreateAccountActivity.class), 110);
          break;
        case 3: 
          startActivityForResult(new Intent(this, AboutBlinkActivity.class), 102);
          break;
        case 4: 
          sendFeedback();
          break;
        case 5: 
          localObject = new Intent("android.intent.action.VIEW", Uri.parse(getString(2131100017)));
          if (((Intent)localObject).resolveActivity(getPackageManager()) != null) {
            startActivity((Intent)localObject);
          }
          break;
        case 6: 
          startActivityForResult(new Intent(this, APITestActivity.class), 114);
        }
      }
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (boolean bool = super.onOptionsItemSelected(paramMenuItem);; bool = true)
    {
      return bool;
      paramMenuItem = (HomeScreenFragment)getSupportFragmentManager().findFragmentByTag("home_screen_tag");
      if (paramMenuItem != null) {
        paramMenuItem.startClipList();
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    pickActionBar(-1);
    refreshScreen();
  }
  
  public void onSectionAttached(int paramInt)
  {
    if (paramInt < 1) {
      this.mTitle = "";
    }
    for (;;)
    {
      return;
      paramInt--;
      String[] arrayOfString;
      if (this.mNavigationDrawerFragment.isSignedIn())
      {
        arrayOfString = getResources().getStringArray(2131427328);
        if (paramInt >= arrayOfString.length) {
          this.mTitle = "";
        } else {
          this.mTitle = arrayOfString[paramInt];
        }
      }
      else
      {
        arrayOfString = getResources().getStringArray(2131427329);
        if (paramInt >= arrayOfString.length) {
          this.mTitle = "";
        } else {
          this.mTitle = arrayOfString[paramInt];
        }
      }
    }
  }
  
  public void pickActionBar(int paramInt)
  {
    int i = 1;
    Object localObject;
    TextView localTextView1;
    if (paramInt < 0)
    {
      localObject = getSupportFragmentManager();
      paramInt = i;
      if (((FragmentManager)localObject).getBackStackEntryCount() > 0)
      {
        paramInt = i;
        if (((FragmentManager)localObject).getBackStackEntryAt(0).getName().equals("camera_settings_tag")) {
          paramInt = 0;
        }
      }
      if (paramInt == 0) {
        break label181;
      }
      if (this.mActionBarView != null)
      {
        localObject = (TextView)this.mActionBarView.findViewById(2131558539);
        localTextView1 = (TextView)this.mActionBarView.findViewById(2131558538);
        if (localObject != null) {
          ((TextView)localObject).setVisibility(8);
        }
        if (localTextView1 != null) {
          localTextView1.setVisibility(8);
        }
      }
      this.mActionBarView = null;
      this.mNavigationDrawerFragment.setUp(2131558694, (DrawerLayout)findViewById(2131558693));
      restoreActionBar();
      if (this.mMenu != null)
      {
        localObject = this.mMenu.findItem(2131558771);
        if (localObject != null) {
          ((MenuItem)localObject).setVisible(true);
        }
      }
    }
    for (;;)
    {
      return;
      if (paramInt == 0) {}
      for (paramInt = 1;; paramInt = 0) {
        break;
      }
      label181:
      this.mNavigationDrawerFragment.tearDown();
      this.mActionBarView = getLayoutInflater().inflate(2130903077, null);
      localObject = getSupportActionBar();
      ((ActionBar)localObject).setCustomView(this.mActionBarView);
      ((ActionBar)localObject).setDisplayOptions(16);
      this.mActionBarView.findViewById(2131558538).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MainActivity.this.onCancelClicked(paramAnonymousView);
        }
      });
      this.mActionBarView.findViewById(2131558539).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!OnClick.ok()) {}
          for (;;)
          {
            return;
            MainActivity.this.onDoneClicked(paramAnonymousView);
          }
        }
      });
      localTextView1 = (TextView)this.mActionBarView.findViewById(2131558539);
      TextView localTextView2 = (TextView)this.mActionBarView.findViewById(2131558538);
      localObject = (TextView)this.mActionBarView.findViewById(2131558488);
      localTextView1.setVisibility(0);
      localTextView2.setVisibility(0);
      ((TextView)localObject).setText(getString(2131099998));
      if (this.mMenu != null)
      {
        localObject = this.mMenu.findItem(2131558771);
        if (localObject != null) {
          ((MenuItem)localObject).setVisible(false);
        }
      }
    }
  }
  
  protected void refreshAllFragments()
  {
    if (!this.mNavigationDrawerFragment.isDetached()) {
      this.mNavigationDrawerFragment.updateListView();
    }
    FragmentManager localFragmentManager = getSupportFragmentManager();
    List localList = localFragmentManager.getFragments();
    int k = 0;
    int i = 0;
    while (i < localList.size())
    {
      int j = k;
      if ((localList.get(i) instanceof BaseFragment))
      {
        j = k;
        if (!((BaseFragment)localList.get(i)).isDetached())
        {
          ((BaseFragment)localList.get(i)).refresh();
          j = 1;
        }
      }
      i++;
      k = j;
    }
    if (k == 0)
    {
      localFragmentManager.beginTransaction().replace(2131558643, HomeScreenFragment.newInstance(-1, this.mBooting), "home_screen_tag").commit();
      this.mBooting = false;
    }
  }
  
  public void refreshScreen()
  {
    Object localObject = BlinkApp.getApp().getUserName();
    String str = BlinkApp.getApp().getPassword();
    if ((localObject == null) || (((String)localObject).isEmpty()) || (str == null) || (str.isEmpty())) {
      logMeOut();
    }
    for (;;)
    {
      return;
      LoginRequest localLoginRequest;
      if ((BlinkApp.getApp().getLoginAuthToken().isEmpty()) || (BlinkApp.getApp().hasLoginExpired()))
      {
        Log.i("MainActivity", "Auto logging in, login expired=" + BlinkApp.getApp().hasLoginExpired());
        localLoginRequest = new LoginRequest();
        localLoginRequest.setEmail((String)localObject);
        localLoginRequest.setPassword(str);
        str = "?";
        j = 0;
        localObject = str;
      }
      try
      {
        PackageInfo localPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        localObject = str;
        str = localPackageInfo.versionName;
        localObject = str;
        i = localPackageInfo.versionCode;
        localObject = str;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          int i = j;
        }
      }
      localLoginRequest.setClient_specifier(Build.MANUFACTURER + " " + Build.MODEL + " | " + String.valueOf(Build.VERSION.SDK_INT) + " | " + (String)localObject + " | " + i);
      localLoginRequest.setClient_version((String)localObject);
      localLoginRequest.setClient_type("android");
      localLoginRequest.setClient_name(Build.MANUFACTURER + " " + Build.MODEL);
      if ((BlinkApp.getApp().getDeviceToken() != null) && (BlinkApp.getApp().getDeviceToken().length() > 0)) {
        localLoginRequest.setNotification_key(BlinkApp.getApp().getDeviceToken());
      }
      BlinkAPI.BlinkAPIRequest(null, null, localLoginRequest, new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymousBlinkError)
        {
          BlinkApp.getApp().setLoginAuthToken("");
          MainActivity.this.mNavigationDrawerFragment.updateListView();
          MainActivity.this.finish();
        }
        
        public void onResult(BlinkData paramAnonymousBlinkData)
        {
          BlinkApp.getApp().setLoginAuthToken(((AuthToken)paramAnonymousBlinkData).getAuthtoken().getAuthtoken());
          MainActivity.this.refreshAllFragments();
        }
      }, false);
      continue;
      refreshAllFragments();
    }
  }
  
  public void restoreActionBar()
  {
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setNavigationMode(0);
    localActionBar.setDisplayShowTitleEnabled(false);
    localActionBar.setCustomView(2130903070);
    localActionBar.setHomeAsUpIndicator(2130837785);
    setActionBarTitle("");
  }
  
  public void setClipRollActionIcon(int paramInt)
  {
    if (this.mMenu != null)
    {
      MenuItem localMenuItem = this.mMenu.findItem(2131558771);
      if (localMenuItem != null) {
        localMenuItem.setIcon(paramInt);
      }
    }
  }
  
  public void startCameraSettings(int paramInt)
  {
    getSupportFragmentManager().beginTransaction().addToBackStack("camera_settings_tag").add(2131558643, CameraSettingsFragment.newInstance(-1, paramInt), "camera_settings_tag").commit();
    pickActionBar(1);
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */