package models.geolocation;

import javax.persistence.Entity;

public class Coordinate {
	final private double lat;
	final private double lon;
	
	public Coordinate(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public double getLatitude() {
		return lat;
	}

	public double getLongitude() {
		return lon;
	}
}
