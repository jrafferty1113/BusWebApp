package services;

import org.w3c.dom.Document;

public interface ExternalCallService {
	public Document getAgencyListXml();
	
	public Document getRouteListXml();
	public Document getRouteConfigXml();
}
