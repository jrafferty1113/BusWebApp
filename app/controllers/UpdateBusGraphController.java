package controllers;

import org.springframework.beans.factory.annotation.Autowired;

import play.libs.Json;
import play.mvc.Result;
import services.AgencyService;

@org.springframework.stereotype.Controller
public class UpdateBusGraphController {
	@Autowired
	private AgencyService agencyService;
	
	public Result test() {
		agencyService.getAgenciesByRegion("California-Northern");
		return play.mvc.Controller.ok(Json.toJson(agencyService.getAllAgencies()));
	}
	
}
