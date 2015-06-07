package services;

import java.util.List;

import models.bus.Agency;

public interface AgencyService {

	/*
	 * Download list of agencies from 3rd-party
	 * For now we only focus on California North
	 */
	public void updateAgenciesCalNorth();
	
	/*
	 * Return all agencies stored
	 */
	public List<Agency> getAllAgencies();
	
	public List<Agency> getAgenciesByRegion(String region);
}
