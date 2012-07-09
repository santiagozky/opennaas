package org.opennaas.extensions.idb.da.dummy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.core.resources.ActivatorException;
import org.opennaas.core.resources.action.IAction;
import org.opennaas.core.resources.action.IActionSet;
import org.opennaas.core.resources.capability.AbstractCapability;
import org.opennaas.core.resources.capability.CapabilityException;
import org.opennaas.core.resources.descriptor.CapabilityDescriptor;
import org.opennaas.core.resources.descriptor.ResourceDescriptorConstants;
import org.opennaas.extensions.queuemanager.IQueueManagerCapability;

public class AdapterDummyCapability extends AbstractCapability implements
		IAdapterDummyCapability {

	public static String CAPABILITY_TYPE = "adapterdummy";

	Log log = LogFactory.getLog(AdapterDummyCapability.class);

	private final String resourceId;

	public AdapterDummyCapability(CapabilityDescriptor descriptor,
			String resourceId) {
		super(descriptor);
		this.resourceId = resourceId;
		log.debug("Built new adapter dummy Capability");
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
			return Activator.getDummyAdapterActionSetService(name, version);
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
}
