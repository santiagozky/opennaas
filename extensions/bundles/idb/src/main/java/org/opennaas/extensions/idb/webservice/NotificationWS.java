/**
 *  This code is part of the Harmony System implemented in Work Package 1 
 *  of the Phosphorus project. This work is supported by the European 
 *  Comission under the Sixth Framework Programme with contract number 
 *  IST-034115.
 *
 *  Copyright (C) 2006-2009 Phosphorus WP1 partners. Phosphorus Consortium.
 *  http://ist-phosphorus.eu/
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.opennaas.extensions.idb.webservice;

import javax.jws.WebService;

import org.w3c.dom.Element;

import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetTopicsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetTopicsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkNotificationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.PublishResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.PublishType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.RemoveTopicResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.RemoveTopicType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.SubscribeResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.SubscribeType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TopicNotFoundFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnsubscribeResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnsubscribeType;
import org.opennaas.extensions.idb.notification.NotificationRequestHandler;

@WebService(serviceName = "networkNotificationService", portName = "networkNotificationPortType", targetNamespace = "http://opennaas.org/nsp/webservice/notification")
public class NotificationWS implements NetworkNotificationPortType {
	NotificationRequestHandler handler;

	public NotificationWS() {
		handler = new NotificationRequestHandler();
	}

	@Override
	public GetTopicsResponseType getTopics(GetTopicsType getTopics) {
		return (GetTopicsResponseType) handler.handle(getTopics, "getTopics");
	}

	@Override
	public PublishResponseType publish(PublishType publish)
			throws TopicNotFoundFault_Exception {

		return (PublishResponseType) handler.handle(publish, "publish");
	}

	@Override
	public UnsubscribeResponseType unsubscribe(UnsubscribeType unsubscribe)
			throws TopicNotFoundFault_Exception {

		return (UnsubscribeResponseType) handler.handle(unsubscribe,
				"unsubscribe");
	}

	@Override
	public AddTopicResponseType addTopic(AddTopicType addTopic) {

		return (AddTopicResponseType) handler.handle(addTopic, "addTopic");
	}

	@Override
	public SubscribeResponseType subscribe(SubscribeType subscribe)
			throws TopicNotFoundFault_Exception {

		return (SubscribeResponseType) handler.handle(subscribe, "subscribe");
	}

	@Override
	public RemoveTopicResponseType removeTopic(RemoveTopicType removeTopic) {

		return (RemoveTopicResponseType) handler.handle(removeTopic,
				"removeTopic");
	}

}