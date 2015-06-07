package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import play.Logger;
import models.bus.Agency;

@Service
@Transactional
public class AgencyServiceImpl implements AgencyService {

	@PersistenceContext
    EntityManager em;
	
	@Autowired
	private ExternalCallService externalCallService;
	
	@Override
	public void updateAgenciesCalNorth() {
		// "California-Northern"
		Document agencyList = externalCallService.getAgencyListXml();
		NodeList nl = agencyList.getElementsByTagName("body").item(0).getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i).getNodeName().equals("agency"))
				Logger.error(nl.item(i).getAttributes().getNamedItem("tag").getNodeValue());
		}
	}
	
	@Override
	public List<Agency> getAgenciesByRegion(String region) {
		return null;
	}

	@Override
	public List<Agency> getAllAgencies() {
		CriteriaQuery<Agency> c = em.getCriteriaBuilder().createQuery(Agency.class);
        c.from(Agency.class);
        return em.createQuery(c).getResultList();
	}

}
