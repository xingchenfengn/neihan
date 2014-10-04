package com.xing.neihan.test;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.xing.neihan.R;
import com.xing.neihan.bin.ImageEntity;
import com.xing.neihan.bin.ImageUrlList;
import com.xing.neihan.client.ClientAPI;

/**
 * 这个文件就是一个测试入口，所有测试内容，都可以放在这里
 * 
 * @author xingchenfeng
 * 
 */
public class TestActivity extends Activity implements Response.Listener<String> {

	public static final String TAG = "TestActivity";
	/**
	 * 分类id,1代表文本，2代表图片
	 */
	public static final int CATEGORY_TEXT = 1;
	public static final int CATEGORY_IMAGE = 2;
	private RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		int itemCount = 30;

		queue = Volley.newRequestQueue(this);

		ClientAPI.getList(queue, itemCount, CATEGORY_IMAGE, this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	@Override
	public void onResponse(String arg0) {
		// TODO Auto-generated method stub

		try {
			JSONObject json = new JSONObject(arg0);

			arg0 = json.toString(4);
			System.out.println("list" + arg0);
			Log.i(TAG, "---json->>>>" + json.toString());

			// 获取根节点下面的data 对象
			JSONObject obj = json.getJSONObject("data");
			// 从data对象中，获取名称为data的数组，他代表的是段子列表的数据
			JSONArray array = obj.getJSONArray("data");

			int len = array.length();
			if (len > 0) {
				// 遍历数组中的每一条图片段子信息
				for (int i = 0; i < len; i++) {
					JSONObject item = array.getJSONObject(i);
					ImageEntity imageEntity=new ImageEntity();
					imageEntity.parseEntity(item);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private List<String> parseImageUrlList(JSONObject largeImage)
			throws JSONException {
		JSONArray urls = largeImage.getJSONArray("url_list");

		// 大图片的网址全部在这里
		List<String> largeImageursl = new LinkedList<String>();
		int ulen = urls.length();
		
		 
		
		for (int j = 0; j < ulen; j++) {
			JSONObject uobj = urls.getJSONObject(j);
			String url = uobj.getString("url");
			largeImageursl.add(url);
		}

		return largeImageursl;
	}

}
