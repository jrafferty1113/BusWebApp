package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import play.Logger;
import models.Bar;
import models.bus.Agency;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private ExternalCallService externalCallService;
	
	@Override
	public void updateAgenciesByRegion(String region) {

		Document agencyList = externalCallService.getAgencyListXml();
		NodeList nl = agencyList.getElementsByTagName("body").item(0).getChildNodes();
		List<Agency> list = new ArrayList<Agency>();
		
		for (int i = 0; i < nl.getLength(); i++) {
			
			if (nl.item(i).getNodeName().equals("agency") && nl.item(i).getAttributes().getNamedItem("regionTitle").getNodeValue().equals(region)) {
				list.add(new Agency(nl.item(i).getAttributes().getNamedItem("tag").getNodeValue(), nl.item(i).getAttributes().getNamedItem("regionTitle").getNodeValue()));
			}		
		}
		
		
		removeAgencyByRegion(region);
		addAgency(list);
		
		
	}
	
	@Override
	public void addAgency(List<Agency> list) {
		for (Agency a: list) {
			em.persist(a);
		}
	}
	
	@Override
	public void removeAgencyByRegion(String region) {
		int cnt = em.createQuery("DELETE FROM Agency WHERE regionTitle=\'" +region + "\'").executeUpdate();
		play.Logger.error(cnt + " deleted");
	}
	
	@Override
	public List<Agency> getAgenciesByRegion(String region) {
		play.Logger.error("Trying to get agencies");
		CriteriaQuery<Agency> c = em.getCriteriaBuilder().createQuery(Agency.class);
		play.Logger.error("Trying to get agencies still");
        c.from(Agency.class);
        
        play.Logger.error("Finally you asshole");
        return em.createQuery(c).setParameter("regionTitle", region).getResultList();
        
	}

	@Override
	public List<Agency> getAllAgencies() {
		play.Logger.error("Trying to get agencies");
		CriteriaQuery<Agency> c = em.getCriteriaBuilder().createQuery(Agency.class);
        c.from(Agency.class);
        List<Agency> list = em.createQuery(c).getResultList();
        return list;    
	}

	@Override
	public void addAgency(Agency agency) {
		em.persist(agency);
		
	}

	

}
