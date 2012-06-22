package org.opennaas.extensions.idb.ws.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.cxf.jaxws.EndpointImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkNotificationService;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkReservationService;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TopologyIFService;
import org.opennaas.extensions.idb.webservice.WebserviceControl;

public class WsTest {

	// publishing ws test
	EndpointImpl topologyEndpoint;
	EndpointImpl reservationEndpoint;
	EndpointImpl notificationEndpoint;
	static int port;

	@BeforeClass
	public static void startWS() throws IOException {

		port = (int) (1024 + Math.random() * 10000);
		WebserviceControl.startWebservices(port);
	}

	@Test
	public void topology() throws MalformedURLException {

		URL wsdl = new URL("http://localhost:" + port
				+ "/nsp/webservice/topology?wsdl");
		TopologyIFService s = new TopologyIFService(wsdl);
		s.getTopologyIFPort();
	}

	@Test
	public void notification() throws MalformedURLException {
		URL wsdl = new URL("http://localhost:" + port
				+ "/nsp/webservice/notification?wsdl");
		NetworkNotificationService s = new NetworkNotificationService(wsdl);
		s.getNetworkNotificationPortType();
	}

	@Test
	public void reservation() throws MalformedURLException {
		URL wsdl = new URL("http://localhost:" + port
				+ "/nsp/webservice/reservation?wsdl");
		NetworkReservationService s = new NetworkReservationService(wsdl);
		s.getNetworkReservationPortType();
	}

	@AfterClass
	public static void stopWS() {
		WebserviceControl.stopWebServices();
	}

}
