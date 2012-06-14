package org.opennaas.extensions.idb.serviceinterface;

import java.net.URI;
import java.net.URISyntaxException;

public class EndpointReference {

	public static final String ANON_URI = "http://docs.oasis-open.org/ws-rx/wsmc/200702/anonymous";
	private final URI url;

	public EndpointReference(String wsdl) throws URISyntaxException {
		this.url = new URI(wsdl);

	}

	public EndpointReference(URI url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url.toString();
	}

	public URI getURI() {
		return url;
	}

}
