package models.bus;

import java.util.List;

import javax.persistence.Id;

public class Direction {
	@Id
	String tag;
	String title;
	String name;
	String useForUI;
	
	private List<Stop> stops;
	
	public Direction(String tag1, String title1, String name1, String useForUI1) {
		this.tag=tag1;
		this.title=title1;
		this.name = name1;
		this.useForUI = useForUI1;
	}
	
	public Direction(List<Stop> stops) {
		this.stops = stops;
	}
	
	public Direction() {
		
	}

	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}
	
	public List<Stop> getStops() {
		return stops;
	}
	
}
