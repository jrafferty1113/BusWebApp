package services;

import java.util.List;

import models.bus.Agency;

public interface AgencyService {

	/*
	 * If we don't have agency list in db, then
	 * download list of agencies from 3rd-party
	 */
	public void loadAgencies();
	
	public void loadAgenciesByRegion(String region);
	
	/*
	 * Return all agencies stored
	 */
	public List<Agency> getAllAgencies();
	
	public List<Agency> getAgenciesByRegion(String region);

	public void addAgency(List<Agency> list);
	
	public void addAgency(Agency agency);
	
	public void removeAgencyByRegion(String region);
}
