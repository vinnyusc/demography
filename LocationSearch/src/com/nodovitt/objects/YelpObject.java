package com.nodovitt.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class YelpObject {

	@SerializedName("businesses")
	public List<Result> results;

	public class Result {
		public String name;
		public double distance;
		public String address1;
		public String address2;
		public String address3;
		public String phone;
		public int review_count;
		public double avg_rating;

		public String toString() {
			String result = "\nName: " + name;
			//result+= "\nAddress: " + address1+" " + address2 + " " + address3;
			result+= "\nDistance: " + distance;
			//result+= "\nRating: " + avg_rating;
			return result;
		}
	}

}
