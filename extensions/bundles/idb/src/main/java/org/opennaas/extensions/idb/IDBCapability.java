package org.opennaas.extensions.idb;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.core.resources.ActivatorException;
import org.opennaas.core.resources.action.IAction;
import org.opennaas.core.resources.action.IActionSet;
import org.opennaas.core.resources.capability.AbstractCapability;
import org.opennaas.core.resources.capability.CapabilityException;
import org.opennaas.core.resources.descriptor.CapabilityDescriptor;
import org.opennaas.core.resources.descriptor.ResourceDescriptorConstants;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkNotificationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkReservationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TopologyIFPortType;
import org.opennaas.extensions.idb.webservice.ContextListener;
import org.opennaas.extensions.idb.webservice.TopologyWS;
import org.opennaas.extensions.queuemanager.IQueueManagerCapability;
import org.osgi.framework.ServiceRegistration;

public class IDBCapability extends AbstractCapability implements IIDBCapability {

	public static String CAPABILITY_TYPE = "idb";

	Log log = LogFactory.getLog(IDBCapability.class);

	private String resourceId = "";

	int port;
	ServiceRegistration topologyRegistration;
	ServiceRegistration notificationRegistration;
	ServiceRegistration reservationRegistration;
	ContextListener contextListener;

	public IDBCapability(CapabilityDescriptor descriptor, String resourceId) {
		super(descriptor);
		port = Integer.parseInt(this.getCapabilityDescriptor()
				.getProperty("port").toString());
		if (port < 1024) {
			log.equals("an IDB with a wrong port of: " + port
					+ " could not be created");
			throw new IllegalArgumentException(
					"the descriptor contains an invalid port or under 1024");
		}

		this.resourceId = resourceId;
		log.debug("Built new IDB Capability");
	}

	@Override
	public String getCapabilityName() {
		return CAPABILITY_TYPE;
	}

	@Override
	public IActionSet getActionSet() throws CapabilityException {

		String name = this.descriptor
				.getPropertyValue(ResourceDescriptorConstants.ACTION_NAME);
		String version = this.descriptor
				.getPropertyValue(ResourceDescriptorConstants.ACTION_VERSION);

		try {
			return Activator.getIDBActionSetService(name, version);
		} catch (ActivatorException e) {
			throw new CapabilityException(e);
		}
	}

	@Override
	public void queueAction(IAction action) throws CapabilityException {
		getQueueManager(resourceId).queueAction(action);
	}

	/**
	 * 
	 * @return QueuemanagerService this capability is associated to.
	 * @throws CapabilityException
	 *             if desired queueManagerService could not be retrieved.
	 */
	private IQueueManagerCapability getQueueManager(String resourceId)
			throws CapabilityException {
		try {
			return Activator.getQueueManagerService(resourceId);
		} catch (ActivatorException e) {
			throw new CapabilityException(
					"Failed to get QueueManagerService for resource "
							+ resourceId, e);
		}
	}

	@Override
	public void StartWebServices() {
		contextListener();
		startTopology();
		startNotification();
		startReservation();
	}

	@Override
	public void StopWebServices() {
		contextListener.contextDestroyed();
		topologyRegistration.unregister();
		notificationRegistration.unregister();
		reservationRegistration.unregister();

	}

	private void startTopology() {

		Dictionary props = new Hashtable();
		props.put("service.exported.interfaces", "*");
		props.put("service.exported.configs", "org.apache.cxf.ws");
		props.put("org.apache.cxf.ws.address", "http://localhost:" + port
				+ "/opennaas/" + "/nsp/webservice/topology");
		String[] features = { "org.apache.cxf.ws.addressing.WSAddressingFeature" };
		props.put("org.apache.cxf.ws.features", features);
		topologyRegistration = Activator.getContext().registerService(
				TopologyIFPortType.class.getName(), new TopologyWS(), props);

	}

	private void startNotification() {
		Dictionary props = new Hashtable();
		props.put("service.exported.interfaces", "*");
		props.put("service.exported.configs", "org.apache.cxf.ws");
		props.put("org.apache.cxf.ws.address", "http://localhost:" + port
				+ "/opennaas/" + "/nsp/webservice/notification");
		String[] features = { "org.apache.cxf.ws.addressing.WSAddressingFeature" };
		props.put("org.apache.cxf.ws.features", features);
		notificationRegistration = Activator.getContext().registerService(
				NetworkNotificationPortType.class.getName(), new TopologyWS(),
				props);

	}

	private void startReservation() {

		Dictionary props = new Hashtable();
		props.put("service.exported.interfaces", "*");
		props.put("service.exported.configs", "org.apache.cxf.ws");
		props.put("org.apache.cxf.ws.address", "http://localhost:" + port
				+ "/opennaas/" + "/nsp/webservice/reservation");
		String[] features = { "org.apache.cxf.ws.addressing.WSAddressingFeature" };
		props.put("org.apache.cxf.ws.features", features);
		reservationRegistration = Activator.getContext().registerService(
				NetworkReservationPortType.class.getName(), new TopologyWS(),
				props);
	}

	private void contextListener() {
		contextListener = new ContextListener();
		contextListener.contextInitialized(); // this used to be a
		// servletcontext listener. now
		// we call before starting the
		// ws

	}

}
