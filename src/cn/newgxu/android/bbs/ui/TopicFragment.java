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

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.newgxu.android.bbs.R;
import cn.newgxu.android.bbs.app.BBSApplication;
import cn.newgxu.android.bbs.provider.EntityProvider;
import cn.newgxu.android.bbs.provider.EntityProvider.Topic;
import cn.newgxu.android.bbs.provider.EntityProvider.User;
import cn.newgxu.android.bbs.util.Consts;
import cn.newgxu.android.bbs.util.NewgxuUtils;

/**
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-23
 * @version 0.1
 */
public class TopicFragment extends SherlockFragment {

	private static final String TAG = TopicFragment.class.getSimpleName();
	private static final String REPLY_CONTENT = "reply_content";
	
	private BBSApplication app;
	private View topicView;
	private ActionBar actionBar;
	private WebView content;
	private TextView authorNick;
	private ImageView userIcon;
	private TextView statistics1;
//	private TextView statistics2;
	private TextView desc;
	private EditText sendReply;
	private ImageView sendIcon;
	
	private int authorUId;
	
	private Resources r;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.app = (BBSApplication) getActivity().getApplication();
		r = getResources();
		actionBar = getSherlockActivity().getSupportActionBar();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		topicView = inflater.inflate(R.layout.topic, container, false);
		authorNick = (TextView) topicView.findViewById(R.id.topic_author_nick);
		content = (WebView) topicView.findViewById(R.id.topic_content); 
		userIcon = (ImageView) topicView.findViewById(R.id.user_icon);
		statistics1 = (TextView) topicView.findViewById(R.id.topic_author_statistics1);
		sendReply = (EditText) topicView.findViewById(R.id.send_reply);
		sendIcon = (ImageView) topicView.findViewById(R.id.send_reply_icon);
		if (savedInstanceState != null) {
			sendReply.setText(savedInstanceState.getString("reply_content"));
		}
//		statistics2 = (TextView) topicView.findViewById(R.id.topic_author_statistics2);
		OnClickListener listener = (OnClickListener) getSherlockActivity();
		sendIcon.setOnClickListener(listener);
		topicView.findViewById(R.id.author).setOnClickListener(listener);
//		userIcon.setOnClickListener(this);
		desc = (TextView) topicView.findViewById(R.id.topic_author_desc);
		return this.topicView;
	}

	@Override
	public void onDestroy() {
		if (content != null) {
			content.clearView();
		}
		super.onDestroy();
	}

//	@Override
//	public void onPause() {
//		super.onPause();
//	}
	
	

	@Override
	public void onStart() {
		super.onStart();
		
		new FetchTopic().execute(getArguments().getInt(Consts.TID, 0));
//		new FetchTopic().execute("423358");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (this.sendReply != null) {
			Editable text = this.sendReply.getText();
			if (text != null) {
				outState.putString(REPLY_CONTENT, text.toString());
			}
		}
		super.onSaveInstanceState(outState);
	}

	private class FetchTopic extends AsyncTask<Integer, String, String> {

		private boolean connected;
		private String error;

		@Override
		protected String doInBackground(Integer... params) {
			String result = NewgxuUtils.httpGet(Consts.TOPIC_URL + params[0]);
			if (result != null) {
				publishProgress(result);
				return "ok";
			}
			return "no";
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
			}
		}

		@Override
		protected void onProgressUpdate(String... values) {
			Log.d(TAG, values[0]);
			try {
				JSONObject json = new JSONObject(values[0]);
				if (json.getInt(Consts.STATUS) == Consts.NO) {
					this.error = r.getString(R.string.error, json.getString(Consts.MSG), json.getString(Consts.REASON));
				} else {
					json = json.getJSONObject(Consts.TOPIC);

					authorNick.setText(json.getString(Topic.AUTHOR_NICK));
					JSONObject user = json.getJSONObject(Consts.AUTHOR);
					UrlImageViewHelper.setUrlDrawable(userIcon, user.getString(User.PORTRAIT).equals("null") ? Consts.DOMAIN + "/images/de_face/4.gif" : user.getString(User.PORTRAIT));
//					UrlImageViewHelper.setUrlDrawable(userIcon,  Consts.DOMAIN + "/images/de_face/4.gif");
					statistics1.setText(r.getString(R.string.user_statistics1, user.getInt(User.EXPERIENCE), user.getInt(User.TOPICS_COUNT), user.getInt(User.REPLIES_COUNT)));
					desc.setText(user.getString(User.DESC));
					actionBar.setTitle(json.getString(Topic.TITLE));
					actionBar.setSubtitle(DateUtils.getRelativeTimeSpanString(json.getLong(Topic.ADDED_TIME)) + "\t" + r.getString(R.string.reply_and_click_times, json.getInt(Topic.REPLIED_TIMES), json.getInt(Topic.CLICK_TIMES)));
					content.setBackgroundColor(0);
					content.loadDataWithBaseURL("", json.getString(Topic.CONTENT), "text/html", "utf-8", "");
					
					authorUId = user.getInt(EntityProvider.ID);
					topicView.findViewById(R.id.author).setTag(authorUId);
				}
			} catch (JSONException e) {
				Log.wtf(TAG, e);
				this.error = r.getString(R.string.error, e.getMessage(), e.getCause() == null ? "" : e.getCause().getMessage());
			}
			
		}

	}

//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.send_reply_icon:
//			Toast.makeText(getActivity(), "send click!", Toast.LENGTH_SHORT).show();
//			break;
//		default:
//			Toast.makeText(getActivity(), "user click!", Toast.LENGTH_SHORT).show();
//			FragmentManager fm = getSherlockActivity().getSupportFragmentManager();
////			if (fm.findFragmentByTag(Consts.USER) == null) {
////				UserFragment fragment = new UserFragment();
////				Bundle args = new Bundle();
////				args.putInt(Consts.UID, authorUId);
////				fm.beginTransaction().replace(R.layout.fragment_container, fragment, Consts.USER).addToBackStack(null).commit();
////			}
//			break;
//		}
//	}

}
