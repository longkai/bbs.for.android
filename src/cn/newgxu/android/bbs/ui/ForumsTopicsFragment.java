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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import cn.newgxu.android.bbs.R;
import cn.newgxu.android.bbs.activity.ViewTopicActivity;
import cn.newgxu.android.bbs.app.BBSApplication;
import cn.newgxu.android.bbs.provider.EntityProvider.Topic;
import cn.newgxu.android.bbs.util.Consts;
import cn.newgxu.android.bbs.util.L;
import cn.newgxu.android.bbs.util.NewgxuUtils;

import com.actionbarsherlock.app.SherlockListFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

/**
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-29
 * @version 0.1
 */
public class ForumsTopicsFragment extends SherlockListFragment implements OnRefreshListener<ListView>, OnLastItemVisibleListener {
	
	private static final String TAG = ForumsTopicsFragment.class.getSimpleName();
	
private static final String LAST_POS = "last_pos";
	
	private SimpleAdapter mAdapter;
	private LinkedList<Map<String, Object>> data;
	
	private BBSApplication app;
	private int mPostion;
	
	private ListView listView;
	private PullToRefreshListView refreshListView;
	
	private int forumId;
	private boolean isClassic;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.app = (BBSApplication) activity.getApplication();
		this.data = new LinkedList<Map<String,Object>>();
		this.forumId = getArguments().getInt(Consts.FID);
		this.isClassic = getArguments().getBoolean(Consts.IS_CLASSIC, false);
	}

	@Override
	public void onDestroyView() {
		data.clear();
		super.onDestroyView();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			this.mPostion = savedInstanceState.getInt(LAST_POS, 0);
		}
		Log.d(TAG, "on create pos: " + mPostion);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(getActivity(), String.format("postion: %d, id: %d", position, id), Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(getActivity(), ViewTopicActivity.class);
		Log.d(TAG, this.data.get(position - 1).get(Topic.TITLE).toString());
		intent.putExtra(Consts.TID, Integer.parseInt(this.data.get(position - 1).get(Topic.ID).toString()));
		getActivity().startActivity(intent);
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		refreshListView = (PullToRefreshListView) inflater.inflate(R.layout.pull_refresh_list, container, false);
		this.listView = refreshListView.getRefreshableView();
		refreshListView.setOnRefreshListener(this);
		refreshListView.setOnLastItemVisibleListener(this);
		
//		refreshListView.setOnRefreshListener(this);
		return refreshListView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle args = getArguments();
		new GetFromBBS().execute(Consts.DOMAIN + args.getString(Consts.URL));
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
//		if (getListView() != null) {
//			mPostion = getListView().getFirstVisiblePosition();
//			Log.d(TAG, "on saved pos: " + mPostion);
//			outState.putInt(LAST_POS, mPostion);
//		}
		super.onSaveInstanceState(outState);
	}
	
	private class GetFromBBS extends AsyncTask<String, String, String> {

		private boolean connected;
		
		@Override
		protected String doInBackground(String... params) {
			if (connected) {
				String result = NewgxuUtils.httpGet(params[0]);
				publishProgress(result);
				return "ok";
			} else {
				return "no";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			if (!connected) {
				Toast.makeText(getActivity(), R.string.network_down, Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onPreExecute() {
			if (app.connected()) {
				connected = true;
			}
		}

		@Override
		protected void onProgressUpdate(String... values) {
			if (!connected) {
				return;
			}
			try {
				JSONArray array = new JSONObject(values[0]).getJSONArray(Consts.TOPICS);
				for (int i = 0; i < array.length(); i++) {
					JSONObject json = array.getJSONObject(i);
					data.add(topic2Map(json));
				}
				mAdapter = new SimpleAdapter(getActivity(), data, R.layout.topic_row,
						new String[] {Topic.TITLE, Topic.ADDED_TIME, Topic.CLICK_TIMES, Topic.REPLIED_TIMES, Topic.LAST_REPLIED_TIME, Topic.LAST_REPLIED_USER_NICK, Topic.AUTHOR_NICK},
						new int[] {R.id.topic_title, R.id.topic_added_time, R.id.topic_click_times, R.id.totpic_replied_times, R.id.topic_last_reply_time, R.id.topic_last_reply_usernick, R.id.topic_author_nick});
				setListAdapter(mAdapter);
//				setSelection(mPostion);
			} catch (JSONException e) {
				Log.wtf(TAG, e);
			}
		}
		
	}
	
	private class RefreshOrLoadMore extends AsyncTask<Boolean, String, String> {

		private boolean connected;
		private String error;
		private boolean isRefresh;
		
		@Override
		protected String doInBackground(Boolean... params) {
			if (connected) {
				String url = null;
				isRefresh = params[0];
				if (isRefresh) {
					url = Consts.DOMAIN + String.format("/api/topics?type=%d&fid=%d&count=20&top_tid=%d", Topic.FORUM_REFRESH, forumId, data.getFirst().get(Topic.ID));
				} else {
					url = Consts.DOMAIN + String.format("/api/topics?type=%d&fid=%d&count=20&last_tid=%d", Topic.FORUM_FETCH_MORE, forumId, data.getLast().get(Topic.ID));
				}
				String result = NewgxuUtils.httpGet(url);
				Log.d(TAG, result);
				publishProgress(result);
				return result;
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
				Toast.makeText(getActivity(), R.string.network_down, Toast.LENGTH_SHORT).show();
			} else if (error != null) {
				Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onProgressUpdate(String... values) {
			JSONObject json;
			try {
				json = new JSONObject(values[0]);
				if (json.getInt(Consts.STATUS) == Consts.NO) {
					this.error = getResources().getString(R.string.error, json.getString(Consts.MSG), json.getString(Consts.REASON));
				} else {
					JSONArray array = json.getJSONArray("topics");
					int length = array.length();
					L.d(TAG, "length is: %d", length);
					for (int i = 0; i < length; i++) {
						JSONObject t = array.getJSONObject(i);
						Map<String, Object> map = topic2Map(t);
						if (isRefresh) {
							data.addFirst(map);
						} else {
							data.addLast(map);
						}
						mAdapter.notifyDataSetChanged();
					}
					Toast.makeText(getActivity(), getResources().getString(isRefresh ? R.string.updates_count : R.string.fetchs_count, length), Toast.LENGTH_SHORT).show();
				}
			} catch (JSONException e) {
				Log.wtf(TAG, e);
			} finally {
				refreshListView.onRefreshComplete();
			}
		}
		
	}
	

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		if (!isClassic) {
			Toast.makeText(getActivity(), "pull to refresh!", Toast.LENGTH_SHORT).show();
			new RefreshOrLoadMore().execute(true);
		} else {
			refreshListView.onRefreshComplete();
		}
	}

	@Override
	public void onLastItemVisible() {
		if (!isClassic) {
			Toast.makeText(getActivity(), R.string.fetching, Toast.LENGTH_SHORT).show();
			new RefreshOrLoadMore().execute(false);
		}
	}
	
	private Map<String, Object> topic2Map(JSONObject t) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Topic.ID, t.getInt(Topic.ID));
		map.put(Topic.ADDED_TIME, DateUtils.getRelativeTimeSpanString(t.getLong(Topic.ADDED_TIME)));
		map.put(Topic.CLICK_TIMES, t.getInt(Topic.CLICK_TIMES) + getResources().getString(R.string.click));
		map.put(Topic.LAST_REPLIED_TIME, DateUtils.getRelativeTimeSpanString(t.getLong(Topic.LAST_REPLIED_TIME)));
		map.put(Topic.REPLIED_TIMES, t.getInt(Topic.REPLIED_TIMES) + getResources().getString(R.string.reply));
		map.put(Topic.TITLE, t.getString(Topic.TITLE));
		map.put(Topic.AUTHOR_NICK, t.getString(Topic.AUTHOR_NICK));
		map.put(Topic.LAST_REPLIED_USER_NICK, t.getString(Topic.LAST_REPLIED_USER_NICK));
		return map;
	}

}
