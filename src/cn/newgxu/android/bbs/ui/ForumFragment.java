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

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.newgxu.android.bbs.R;
import cn.newgxu.android.bbs.app.BBSApplication;
import cn.newgxu.android.bbs.provider.EntityProvider;
import cn.newgxu.android.bbs.provider.EntityProvider.Forum;
import cn.newgxu.android.bbs.provider.ForumsProvider;
import cn.newgxu.android.bbs.util.Consts;
import cn.newgxu.android.bbs.util.NewgxuUtils;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * 板块信息界面片段。
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-24
 * @version 0.1
 */
public class ForumFragment extends SherlockFragment {

	private static final String TAG = ForumFragment.class.getSimpleName();

	private BBSApplication app;
	
	private TextView about;
	private TextView masters;
	private ListView listView;
	private ArrayAdapter<String> adapter;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.app = (BBSApplication) activity.getApplication();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.forum, container, false);
		about = (TextView) v.findViewById(R.id.about);
		masters = (TextView) v.findViewById(R.id.masters);
		listView = (ListView) v.findViewById(android.R.id.list);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		new GetForum().execute(getArguments().getInt(Consts.FID, -1));
	}

	private class GetForum extends AsyncTask<Integer, String, String> {

		private boolean connected;
		private String error;

		@Override
		protected String doInBackground(Integer... params) {
			if (connected) {
				String result = NewgxuUtils.httpGet(Consts.FORUM_URL + params[0]);
				publishProgress(result);
				return "ok";
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			if (app.connected()) {
				connected = true;
			}
		}

		@Override
		protected void onPostExecute(String result) {
			if (!connected) {
				Toast.makeText(getSherlockActivity(), getResources().getString(R.string.network_down), Toast.LENGTH_SHORT).show();
			} else if (error != null) {
				Toast.makeText(getSherlockActivity(), error, Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onProgressUpdate(String... values) {
			if (connected) {
				try {
					JSONObject json = new JSONObject(values[0]);
					final Resources r = getResources();
					if (json.getInt(Consts.STATUS) == Consts.NO) {
						this.error = r.getString(R.string.error, json.getString(Consts.MSG), json.getString(Consts.REASON));
					} else {
						List<String> data = new ArrayList<String>();
						JSONObject f = json.getJSONObject(Consts.FORUM);
						data.add(r.getString(R.string.hot, f.getBoolean(Forum.HOT) ? r.getString(R.string.yes): r.getString(R.string.no)));
						data.add(r.getString(R.string.total_topics_count, f.getInt(Forum.TOTAL_TOPICS_COUNT)));
						data.add(r.getString(R.string.fantasy_count, f.getInt(Forum.FANTASY_COUNT)));
						data.add(r.getString(R.string.money_per_topic, f.getInt(Forum.MONEY_PER_TOPIC)));
						data.add(r.getString(R.string.money_per_reply, f.getInt(Forum.MONEY_PER_REPLY)));
						data.add(r.getString(R.string.exp_per_topic, f.getInt(Forum.EXP_PER_TOPIC)));
						data.add(r.getString(R.string.exp_per_reply, f.getInt(Forum.EXP_PER_REPLY)));
						
						about.setText(f.getString(Forum.ABOUT));
						masters.setText(r.getString(R.string.master, f.getString(Forum.MASTERS)));
						adapter = new ArrayAdapter<String>(getSherlockActivity(), android.R.layout.simple_list_item_1, data);
						listView.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					}
					
				} catch (JSONException e) {
					Log.wtf(TAG, e);
				}
			}
		}

	}

}
