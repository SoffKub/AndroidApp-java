package com.kubalski.sofia.kubalskitrading;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.kubalski.sofia.kubalskitrading.R;
import com.kubalski.sofia.kubalskitrading.fragments.AboutFragment;
import com.kubalski.sofia.kubalskitrading.fragments.CostFragment;
import com.kubalski.sofia.kubalskitrading.fragments.HomeFragment;
import com.kubalski.sofia.kubalskitrading.fragments.IncotermFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofia on 2016-05-15
 */
public class MainActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {


    ViewPager viewPager;
    TabHost tabHost;
    private int counter = 0;
    private String tempTabId = null;
    private boolean changed = false;
    private int previousTab = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPagerActivator();
        tabHostActivator();

        viewPager.setOnPageChangeListener(this);
    }

    private void tabHostActivator(){
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        String[] tabNames = {"Home", "For you", "Incoterm", "Cost"};


        for(int i = 0; i < tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeClass(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
        final TabWidget tw = (TabWidget)tabHost.findViewById(android.R.id.tabs);
        tabHost.getTabWidget().getChildTabViewAt(tabHost.getCurrentTab()).setBackgroundColor(Color.DKGRAY);
        for (int i = 0; i < tw.getChildCount(); ++i)
        {
            final View tabView = tw.getChildTabViewAt(i);
            final TextView tv = (TextView)tabView.findViewById(android.R.id.title);
            tv.setTextSize(9);
        }

        tabHost.setOnTabChangedListener(this);
    }
    private void viewPagerActivator(){
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        List<Fragment> lf = new ArrayList<Fragment>();
        lf.add(new HomeFragment());
        lf.add(new AboutFragment());
        lf.add(new IncotermFragment());
        lf.add(new CostFragment());

        com.kubalski.navid.kubalskitrading.FragmentPagerAdapter fpa = new com.kubalski.navid.kubalskitrading.FragmentPagerAdapter(getSupportFragmentManager(), lf);

        viewPager.setAdapter(fpa);
    }


    @Override
    public void onTabChanged(String tabId) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(tabHost.getCurrentTab());
            }
        }, 200);

        for(int i = 0; i < tabHost.getTabWidget().getChildCount(); i++){
            if (tabHost.getCurrentTab() != i){
                tabHost.getTabWidget().getChildTabViewAt(i).setBackgroundColor(Color.parseColor("#D3D3D3"));
            }
        }
        tabHost.getTabWidget().getChildTabViewAt(tabHost.getCurrentTab()).setBackgroundColor(Color.DKGRAY);
        counter++;
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public class FakeClass implements TabHost.TabContentFactory{

        Context context;
        public FakeClass(Context mContext){
        context = mContext;
        }

        @Override
        public View createTabContent(String tag) {

            View v = new View(context);
            v.setMinimumHeight(0);
            v.setMinimumWidth(0);

            return v;
        }
    }

}
