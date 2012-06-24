package org.opennaas.extensions.idb.webservice;

import java.util.HashMap;

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

	public static HashMap<Integer, WebServiceHolder> webservices = new HashMap<Integer, WebServiceHolder>();

	public static void startWebservices(int port) {

		WebServiceHolder holder = new WebServiceHolder(port);
		holder.start();
		webservices.put(port, holder);

	}

	public static void stopWebServices(int port) {
		WebServiceHolder holder = webservices.get(port);
		if (holder != null) {
			holder.stop();
		}
	}

}

class WebServiceHolder {

	int port;
	EndpointImpl topologyEndpoint;
	EndpointImpl notificationEndpoint;
	EndpointImpl reservationEndpoint;
	ContextListener contextListener;

	public WebServiceHolder(int i) {
		port = i;
	}

	public void start() {
		startTopology();
		startNotification();
		startReservation();

	}

	public void stop() {
		topologyEndpoint.stop();
		notificationEndpoint.stop();
		reservationEndpoint.stop();
	}

	private void startTopology() {
		TopologyIFPortType impl = new TopologyWS();
		String addressTopology = "http://localhost:" + port
				+ "/nsp/webservice/topology";
		topologyEndpoint = (EndpointImpl) Endpoint.create(impl);
		topologyEndpoint.getFeatures().add(new WSAddressingFeature());
		topologyEndpoint.publish(addressTopology);
	}

	private void startNotification() {
		NetworkNotificationPortType notification = new NotificationWS();
		String addressNotification = "http://localhost:" + port
				+ "/nsp/webservice/notification";
		notificationEndpoint = (EndpointImpl) Endpoint.create(notification);
		notificationEndpoint.getFeatures().add(new WSAddressingFeature());
		notificationEndpoint.publish(addressNotification);

	}

	private void startReservation() {

		NetworkReservationPortType reservation = new ReservationWS();
		String addressReservation = "http://localhost:" + port
				+ "/nsp/webservice/reservation";
		reservationEndpoint = (EndpointImpl) Endpoint.create(reservation);
		reservationEndpoint.getFeatures().add(new WSAddressingFeature());
		reservationEndpoint.publish(addressReservation);
	}

	private void contextListener() {
		contextListener = new ContextListener();
		contextListener.contextInitialized(); // this used to be a
		// servletcontext listener. now
		// we call before starting the
		// ws

	}

}
