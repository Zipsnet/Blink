package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.MainActivity;

public class NavigationDrawerFragment
  extends Fragment
{
  private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
  private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
  private static final String STATE_SIGNED_IN = "is_signed_in";
  static int[] drawer_signed_in_icons = { 0, 2130837737, 2130837745, 2130837718, 2130837734, 2130837728, 2130837750, 0 };
  static int[] drawer_signed_out_icons = { 0, 2130837748, 2130837745, 2130837718, 2130837734, 2130837728, 0 };
  private NavigationDrawerCallbacks mCallbacks;
  private int mCurrentSelectedPosition = 0;
  private DrawerLayout mDrawerLayout;
  private ListView mDrawerListView;
  private ActionBarDrawerToggle mDrawerToggle;
  private View mFragmentContainerView;
  private boolean mFromSavedInstanceState;
  private boolean mIsAttached = false;
  private boolean mUserLearnedDrawer;
  
  private ActionBar getActionBar()
  {
    return ((AppCompatActivity)getActivity()).getSupportActionBar();
  }
  
  private void selectItem(int paramInt)
  {
    this.mCurrentSelectedPosition = paramInt;
    if (this.mDrawerLayout != null) {
      this.mDrawerLayout.closeDrawer(this.mFragmentContainerView);
    }
    if (this.mCallbacks != null) {
      this.mCallbacks.onNavigationDrawerItemSelected(paramInt);
    }
  }
  
  private void showGlobalContextActionBar()
  {
    ActionBar localActionBar = getActionBar();
    localActionBar.setDisplayShowTitleEnabled(true);
    localActionBar.setNavigationMode(0);
    localActionBar.setTitle(2131099745);
  }
  
  public boolean closeDrawer()
  {
    if ((isDrawerOpen()) && (this.mDrawerLayout != null)) {
      this.mDrawerLayout.closeDrawer(this.mFragmentContainerView);
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isDrawerOpen()
  {
    if ((this.mDrawerLayout != null) && (this.mDrawerLayout.isDrawerOpen(this.mFragmentContainerView))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isSignedIn()
  {
    return BlinkApp.getApp().getLoggedIn();
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    setHasOptionsMenu(true);
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.mDrawerToggle.onConfigurationChanged(paramConfiguration);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mUserLearnedDrawer = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).getBoolean("navigation_drawer_learned", false);
    if (paramBundle != null)
    {
      this.mCurrentSelectedPosition = paramBundle.getInt("selected_navigation_drawer_position");
      BlinkApp.getApp().setLoggedIn(paramBundle.getBoolean("is_signed_in"));
      this.mFromSavedInstanceState = true;
    }
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    if ((this.mDrawerLayout != null) && (isDrawerOpen())) {}
    super.onCreateOptionsMenu(paramMenu, paramMenuInflater);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mDrawerListView = ((ListView)paramLayoutInflater.inflate(2130903111, paramViewGroup, false));
    this.mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        NavigationDrawerFragment.this.selectItem(paramAnonymousInt);
      }
    });
    return this.mDrawerListView;
  }
  
  public void onDetach()
  {
    super.onDetach();
    this.mCallbacks = null;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (this.mDrawerToggle.onOptionsItemSelected(paramMenuItem)) {}
    for (boolean bool = true;; bool = super.onOptionsItemSelected(paramMenuItem)) {
      return bool;
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("selected_navigation_drawer_position", this.mCurrentSelectedPosition);
    paramBundle.putBoolean("is_signed_in", isSignedIn());
  }
  
  public boolean openDrawer()
  {
    if ((!isDrawerOpen()) && (this.mDrawerLayout != null)) {
      this.mDrawerLayout.openDrawer(this.mFragmentContainerView);
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void setUp(int paramInt, DrawerLayout paramDrawerLayout)
  {
    this.mFragmentContainerView = getActivity().findViewById(paramInt);
    this.mDrawerLayout = paramDrawerLayout;
    this.mDrawerLayout.setDrawerShadow(2130837682, 8388611);
    paramDrawerLayout = getActionBar();
    paramDrawerLayout.setDisplayHomeAsUpEnabled(true);
    paramDrawerLayout.setHomeButtonEnabled(true);
    paramDrawerLayout.setHomeAsUpIndicator(2130837785);
    this.mDrawerToggle = new ActionBarDrawerToggle(getActivity(), this.mDrawerLayout, 2130837785, 2131099884, 2131099883)
    {
      public void onDrawerClosed(View paramAnonymousView)
      {
        super.onDrawerClosed(paramAnonymousView);
        if (!NavigationDrawerFragment.this.isAdded()) {}
        for (;;)
        {
          return;
          ((MainActivity)NavigationDrawerFragment.this.getActivity()).navigationDrawerDidClose();
          paramAnonymousView = ((AppCompatActivity)NavigationDrawerFragment.this.getActivity()).getSupportActionBar();
          paramAnonymousView.setDisplayHomeAsUpEnabled(true);
          paramAnonymousView.setHomeAsUpIndicator(2130837785);
          NavigationDrawerFragment.this.getActivity().supportInvalidateOptionsMenu();
        }
      }
      
      public void onDrawerOpened(View paramAnonymousView)
      {
        super.onDrawerOpened(paramAnonymousView);
        if (!NavigationDrawerFragment.this.isAdded()) {}
        for (;;)
        {
          return;
          if (!NavigationDrawerFragment.this.mUserLearnedDrawer)
          {
            NavigationDrawerFragment.access$102(NavigationDrawerFragment.this, true);
            PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putBoolean("navigation_drawer_learned", true).commit();
          }
          ((AppCompatActivity)NavigationDrawerFragment.this.getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
          NavigationDrawerFragment.this.getActivity().supportInvalidateOptionsMenu();
        }
      }
    };
    this.mDrawerLayout.post(new Runnable()
    {
      public void run()
      {
        NavigationDrawerFragment.this.mDrawerToggle.syncState();
      }
    });
    this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);
    updateListView();
    try
    {
      this.mCallbacks = ((NavigationDrawerCallbacks)getActivity());
      return;
    }
    catch (ClassCastException paramDrawerLayout)
    {
      throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
    }
  }
  
  public void tearDown()
  {
    this.mCallbacks = null;
  }
  
  public void updateListView()
  {
    String[] arrayOfString;
    if (BlinkApp.getApp().getLoggedIn()) {
      arrayOfString = getResources().getStringArray(2131427328);
    }
    for (int[] arrayOfInt = drawer_signed_in_icons;; arrayOfInt = drawer_signed_out_icons)
    {
      for (int i = 0; i < arrayOfInt.length; i++) {
        Log.i("tag", "[" + i + "] " + arrayOfInt[i]);
      }
      arrayOfString = getResources().getStringArray(2131427329);
    }
    this.mDrawerListView.setAdapter(new DrawerArrayAdapter(getActionBar().getThemedContext(), 2130903127, 16908308, arrayOfString, arrayOfInt));
    if (!BlinkApp.getApp().getLoggedIn()) {
      this.mDrawerLayout.openDrawer(this.mFragmentContainerView);
    }
  }
  
  protected class DrawerArrayAdapter
    extends ArrayAdapter
  {
    int mResId;
    int[] mResList;
    String[] mStrList;
    int mTitleId;
    
    public DrawerArrayAdapter(Context paramContext, int paramInt1, int paramInt2, String[] paramArrayOfString, int[] paramArrayOfInt)
    {
      super(paramInt1, paramInt2, paramArrayOfString);
      this.mResList = paramArrayOfInt;
      this.mStrList = paramArrayOfString;
      this.mResId = paramInt1;
      this.mTitleId = paramInt2;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView = paramView;
      if (paramView == null) {
        localView = View.inflate(NavigationDrawerFragment.this.getActivity(), this.mResId, null);
      }
      ((TextView)localView.findViewById(this.mTitleId)).setText(this.mStrList[paramInt]);
      int i = this.mResList[paramInt];
      Log.i("tag", "ResId at " + paramInt + " is " + i);
      if (i != 0) {
        ((ImageView)localView.findViewById(2131558487)).setImageResource(i);
      }
      paramView = super.getView(paramInt, localView, paramViewGroup);
      if (paramInt == 0)
      {
        paramView.setEnabled(false);
        paramView.setOnClickListener(null);
      }
      if (paramInt == this.mResList.length - 1)
      {
        paramView.setVisibility(8);
        paramView.setEnabled(false);
        paramView.setOnClickListener(null);
      }
      return paramView;
    }
  }
  
  public static abstract interface NavigationDrawerCallbacks
  {
    public abstract void onNavigationDrawerItemSelected(int paramInt);
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/NavigationDrawerFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */