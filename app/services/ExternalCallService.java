package services;

import org.w3c.dom.Document;

public interface ExternalCallService {
	public Document getAgencyListXml();
	
	public Document getRouteListXml(String agency);
	
	public Document getRouteConfigXml(String agency, String route);

}
