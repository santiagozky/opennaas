package org.opennaas.extensions.idb.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.opennaas.core.resources.shell.GenericKarafCommand;
import org.opennaas.extensions.idb.notification.NotificationRequestHandler;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkNotificationPortType;

@Command(scope = "idb", name = "addTopic", description = "Add topic")
public class AddTopicCommand extends GenericKarafCommand {
	@Argument(index = 0, name = "resourceType:resourceName", description = "The topic name", required = true, multiValued = false)
	private String topicName;

	@Override
	protected Object doExecute() throws Exception {
		printInitCommand("IDB addTopic");

		try {
			AddTopicType type = new AddTopicType();
			type.setTopic(topicName);
			NetworkNotificationPortType handler = new NotificationRequestHandler();
			AddTopicResponseType response = handler.addTopic(type);
			if (response.isResult()) {
				printInfo("topic added");
			} else {
				printInfo("topic not added");
			}
			return response.isResult();

		} catch (Exception e) {
			printError("Error listing links of  " + topicName);
			printError(e);
		} finally {
			printEndCommand();
		}
		return null;
	}
}