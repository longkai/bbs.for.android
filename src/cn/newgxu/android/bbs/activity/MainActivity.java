/*
 * Copyright (c) 2001-2013 newgxu.cn <the original author or authors>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package cn.newgxu.android.bbs.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import cn.newgxu.android.bbs.R;
import cn.newgxu.android.bbs.app.BBSApplication;
import cn.newgxu.android.bbs.ui.BBSPagerAdapter;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

public class MainActivity extends SherlockFragmentActivity {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	private BBSApplication app;
	
	private FragmentManager fm;
	private ViewPager pager;
	private BBSPagerAdapter bbsPagerAdapter;
	private PagerTabStrip pagerTabStrip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		app = (BBSApplication) getApplication();
		
//		do the initialize things...
		pager = (ViewPager) findViewById(R.id.pager);
		fm = getSupportFragmentManager();
		bbsPagerAdapter = new BBSPagerAdapter(fm);
		pager.setAdapter(bbsPagerAdapter);
		pager.setCurrentItem(1);
		pagerTabStrip = (PagerTabStrip) pager.findViewById(R.id.pager_tab_strip);
		pagerTabStrip.setTabIndicatorColorResource(R.color.pager_title);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_main, menu);
		SubMenu subMenu = menu.addSubMenu("Action item");
		subMenu.addSubMenu("²Ù×÷1").setIcon(R.drawable.action_settings);
		subMenu.addSubMenu("²Ù×÷2");
		subMenu.addSubMenu("²Ù×÷3");
//		subMenu.addSubMenu(arg0, arg1, arg2, arg3)
		
		MenuItem menuItem = subMenu.getItem();
		menuItem.setIcon(R.drawable.ic_action_overflow);
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		
		SubMenu subMenu2 = menu.addSubMenu("item list");
		subMenu2.addSubMenu("item 1");
		subMenu2.addSubMenu("item 2");
		MenuItem menuItem2 = subMenu2.getItem();
		
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(
			MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
	
}
