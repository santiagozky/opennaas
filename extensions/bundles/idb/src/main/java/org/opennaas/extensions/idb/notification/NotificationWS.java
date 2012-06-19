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

package org.opennaas.extensions.idb.notification;

import javax.jws.WebService;

import org.opennaas.extensions.idb.notification.producer.NotificationProducerHandler;
import org.opennaas.extensions.idb.notification.producer.NotificationSubscriptionHandler;
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
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnsubscribeResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnsubscribeType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.TopicNotFoundFaultException;

/** Provision Request Handler. */
@WebService(portName = "idbNotificationCapabilityPort", serviceName = "idbNotificationCapabilityService", targetNamespace = "http://opennaas.org/ws")
public final class NotificationWS implements NetworkNotificationPortType {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.notification.handler.INotification#addTopic
	 * (org
	 * .opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicType)
	 */
	@Override
	public AddTopicResponseType addTopic(final AddTopicType element) {
		return NotificationProducerHandler.getInstance().addTopic(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.notification.handler.INotification#getTopics
	 * (org
	 * .opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetTopicsType)
	 */
	@Override
	public GetTopicsResponseType getTopics(final GetTopicsType element) {
		return NotificationProducerHandler.getInstance().getTopics(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.notification.handler.INotification#publish
	 * (org
	 * .opennaas.extensions.idb.serviceinterface.databinding.jaxb.PublishType)
	 */
	@Override
	public PublishResponseType publish(final PublishType element)
			throws TopicNotFoundFaultException {
		return NotificationProducerHandler.getInstance().publish(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.notification.handler.INotification#removeTopic
	 * (
	 * org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.RemoveTopicType
	 * )
	 */
	@Override
	public RemoveTopicResponseType removeTopic(final RemoveTopicType element) {
		return NotificationProducerHandler.getInstance().removeTopic(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.notification.handler.INotification#subscribe
	 * (org
	 * .opennaas.extensions.idb.serviceinterface.databinding.jaxb.SubscribeType)
	 */
	@Override
	public SubscribeResponseType subscribe(final SubscribeType element)
			throws TopicNotFoundFaultException {
		return NotificationSubscriptionHandler.getInstance().subscribe(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.notification.handler.INotification#unsubscribe
	 * (
	 * org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnsubscribeType
	 * )
	 */
	@Override
	public UnsubscribeResponseType unsubscribe(final UnsubscribeType element)
			throws TopicNotFoundFaultException {
		return NotificationSubscriptionHandler.getInstance().unsubscribe(
				element);
	}
}
