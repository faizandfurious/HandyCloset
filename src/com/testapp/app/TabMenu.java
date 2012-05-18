package com.testapp.app;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class TabMenu extends TabActivity {
    /** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Resources res = getResources();
        TabHost tabHost = getTabHost();       
      
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("My Closet", res.getDrawable(R.drawable.ic_tabs_closet)).setContent(new Intent(this, Gallery.class)));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Add Clothing", res.getDrawable(R.drawable.ic_tabs_add)).setContent(new Intent(this, Camera.class)));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Match", res.getDrawable(R.drawable.ic_tabs_match)).setContent(new Intent(this, MatchClothing.class)));
        tabHost.setCurrentTab(0); 
    }
	
	
}