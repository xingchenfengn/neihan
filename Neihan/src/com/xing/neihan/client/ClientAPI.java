package com.xing.neihan.client;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.xing.neihan.test.TestActivity;

/**
 * 所有和客户端接口对接的方法，全部在这个类里面
 * 
 * @author xingchenfeng
 * 
 */
public class ClientAPI {

	/**
	 * 获取内涵段子列表内容
	 * 
	 * @param Volley请求的队列
	 * @param itemCount
	 *            服务器一次传回来的条目数
	 * @param categoryType
	 *            要获取的参数类型
	 * @responseListener 用于获取段子列表的回掉接口，任何调用getList方法时，此参数用于更新段子列表
	 * @see TestActivity#CATEGORY_TEXT
	 * @see TestActivity#CATEGORY_IMAGE
	 */
	public static void getList(RequestQueue queue, int itemCount,
			int categoryType, Response.Listener<String> respListener) {

		// 测试内涵段子列表接口，获取文本列表

		String CATEGORY_LIST_API = "http://ic.snssdk.com/2/essay/zone/category/data/";

		// 分类参数,根据类型获取不同的数据
		String categoryParam = "category_id=" + categoryType;
		String countParam = "count=" + itemCount;
		String deviceTypeParam = "device_type=KFTT";
		String openUDidParam = "openudid=b90ca6a3a19a78d6";

		String url = CATEGORY_LIST_API
				+ "?"
				+ categoryParam
				+ "&"
				+ countParam
				+ "&"
				+ deviceTypeParam
				+ "&"
				+ openUDidParam
				+ "&level=6&iid=2337593504&device_id=2757969807&ac=wifi&channel=wandoujia&aid=7&app_name=joke_essay&version_code=302&device_platform=android&os_api=15&os_version=4.0.3";

		queue.add(new StringRequest(Request.Method.GET, url,

		respListener, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub

			}
		}));
		queue.start();
	}

}
