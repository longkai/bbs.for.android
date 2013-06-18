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

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import cn.newgxu.android.bbs.R;
import cn.newgxu.android.bbs.app.BBSApplication;
import cn.newgxu.android.bbs.provider.EntityProvider.User;
import cn.newgxu.android.bbs.util.Consts;
import cn.newgxu.android.bbs.util.NewgxuUtils;

import com.actionbarsherlock.app.SherlockFragment;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

/**
 * 用户信息界面片段。
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-25
 * @version 0.1
 */
public class UserFragment extends SherlockFragment {

	private static final String TAG = UserFragment.class.getSimpleName();

	private BBSApplication app;

	private Button powers;
	private Button sex_and_qq;
	private Button desc;
	private Button exp_and_xdb;
	private Button nick;
	private Button title_and_register_time;
	private Button statistics;
	private Button honors;
	private ImageView icon;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		app = (BBSApplication) activity.getApplication();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.user, container, false);
		powers = (Button) v.findViewById(R.id.powers);
		sex_and_qq = (Button) v.findViewById(R.id.sex_and_qq);
		desc = (Button) v.findViewById(R.id.desc);
		exp_and_xdb = (Button) v.findViewById(R.id.exp_and_xdb);
		nick = (Button) v.findViewById(R.id.nick);
		title_and_register_time = (Button) v
				.findViewById(R.id.title_and_register_time);
		statistics = (Button) v.findViewById(R.id.statistics);
		honors = (Button) v.findViewById(R.id.honors);
		icon = (ImageView) v.findViewById(R.id.icon);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		new LoadUser().execute(getArguments().getInt(Consts.UID,
				Integer.MIN_VALUE));
	}

	private class LoadUser extends AsyncTask<Integer, String, String> {

		private boolean connected;
		private String error;

		@Override
		protected String doInBackground(Integer... params) {
			if (connected) {
				String data = NewgxuUtils.httpGet(Consts.USER_URL + params[0]);
				publishProgress(data);
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
				final Resources r = getResources();
				try {
					Log.i(TAG, values[0]);
					JSONObject json = new JSONObject(values[0]);
					if (json.getInt(Consts.STATUS) == Consts.NO) {
						this.error = r.getString(R.string.error, json.getString(Consts.MSG), json.getString(Consts.REASON));
					} else {
						JSONObject u = json.getJSONObject(Consts.USER);
						powers.setText(r.getString(R.string.current_and_max_power, u.getInt(User.CURRENT_POWER), u.getInt(User.MAX_POWER)));
						sex_and_qq.setText(r.getString(R.string.sex_and_qq, u.getBoolean(User.SEX) ? r.getString(R.string.woman) : r.getString(R.string.man), u.getString(User.QQ)));
						desc.setText(TextUtils.isEmpty(u.getString(User.DESC).trim()) ? r.getString(R.string.no_desc) : Html.fromHtml(u.getString(User.DESC)));
						exp_and_xdb.setText(r.getString(R.string.exp_and_xdb, u.getInt(User.EXPERIENCE), u.getInt(User.XDB)));
						nick.setText(u.getString(User.NICK));

						String title = u.getString(User.TITLE);
						title = TextUtils.isEmpty(title) ? r.getString(R.string.no_title) : title;
						title += "\n";
						title_and_register_time.setText(r.getString(R.string.title_and_register_time, title, DateUtils.getRelativeTimeSpanString(u.getLong(User.REGISTER_TIME))));
						
						statistics.setText(r.getString(R.string.statistics, u.getInt(User.TOPICS_COUNT), u.getInt(User.REPLIES_COUNT), u.getInt(User.FANTASTIC_COUNT)));
						honors.setText(TextUtils.isEmpty(u.getString(User.HONORS).trim()) ? r.getString(R.string.no_honor) : u.getString(User.HONORS));
						UrlImageViewHelper.setUrlDrawable(icon, u.getString(User.PORTRAIT));
					}
				} catch (JSONException e) {
					Log.wtf(TAG, e);
				}
			}
		}

	}

}
