package org.opennaas.extensions.idb.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.opennaas.core.resources.shell.GenericKarafCommand;
import org.opennaas.extensions.idb.webservice.WebserviceControl;

@Command(scope = "idb", name = "stopWebservice", description = "Stop IDB Webservices")
public class StopWebservices extends GenericKarafCommand {
	@Argument(index = 0, name = "portname", description = "port number", required = true, multiValued = false)
	private int portNumber;

	@Override
	protected Object doExecute() throws Exception {
		try {
			WebserviceControl.stopWebServices(portNumber);
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}

}