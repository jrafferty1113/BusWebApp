package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import models.bus.Agency;
import models.bus.Route;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private ExternalCallService externalCallService;
	
	@Override
	public void loadAgencies() {
		List<Agency> list = getAllAgencies();
		if (list.size() != 0) return;
		
		Document agencyList = externalCallService.getAgencyListXml();
		NodeList nl = agencyList.getElementsByTagName("body").item(0).getChildNodes();
		list = new ArrayList<Agency>();
		
		for (int i = 0; i < nl.getLength(); i++) {
			
			if (nl.item(i).getNodeName().equals("agency")) {
				list.add(new Agency(nl.item(i).getAttributes().getNamedItem("tag").getNodeValue(), nl.item(i).getAttributes().getNamedItem("regionTitle").getNodeValue()));
			}		
		}
		addAgency(list);
	}
	
	@Override
	public void loadAgenciesByRegion(String region) {
		List<Agency> list = getAgenciesByRegion(region);
		if (list.size() != 0) return;
		
		loadAgencies();
	}
	
	@Override
	public void loadRoutes(Agency agency) {
		List<Route> list = getAllRoutes();
		if (list.size() != 0) return;
		
		Document xml = externalCallService.getRouteListXml(agency.tag);
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
		
		addRoute(routes);
	}
	
	@Override
	public void removeAgencyByRegion(String region) {
		em.createQuery("DELETE FROM Agency WHERE regionTitle=\'" +region + "\'").executeUpdate();
	}
	
	@Override
	public List<Agency> getAgenciesByRegion(String region) {
		CriteriaQuery<Agency> c = em.getCriteriaBuilder().createQuery(Agency.class);
		c.from(Agency.class);
        
		return em.createQuery(c).setParameter("regionTitle", region).getResultList();
	}

	@Override
	public List<Agency> getAllAgencies() {
		CriteriaQuery<Agency> c = em.getCriteriaBuilder().createQuery(Agency.class);
        c.from(Agency.class);
        List<Agency> list = em.createQuery(c).getResultList();
        return list;    
	}

	@Override
	public void addAgency(Agency agency) {
		em.persist(agency);
	}	
	
	@Override
	public void addAgency(List<Agency> list) {
		for (Agency a: list) {
			em.persist(a);
		}
	}
	
	@Override
	public List<Route> getAllRoutes() {
		CriteriaQuery<Route> c = em.getCriteriaBuilder().createQuery(Route.class);
        c.from(Route.class);
        List<Route> list = em.createQuery(c).getResultList();
        
        return list; 
	}
	
	@Override
	public List<Route> getRoutes(String agency) {
		CriteriaQuery<Route> c = em.getCriteriaBuilder().createQuery(Route.class);
		c.from(Route.class);
        
		return em.createQuery(c).setParameter("agency", agency).getResultList();
	}
	
	@Override
	public List<Route> getRoutes(List<Agency> list) {
		List<Route> rList = new ArrayList<Route>();
		for (Agency a: list) {
			rList.addAll(getRoutes(a.tag));
		}
		
		return rList;
	}

	@Override
	public void addRoute(Route route) {
		em.persist(route);
	}

	@Override
	public void addRoute(List<Route> list) {
		for (Route r: list)
			em.persist(r);
	}
}
