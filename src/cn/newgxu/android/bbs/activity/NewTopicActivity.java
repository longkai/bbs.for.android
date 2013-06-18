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

import cn.newgxu.android.bbs.R;
import cn.newgxu.android.bbs.app.BBSApplication;
import cn.newgxu.android.bbs.ui.NewTopicFragment;

import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-29
 * @version 0.1
 */
public class NewTopicActivity extends SherlockFragmentActivity {

	private static final String TAG = NewTopicActivity.class.getSimpleName();
	
	private BBSApplication app;
	private FragmentManager fm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_container);
		
		this.app = (BBSApplication) getApplication();
		this.fm = getSupportFragmentManager();
		
		NewTopicFragment frag = new NewTopicFragment();
		fm.beginTransaction().add(R.id.fragment_container, frag, "x").commit();
		
		getSupportActionBar().setTitle("发表新主题！");
		getSupportActionBar().setIcon(R.drawable.face);
	}
	
}
