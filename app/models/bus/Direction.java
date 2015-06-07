package models.bus;

import java.util.List;

import javax.persistence.Id;

public class Direction {
	@Id
	String tag;
	private List<Stop> stops;
	
	public Direction(List<Stop> stops) {
		this.stops = stops;
	}
	
	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}
	
	public List<Stop> getStops() {
		return stops;
	}
}
