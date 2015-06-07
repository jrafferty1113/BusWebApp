package models.bus;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Route {
	@Id
	public String tag;
	public String title;
	
	public Route() {
		
	}
	
	public Route(String t,String tt) {
		this.tag=t;
		this.title=tt;
	}
}