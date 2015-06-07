package models.bus;

import javax.persistence.Id;

public class Stop {
	@Id
	String stopId;
	String name;
	String tag;
	private double lon;
	private double lat;
	
	public Stop(String tag1, String stopId1, String lat1, String lon1, String name1) {
		this.tag = tag1;
		this.stopId = stopId1;
		this.lat = Double.valueOf(lat1);
		this.lon = Double.valueOf(lon1);
		this.name = name1;
	}
	
	public Stop() {
		
	}
	
	public Stop(String tag1) {
		this.tag = tag1;
	}
	
	public Stop(double lon, double lat) {
		this.lon = lon;
		this.lat = lat;
	}
	
	public void setLongitude(double l) {
		lon = l;
	}
	
	public double getLongitude() {
		return lon;
	}
	
	public void setLatitude(double l) {
		lat = l;
	}
	
	public double getLatitude() {
		return lat;
	}
	
}
