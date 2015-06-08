package models.bus;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class AgencyListHandler {
	private AgencyListHandler() {}
	
	public static List<Agency> parseAgencyListXml(Document xml) {
		NodeList nl = xml.getElementsByTagName("body").item(0).getChildNodes();
		List<Agency> list = new ArrayList<Agency>();
		
		for (int i = 0; i < nl.getLength(); i++) {
			
			if (nl.item(i).getNodeName().equals("agency")) {
				list.add(new Agency(nl.item(i).getAttributes().getNamedItem("tag").getNodeValue(), nl.item(i).getAttributes().getNamedItem("regionTitle").getNodeValue()));
			}		
		}
		
		return list;
	}
	
	public static List<Route> parseRouteListXml(Document xml, Agency agency) {
		NodeList nl = xml.getElementsByTagName("body").item(0).getChildNodes();
		
		List<Route> routes = new ArrayList<Route>();
		
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeName().equals("route")) {
				
				NamedNodeMap attributes = nl.item(i).getAttributes();
				Route r = new Route();
				
				r.setTag(attributes.getNamedItem("tag").getNodeValue());
				r.setTitle(attributes.getNamedItem("title").getNodeValue());
				r.setAgency(agency.tag);
				
				routes.add(r);
			}
		}
		
		return routes;
	}
	
	public static List parseRouteConfigXml(Document xml) {
		return null;
		//TODO
	}
}
