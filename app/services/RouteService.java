package services;

import java.util.Set;

import org.w3c.dom.Document;

import models.bus.Direction;

public interface RouteService {

	public void updateRoutes();
	public Set<Direction> parseRouteXml(Document xml);
	
}
