package services;


import java.util.Set;

import org.w3c.dom.Document;

import models.bus.Direction;

import java.util.List;



import models.bus.Route;

public interface RouteService {

	/*
	 * Return all agencies stored
	 */
	public List<Route> getAllRoutes();

	public void addRoute(List<Route> list);
	
	public void addRoute(Route route);

}
