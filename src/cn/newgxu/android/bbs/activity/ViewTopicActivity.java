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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import cn.newgxu.android.bbs.R;
import cn.newgxu.android.bbs.app.BBSApplication;
import cn.newgxu.android.bbs.ui.RepliesFragment;
import cn.newgxu.android.bbs.ui.TopicFragment;
import cn.newgxu.android.bbs.ui.UserFragment;
import cn.newgxu.android.bbs.util.Consts;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * 查看帖子的活动。
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-23
 * @version 0.1
 */
public class ViewTopicActivity extends SherlockFragmentActivity implements OnTouchListener, OnClickListener {

	private static final String TAG = ViewTopicActivity.class.getSimpleName();
	
	private BBSApplication app;
	private FragmentManager fm;
	private int tid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_container);
		this.tid = getIntent().getIntExtra(Consts.TID, 0);
		this.app = (BBSApplication) getApplication();
//		this.detector = new GestureDetector(this, new SwipeGestrue());
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		this.fm = getSupportFragmentManager();
		if (fm.findFragmentByTag(Consts.TOPIC) == null) {
			Fragment fragment = new TopicFragment();
			fragment.setArguments(getIntent().getExtras());
			this.fm.beginTransaction().add(R.id.fragment_container, fragment, Consts.TOPIC).commit();
		}
	}
	

	private GestureDetector detector;
	
	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		SubMenu replyMenu = menu.addSubMenu("reply").setIcon(R.drawable.ic_action_send);
//		MenuItem menuItem = replyMenu.getItem();
//		menuItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
		getSupportMenuInflater().inflate(R.menu.view_topic, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Log.d(TAG, "homing...");
			finish();
			break;
		case R.id.view_replies:
			Toast.makeText(this, "view replies...", Toast.LENGTH_SHORT).show();
			if (this.fm.findFragmentByTag(Consts.REPLIES) == null) {
				RepliesFragment fragment = new RepliesFragment();
				Log.d(TAG, getIntent().getExtras().toString());
				fragment.setArguments(getIntent().getExtras());
				this.fm.beginTransaction().replace(R.id.fragment_container, fragment, Consts.REPLIES).addToBackStack(null).commit();
			}
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private class SwipeGestrue extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			Log.d(TAG, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			return super.onFling(e1, e2, velocityX, velocityY);
		}
		
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return this.detector.onTouchEvent(event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send_reply_icon:
			Toast.makeText(this, "send click!", Toast.LENGTH_SHORT).show();
			break;
		default:
			Toast.makeText(this, "user click!", Toast.LENGTH_SHORT).show();
			FragmentManager fm = getSupportFragmentManager();
			if (fm.findFragmentByTag(Consts.USER) == null) {
				UserFragment fragment = new UserFragment();
				Bundle args = new Bundle();
				final String str = v.getTag().toString();
				Log.d(TAG, str);
				args.putInt(Consts.UID, Integer.parseInt(str));
				fragment.setArguments(args);
				getSupportActionBar().setTitle(R.string.user_info);
				getSupportActionBar().setSubtitle(null);
				fm.beginTransaction().replace(R.id.fragment_container, fragment, Consts.USER).addToBackStack(null).commit();
			}
			break;
		}
	}
	
}
