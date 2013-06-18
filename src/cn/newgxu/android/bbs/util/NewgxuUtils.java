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
package cn.newgxu.android.bbs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * 全局工具类.
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-21
 * @version 0.1
 */
public class NewgxuUtils {

	private static final String TAG = NewgxuUtils.class.getSimpleName();

	public static final String httpGet(String url) {
		return get(url, null);
	}
	
	public static final String get(String url, String charset) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		get.setHeader("Accept", "application/json");
		HttpResponse response = null;
		HttpEntity entity = null;
		Scanner scanner = null;
		try {
			response = client.execute(get);
			entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				InputStream in = entity.getContent();
				scanner = new Scanner(in, charset == null ? "utf-8" : charset).useDelimiter("\\A");
				return scanner.hasNext() ? scanner.next() : null;
			}
		} catch (ClientProtocolException e) {
			Log.wtf(TAG, e);
		} catch (IOException e) {
			Log.wtf(TAG, e);
		} finally {
			try {
				if (entity != null) {
					entity.consumeContent();
				}
			} catch (IOException e) {
				Log.wtf(TAG, e);
			}
		}
		return null;
	}
	
	public static final boolean connected(Context context) {
		ConnectivityManager cm = (ConnectivityManager)
				context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

}
