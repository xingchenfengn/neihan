package com.xing.neihan.bean;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageUrlList {

	private List<String> largeImageList;
	private String uri;
	private int width;
	private int height;

	public void parseJson(JSONObject json) throws JSONException{
		largeImageList = parseImageUrlList(json);
		uri = json.getString("uri");
		width = json.getInt("width");
		height = json.getInt("height");
	}

	public List<String> getLargeImageList() {
		return largeImageList;
	}

	public String getUri() {
		return uri;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
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
