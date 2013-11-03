package com.nodovitt.objects;

public class Node {
	private double lat=0.0;
	private double lng=0.0;
	
	public Node(){
		
	}
	public Node(double lat, double lng){
		this.lat = lat;
		this.lng = lng;		
	}
	
	public double getLat(){
		return this.lat;
	}
	public double getLng(){
		return this.lng;
	}
	public void setLat(double lat){
		this.lat = lat;
	}
	public void setLng(double lng){
		this.lng = lng;
	}
}
