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
package cn.newgxu.android.bbs.provider;

/**
 * 一个简单的实体信息类，不是content provider哟。
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-22
 * @version 0.1
 */
public class EntityProvider {
	
	public static final String ID = "_ID";
	public static final String INDEX = "index";

	/**
	 * 帖子。
	 * @author im.longkai
	 *
	 */
	public static class Topic {
		public static final int		LATEST_TOPICS				= 0;
		public static final int		REFRESH						= 1;
		public static final int		FETCH_MORE					= 2;
		public static final int		FORUM_LATEST_TOPICS			= 3;
		public static final int		FORUM_REFRESH				= 4;
		public static final int		FORUM_FETCH_MORE			= 5;
		public static final int		CLASSICS					= -1;
		
		public static final String ID = "_ID";
		public static final String TITLE = "title";
		public static final String ADDED_TIME = "added_time";
		public static final String CLICK_TIMES = "click_times";
		public static final String AUTHOR_NICK = "author_nick";
		public static final String REPLIED_TIMES = "replied_times";
		public static final String LAST_REPLIED_TIME = "last_replied_time";
		public static final String LAST_REPLIED_USER_NICK = "last_replied_user_nick";
		public static final String CONTENT = "content";
		public static final String FORUM = "forum";
	}

	/**
	 * 网友
	 * @author im.longkai
	 *
	 */
	public static final class User {
		public static final String USERNAME = "username";
		public static final String NICK = "nick";
		public static final String TITLE = "title";
		public static final String PORTRAIT = "portrait";
		public static final String DESC = "desc";
		public static final String LOGIN_TIMES ="login_times";
		public static final String LAST_LOGIN_TIME = "last_login_time";
		public static final String SEX = "sex"; 
		public static final String BIRTHDAY = "birthday";
		public static final String EMAIL = "email";
		public static final String HOMEPAGE = "homepage";
		public static final String QQ = "qq";
		public static final String PHONE = "phone";
		public static final String CURRENT_POWER = "current_power";
		public static final String MAX_POWER = "max_power";
		public static final String TOPICS_COUNT = "topics_count";
		public static final String REPLIES_COUNT = "replies_count";
		public static final String FANTASTIC_COUNT = "FANTASTIC_COUNT";
		public static final String EXPERIENCE = "experience";
		public static final String XDB = "xdb";
		public static final String REGISTER_TIME = "register_time";
		public static final String HONORS = "honors";
	}
	
	/**
	 * 回复。
	 * @author im.longkai
	 *
	 */
	public static final class Reply {
		public static final int LATEST = 0;
		
		public static final String TID = "tid";
		public static final String POST_UID = "post_uid";
		public static final String POST_USER_NICK = "post_user_nick";
		public static final String POST_USER_PORTRAIT = "post_user_portrait";
		public static final String TOPIC_TITLE = "topic_title";
		public static final String POST_TIME = "post_time";
		public static final String CONTENT = "content"; 
	}
	
	/**
	 * 板块
	 * @author im.longkai
	 *
	 */
	public static final class Forum {
		public static final String NAME = "name";
		public static final String ABOUT = "about";
		public static final String MONEY_PER_TOPIC = "money_per_topic";
		public static final String MONEY_PER_REPLY = "money_per_reply";
		public static final String EXP_PER_TOPIC = "exp_per_topic";
		public static final String EXP_PER_REPLY = "exp_per_reply";
		public static final String TOTAL_TOPICS_COUNT = "total_topics_count";
		public static final String FANTASY_COUNT = "fantasy_count";
		public static final String HOT = "hot";
		public static final String MASTERS = "masters";
	}
	

}
