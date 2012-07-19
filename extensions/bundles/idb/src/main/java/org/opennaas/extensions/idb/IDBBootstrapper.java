package org.opennaas.extensions.idb;

import org.opennaas.core.resources.IResource;
import org.opennaas.core.resources.IResourceBootstrapper;
import org.opennaas.core.resources.ResourceException;

public class IDBBootstrapper implements IResourceBootstrapper {

	@Override
	public void bootstrap(IResource resource) throws ResourceException {
		resource.setModel(new IDBModel());

	}

	@Override
	public void revertBootstrap(IResource resource) throws ResourceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetModel(IResource resource) throws ResourceException {
		// TODO Auto-generated method stub

	}

}
