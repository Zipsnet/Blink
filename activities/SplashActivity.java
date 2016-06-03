package com.immediasemi.blink.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkRegions;
import com.immediasemi.blink.gcm.BlinkGcmRegistrationIntentService;
import com.immediasemi.blink.models.RegionManager;
import com.immediasemi.blink.utils.ChooseDialog;
import com.immediasemi.blink.utils.ChooseDialog.NoticeDialogListener;
import com.immediasemi.blink.utils.OnClick;

public class SplashActivity
  extends BaseActivity
  implements ChooseDialog.NoticeDialogListener
{
  private BlinkRegions blinkRegions;
  private ChooseDialog mChooseDialog;
  private BroadcastReceiver mMessageReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ((paramAnonymousIntent.getAction().equals("BlinkRegionsDidUpdate")) && (SplashActivity.this.regionManager.isGetRegionsSuceeded())) {}
    }
  };
  private long mTimeStamp;
  private String[] mUrls;
  private boolean mWasLongClicked;
  private RegionManager regionManager;
  
  private void showChooseServer()
  {
    for (int i = this.mUrls.length - 1;; i--) {
      if ((i < 0) || (this.mUrls[i].equals(BlinkApp.getApp().getServerUrl())))
      {
        Bundle localBundle = new Bundle();
        localBundle.putString("Title", getString(2131099786));
        localBundle.putStringArray("List", this.mUrls);
        localBundle.putInt("Current_selection", i);
        localBundle.putInt("Layout_id_key", 0);
        localBundle.putString("Ok_Button_Label", getResources().getString(2131099891));
        this.mChooseDialog = new ChooseDialog();
        this.mChooseDialog.setArguments(localBundle);
        this.mChooseDialog.show(getSupportFragmentManager(), "ChooseDialog");
        return;
      }
    }
  }
  
  public void enterNewIP(String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Change this IP?");
    final EditText localEditText = new EditText(this);
    localEditText.setText(paramString);
    localEditText.setInputType(1);
    localBuilder.setView(localEditText);
    localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = localEditText.getText().toString();
        BlinkApp.getApp().setServerUrl(paramAnonymousDialogInterface);
        SplashActivity.this.mUrls[3] = paramAnonymousDialogInterface;
        SplashActivity.this.mChooseDialog.dismiss();
        SplashActivity.this.showChooseServer();
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.show();
  }
  
  protected void initActionBar()
  {
    getSupportActionBar().hide();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903145);
    this.mUrls = getResources().getStringArray(2131427333);
    BlinkApp.getApp().getServerUrl();
    if (BlinkApp.getApp().getDeviceToken() == null) {
      startService(new Intent(this, BlinkGcmRegistrationIntentService.class));
    }
    paramBundle = (Button)findViewById(2131558657);
    Button localButton = (Button)findViewById(2131558760);
    TextView localTextView = (TextView)findViewById(2131558762);
    try
    {
      PackageInfo localPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
      StringBuilder localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      String[] arrayOfString = (getString(2131100013) + localPackageInfo.versionName).split("\\.");
      localStringBuilder = new java/lang/StringBuilder;
      localStringBuilder.<init>();
      localTextView.setText(arrayOfString[0] + "." + arrayOfString[1] + "." + arrayOfString[2] + " (" + localPackageInfo.versionCode + ")");
      if ((getApplicationInfo().flags & 0x2) != 0)
      {
        i = 1;
        if (i != 0) {
          break label347;
        }
        BlinkApp.getApp().setServerUrlForDNSSubdomain(BlinkApp.getApp().getDnsSubdomain());
        paramBundle.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {}
            for (;;)
            {
              return;
              paramAnonymousView = new Intent(SplashActivity.this, LoginActivity.class);
              SplashActivity.this.startActivity(paramAnonymousView, null);
            }
          }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mMessageReceiver, new IntentFilter("BlinkRegionsDidUpdate"));
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mMessageReceiver, new IntentFilter("BlinkRegionsDidFailUpdate"));
        this.blinkRegions = new BlinkRegions();
        this.regionManager = this.blinkRegions.regionManager;
        localButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {}
            for (;;)
            {
              return;
              paramAnonymousView = new Intent(SplashActivity.this, CreateAccountActivity.class);
              paramAnonymousView.putExtra("regionManager", SplashActivity.this.regionManager);
              SplashActivity.this.startActivity(paramAnonymousView, null);
            }
          }
        });
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
        continue;
        int i = 0;
        continue;
        label347:
        View localView = findViewById(2131558758);
        localView.setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramAnonymousView)
          {
            SplashActivity.access$002(SplashActivity.this, System.currentTimeMillis());
            SplashActivity.access$102(SplashActivity.this, true);
            return true;
          }
        });
        localView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {}
            for (;;)
            {
              return;
              long l = System.currentTimeMillis();
              if (l - SplashActivity.this.mTimeStamp > 3000L) {
                SplashActivity.access$102(SplashActivity.this, false);
              }
              SplashActivity.access$002(SplashActivity.this, l);
              if (SplashActivity.this.mWasLongClicked)
              {
                SplashActivity.access$102(SplashActivity.this, false);
                SplashActivity.this.showChooseServer();
              }
            }
          }
        });
      }
    }
  }
  
  public void onDialogExtraButtonClick(DialogFragment paramDialogFragment) {}
  
  public void onDialogListClick(DialogFragment paramDialogFragment, int paramInt)
  {
    BlinkApp.getApp().setServerUrl(this.mUrls[paramInt]);
    if (paramInt == 3) {
      enterNewIP(this.mUrls[paramInt]);
    }
  }
  
  public void onDialogNegativeClick(DialogFragment paramDialogFragment) {}
  
  public void onDialogPositiveClick(DialogFragment paramDialogFragment) {}
  
  protected void onResume()
  {
    super.onResume();
    if (BlinkApp.getApp().getLoggedIn())
    {
      Intent localIntent = new Intent(this, MainActivity.class);
      Bundle localBundle = new Bundle();
      localBundle.putBoolean("arg_app_is_bootin", true);
      localIntent.putExtras(localBundle);
      startActivity(localIntent, null);
      finish();
    }
    for (;;)
    {
      return;
      this.blinkRegions.getRegions();
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/SplashActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */