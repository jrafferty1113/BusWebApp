package services;

import java.util.List;

import models.bus.Agency;
import models.bus.Route;

public interface AgencyService {
	/*
	 * Return all agencies stored
	 */
	public List<Agency> getAllAgencies();
	
	public List<Agency> getAgenciesByRegion(String region);

	public void addAgencies(List<Agency> list);
	
	public void addAgency(Agency agency);
	
	/*
	 * Return all routes stored
	 */
	public List<Route> getAllRoutes();
	
	public List<Route> getRoutesByAgency(Agency agency);

	public void addRoute(Route route);
	
	public void addRoutes(List<Route> list);
}
