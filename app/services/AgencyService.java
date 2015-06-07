package services;

import java.util.List;

import models.bus.Agency;

public interface AgencyService {

	/*
	 * Download list of agencies from 3rd-party
	 * For now we only focus on California North
	 */
	public void updateAgenciesByRegion(String region);
	
	/*
	 * Return all agencies stored
	 */
	public List<Agency> getAllAgencies();
	
	public List<Agency> getAgenciesByRegion(String region);

	public void addAgency(List<Agency> list);
	
	public void addAgency(Agency agency);

	
	
	public void removeAgencyByRegion(String region);
}
