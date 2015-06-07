package services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.bus.Agency;
import models.bus.Direction;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class RouteServiceImpl implements RouteService {

	@Override
	public void updateRoutes() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Direction> parseRouteXml(Document xml) {
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
