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

import models.bus.Agency;
import models.bus.AgencyListHandler;
import models.bus.Route;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private ExternalCallService externalCallService;
	
	/* ======================================================
	 * AgencyList Related Methods
	 * ======================================================
	 */
	
	@Override
	public List<Agency> getAllAgencies() {
		// Query db for all agencies
		CriteriaQuery<Agency> c = em.getCriteriaBuilder().createQuery(Agency.class);
        c.from(Agency.class);
        List<Agency> list = em.createQuery(c).getResultList();
        
        // Doesn't exist in db
        if (list.size() != 0) return list;
		
        // Call external url to get xml, then parse them
		list = AgencyListHandler.parseAgencyListXml(externalCallService.getAgencyListXml());
		
		// Persist in db
		addAgencies(list);
        return list;    
	}
	
	@Override
	public List<Agency> getAgenciesByRegion(String region) {
		// Query db for all agencies
		CriteriaQuery<Agency> c = em.getCriteriaBuilder().createQuery(Agency.class);
		c.from(Agency.class);
		List<Agency> list = em.createQuery(c).setParameter("regionTitle", region).getResultList();
		
		// Doesn't exist in db
		if (list.size() != 0) return list;
		
		// Call external url to get xml, then parse them
		list = AgencyListHandler.parseAgencyListXml(externalCallService.getAgencyListXml());
		
		List<Agency> res = new ArrayList<Agency>();
			
		// Persist in db
		addAgencies(list);
		
		for (Agency a: list) {
			if (a.regionTitle.equalsIgnoreCase(region)) {
				res.add(a);
			}
		}
		
        return res;    
	}

	@Override
	public void addAgencies(List<Agency> list) {
		for (Agency a: list) {
			em.persist(a);
		}
	}
	
	@Override
	public void addAgency(Agency agency) {
		em.persist(agency);
	}	
	
	/* ======================================================
	 * RouteList Related Methods
	 * ======================================================
	 */

	
	@Override
	public List<Route> getAllRoutes() {
		// Query db for all routes
		CriteriaQuery<Route> c = em.getCriteriaBuilder().createQuery(Route.class);
        c.from(Route.class);
        List<Route> list = em.createQuery(c).getResultList();
		
		if (list.size() != 0) return list;
		
		List<Agency> allAgencies = this.getAllAgencies();
		
		for (Agency agency: allAgencies) {
			Document xml = externalCallService.getRouteListXml(agency.tag);
			list.addAll(AgencyListHandler.parseRouteListXml(xml, agency));
		}
		
		addRoutes(list);
		
        return list; 
	}
	
	@Override
	public List<Route> getRoutesByAgency(Agency agency) {
		CriteriaQuery<Route> c = em.getCriteriaBuilder().createQuery(Route.class);
		c.from(Route.class);
        
		List<Route> list = em.createQuery(c).setParameter("agency", agency.tag).getResultList();
		
		if (list.size() != 0) return list;
		
		Document xml = externalCallService.getRouteListXml(agency.tag);
		list.addAll(AgencyListHandler.parseRouteListXml(xml, agency));
		
		if (list.size() != 0) {
			addRoutes(list);    
		} else {
			play.Logger.error("AgencyService.getRoutesByAgency recevied empty list of agencies");
		}
		return list;
	}

	@Override
	public void addRoute(Route route) {
		em.persist(route);
	}

	@Override
	public void addRoutes(List<Route> list) {
		for (Route r: list)
			em.persist(r);
	}
}
