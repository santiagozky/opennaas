package org.opennaas.extensions.idb.webservice;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.addressing.WSAddressingFeature;
import org.opennaas.extensions.idb.notification.NotificationWS;
import org.opennaas.extensions.idb.reservation.ReservationWS;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkNotificationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkReservationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TopologyIFPortType;
import org.opennaas.extensions.idb.topology.TopologyWS;

/**
 * this class can start and stop the three IDB webservices.
 * 
 * @author santiago
 * 
 */
public class WebserviceControl {

	public static EndpointImpl topologyEndpoint;

	public static EndpointImpl notificationEndpoint;

	public static EndpointImpl reservationEndpoint;

	private static ContextListener contextListener;

	public static void startWebservices(int port) {

		contextListener(); // the ex-servletcontext listener
		startNotification(port);
		startTopology(port);
		startReservation(port);

	}

	public static void stopWebServices() {

		notificationEndpoint.stop();
		reservationEndpoint.stop();
		topologyEndpoint.stop();
	}

	private static void startTopology(int port) {
		TopologyIFPortType impl = new TopologyWS();
		String addressTopology = "http://localhost:" + port
				+ "/nsp/webservice/topology";
		topologyEndpoint = (EndpointImpl) Endpoint.create(impl);
		topologyEndpoint.getFeatures().add(new WSAddressingFeature());
		topologyEndpoint.publish(addressTopology);
	}

	private static void startNotification(int port) {
		NetworkNotificationPortType notification = new NotificationWS();
		String addressNotification = "http://localhost:" + port
				+ "/nsp/webservice/notification";
		notificationEndpoint = (EndpointImpl) Endpoint.create(notification);
		notificationEndpoint.getFeatures().add(new WSAddressingFeature());
		notificationEndpoint.publish(addressNotification);
	}

	private static void startReservation(int port) {

		NetworkReservationPortType reservation = new ReservationWS();
		String addressReservation = "http://localhost:" + port
				+ "/nsp/webservice/reservation";
		reservationEndpoint = (EndpointImpl) Endpoint.create(reservation);
		reservationEndpoint.getFeatures().add(new WSAddressingFeature());
		reservationEndpoint.publish(addressReservation);
	}

	private static void contextListener() {
		contextListener = new ContextListener();
		contextListener.contextInitialized(); // this used to be a
												// servletcontext listener. now
												// we call before starting the
												// ws

	}
}
