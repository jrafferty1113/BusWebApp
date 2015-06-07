package models.path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import models.bus.Direction;
import models.bus.Route;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import services.RouteService;
import util.XmlHandler;
import static org.fest.assertions.Assertions.assertThat;

public class StopGraphTest {
	private StopGraph g;
	
	@Autowired
	private RouteService routeService;
	
	@Before
	public void before() {
		System.out.println("Test");
		Document doc = XmlHandler.getXml("test/resources/Route38.xml");
		//Set<Direction> directions = routeService.parseRouteXml(doc);
		Set<Direction> directions = new HashSet<Direction>();
		directions.add(new Direction(new ArrayList()));
		g = new StopGraphMock(directions);
		//TODO
		

	}
	
	@Test
	public void addNewDirectionTest() {
		
	}
	
	@Test
	public void withInWalkingDistanceComparatorTest() {
		TreeSet<StopNode> ts = new TreeSet<StopNode>(g.new CordinateGradientComparator());
		ts.add(new StopNode(1, 3)); // 10
		ts.add(new StopNode(2, 2)); // 8
		ts.add(new StopNode(3, 2)); // 13
		ts.add(new StopNode(0, 0)); // 0
		assertThat(ts.subSet(new StopNode(2, 2), true, new StopNode(3, 2), true).size() == 2);
	}
	
	public static void main(String[] arg) {
		System.out.println("Test");
//		Document xml = XmlHandler.getXml("test/resources/Route38.xml");
		Document xml = XmlHandler.getXml("test/resources/RouteConfig-sf-muni.xml");
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

		
	}
	
	public class StopGraphMock extends StopGraph {

		public StopGraphMock(Set<Direction> directions) {
			super(directions);
		}
		
		public TreeSet<StopNode> getAllNodes() {
			return ts;
		}
		
	}

}
