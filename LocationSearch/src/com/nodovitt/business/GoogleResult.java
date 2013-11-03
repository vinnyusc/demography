package com.nodovitt.business;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.nodovitt.locationsearch.CommonResult;
import com.nodovitt.locationsearch.Process;
import com.nodovitt.objects.GoogleObject;
import com.nodovitt.objects.Node;
import com.nodovitt.objects.Result;

public class GoogleResult implements CommonResult {

	Process p = new Process();
	double latitude, longitude;
	Node source = null;
	String jsonString = null;

	public GoogleObject getGoogleObject(String googleJson) {

		Gson gson = new Gson();
		GoogleObject response = gson.fromJson(googleJson, GoogleObject.class);
		List<GoogleObject.Result> results = response.results;

		for (GoogleObject.Result eachResult : results) {
			double lat1 = eachResult.geometry.location.lat;
			double lng1 = eachResult.geometry.location.lng;
			double dist = p.computeDistance(latitude, longitude, lat1, lng1);
			
			DecimalFormat df = new DecimalFormat("####0.00");
			eachResult.distance = Double.parseDouble(df.format(dist));
		}

		return response;
	}

	public List<Result> getList(GoogleObject g) {

		List<Result> resultList = new ArrayList<Result>();
		List<GoogleObject.Result> googleResults = g.results;
		//CommonResultObject c = new CommonResultObject();

		for (GoogleObject.Result each : googleResults) {
			Result r = new Result();
			r.address = each.vicinity;
			r.lat = each.geometry.location.lat;
			r.lng = each.geometry.location.lng;
			r.distance = each.distance;
			r.name = each.name;
			resultList.add(r);
		}

		return resultList;

	}

	public List<Result> getCommonResult(String zip,
			String miles, String query, String lat, String lng) {

		// Taking miles as default if not mentioned
		if (miles == null) {
			miles = Integer.toString(CommonResult.miles);
		}
		
		latitude = Double.parseDouble(lat);
		longitude = Double.parseDouble(lng);
		/*
		 * if (lat == null || lng == null || zip!=null) { source =
		 * p.convertZipToGeo(Integer.parseInt(zip)); latitude = source.getLat();
		 * longitude = source.getLng(); } else { latitude =
		 * Double.parseDouble(lat); longitude = Double.parseDouble(lng); }
		 */

		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"

				+ "location="
				+ lat
				+ ","
				+ lng
				+ "&radius="
				+ Double.parseDouble(miles)
				* 1609.344
				// + "&types="
				// +"&name=physician"
				+ "&keyword="
				+ query
				// + "&rankby=distance"
				// + "&query="
				// + query
				+ "&sensor=true"
				+ "&key=AIzaSyAEzoA7z11qqRiOXGdL0_UQX-F7Cl4GAoA";
		
		//System.out.println(url);

		jsonString = p.getJsonString(url);
		GoogleObject g = getGoogleObject(jsonString);
		List<Result> list = getList(g);
		return list;

	}

}
