package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import play.Play;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

@Service
public class ExternalCallServiceImpl implements ExternalCallService {
//	private WSClient ws = WS.client();
	
//	@Autowired
//	private WSClient ws;
	
	
	@Override
	public Document getAgencyListXml() {
		Promise<WSResponse> responsePromise = WS.url(Play.application().configuration().getString("nextbus.agencylist")).get();
		play.Logger.info("Getting Agency List from" + Play.application().configuration().getString("nextbus.agencylist"));
		return responsePromise.get(Play.application().configuration().getInt("external.timeout")).asXml();
	}
	
	@Override
	public Document getRouteListXml(String agency) {
		Promise<WSResponse> responsePromise = WS.url(Play.application().configuration().getString("nextbus.routelist") + "&a=" + agency).get();
		play.Logger.info("Getting Route List from" + Play.application().configuration().getString("nextbus.routelist") + "&a=" + agency);
		return responsePromise.get(Play.application().configuration().getInt("external.timeout")).asXml();
	}
	
	@Override
	public Document getRouteConfigXml(String agency, String route) {
		String url = Play.application().configuration().getString("nextbus.routeconfig");
		url = url + "&a=" + agency + "&r=" + route;
		Promise<WSResponse> responsePromise = WS.url(url).get();
		play.Logger.info("Getting Route Config from" + url);
		return responsePromise.get(Play.application().configuration().getInt("external.timeout")).asXml();
	}
}
