package models.bus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Agency {
	@Id
	@GeneratedValue
    public String id;
	public String tag;
	public String regionTitle;
	
	public Agency() {
		
	}
	
	public Agency(String t, String rT) {
		this.tag=t;
		this.regionTitle=rT;
	}
	
	
}
