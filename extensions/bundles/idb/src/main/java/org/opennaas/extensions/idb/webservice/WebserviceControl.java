package org.opennaas.extensions.idb.webservice;

import java.util.HashMap;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.addressing.WSAddressingFeature;

import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkNotificationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkReservationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TopologyIFPortType;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * this class can start and stop the three IDB webservices.
 * 
 * @author santiago
 * 
 */
public class WebserviceControl {

	public static HashMap<Integer, WebServiceHolder> webservices = new HashMap<Integer, WebServiceHolder>();

	public static void startWebservices(int port) {
		if (port < 1024) {
			throw new IllegalArgumentException(
					"the port should be over 1024 or higher");
		}

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
	ApplicationContext springContext;

	public WebServiceHolder(int i) {
		port = i;
	}

	public void start() {
		startTopology();
		startNotification();
		startReservation();
		startContext();

	}

	public void stop() {
		topologyEndpoint.stop();
		notificationEndpoint.stop();
		reservationEndpoint.stop();
		springContext = null;
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

	private void startContext() {
		springContext = new ClassPathXmlApplicationContext("context.xml") {
			@Override
			protected void initBeanDefinitionReader(
					XmlBeanDefinitionReader reader) {
				super.initBeanDefinitionReader(reader);
				reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);
				reader.setBeanClassLoader(getClassLoader());
			}
		};
	}

}