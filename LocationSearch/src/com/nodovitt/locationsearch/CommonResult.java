package com.nodovitt.locationsearch;

import java.util.List;

import com.nodovitt.objects.Result;

public interface CommonResult {
	int zip = 90001;
	int miles = 5;
	
	List<Result> getCommonResult(String zip, String miles, String query, String lat, String lng);
}
