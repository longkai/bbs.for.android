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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供论坛版块的数据，注意，不是content provider!
 * 
 * @author longkai
 * @email im.longkai@gmail.com
 * @since 2013-5-22
 * @version 0.1
 */
public class ForumsProvider {

//	public static final String[] AREAS = {};
	
	public static final String NAME = "name";
	public static final String ABOUT = "about";
	public static final String FID = "fid";
	
	public static final List<Map<String, String>> AREAS = new ArrayList<Map<String,String>>();
	public static final List<List<Map<String, String>>> FORUMS = new ArrayList<List<Map<String,String>>>();
	
	static {
//		站务管理
		Map<String, String> area1= new HashMap<String, String>();
		area1.put(NAME, "站务管理");
		List<Map<String, String>> area1Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area1Forum1 = new HashMap<String, String>();
		area1Forum1.put(NAME, "问题意见");
		area1Forum1.put(ABOUT, "严禁灌水,只用于站务、网友问题的处理及建议。");
		area1Forum1.put(FID, "1");
		area1Forums.add(area1Forum1);
		
		Map<String, String> area1Forum2 = new HashMap<String, String>();
		area1Forum2.put(NAME, "版主申请");
		area1Forum2.put(ABOUT, "欢迎有时间和精力的朋友加入我们的管理团队。 当版主就意味着一种坚持和责任，发贴前请思之、慎之。");
		area1Forum2.put(FID, "14");
		area1Forums.add(area1Forum2);
		
		Map<String, String> area1Forum3 = new HashMap<String, String>();
		area1Forum3.put(NAME, "举报中心");
		area1Forum3.put(ABOUT, "举报中心");
		area1Forum3.put(FID, "66");
		area1Forums.add(area1Forum3);
		
		Map<String, String> area1Forum4 = new HashMap<String, String>();
		area1Forum4.put(NAME, "论坛投诉");
		area1Forum4.put(ABOUT, "论坛投诉");
		area1Forum4.put(FID, "93");
		area1Forums.add(area1Forum4);
		
		Map<String, String> area1Forum5 = new HashMap<String, String>();
		area1Forum5.put(NAME, "惩罚记录");
		area1Forum5.put(ABOUT, "惩罚记录");
		area1Forum5.put(FID, "105");
		area1Forums.add(area1Forum5);
		
		FORUMS.add(area1Forums);
		AREAS.add(area1);
		
//		放眼西大
		Map<String, String> area2 = new HashMap<String, String>();
		area2.put(NAME, "放眼西大");
		List<Map<String, String>> area2Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area2Forum1 = new HashMap<String, String>();
		area2Forum1.put(NAME, "校园聚焦");
		area2Forum1.put(ABOUT, "西大的天空，因我们的关注而绚丽；我们的校园，因大家的聚焦而多姿。");
		area2Forum1.put(FID, "10");
		area2Forums.add(area2Forum1);
		
		Map<String, String> area2Forum2 = new HashMap<String, String>();
		area2Forum2.put(NAME, "新生专版");
		area2Forum2.put(ABOUT, "迎新专版,欢迎新生来发问,也欢迎老生来回答新生的问题!");
		area2Forum2.put(FID, "131");
		area2Forums.add(area2Forum2);
		
		Map<String, String> area2Forum3 = new HashMap<String, String>();
		area2Forum3.put(NAME, "校报速递");
		area2Forum3.put(ABOUT, "校报速递");
		area2Forum3.put(FID, "48");
		area2Forums.add(area2Forum3);
		
		Map<String, String> area2Forum4 = new HashMap<String, String>();
		area2Forum4.put(NAME, "校友之家");
		area2Forum4.put(ABOUT, "欢迎校友常回家看看！");
		area2Forum4.put(FID, "110");
		area2Forums.add(area2Forum4);
		
		FORUMS.add(area2Forums);
		AREAS.add(area2);
		
//		社团风采
		Map<String, String> area3 = new HashMap<String, String>();
		area3.put(NAME, "社团风采");
		List<Map<String, String>> area3Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area3Forum1 = new HashMap<String, String>();
		area3Forum1.put(NAME, "社团人");
		area3Forum1.put(ABOUT, "社团风采");
		area3Forum1.put(FID, "81");
		area3Forums.add(area3Forum1);
		
		Map<String, String> area3Forum2 = new HashMap<String, String>();
		area3Forum2.put(NAME, "文学联社");
		area3Forum2.put(ABOUT, "文学联社");
		area3Forum2.put(FID, "146");
		area3Forums.add(area3Forum2);
		
		Map<String, String> area3Forum3 = new HashMap<String, String>();
		area3Forum3.put(NAME, "书画协会");
		area3Forum3.put(ABOUT, "给我一根线条，就能创造出一个美妙的世界");
		area3Forum3.put(FID, "137");
		area3Forums.add(area3Forum3);
		
		Map<String, String> area3Forum4 = new HashMap<String, String>();
		area3Forum4.put(NAME, "科技协会");
		area3Forum4.put(ABOUT, "同吹西大科技风 共创学生科技史");
		area3Forum4.put(FID, "139");
		area3Forums.add(area3Forum4);
		
		Map<String, String> area3Forum5 = new HashMap<String, String>();
		area3Forum5.put(NAME, "音乐协会");
		area3Forum5.put(ABOUT, "音乐爱好者协会");
		area3Forum5.put(FID, "134");
		area3Forums.add(area3Forum5);
		
		Map<String, String> area3Forum6 = new HashMap<String, String>();
		area3Forum6.put(NAME, "天文协会");
		area3Forum6.put(ABOUT, "那宁静唯美璀璨的星空，让我们的心灵栖息相偎");
		area3Forum6.put(FID, "160");
		area3Forums.add(area3Forum6);
		
		Map<String, String> area3Forum7 = new HashMap<String, String>();
		area3Forum7.put(NAME, "广告协会");
		area3Forum7.put(ABOUT, "广告协会");
		area3Forum7.put(FID, "132");
		area3Forums.add(area3Forum7);
		
		Map<String, String> area3Forum8 = new HashMap<String, String>();
		area3Forum8.put(NAME, "漫画研究社");
		area3Forum8.put(ABOUT, "漫画研究社");
		area3Forum8.put(FID, "135");
		area3Forums.add(area3Forum8);
		
		Map<String, String> area3Forum9 = new HashMap<String, String>();
		area3Forum9.put(NAME, "自行车协会");
		area3Forum9.put(ABOUT, "自行车协会");
		area3Forum9.put(FID, "133");
		area3Forums.add(area3Forum9);
		
		Map<String, String> area3Forum10 = new HashMap<String, String>();
		area3Forum10.put(NAME, "青年志愿者协会");
		area3Forum10.put(ABOUT, "奉献 友爱 互助 进步");
		area3Forum10.put(FID, "178");
		area3Forums.add(area3Forum10);
		
		FORUMS.add(area3Forums);
		AREAS.add(area3);
		
//		休闲娱乐
		Map<String, String> area4 = new HashMap<String, String>();
		area4.put(NAME, "休闲娱乐");
		List<Map<String, String>> area4Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area4Forum1 = new HashMap<String, String>();
		area4Forum1.put(NAME, "音乐天堂");
		area4Forum1.put(ABOUT, "在这里，我们用音乐说话");
		area4Forum1.put(FID, "5");
		area4Forums.add(area4Forum1);
		
		Map<String, String> area4Forum2 = new HashMap<String, String>();
		area4Forum2.put(NAME, "天下无图");
		area4Forum2.put(ABOUT, "有什么好图片就砸过来吧");
		area4Forum2.put(FID, "11");
		area4Forums.add(area4Forum2);

		Map<String, String> area4Forum3 = new HashMap<String, String>();
		area4Forum3.put(NAME, "自由发帖");
		area4Forum3.put(ABOUT, "灌水亦需有公德");
		area4Forum3.put(FID, "9");
		area4Forums.add(area4Forum3);
		
		Map<String, String> area4Forum4 = new HashMap<String, String>();
		area4Forum4.put(NAME, "精品时尚");
		area4Forum4.put(ABOUT, "最COOL的时尚新潮，最前卫的装束，最新的FASHION SHOW尽在精品时尚！");
		area4Forum4.put(FID, "13");
		area4Forums.add(area4Forum4);
		
		Map<String, String> area4Forum5 = new HashMap<String, String>();
		area4Forum5.put(NAME, "游戏东西");
		area4Forum5.put(ABOUT, "游戏东西");
		area4Forum5.put(FID, "20");
		area4Forums.add(area4Forum5);
		
		Map<String, String> area4Forum6 = new HashMap<String, String>();
		area4Forum6.put(NAME, "手机数码");
		area4Forum6.put(ABOUT, "手机，mp3,dc,dv，4大件...");
		area4Forum6.put(FID, "70");
		area4Forums.add(area4Forum6);
		
		Map<String, String> area4Forum7 = new HashMap<String, String>();
		area4Forum7.put(NAME, "驴行天下");
		area4Forum7.put(ABOUT, "驴行天下");
		area4Forum7.put(FID, "98");
		area4Forums.add(area4Forum7);
		
		Map<String, String> area4Forum8 = new HashMap<String, String>();
		area4Forum8.put(NAME, "美食沙龙");
		area4Forum8.put(ABOUT, "享天下美食，品五味人生，尽在美食沙龙");
		area4Forum8.put(FID, "99");
		area4Forums.add(area4Forum8);
		
		Map<String, String> area4Forum9 = new HashMap<String, String>();
		area4Forum9.put(NAME, "影视长廊");
		area4Forum9.put(ABOUT, "看电影，我们并不是在消磨时光…");
		area4Forum9.put(FID, "108");
		area4Forums.add(area4Forum9);
		
		Map<String, String> area4Forum10 = new HashMap<String, String>();
		area4Forum10.put(NAME, "摄影天地");
		area4Forum10.put(ABOUT, "摄影天地");
		area4Forum10.put(FID, "119");
		area4Forums.add(area4Forum10);
		
		FORUMS.add(area4Forums);
		AREAS.add(area4);
		
//		文化艺术
		Map<String, String> area5 = new HashMap<String, String>();
		area5.put(NAME, "文化艺术");
		List<Map<String, String>> area5Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area5Forum1 = new HashMap<String, String>();
		area5Forum1.put(NAME, "文行天下");
		area5Forum1.put(ABOUT, "书生活百态，行书山之径，览天下美文，扬纯净文气。");
		area5Forum1.put(FID, "4");
		area5Forums.add(area5Forum1);
		
		Map<String, String> area5Forum2 = new HashMap<String, String>(3);
		area5Forum2.put(NAME, "动漫天地");
		area5Forum2.put(ABOUT, "动画、漫画，多少值得我们回忆的...你还记得我们的叮当吗？");
		area5Forum2.put(FID, "57");
		area5Forums.add(area5Forum2);
		
		Map<String, String> area5Forum3 = new HashMap<String, String>(3);
		area5Forum3.put(NAME, "科幻世界");
		area5Forum3.put(ABOUT, "我们的网络科幻");
		area5Forum3.put(FID, "60");
		area5Forums.add(area5Forum3);
		
		Map<String, String> area5Forum4 = new HashMap<String, String>(3);
		area5Forum4.put(NAME, "武侠江湖");
		area5Forum4.put(ABOUT, "桃李春风一杯酒，江湖夜雨十年灯");
		area5Forum4.put(FID, "107");
		area5Forums.add(area5Forum4);
		
		Map<String, String> area5Forum5 = new HashMap<String, String>(3);
		area5Forum5.put(NAME, "史海沉沙");
		area5Forum5.put(ABOUT, "史海沉沙");
		area5Forum5.put(FID, "164");
		area5Forums.add(area5Forum5);
		
		FORUMS.add(area5Forums);
		AREAS.add(area5);
		
//		体育健身 
		Map<String, String> area6 = new HashMap<String, String>();
		area6.put(NAME, "体育健身");
		List<Map<String, String>> area6Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area6Forum1 = new HashMap<String, String>(3);
		area6Forum1.put(NAME, "体育资讯");
		area6Forum1.put(ABOUT, "最新的体坛资讯，最快的体坛动态，统统在这里！");
		area6Forum1.put(FID, "21");
		area6Forums.add(area6Forum1);
		
		Map<String, String> area6Forum2 = new HashMap<String, String>(3);
		area6Forum2.put(NAME, "玩转篮球");
		area6Forum2.put(ABOUT, "I Love This Game");
		area6Forum2.put(FID, "101");
		area6Forums.add(area6Forum2);
		
		Map<String, String> area6Forum3 = new HashMap<String, String>(3);
		area6Forum3.put(NAME, "绿茵场上");
		area6Forum3.put(ABOUT, "绿茵场上是最华丽的舞台！");
		area6Forum3.put(FID, "102");
		area6Forums.add(area6Forum3);
		
		FORUMS.add(area6Forums);
		AREAS.add(area6);
		
//		知性感性
		Map<String, String> area7 = new HashMap<String, String>();
		area7.put(NAME, "知性感性");
		List<Map<String, String>> area7Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area7Forum1 = new HashMap<String, String>(3);
		area7Forum1.put(NAME, "生活空间");
		area7Forum1.put(ABOUT, "我们的生活总是充满了阳光，幸福，从点点滴滴开始");
		area7Forum1.put(FID, "6");
		area7Forums.add(area7Forum1);
		
		Map<String, String> area7Forum2 = new HashMap<String, String>(3);
		area7Forum2.put(NAME, "心情故事");
		area7Forum2.put(ABOUT, "亲情、友情、爱情，只要你愿意和大家分享，请到这里来");
		area7Forum2.put(FID, "12");
		area7Forums.add(area7Forum2);
		
		Map<String, String> area7Forum3 = new HashMap<String, String>(3);
		area7Forum3.put(NAME, "单身部落");
		area7Forum3.put(ABOUT, "单身就是：一个人也要过得精彩，以最好的自己来遇见你");
		area7Forum3.put(FID, "23");
		area7Forums.add(area7Forum3);
		
		FORUMS.add(area7Forums);
		AREAS.add(area7);
		
//		学术科学
		Map<String, String> area8 = new HashMap<String, String>();
		area8.put(NAME, "学术科学");
		List<Map<String, String>> area8Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area8Forum1 = new HashMap<String, String>(3);
		area8Forum1.put(NAME, "经济纵横");
		area8Forum1.put(ABOUT, "解读政策、点评市场、分析时下热点事件、点拨最新财经动向");
		area8Forum1.put(FID, "115");
		area8Forums.add(area8Forum1);
		
		Map<String, String> area8Forum2 = new HashMap<String, String>(3);
		area8Forum2.put(NAME, "工业技术");
		area8Forum2.put(ABOUT, "机械、电气、化工、计算机、电子等与工业相关的内容");
		area8Forum2.put(FID, "117");
		area8Forums.add(area8Forum2);
		
		Map<String, String> area8Forum3 = new HashMap<String, String>(3);
		area8Forum3.put(NAME, "农业科学");
		area8Forum3.put(ABOUT, "农业科学");
		area8Forum3.put(FID, "116");
		area8Forums.add(area8Forum3);
		
		Map<String, String> area8Forum4 = new HashMap<String, String>(3);
		area8Forum4.put(NAME, "思想前线");
		area8Forum4.put(ABOUT, "人类一思考，上帝就发慌！");
		area8Forum4.put(FID, "3");
		area8Forums.add(area8Forum4);
		
		Map<String, String> area8Forum5 = new HashMap<String, String>(3);
		area8Forum5.put(NAME, "英语角");
		area8Forum5.put(ABOUT, "Where there is a will ,there is a way.Come on ,baby !");
		area8Forum5.put(FID, "62");
		area8Forums.add(area8Forum5);
		
		FORUMS.add(area8Forums);
		AREAS.add(area8);
		
//		电脑技术
		Map<String, String> area9 = new HashMap<String, String>();
		area9.put(NAME, "电脑技术");
		List<Map<String, String>> area9Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area9Forum1 = new HashMap<String, String>(3);
		area9Forum1.put(NAME, "软硬兼施");
		area9Forum1.put(ABOUT, "软件问题,硬件问题,疑难杂症都从这里找答案~~~~");
		area9Forum1.put(FID, "8");
		area9Forums.add(area9Forum1);
		
		Map<String, String> area9Forum2 = new HashMap<String, String>(3);
		area9Forum2.put(NAME, "操作系统");
		area9Forum2.put(ABOUT, "操作系统");
		area9Forum2.put(FID, "96");
		area9Forums.add(area9Forum2);
		
		Map<String, String> area9Forum3 = new HashMap<String, String>(3);
		area9Forum3.put(NAME, "图形图像");
		area9Forum3.put(ABOUT, "让我们一起打造 梦幻新世界");
		area9Forum3.put(FID, "97");
		area9Forums.add(area9Forum3);
		
		Map<String, String> area9Forum4 = new HashMap<String, String>(3);
		area9Forum4.put(NAME, "网络纵横");
		area9Forum4.put(ABOUT, "网络纵横");
		area9Forum4.put(FID, "111");
		area9Forums.add(area9Forum4);
		
		FORUMS.add(area9Forums);
		AREAS.add(area9);
		
//		综合信息
		Map<String, String> area10 = new HashMap<String, String>();
		area10.put(NAME, "综合信息");
		List<Map<String, String>> area10Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area10Forum1 = new HashMap<String, String>(3);
		area10Forum1.put(NAME, "情牵广西");
		area10Forum1.put(ABOUT, "情牵广西 乡土中国");
		area10Forum1.put(FID, "24");
		area10Forums.add(area10Forum1);
		
		Map<String, String> area10Forum2 = new HashMap<String, String>(3);
		area10Forum2.put(NAME, "考研咨询");
		area10Forum2.put(ABOUT, "考研咨询");
		area10Forum2.put(FID, "63");
		area10Forums.add(area10Forum2);
		
		Map<String, String> area10Forum3 = new HashMap<String, String>(3);
		area10Forum3.put(NAME, "前程无忧");
		area10Forum3.put(ABOUT, "各种招聘信息，面试技巧及考公务员信息");
		area10Forum3.put(FID, "72");
		area10Forums.add(area10Forum3);
		
		Map<String, String> area10Forum4 = new HashMap<String, String>(3);
		area10Forum4.put(NAME, "热点时事");
		area10Forum4.put(ABOUT, "热点时事");
		area10Forum4.put(FID, "109");
		area10Forums.add(area10Forum4);
		
		Map<String, String> area10Forum5 = new HashMap<String, String>(3);
		area10Forum5.put(NAME, "寻人寻物");
		area10Forum5.put(ABOUT, "寻人寻物");
		area10Forum5.put(FID, "103");
		area10Forums.add(area10Forum5);
		
		Map<String, String> area10Forum6 = new HashMap<String, String>(3);
		area10Forum6.put(NAME, "考试认证");
		area10Forum6.put(ABOUT, "考试认证");
		area10Forum6.put(FID, "104");
		area10Forums.add(area10Forum6);
		
		FORUMS.add(area10Forums);
		AREAS.add(area10);
		
//		交易市场
		Map<String, String> area11 = new HashMap<String, String>();
		area11.put(NAME, "交易市场");
		List<Map<String, String>> area11Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area11Forum1 = new HashMap<String, String>(3);
		area11Forum1.put(NAME, "新旧书市");
		area11Forum1.put(ABOUT, "新旧书市");
		area11Forum1.put(FID, "125");
		area11Forums.add(area11Forum1);
		
		Map<String, String> area11Forum2 = new HashMap<String, String>(3);
		area11Forum2.put(NAME, "日用百货");
		area11Forum2.put(ABOUT, "日用百货");
		area11Forum2.put(FID, "124");
		area11Forums.add(area11Forum2);
		
		Map<String, String> area11Forum3 = new HashMap<String, String>(3);
		area11Forum3.put(NAME, "数码通讯");
		area11Forum3.put(ABOUT, "数码通讯");
		area11Forum3.put(FID, "126");
		area11Forums.add(area11Forum3);
		
		Map<String, String> area11Forum4 = new HashMap<String, String>(3);
		area11Forum4.put(NAME, "电脑配件");
		area11Forum4.put(ABOUT, "电脑配件");
		area11Forum4.put(FID, "127");
		area11Forums.add(area11Forum4);
		
		FORUMS.add(area11Forums);
		AREAS.add(area11);
		
//		驻站写手
		Map<String, String> area12 = new HashMap<String, String>();
		area12.put(NAME, "驻站写手");
		List<Map<String, String>> area12Forums = new ArrayList<Map<String,String>>();
		
		Map<String, String> area12Forum1 = new HashMap<String, String>(3);
		area12Forum1.put(NAME, "雅瑞");
		area12Forum1.put(ABOUT, "雅瑞");
		area12Forum1.put(FID, "53");
		area12Forums.add(area12Forum1);
		
		Map<String, String> area12Forum2 = new HashMap<String, String>(3);
		area12Forum2.put(NAME, "后羿");
		area12Forum2.put(ABOUT, "后羿");
		area12Forum2.put(FID, "114");
		area12Forums.add(area12Forum2);
		
		Map<String, String> area12Forum3 = new HashMap<String, String>(3);
		area12Forum3.put(NAME, "echoo");
		area12Forum3.put(ABOUT, "每天写一点，既不抱希望，也不抱绝望，用你最大的努力写。------卡弗");
		area12Forum3.put(FID, "94");
		area12Forums.add(area12Forum3);
		
		Map<String, String> area12Forum4 = new HashMap<String, String>(3);
		area12Forum4.put(NAME, "马萧萧");
		area12Forum4.put(ABOUT, "马萧萧");
		area12Forum4.put(FID, "73");
		area12Forums.add(area12Forum4);
		
		Map<String, String> area12Forum5 = new HashMap<String, String>(3);
		area12Forum5.put(NAME, "帕涅罗珀");
		area12Forum5.put(ABOUT, "千千寒");
		area12Forum5.put(FID, "112");
		area12Forums.add(area12Forum5);
		
		Map<String, String> area12Forum6 = new HashMap<String, String>(3);
		area12Forum6.put(NAME, "朱一刀");
		area12Forum6.put(ABOUT, "朱一刀");
		area12Forum6.put(FID, "50");
		area12Forums.add(area12Forum6);
		
		FORUMS.add(area12Forums);
		AREAS.add(area12);
	}
	
	public static String getForumId(int gid, int cid) {
		return FORUMS.get(gid).get(cid).get(FID);
	}
	
	public static final Map<String, String> getForumData(int gid, int cid) {
		return FORUMS.get(gid).get(cid);
	}
	
}
