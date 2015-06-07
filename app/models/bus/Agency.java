package models.bus;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Agency {
	@Id
	public String tag;
	public String title;
	public String shortTitle;
	public String regionTitle;
}
