package org.opennaas.extensions.idb.serviceinterface;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class EndpointReference {

	public static final String ANON_URI = "http://docs.oasis-open.org/ws-rx/wsmc/200702/anonymous";
	private final URI url;
	private URL wsdl;

	public EndpointReference(String url) throws URISyntaxException {
		this.url = new URI(url);
		try {
			URL a = new URL(url + "?wsdl");
			this.wsdl = a;
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}

	}

	public EndpointReference(URI url) {
		this.url = url;
		try {
			this.wsdl = new URL(url.toString() + "?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return url.toString();
	}

	public URI getURI() {
		return url;
	}

	public URL getWSDL() {
		return wsdl;
	}
}
