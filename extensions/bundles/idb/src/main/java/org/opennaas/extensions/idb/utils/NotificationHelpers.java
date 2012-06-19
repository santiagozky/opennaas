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

package org.opennaas.extensions.idb.utils;

import java.util.MissingResourceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.extensions.idb.Constants;
import org.opennaas.extensions.idb.database.hibernate.Domain;
import org.opennaas.extensions.idb.exception.database.DatabaseException;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.RemoveTopicResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.RemoveTopicType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.SubscribeResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.SubscribeType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TopicNotFoundFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.notification.SimpleNotificationClient;
import org.opennaas.extensions.idb.serviceinterface.utils.Config;

public class NotificationHelpers {
	/** Logger. */
	private static Log logger = LogFactory.getLog(NotificationHelpers.class);

	/**
	 * method to create a topic in the according notification-WS
	 * 
	 * @param reservationID
	 *            IDB-reservationID
	 */
	public static void createTopicForWSN(final long reservationID) {
		// it may be helpful if not only parent IDB's can receive notifications,
		// but also e.g. users, so skip this check
		// // check if there are parent IDB's available
		// try {
		// Config.getString(Constants.hsiProperties, "parent.reservationEPR");
		// } catch (final MissingResourceException e) {
		// // No parent IDB existent for notifications
		// NotificationHelpers.logger
		// .info("No parent IDB existent for notifications!");
		// return;
		// }

		// create Topic
		final NotificationTopic notificationTopic = new NotificationTopic(
				Config.getString(Constants.hsiProperties, "domain.name"),
				reservationID);
		// create AddTopic
		final AddTopicType requestType = new AddTopicType();
		requestType.setTopic(notificationTopic.toString());

		try {

			final SimpleNotificationClient notificationClient = new SimpleNotificationClient(
					Config.getString(Constants.hsiProperties,
							"domain.notificationEPR"));
			final AddTopicResponseType responseType = notificationClient
					.addTopic(requestType);

			if (responseType.isResult()) {
				NotificationHelpers.logger.debug("added topic "
						+ requestType.getTopic() + "!");
			} else {
				NotificationHelpers.logger.debug("addition of topic "
						+ requestType.getTopic()
						+ " not successful, it is already present!");
			}

		} catch (final MissingResourceException e) {
			// Failure from getting Config-String -> not notification-WS
			// declared
			NotificationHelpers.logger
					.error("no notification-EPR declared to add Topic!");
		}
	}

	/**
	 * method to delete a topic in the according notification-WS
	 * 
	 * @param reservationID
	 *            IDB-reservationID
	 */
	public static void removeTopicFromWSN(final long reservationID) {
		// create Topic
		final NotificationTopic notificationTopic = new NotificationTopic(
				Config.getString(Constants.hsiProperties, "domain.name"),
				reservationID);
		// create RemoveTopic
		final RemoveTopicType requestType = new RemoveTopicType();
		requestType.setTopic(notificationTopic.toString());

		try {

			final SimpleNotificationClient notificationClient = new SimpleNotificationClient(
					Config.getString(Constants.hsiProperties,
							"domain.notificationEPR"));
			final RemoveTopicResponseType responseType = notificationClient
					.removeTopic(requestType);

			if (responseType.isResult()) {
				NotificationHelpers.logger.debug("removed topic "
						+ requestType.getTopic() + "!");
			} else {
				NotificationHelpers.logger.debug("removal of topic "
						+ requestType.getTopic() + " not successful!");
			}

		} catch (final MissingResourceException e) {
			// Failure from getting Config-String -> not notification-WS
			// declared
			NotificationHelpers.logger
					.error("no notification-EPR declared to remove Topic!");
		}
	}

	/**
	 * method to subscribe on a topic
	 * 
	 * @param domain
	 *            NRPS-domain-name of the notification-WS to subscribe on
	 * @param reservationId
	 *            NRPS-reservationID
	 */
	public static void subscribeToWSN(final String domain,
			final long reservationId) {
		String notificationEPR = "";
		// try to get domain-infos from DB
		try {
			final Domain dom = Domain.load(domain);
			notificationEPR = dom.getNotificationEPR();
		} catch (final DatabaseException e) {
			// domain info to subscribe not found
			NotificationHelpers.logger.info("No info for domain " + domain
					+ " to create subscription!");
			return;
		}

		if ((notificationEPR == null) || (notificationEPR.length() == 0)) {
			// no notificationEPR set in domain info
			NotificationHelpers.logger
					.info("No notificationEPR set in DBfor domain " + domain);
			return;
		}

		// create Subscription
		final SubscribeType subscribeType = new SubscribeType();
		subscribeType.setTopic(new NotificationTopic(domain, reservationId)
				.toString());
		subscribeType.setConsumerReference(Config.getString(
				Constants.hsiProperties, "domain.reservationEPR"));

		try {

			final SimpleNotificationClient notificationClient = new SimpleNotificationClient(
					notificationEPR);
			final SubscribeResponseType responseType = notificationClient
					.subscribe(subscribeType);

			if (responseType.isResult()) {
				NotificationHelpers.logger.debug("subscribed on topic "
						+ subscribeType.getTopic() + "!");
			} else {
				NotificationHelpers.logger.debug("subscription on topic "
						+ subscribeType.getTopic() + " not successful!");
			}

		} catch (TopicNotFoundFault_Exception e) {
			logger.error("the topic was not found");
			e.printStackTrace();
		}
	}
}
