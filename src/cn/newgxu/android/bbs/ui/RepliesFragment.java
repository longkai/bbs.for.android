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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Html;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.newgxu.android.bbs.R;
import cn.newgxu.android.bbs.app.BBSApplication;
import cn.newgxu.android.bbs.provider.EntityProvider;
import cn.newgxu.android.bbs.provider.EntityProvider.Reply;
import cn.newgxu.android.bbs.provider.EntityProvider.User;
import cn.newgxu.android.bbs.util.Consts;
import cn.newgxu.android.bbs.util.NewgxuUtils;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

/**
 * 回复列表界面片段。
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-24
 * @version 0.1
 */
public class RepliesFragment extends ListFragment {

	private static final String TAG = RepliesFragment.class.getSimpleName();
	private static final String REPLY_INDEX = "reply_index";
	
	private BBSApplication app;
	
	private SimpleAdapter mAdapter;
	private List<Map<String, Object>> data;
	private int mPosition;
	
	public RepliesFragment() {}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		app = (BBSApplication) activity.getApplicationContext();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			mPosition = savedInstanceState.getInt(REPLY_INDEX);
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.d(TAG, "nimei");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListView().setSelection(mPosition);
		getListView().setClickable(true);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Bundle arguments = getArguments();
//		new fetchReplies().execute(arguments.getInt("tid", 0), arguments.getInt("last_rid", 0));
		new fetchReplies().execute(arguments.getInt(Consts.TID, 0), arguments.getInt(Consts.LAST_RID, 0));
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(REPLY_INDEX, mPosition);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroy() {
		if (data != null) {
			data.clear();
			data = null;
		}
		super.onDestroy();
	}
	
	private class fetchReplies extends AsyncTask<Integer, String, String> {
		
		private boolean connect;
		private String error;
		
		@Override
		protected String doInBackground(Integer... params) {
			if (connect) {
				String url = String.format("/api/replies?type=%d&count=20&tid=%d&last_rid=%d", Reply.LATEST, params[0], params[1]);
				String result = NewgxuUtils.httpGet(Consts.DOMAIN + url);
				Log.d(TAG, Consts.DOMAIN + url);
				Log.d(TAG, "result: " + result);
				publishProgress(result);
				return "ok";
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			if (app.connected()) {
				connect = true;
			}
		}

		@Override
		protected void onPostExecute(String result) {
			if (!connect) {
				Toast.makeText(getActivity(), getResources().getString(R.string.network_down), Toast.LENGTH_SHORT).show();
			} else if (error != null) {
				Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onProgressUpdate(String... values) {
			try {
				final Resources r = getResources();
				JSONObject json = new JSONObject(values[0]);
				if (json.getInt(Consts.STATUS) == Consts.NO) {
					error = r.getString(R.string.error, json.getString(Consts.MSG), json.getString(Consts.REASON));
				} else {
					JSONArray replies = json.getJSONArray(Consts.REPLIES);
					data = new ArrayList<Map<String,Object>>();
					JSONObject reply = null;
					Map<String, Object> m;
					for (int i = 0; i < replies.length(); i++) {
						reply = replies.getJSONObject(i);
						m = new HashMap<String, Object>();
						m.put(EntityProvider.ID, reply.getInt(EntityProvider.ID));
						m.put(Reply.CONTENT, reply.getString(Reply.CONTENT));
						m.put(Reply.POST_TIME, DateUtils.getRelativeTimeSpanString(reply.getLong(Reply.POST_TIME)));
						m.put(Reply.POST_UID, reply.getInt(Reply.POST_UID));
						m.put(Reply.POST_USER_NICK, reply.getString(Reply.POST_USER_NICK));
						m.put(Reply.POST_USER_PORTRAIT, reply.getString(Reply.POST_USER_PORTRAIT));
						m.put(Reply.TID, reply.getInt(Reply.TID));
						m.put(EntityProvider.INDEX, i + 1);
						data.add(m);
					}
					mAdapter = new RepliesAdapter(R.layout.reply_row, new String[] {Reply.CONTENT, Reply.POST_TIME, Reply.POST_USER_NICK, Reply.POST_USER_PORTRAIT, EntityProvider.INDEX}, new int[] {R.id.content, R.id.post_time, R.id.user_nick, R.id.user_icon, android.R.id.text1});
					setListAdapter(mAdapter);
				}
			} catch (JSONException e) {
				Log.wtf(TAG, e);
			}
		}
		
	}
	
	private class RepliesAdapter extends SimpleAdapter {
		
//		private List<? extends Map<String, ?>> data;
		
		public RepliesAdapter(int resource, String[] from, int[] to) {
			super(getActivity(), data, resource, from, to);
			inflater = LayoutInflater.from(getActivity());
		}

		private LayoutInflater inflater;

		private class ViewHolder {
			TextView userNick;
			TextView postTime;
			TextView index;
			TextView content;
			ImageView icon;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.reply_row, parent, false);
				holder = new ViewHolder();
				holder.userNick = (TextView) convertView.findViewById(R.id.user_nick);
				holder.postTime = (TextView) convertView.findViewById(R.id.post_time);
				holder.index = (TextView) convertView.findViewById(android.R.id.text1);
				holder.content = (TextView) convertView.findViewById(R.id.content);
				holder.icon = (ImageView) convertView.findViewById(R.id.user_icon);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			
			holder.userNick.setText(data.get(position).get(Reply.POST_USER_NICK).toString());
			holder.index.setText(data.get(position).get(EntityProvider.INDEX).toString());
			holder.postTime.setText(data.get(position).get(Reply.POST_TIME).toString());
			holder.content.setText(Html.fromHtml(data.get(position).get(Reply.CONTENT).toString()));
//			UrlImageViewHelper.setUrlDrawable(holder.icon, data.get(position).get(Reply.POST_USER_PORTRAIT).toString());
			String img = data.get(position).get(Reply.POST_USER_PORTRAIT).toString();
			Log.d(TAG, img);
//			img = Consts.DOMAIN + "/images/de_face/4.gif";
			UrlImageViewHelper.setUrlDrawable(holder.icon, img);
//			holder.icon.setImageBitmap(UrlImageViewHelper.getCachedBitmap(img));
			
			holder.icon.setTag(data.get(position).get(Reply.POST_UID));
			holder.icon.setOnClickListener((OnClickListener) getActivity());
			
			final String userNick = data.get(position).get(Reply.POST_USER_NICK).toString();
			holder.content.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "content click!", Toast.LENGTH_SHORT).show();
					ReplyDialogFragment fragment = new ReplyDialogFragment();
					Bundle args = new Bundle();
					args.putString(User.NICK, userNick);
					fragment.setArguments(args);
					fragment.show(getFragmentManager(), "reply_dialog");
				}
			});
			
			return convertView;
		}


	}

}
