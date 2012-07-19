package org.opennaas.extensions.idb.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.opennaas.core.resources.IResource;
import org.opennaas.core.resources.shell.GenericKarafCommand;
import org.opennaas.extensions.idb.IDBCapability;

@Command(scope = "idb", name = "controlIDB", description = "control IDB")
public class ControlIDB extends GenericKarafCommand {
	@Argument(index = 0, name = "resourceType:resourceName", description = "the resource id", required = true, multiValued = false)
	private String resourceId;

	@Argument(index = 1, description = "on or off", required = true, multiValued = false)
	private String on;

	@Override
	protected Object doExecute() throws Exception {
		printInitCommand("IDB addTopic");
		on = on.toLowerCase();
		boolean start = false;
		if (on.equals("on")) {
			start = true;
		} else if (on.equals("off")) {
			start = false;
		} else {
			throw new IllegalArgumentException(
					"you must provide an on/off value");
		}
		try {
			if (start) {

				IResource resource = getResourceFromFriendlyName(resourceId);
				IDBCapability idbCapability = (IDBCapability) resource
						.getCapabilityByType("idb");
				idbCapability.StartWebServices();
			} else {
				IResource resource = getResourceFromFriendlyName(resourceId);
				IDBCapability idbCapability = (IDBCapability) resource
						.getCapabilityByType("idb");
				idbCapability.StopWebServices();
			}

		} catch (Exception e) {

			printError(e);
		} finally {
			printEndCommand();
		}
		return null;
	}
}