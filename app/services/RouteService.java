package services;


import java.util.Set;

import org.w3c.dom.Document;

import models.bus.Direction;

import java.util.List;



import models.bus.Route;

public interface RouteService {


	public void loadRoutes();
	public Set<Route> getRoutes(Document xml);
	public Set<Direction> getDirections(Document xml);
	

	/*
	 * Download list of agencies from 3rd-party
	 * For now we only focus on California North
	 */
	public void updateRoutes();
	
	/*
	 * Return all agencies stored
	 */
	public List<Route> getAllRoutes();
	
	

	public void addRoute(List<Route> list);
	
	public void addRoute(Route route);

	
	
	public void removeRoute(String region);

}
