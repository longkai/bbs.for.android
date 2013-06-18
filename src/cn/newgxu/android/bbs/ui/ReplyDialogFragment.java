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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cn.newgxu.android.bbs.R;
import cn.newgxu.android.bbs.provider.EntityProvider.User;

import com.actionbarsherlock.app.SherlockDialogFragment;

/**
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-24
 * @version 0.1
 */
public class ReplyDialogFragment extends SherlockDialogFragment {

	private static final String SAVED_REPLY_CONTENT = "content";
	private EditText replyContent;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater factory = LayoutInflater.from(getSherlockActivity());
		final View view = factory.inflate(R.layout.reply_dialog, null);
		replyContent = (EditText) view.findViewById(R.id.reply_content);
		if (savedInstanceState != null) {
			replyContent.setText(savedInstanceState.getString(SAVED_REPLY_CONTENT));
		}
        return new AlertDialog.Builder(getSherlockActivity())
            .setIcon(R.drawable.social_send_now)
            .setTitle(getArguments().getString(User.NICK))
            .setView(view)
            .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked cancel so do some stuff */
                }
            })
            .create();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (replyContent != null) {
			String content = replyContent.getText().toString();
			if (!TextUtils.isEmpty(content)) {
				outState.putString(SAVED_REPLY_CONTENT, content);
			}
		}
		super.onSaveInstanceState(outState);
	}

}
