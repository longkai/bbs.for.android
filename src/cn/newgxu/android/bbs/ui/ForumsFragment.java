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
package cn.newgxu.android.bbs.ui;

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;
import cn.newgxu.android.bbs.R;
import cn.newgxu.android.bbs.activity.ForumActivity;
import cn.newgxu.android.bbs.app.BBSApplication;
import cn.newgxu.android.bbs.provider.ForumsProvider;

/**
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-21
 * @version 0.1
 */
public class ForumsFragment extends Fragment implements OnChildClickListener {

	private static final String TAG = ForumsFragment.class.getSimpleName();

	private BBSApplication app;

	private int mPostion;
	ExpandableListView listView;
	private ExpandableListAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.listView = new ExpandableListView(getActivity());
		this.app = (BBSApplication) getActivity().getApplication();

		this.mAdapter = new SimpleExpandableListAdapter(getActivity(),
				ForumsProvider.AREAS, android.R.layout.simple_expandable_list_item_1,
				new String[] { ForumsProvider.NAME, ForumsProvider.ABOUT }, new int[] { android.R.id.text1,
						android.R.id.text2 }, ForumsProvider.FORUMS,
				R.layout.forum_row, new String[] {
			ForumsProvider.NAME, ForumsProvider.ABOUT }, new int[] { android.R.id.text1,
						android.R.id.text2 });

		listView.setAdapter(mAdapter);
		listView.setOnChildClickListener(this);
		listView.setTextFilterEnabled(true);

		return listView;
	}


	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Map<String, String> fmap = ForumsProvider.getForumData(groupPosition, childPosition);
		Intent intent = new Intent(getActivity(), ForumActivity.class);
		intent.putExtra(ForumsProvider.FID, fmap.get(ForumsProvider.FID));
		intent.putExtra(ForumsProvider.NAME, fmap.get(ForumsProvider.NAME));
		intent.putExtra(ForumsProvider.ABOUT, fmap.get(ForumsProvider.ABOUT));
		startActivity(intent);
		return true;
	}

}
