package com.xing.neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageEntity {

	private int type;
	
	private int commentCount;

	private long group_id;
	
	private JSONObject group;
	
	private String content;
	
	private ImageUrlList larImageUrlList;
	
	private ImageUrlList middleImageUrlList;

	public void parseEntity(JSONObject item) throws JSONException {
		type = item.getInt("type");

		group = item.getJSONObject("group");
		commentCount = group.getInt("comment_count");
		JSONObject largeImage = group.getJSONObject("large_image");
		JSONObject middleImage = group.getJSONObject("middle_image");
		group_id = group.getLong("group_id");
		content = group.getString("content");

		larImageUrlList = new ImageUrlList();
		larImageUrlList.parseJson(largeImage);

		middleImageUrlList = new ImageUrlList();
		middleImageUrlList.parseJson(middleImage);
	}

}
