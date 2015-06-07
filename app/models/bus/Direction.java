package models.bus;

import java.util.List;

import javax.persistence.Id;

public class Direction {
	@Id
	String tag;
	
	public List<Stop> getStops() {
		return null;
	}
}
