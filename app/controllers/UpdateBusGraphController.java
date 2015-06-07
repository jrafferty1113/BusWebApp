package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Result;
import services.AgencyService;

@org.springframework.stereotype.Controller
public class UpdateBusGraphController {
	@Autowired
	private AgencyService agencyService;
	
	public Result test() {
		agencyService.updateAgenciesCalNorth();
		return null;
	}
	
}
