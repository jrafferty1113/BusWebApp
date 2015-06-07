package services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.bus.Agency;
import models.bus.Direction;
import models.bus.Route;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class RouteServiceImpl implements RouteService {

	@Override
	public void loadRoutes() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Route> getRoutes(Document xml) {
		NodeList nl = xml.getElementsByTagName("body").item(0).getChildNodes();
		
		Set<Route> routes = new HashSet<Route>();
		
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeName().equals("route")) {
				
				NamedNodeMap attributes = nl.item(i).getAttributes();
				Route r = new Route();
				
				r.setTag(attributes.getNamedItem("tag").getNodeValue());
				r.setTitle(attributes.getNamedItem("title").getNodeValue());
				r.setLatMax(Double.valueOf(attributes.getNamedItem("latMax").getNodeValue()));
				r.setLatMin(Double.valueOf(attributes.getNamedItem("latMin").getNodeValue()));
				r.setLonMax(Double.valueOf(attributes.getNamedItem("lonMax").getNodeValue()));
				r.setLonMin(Double.valueOf(attributes.getNamedItem("lonMin").getNodeValue()));
				
				routes.add(r);
			}
		}
		
		return routes;
	}
	
	@Override
	public Set<Direction> getDirections(Document xml) {
		NodeList nl = xml.getElementsByTagName("body").item(0).getChildNodes().item(0).getChildNodes();
		play.Logger.error(nl.item(0).getNodeName());
		
		Set<Direction> list = new HashSet<Direction>();
		
//		for (int i = 0; i < nl.getLength(); i++) {
//			
//			if (nl.item(i).getNodeName().equals("route") && nl.item(i).getAttributes().getNamedItem("regionTitle").getNodeValue().equals(region)) {
//				list.add(new Agency(nl.item(i).getAttributes().getNamedItem("tag").getNodeValue(), nl.item(i).getAttributes().getNamedItem("regionTitle").getNodeValue()));
//			}		
//		}
		return list;
	}
}
