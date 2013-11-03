package com.nodovitt.business;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.nodovitt.locationsearch.CommonResult;
import com.nodovitt.locationsearch.Process;
import com.nodovitt.objects.Node;
import com.nodovitt.objects.Result;
import com.nodovitt.objects.YelpObject;
public class YelpResult implements CommonResult {

	Process p = new Process();
	double latitude, longitude;
	Node source = null;
	String jsonString = null;

	public YelpObject getYelpObject(String yelpJson) {

		Gson gson = new Gson();
		YelpObject response = gson.fromJson(yelpJson, YelpObject.class);

		return response;
	}

	public List<Result> getList(YelpObject y) {

		
		List<Result> resultList = new ArrayList<Result>();
		List<YelpObject.Result> results = y.results;
		//CommonResultObject c = new CommonResultObject();
		
		for(YelpObject.Result each: results){
			Result r = new Result();
			r.address = each.address1 + each.address2 + each.address3;
			r.distance = each.distance;
			r.name = each.name;
			resultList.add(r);
		}

		return resultList;

	}

	@Override
	public List<Result> getCommonResult(String zip, String miles, String query,
			String lat, String lng) {

		// Taking miles as default if not mentioned
		if (miles == null) {
			miles = Integer.toString(CommonResult.miles);
		}

		
		/*if (lat == null || lng == null || zip != null) {
			source = p.convertZipToGeo(Integer.parseInt(zip));
			latitude = source.getLat();
			longitude = source.getLng();
		} else {
			latitude = Double.parseDouble(lat);
			longitude = Double.parseDouble(lng);
		}
		*/
		String url = "http://api.yelp.com/business_review_search?";
		url += "term=" + query;
		url += "&lat=" + lat + "&long=" + lng;
		url += "&radius=" + miles;
		url += "&limit=20";
		url += "&ywsid=mD5dWViFBB0JyenJnZRCXg";

		jsonString = p.getJsonString(url);
		YelpObject y = getYelpObject(jsonString);
		List<Result> list = getList(y);
		return list;

	}

}
