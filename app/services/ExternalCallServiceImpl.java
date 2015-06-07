package services;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import play.Play;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

@Service
public class ExternalCallServiceImpl implements ExternalCallService {
	private WSClient ws = WS.client();
	private String urlAgencyList = Play.application().configuration().getString("nextbus.agencylist");
	private int timeout = Play.application().configuration().getInt("external.timeout");
	
	@Override
	public Document getAgencyListXml() {
		Promise<WSResponse> responsePromise = ws.url(urlAgencyList).get();	
		return responsePromise.get(timeout).asXml();
	}
}
