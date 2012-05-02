package org.opennaas.extensions.idb;

import java.util.Vector;

import org.opennaas.extensions.queuemanager.IQueueManagerService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.core.resources.ActivatorException;
import org.opennaas.core.resources.action.IAction;
import org.opennaas.core.resources.action.IActionSet;
import org.opennaas.core.resources.capability.AbstractCapability;
import org.opennaas.core.resources.capability.CapabilityException;
import org.opennaas.core.resources.command.Response;
import org.opennaas.core.resources.descriptor.CapabilityDescriptor;
import org.opennaas.core.resources.descriptor.ResourceDescriptorConstants;

public class IDBCapability extends AbstractCapability {

	public static String CAPABILITY_NAME = "IDB";

	Log log = LogFactory.getLog(IDBCapability.class);

	private String resourceId = "";

	public IDBCapability(CapabilityDescriptor descriptor, String resourceId) {

		super(descriptor);
		this.resourceId = resourceId;
		log.debug("Built new IDB Capability");
	}

	@Override
	public Object sendMessage(String idOperation, Object params) {

		log.debug("Sending message to IDB Capability");
		try {
			IQueueManagerService queueManager = Activator
					.getQueueManagerService(resourceId);
			IAction action = createAction(idOperation);
			action.setParams(params);
			action.setModelToUpdate(resource.getModel());
			queueManager.queueAction(action);

		} catch (Exception e) {
			Vector<String> errorMsgs = new Vector<String>();
			errorMsgs
					.add(e.getMessage() + ":" + '\n' + e.getLocalizedMessage());
			return Response.errorResponse(idOperation, errorMsgs);
		}

		return Response.okResponse(idOperation);
	}

	@Override
	protected void initializeCapability() throws CapabilityException {

	}

	@Override
	protected void activateCapability() throws CapabilityException {

	}

	@Override
	protected void deactivateCapability() throws CapabilityException {

	}

	@Override
	protected void shutdownCapability() throws CapabilityException {

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

}
