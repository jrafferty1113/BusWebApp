package models.bus;

public class Stop {
	private double lon;
	private double lat;
	
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
