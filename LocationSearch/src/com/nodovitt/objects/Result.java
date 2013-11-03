package com.nodovitt.objects;

public class Result implements Comparable<Result>{
	public double distance;
	public String name;
	public String address;
	public double lat;
	public double lng;
	
	@Override
	public int compareTo(Result a) {
		if(a.distance>this.distance)
			return -1;
		else if(a.distance<this.distance)
			return 1;
		else 
			return 0;
	}		
	
}