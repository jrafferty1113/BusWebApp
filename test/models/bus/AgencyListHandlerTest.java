package models.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.w3c.dom.Document;

import util.XmlHandler;
import base.TestDataConfig;
import configs.AppConfig;

@ContextConfiguration(classes={AppConfig.class, TestDataConfig.class})
public class AgencyListHandlerTest {

	@Before
	public void init() {
//		Route nx = new Route("NX", "NX-Express");
//		Route kt = new Route("KT", "KT-Ingleside/Third Street");
	}

	@Test
	public void parseAgencyListXmlTest() {
		Document xml = XmlHandler.getXml("test/resources/AgencyList.xml");
		List<Agency> agencies = AgencyListHandler.parseAgencyListXml(xml);
		assertTrue(agencies.size() != 0);
	}

	@Test
	public void parseRouteListXmlTest() {
		Document xml = XmlHandler.getXml("test/resources/RouteList-sf-muni.xml");
		List<Route> routes = AgencyListHandler.parseRouteListXml(xml, new Agency("sf-muni", "Carlifornia-Northen"));
		int numOfNX = 0;
		for (Route r: routes) {
			if (r.getTag().equals("NX")) {
				numOfNX++;
			}
		}
		assertTrue(numOfNX == 1);
		
		xml = XmlHandler.getXml("test/resources/RouteList-Error-Invalid-agency.xml");
		routes = AgencyListHandler.parseRouteListXml(xml, new Agency("sf-muni", "Carlifornia-Northen"));
		assertTrue(routes.size() == 0);
	}
}
