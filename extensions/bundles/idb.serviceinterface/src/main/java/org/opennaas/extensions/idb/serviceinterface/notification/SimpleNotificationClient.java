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

package org.opennaas.extensions.idb.serviceinterface.notification;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.ws.soap.AddressingFeature;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.extensions.idb.serviceinterface.EndpointReference;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetTopicsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetTopicsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkNotificationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkNotificationService;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.PublishResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.PublishType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.RemoveTopicResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.RemoveTopicType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.SubscribeResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.SubscribeType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TopicNotFoundFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnsubscribeResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnsubscribeType;

public class SimpleNotificationClient {

	NetworkNotificationPortType client;
	private final Log logger;

	/**
	 * Constructor from superclass.
	 * 
	 * @param endpointReference
	 */
	public SimpleNotificationClient(final EndpointReference endpointReference) {
		logger = LogFactory.getLog(this.getClass());
		NetworkNotificationService s;
		try {
			s = new NetworkNotificationService(endpointReference.getURI()
					.toURL());
		} catch (MalformedURLException e) {
			s = new NetworkNotificationService();
			logger.error("Could not get convert to URL "
					+ endpointReference.getURI());
			e.printStackTrace();
		}
		client = s.getNetworkNotificationPortType(new AddressingFeature());

	}

	/**
	 * @param webservice
	 */
	public SimpleNotificationClient(final NetworkNotificationPortType webservice) {
		logger = LogFactory.getLog(this.getClass());

		this.client = webservice;
	}

	/**
	 * Constructor from Superclass.
	 * 
	 * @param endpointReference
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	public SimpleNotificationClient(final String endpointReference) {
		logger = LogFactory.getLog(this.getClass());

		NetworkNotificationService s;
		try {
			s = new NetworkNotificationService(new URL(endpointReference
					+ "?wsdl"));

		} catch (MalformedURLException e) {
			s = new NetworkNotificationService();
			e.printStackTrace();
			logger.error("Could not get convert to URL " + endpointReference);
		}
		client = s.getNetworkNotificationPortType(new AddressingFeature());

	}

	/**
	 * @param request
	 * @return
	 * @throws SoapFault
	 */
	public AddTopicResponseType addTopic(final AddTopicType request) {

		return client.addTopic(request);
	}

	/**
	 * @param request
	 * @return
	 * @throws SoapFault
	 */
	public GetTopicsResponseType getTopics(final GetTopicsType request) {

		return client.getTopics(request);
	}

	/**
	 * @param request
	 * @return
	 * @throws TopicNotFoundFault_Exception
	 * @throws SoapFault
	 */
	public PublishResponseType publish(final PublishType request)
			throws TopicNotFoundFault_Exception {

		return client.publish(request);
	}

	/**
	 * @param request
	 * @return
	 * @throws SoapFault
	 */
	public RemoveTopicResponseType removeTopic(final RemoveTopicType request) {

		return client.removeTopic(request);

	}

	/**
	 * @param request
	 * @return
	 * @throws TopicNotFoundFault_Exception
	 * @throws SoapFault
	 */
	public SubscribeResponseType subscribe(final SubscribeType request)
			throws TopicNotFoundFault_Exception {

		return client.subscribe(request);
	}

	/**
	 * @param request
	 * @return
	 * @throws TopicNotFoundFault_Exception
	 * @throws SoapFault
	 */
	public UnsubscribeResponseType unsubscribe(final UnsubscribeType request)
			throws TopicNotFoundFault_Exception {

		return client.unsubscribe(request);
	}
}
