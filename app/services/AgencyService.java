package services;

import java.util.List;

import models.bus.Agency;
import models.bus.Route;

public interface AgencyService {

	/*
	 * If we don't have agency list in db, then
	 * download list of agencies from 3rd-party
	 */
	public void loadAgencies();
	
	public void loadAgenciesByRegion(String region);
	
	public void loadRoutes(Agency agency);
	
	/*
	 * Return all agencies stored
	 */
	public List<Agency> getAllAgencies();
	
	public List<Agency> getAgenciesByRegion(String region);

	public void addAgency(List<Agency> list);
	
	public void addAgency(Agency agency);
	
	public void removeAgencyByRegion(String region);
	
	/*
	 * Return all routes stored
	 */
	public List<Route> getAllRoutes();
	
	public List<Route> getRoutes(String agency);
	
	public List<Route> getRoutes(List<Agency> list);
	
	public void addRoute(Route route);
	
	public void addRoute(List<Route> list);
}
