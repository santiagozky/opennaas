package org.opennaas.extensions.idb;

import org.opennaas.core.resources.capability.ICapability;

public interface IIDBCapability extends ICapability {

	/**
	 * start the webservices under the given port
	 * 
	 * @param port
	 *            to start the service
	 */
	public void StartWebServices(int port);

	/**
	 * stop the webservices
	 */
	public void StopWebServices();

}
