package org.opennaas.extensions.idb.webservice;

import org.opennaas.core.resources.capability.CapabilityException;

public interface IIDBCapabilityService {

	void startIDB(String resourceId) throws CapabilityException;

	void stopIDB(String resourceId) throws CapabilityException;

}
