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

/**
 * these tests test two things: abilitiy to public web services abilitiy to
 * public them in multiple ports
 * 
 * @author santiago
 * 
 */
public class WsTest {

	// publishing ws test
	EndpointImpl topologyEndpoint;
	EndpointImpl reservationEndpoint;
	EndpointImpl notificationEndpoint;
	static int port1;
	static int port2;

	@BeforeClass
	public static void startWS() throws IOException {

		port1 = (int) (1024 + Math.random() * 10000);
		port2 = (int) (1024 + Math.random() * 10000);
		while (port1 == port2) {
			port1 = (int) (1024 + Math.random() * 10000);
		}

		WebserviceControl.startWebservices(port1);
		WebserviceControl.startWebservices(port2);

	}

	@Test
	public void topology() throws MalformedURLException {

		URL wsdl = new URL("http://localhost:" + port1
				+ "/nsp/webservice/topology?wsdl");
		TopologyIFService s = new TopologyIFService(wsdl);
		s.getTopologyIFPort();

		URL wsdl2 = new URL("http://localhost:" + port2
				+ "/nsp/webservice/topology?wsdl");
		TopologyIFService s2 = new TopologyIFService(wsdl2);
		s2.getTopologyIFPort();
	}

	@Test
	public void notification() throws MalformedURLException {
		URL wsdl = new URL("http://localhost:" + port1
				+ "/nsp/webservice/notification?wsdl");
		NetworkNotificationService s = new NetworkNotificationService(wsdl);
		s.getNetworkNotificationPortType();

		URL wsdl2 = new URL("http://localhost:" + port2
				+ "/nsp/webservice/notification?wsdl");
		NetworkNotificationService s2 = new NetworkNotificationService(wsdl2);
		s2.getNetworkNotificationPortType();
	}

	@Test
	public void reservation() throws MalformedURLException {
		URL wsdl = new URL("http://localhost:" + port1
				+ "/nsp/webservice/reservation?wsdl");
		NetworkReservationService s = new NetworkReservationService(wsdl);
		s.getNetworkReservationPortType();

		URL wsdl2 = new URL("http://localhost:" + port2
				+ "/nsp/webservice/reservation?wsdl");
		NetworkReservationService s2 = new NetworkReservationService(wsdl2);
		s2.getNetworkReservationPortType();
	}

	@AfterClass
	public static void stopWS() {
		WebserviceControl.stopWebServices(port1);
		WebserviceControl.stopWebServices(port2);
	}

}
