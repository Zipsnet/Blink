package com.immediasemi.blink.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class HeaderListView
  extends RelativeLayout
{
  private static final int FADE_DELAY = 1000;
  private static final int FADE_DURATION = 2000;
  private SectionAdapter mAdapter;
  private AbsListView.OnScrollListener mExternalOnScrollListener;
  private RelativeLayout mHeader;
  private View mHeaderConvertView;
  private InternalListView mListView;
  private FrameLayout mScrollView;
  
  public HeaderListView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }
  
  public HeaderListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  private float dpToPx(float paramFloat)
  {
    return TypedValue.applyDimension(1, paramFloat, getContext().getResources().getDisplayMetrics());
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    this.mListView = new InternalListView(getContext(), paramAttributeSet);
    paramAttributeSet = new RelativeLayout.LayoutParams(-1, -1);
    paramAttributeSet.addRule(10);
    this.mListView.setLayoutParams(paramAttributeSet);
    this.mListView.setOnScrollListener(new HeaderListViewOnScrollListener(null));
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (HeaderListView.this.mAdapter != null) {
          HeaderListView.this.mAdapter.onItemClick(paramAnonymousAdapterView, paramAnonymousView, paramAnonymousInt, paramAnonymousLong);
        }
      }
    });
    addView(this.mListView);
    this.mHeader = new RelativeLayout(getContext());
    paramAttributeSet = new RelativeLayout.LayoutParams(-1, -2);
    paramAttributeSet.addRule(10);
    this.mHeader.setLayoutParams(paramAttributeSet);
    this.mHeader.setGravity(80);
    addView(this.mHeader);
    paramAttributeSet = getResources().getDrawable(2130837794);
    this.mScrollView = new FrameLayout(getContext());
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramAttributeSet.getIntrinsicWidth(), -1);
    localLayoutParams.addRule(11);
    localLayoutParams.rightMargin = ((int)dpToPx(2.0F));
    this.mScrollView.setLayoutParams(localLayoutParams);
    paramContext = new ImageView(paramContext);
    paramContext.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    paramContext.setImageDrawable(paramAttributeSet);
    paramContext.setScaleType(ImageView.ScaleType.FIT_XY);
    this.mScrollView.addView(paramContext);
    this.mScrollView.setVisibility(4);
    addView(this.mScrollView);
  }
  
  public void addHeaderView(View paramView)
  {
    this.mListView.addHeaderView(paramView);
  }
  
  public ListView getListView()
  {
    return this.mListView;
  }
  
  public void setAdapter(SectionAdapter paramSectionAdapter)
  {
    this.mAdapter = paramSectionAdapter;
    this.mListView.setAdapter(paramSectionAdapter);
  }
  
  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    this.mExternalOnScrollListener = paramOnScrollListener;
  }
  
  private class HeaderListViewOnScrollListener
    implements AbsListView.OnScrollListener
  {
    private int actualSection = 0;
    private boolean didScroll = false;
    private int direction = 0;
    private boolean doneMeasuring = false;
    private AlphaAnimation fadeOut = new AlphaAnimation(1.0F, 0.0F);
    private int lastResetSection = -1;
    private View next;
    private int nextH;
    private boolean noHeaderUpToHeader = false;
    private int prevH;
    private View previous;
    private int previousFirstVisibleItem = -1;
    private boolean scrollingStart = false;
    
    private HeaderListViewOnScrollListener() {}
    
    private void addSectionHeader(int paramInt)
    {
      if (HeaderListView.this.mHeader.getChildAt(0) != null) {
        HeaderListView.this.mHeader.removeViewAt(0);
      }
      if (HeaderListView.this.mAdapter.hasSectionHeaderView(paramInt))
      {
        HeaderListView.access$602(HeaderListView.this, HeaderListView.this.mAdapter.getSectionHeaderView(paramInt, HeaderListView.this.mHeaderConvertView, HeaderListView.this.mHeader));
        HeaderListView.this.mHeaderConvertView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        HeaderListView.this.mHeaderConvertView.measure(View.MeasureSpec.makeMeasureSpec(HeaderListView.this.mHeader.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        HeaderListView.this.mHeader.getLayoutParams().height = HeaderListView.this.mHeaderConvertView.getMeasuredHeight();
        HeaderListView.this.mHeaderConvertView.scrollTo(0, 0);
        HeaderListView.this.mHeader.scrollTo(0, 0);
        HeaderListView.this.mHeader.addView(HeaderListView.this.mHeaderConvertView, 0);
      }
      for (;;)
      {
        HeaderListView.this.mScrollView.bringToFront();
        return;
        HeaderListView.this.mHeader.getLayoutParams().height = 0;
        HeaderListView.this.mHeader.scrollTo(0, 0);
      }
    }
    
    private int getRealFirstVisibleItem(int paramInt1, int paramInt2)
    {
      if (paramInt2 == 0) {}
      int j;
      for (paramInt1 = -1;; paramInt1 = Math.max(paramInt1, paramInt1 + j - 1))
      {
        return paramInt1;
        int i = HeaderListView.this.mListView.getChildAt(0).getTop();
        for (j = 0; (j < paramInt2) && (i < HeaderListView.this.mHeader.getHeight()); j++) {
          i += HeaderListView.this.mListView.getChildAt(j).getHeight();
        }
      }
    }
    
    private void resetHeader(int paramInt)
    {
      this.scrollingStart = false;
      addSectionHeader(paramInt);
      HeaderListView.this.mHeader.requestLayout();
      this.lastResetSection = paramInt;
    }
    
    private void setMeasurements(int paramInt1, int paramInt2)
    {
      boolean bool2 = false;
      if (this.direction > 0)
      {
        if (paramInt1 >= paramInt2)
        {
          paramInt1 = HeaderListView.this.mListView.getChildAt(paramInt1 - paramInt2).getMeasuredHeight();
          this.nextH = paramInt1;
        }
      }
      else
      {
        this.previous = HeaderListView.this.mHeader.getChildAt(0);
        if (this.previous == null) {
          break label212;
        }
        paramInt1 = this.previous.getMeasuredHeight();
        label67:
        this.prevH = paramInt1;
        if (this.direction < 0)
        {
          if (this.lastResetSection != this.actualSection - 1)
          {
            addSectionHeader(Math.max(0, this.actualSection - 1));
            this.next = HeaderListView.this.mHeader.getChildAt(0);
          }
          if (HeaderListView.this.mHeader.getChildCount() <= 0) {
            break label226;
          }
        }
      }
      label212:
      label226:
      for (paramInt1 = HeaderListView.this.mHeader.getChildAt(0).getMeasuredHeight();; paramInt1 = 0)
      {
        this.nextH = paramInt1;
        HeaderListView.this.mHeader.scrollTo(0, this.prevH);
        boolean bool1 = bool2;
        if (this.previous != null)
        {
          bool1 = bool2;
          if (this.prevH > 0)
          {
            bool1 = bool2;
            if (this.nextH > 0) {
              bool1 = true;
            }
          }
        }
        this.doneMeasuring = bool1;
        return;
        paramInt1 = 0;
        break;
        paramInt1 = HeaderListView.this.mHeader.getHeight();
        break label67;
      }
    }
    
    private void startScrolling()
    {
      this.scrollingStart = true;
      this.doneMeasuring = false;
      this.lastResetSection = -1;
    }
    
    private void updateScrollBar()
    {
      int m;
      int k;
      FrameLayout localFrameLayout;
      if ((HeaderListView.this.mHeader != null) && (HeaderListView.this.mListView != null) && (HeaderListView.this.mScrollView != null))
      {
        m = HeaderListView.this.mListView.computeVerticalScrollOffset();
        j = HeaderListView.this.mListView.computeVerticalScrollRange();
        k = HeaderListView.this.mListView.computeVerticalScrollExtent();
        localFrameLayout = HeaderListView.this.mScrollView;
        if (k < j) {
          break label92;
        }
      }
      label92:
      for (int i = 4;; i = 0)
      {
        localFrameLayout.setVisibility(i);
        if (k < j) {
          break;
        }
        return;
      }
      if (j == 0)
      {
        i = HeaderListView.this.mListView.getHeight();
        label112:
        if (j != 0) {
          break label221;
        }
      }
      label221:
      for (int j = 0;; j = HeaderListView.this.mListView.getHeight() - HeaderListView.this.mListView.getHeight() * (m + k) / j)
      {
        HeaderListView.this.mScrollView.setPadding(0, i, 0, j);
        this.fadeOut.reset();
        this.fadeOut.setFillBefore(true);
        this.fadeOut.setFillAfter(true);
        this.fadeOut.setStartOffset(1000L);
        this.fadeOut.setDuration(2000L);
        HeaderListView.this.mScrollView.clearAnimation();
        HeaderListView.this.mScrollView.startAnimation(this.fadeOut);
        break;
        i = HeaderListView.this.mListView.getHeight() * m / j;
        break label112;
      }
    }
    
    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
    {
      if (HeaderListView.this.mExternalOnScrollListener != null) {
        HeaderListView.this.mExternalOnScrollListener.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
      }
      if (!this.didScroll) {}
      int i;
      for (;;)
      {
        return;
        i = paramInt1 - HeaderListView.this.mListView.getHeaderViewsCount();
        if (i >= 0) {
          break;
        }
        HeaderListView.this.mHeader.removeAllViews();
      }
      updateScrollBar();
      if ((paramInt2 > 0) && (i == 0) && (HeaderListView.this.mHeader.getChildAt(0) == null))
      {
        addSectionHeader(0);
        this.lastResetSection = 0;
      }
      int j = getRealFirstVisibleItem(i, paramInt2);
      boolean bool2;
      if ((paramInt3 > 0) && (this.previousFirstVisibleItem != j))
      {
        this.direction = (j - this.previousFirstVisibleItem);
        this.actualSection = HeaderListView.this.mAdapter.getSection(j);
        bool2 = HeaderListView.this.mAdapter.isSectionHeader(j);
        boolean bool4 = HeaderListView.this.mAdapter.hasSectionHeaderView(this.actualSection - 1);
        boolean bool1 = HeaderListView.this.mAdapter.hasSectionHeaderView(this.actualSection + 1);
        boolean bool3 = HeaderListView.this.mAdapter.hasSectionHeaderView(this.actualSection);
        if (HeaderListView.this.mAdapter.getRowInSection(j) == HeaderListView.this.mAdapter.numberOfRows(this.actualSection) - 1)
        {
          paramInt2 = 1;
          label253:
          if (HeaderListView.this.mAdapter.numberOfRows(this.actualSection - 1) <= 0) {
            break label742;
          }
          paramInt1 = 1;
          label274:
          if (HeaderListView.this.mAdapter.getRowInSection(j) != 0) {
            break label747;
          }
          paramInt3 = 1;
          label292:
          if ((paramInt3 == 0) || (bool3) || (!bool4) || (j == i)) {
            break label753;
          }
          paramInt3 = 1;
          label317:
          if ((paramInt2 == 0) || (!bool3) || (bool1) || (j != i) || (Math.abs(HeaderListView.this.mListView.getChildAt(0).getTop()) < HeaderListView.this.mListView.getChildAt(0).getHeight() / 2)) {
            break label759;
          }
          paramInt2 = 1;
          label376:
          this.noHeaderUpToHeader = false;
          if ((!bool2) || (bool4) || (i < 0)) {
            break label772;
          }
          if (this.direction >= 0) {
            break label764;
          }
          paramInt1 = this.actualSection - 1;
          label410:
          resetHeader(paramInt1);
          label415:
          this.previousFirstVisibleItem = j;
        }
      }
      else if (this.scrollingStart)
      {
        if (j < i) {
          break label842;
        }
        paramInt1 = HeaderListView.this.mListView.getChildAt(j - i).getTop();
        label454:
        if (!this.doneMeasuring) {
          setMeasurements(j, i);
        }
        if (!this.doneMeasuring) {
          break label863;
        }
        int k = this.prevH;
        j = this.nextH;
        i = this.direction;
        paramInt3 = Math.abs(paramInt1);
        if (this.direction >= 0) {
          break label847;
        }
        paramInt2 = this.nextH;
        label512:
        paramInt3 = paramInt3 * ((k - j) * i) / paramInt2;
        if (this.direction <= 0) {
          break label855;
        }
        paramInt2 = this.nextH;
        label539:
        paramInt2 = paramInt3 + paramInt2;
        label544:
        HeaderListView.this.mHeader.scrollTo(0, -Math.min(0, paramInt1 - paramInt2));
        if ((this.doneMeasuring) && (paramInt2 != HeaderListView.this.mHeader.getLayoutParams().height)) {
          if (this.direction >= 0) {
            break label868;
          }
        }
      }
      label742:
      label747:
      label753:
      label759:
      label764:
      label772:
      label842:
      label847:
      label855:
      label863:
      label868:
      for (paramAbsListView = this.next.getLayoutParams();; paramAbsListView = this.previous.getLayoutParams())
      {
        paramAbsListView = (RelativeLayout.LayoutParams)paramAbsListView;
        paramAbsListView.topMargin = (paramInt2 - paramAbsListView.height);
        HeaderListView.this.mHeader.getLayoutParams().height = paramInt2;
        HeaderListView.this.mHeader.requestLayout();
        if (!this.noHeaderUpToHeader) {
          break;
        }
        if (this.lastResetSection != this.actualSection)
        {
          addSectionHeader(this.actualSection);
          this.lastResetSection = (this.actualSection + 1);
        }
        HeaderListView.this.mHeader.scrollTo(0, HeaderListView.this.mHeader.getLayoutParams().height - (HeaderListView.this.mListView.getChildAt(0).getHeight() + HeaderListView.this.mListView.getChildAt(0).getTop()));
        break;
        paramInt2 = 0;
        break label253;
        paramInt1 = 0;
        break label274;
        paramInt3 = 0;
        break label292;
        paramInt3 = 0;
        break label317;
        paramInt2 = 0;
        break label376;
        paramInt1 = this.actualSection;
        break label410;
        if (((bool2) && (i > 0)) || (paramInt3 != 0))
        {
          if (paramInt1 == 0) {
            resetHeader(this.actualSection - 1);
          }
          startScrolling();
          break label415;
        }
        if (paramInt2 != 0)
        {
          this.noHeaderUpToHeader = true;
          break label415;
        }
        if (this.lastResetSection == this.actualSection) {
          break label415;
        }
        resetHeader(this.actualSection);
        break label415;
        paramInt1 = 0;
        break label454;
        paramInt2 = this.prevH;
        break label512;
        paramInt2 = this.prevH;
        break label539;
        paramInt2 = 0;
        break label544;
      }
    }
    
    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
      if (HeaderListView.this.mExternalOnScrollListener != null) {
        HeaderListView.this.mExternalOnScrollListener.onScrollStateChanged(paramAbsListView, paramInt);
      }
      this.didScroll = true;
    }
  }
  
  protected class InternalListView
    extends ListView
  {
    public InternalListView(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    protected int computeVerticalScrollExtent()
    {
      return super.computeVerticalScrollExtent();
    }
    
    protected int computeVerticalScrollOffset()
    {
      return super.computeVerticalScrollOffset();
    }
    
    protected int computeVerticalScrollRange()
    {
      return super.computeVerticalScrollRange();
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/HeaderListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */