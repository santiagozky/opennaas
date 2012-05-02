package org.opennaas.extensions.idb.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

import org.opennaas.core.resources.IResource;
import org.opennaas.core.resources.shell.GenericKarafCommand;

import org.opennaas.extensions.idb.notification.handler.NotificationRequestHandler;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicType;
import org.opennaas.extensions.network.model.NetworkModel;
import org.opennaas.extensions.network.model.topology.Interface;
import org.opennaas.extensions.network.model.topology.Link;

import static com.google.common.collect.Iterables.filter;

@Command(scope = "IDB", name = "addTopic", description = "Add topic")
public class AddTopicCommand extends GenericKarafCommand {
	@Argument(index = 0, name = "resourceType:resourceName", description = "The topic name", required = true, multiValued = false)
	private String topicName;

	@Override
	protected Object doExecute() throws Exception {
		printInitCommand("l2bod:links");

		try {
			AddTopicType type = new AddTopicType();
			type.setTopic(topicName);
			NotificationRequestHandler handler = NotificationRequestHandler
					.getInstance();
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