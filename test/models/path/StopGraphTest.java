package models.path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import models.bus.Direction;

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
		Document xml = XmlHandler.getXml("test/resources/Route38.xml");
		NodeList nl = xml.getElementsByTagName("body").item(0).getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeName().equals("route")) {
				
				NamedNodeMap attributes = nl.item(i).getAttributes();
				System.out.println(attributes.getNamedItem("tag")); // 38
				System.out.println(attributes.getNamedItem("title")); // 38-Geary
				
				
			}
			System.out.println("i=" + i);
		}
		System.out.println(nl.item(1));
		
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
