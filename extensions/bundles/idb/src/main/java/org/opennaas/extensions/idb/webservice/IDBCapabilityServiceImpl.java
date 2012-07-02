package org.opennaas.extensions.idb.webservice;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.core.resources.IResource;
import org.opennaas.core.resources.ResourceException;
import org.opennaas.core.resources.capability.CapabilityException;

import org.opennaas.extensions.idb.IIDBCapability;
import org.opennaas.extensions.ws.impl.GenericCapabilityService;

/**
 * @author Santiago García Pimentel
 */
@WebService(portName = "IDBCapabilityPort", serviceName = "IDBCapabilityService", targetNamespace = "http:/www.opennaas.org/ws")
public class IDBCapabilityServiceImpl extends GenericCapabilityService
		implements IIDBCapabilityService {

	Log log = LogFactory.getLog(IDBCapabilityServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.webservice.IIDB.CapabilityService#startIDB
	 * (java.lang.String)
	 */
	@Override
	public void startIDB(String resourceId) throws CapabilityException {
		try {
			log.info("start startIDB call");
			IIDBCapability idbCapability = (IIDBCapability) getCapability(
					resourceId, IIDBCapability.class);
			idbCapability.StartWebServices();

		} catch (CapabilityException e) {
			log.error(e);
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.webservice.IIDB.CapabilityService#stopIDB
	 * (java.lang.String)
	 */
	@Override
	public void stopIDB(String resourceId) throws CapabilityException {
		try {
			log.info("start stopIDB call");

			IIDBCapability idbCapability = (IIDBCapability) getCapability(
					resourceId, IIDBCapability.class);
			idbCapability.StartWebServices();
		} catch (CapabilityException e) {
			log.error(e);
			throw e;
		}

	}

}
