package services;

import java.util.Set;

import org.w3c.dom.Document;

import models.bus.Direction;
import models.bus.Route;

public interface RouteService {

	public void loadRoutes();
	public Set<Route> getRoutes(Document xml);
	public Set<Direction> getDirections(Document xml);
	
}
