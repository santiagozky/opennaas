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

package org.opennaas.extensions.idb.serviceinterface.databinding.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.Element;

import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.*;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusResponseType.ServiceStatus;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.InvalidRequestFaultException;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.InvalidReservationIDFaultException;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.UnexpectedFaultException;

/**
 * Utility class to handle request and response elements.
 * 
 * @see TestDomainViola
 * @author Alexander Willner (willner@cs.uni-bonn.de)
 * @version $Id: WebserviceUtils.java 619 2007-08-29 19:04:26Z
 *          willner@cs.uni-bonn.de $
 */
public final class WebserviceUtils {

	/**
	 * converts a long reservation-ID to a String
	 * 
	 * @param resID
	 *            reservationID
	 * @return String reservationID
	 */
	public static String convertReservationID(final long resID) {
		return String.valueOf(resID);
	}

	/**
	 * converts a String reservation-ID to a long
	 * 
	 * @param resID
	 *            reservationID
	 * @return long reservationID
	 * @throws InvalidReservationIDFaultException
	 */
	public static long convertReservationID(final String resID)
			throws InvalidReservationIDFaultException {
		String id = resID;
		try {
			if (resID.contains("@")) {
				final String[] part = resID.split("@");
				id = part[0];
			}
			return Long.parseLong(id);
		} catch (final NumberFormatException e) {
			throw new InvalidReservationIDFaultException(
					"ReservationID is not parsable: '" + id + "'", e);
		}
	}

	public static Element createActivateRequest(final ActivateType activateType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final Activate request = new Activate();
		/* generating valid Activate-Request ------------------------------- */
		request.setActivate(activateType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/*
	 * ------------------------------------------------------------------------
	 * DOMAIN-UTILS
	 * -----------------------------------------------------------------------
	 */
	/**
	 * Create an AddDomainRequest.
	 * 
	 * @param domain
	 *            The domain informations.
	 * @return The AddDomainRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createAddDomainRequest(final AddDomainType domainType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final AddDomain request = new AddDomain();
		/* generating valid AddDomain-Request ------------------------------ */
		request.setAddDomain(domainType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create an AddEndpointRequest.
	 * 
	 * @param endpoint
	 *            The request type.
	 * @return The AddEndpointRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createAddEndpointRequest(final EndpointType endpoint)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final AddEndpoint request = new AddEndpoint();
		final AddEndpointType requestType = new AddEndpointType();
		/* generating valid AddEndpoint-Request ---------------------------- */
		requestType.setEndpoint(endpoint);
		request.setAddEndpoint(requestType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create an AddLinkRequest.
	 * 
	 * @param link
	 *            The request type.
	 * @return The AddLinkRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createAddLinkRequest(final AddLinkType addLinkType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final AddLink request = new AddLink();
		/* generating valid AddLink-Request -------------------------------- */
		request.setAddLink(addLinkType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * 
	 * @param addOrEditDomainType
	 * @return
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createAddOrEditDomainRequest(
			final AddOrEditDomainType addOrEditDomainType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final AddOrEditDomain request = new AddOrEditDomain();

		/* generating valid request ---------------------------------------- */
		request.setAddOrEditDomain(addOrEditDomainType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/*
	 * ------------------------------------------------------------------------
	 * ENDPOINT-UTILS
	 * -----------------------------------------------------------------------
	 */

	/**
	 * Create a AddTopicRequest.
	 * 
	 * @param addTopicType
	 *            The request type.
	 * @return The AddTopicRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createAddTopicRequest(final AddTopicType addTopicType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final AddTopic request = new AddTopic();
		/* generating valid AddTopic-Request ---------------------- */
		request.setAddTopic(addTopicType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	public static Element createCancelReservationRequest(
			final CancelReservationType cancelReservationType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final CancelReservation request = new CancelReservation();
		/* generating valid CancelReservation-Request ---------------------- */
		request.setCancelReservation(cancelReservationType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create a DeleteDomainRequest.
	 * 
	 * @param domain
	 *            The domain informations.
	 * @return The DeleteDomainRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createDeleteDomainRequest(final String domainID)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final DeleteDomain request = new DeleteDomain();
		final DeleteDomainType requestType = new DeleteDomainType();
		/* generating valid DeleteDomain-Request --------------------------- */
		requestType.setDomainId(domainID);
		request.setDeleteDomain(requestType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create an DeleteEndpointRequest.
	 * 
	 * @param endpoint
	 *            The request type.
	 * @return The DeleteEndpointRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createDeleteEndpointRequest(
			final DeleteEndpointType deleteEndpointType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final DeleteEndpoint request = new DeleteEndpoint();
		/* generating valid DeleteEndpoint-Request ------------------------- */
		request.setDeleteEndpoint(deleteEndpointType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/*
	 * ------------------------------------------------------------------------
	 * LINK-UTILS
	 * -----------------------------------------------------------------------
	 */

	/**
	 * Create a DeleteLinkRequest.
	 * 
	 * @param link
	 *            The request type.
	 * @return The DeleteLinkRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createDeleteLinkRequest(
			final DeleteLinkType deleteLinkType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final DeleteLink request = new DeleteLink();

		/* generating valid DeleteLinks-Request ---------------------------- */
		request.setDeleteLink(deleteLinkType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create a EditDomainRequest.
	 * 
	 * @param domain
	 *            The domain informations.
	 * @return The EditDomainRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createEditDomainRequest(
			final EditDomainType editDomainType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final EditDomain request = new EditDomain();
		/* generating valid EditDomain-Request ----------------------------- */
		request.setEditDomain(editDomainType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create an EditEndpointRequest.
	 * 
	 * @param endpoint
	 *            The request type.
	 * @return The EditEndpointRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createEditEndpointRequest(
			final EditEndpointType editEndpointType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final EditEndpoint request = new EditEndpoint();
		/* generating valid EditEndpoint-Request --------------------------- */
		request.setEditEndpoint(editEndpointType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create a EditLinkRequest.
	 * 
	 * @param link
	 *            The request type.
	 * @return The EditLinkRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createEditLinkRequest(final EditLinkType editLinkType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final EditLink request = new EditLink();

		/* generating valid EditLinks-Request ------------------------------ */
		request.setEditLink(editLinkType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/*
	 * ------------------------------------------------------------------------
	 * RESERVATION-UTILS
	 * -----------------------------------------------------------------------
	 */

	/**
	 * Create a GetDomainsRequest.
	 * 
	 * @param domain
	 *            The domain informations.
	 * @return The GetDomainsRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createGetDomainsRequest(
			final GetDomainsType getDomainsType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final GetDomains request = new GetDomains();
		/* generating valid GetDomains-Request ----------------------------- */
		request.setGetDomains(getDomainsType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create an GetEndpointsRequest.
	 * 
	 * @param endpoint
	 *            The request type.
	 * @return The GetEndpointsRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createGetEndpointsRequest(
			final GetEndpointsType getEndpointsType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final GetEndpoints request = new GetEndpoints();
		/* generating valid GetEndpoints-Request --------------------------- */
		request.setGetEndpoints(getEndpointsType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create an GetLinksRequest.
	 * 
	 * @param link
	 *            The request type.
	 * @return The GetLinksRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createGetLinksRequest(final GetLinksType getLinksType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final GetLinks request = new GetLinks();

		/* generating valid GetLinks-Request ------------------------------- */
		request.setGetLinks(getLinksType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	public static Element createGetStatusRequest(
			final GetStatusType getStatusType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final GetStatus request = new GetStatus();
		/* generating valid GetStatus-Request ------------------------------ */
		request.setGetStatus(getStatusType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create a GetTopicsRequest.
	 * 
	 * @param getTopicsType
	 *            The request type.
	 * @return The GetTopicsRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createGetTopicsRequest(
			final GetTopicsType getTopicsType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final GetTopics request = new GetTopics();
		/* generating valid GetTopics-Request ---------------------- */
		request.setGetTopics(getTopicsType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	public static Element createIsAvailableRequest(
			final IsAvailableType isAvailableType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final IsAvailable request = new IsAvailable();
		/* generating valid IsAvailable-Request ---------------------------- */
		request.setIsAvailable(isAvailableType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/*
	 * ------------------------------------------------------------------------
	 * NOTIFICATION-UTILS
	 * -----------------------------------------------------------------------
	 */

	/**
	 * Create a NotifyRequest.
	 * 
	 * @param notifyType
	 *            The request type.
	 * @return The NotifyRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createNotificationRequest(
			final NotificationType notifyType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final Notification request = new Notification();
		/* generating valid GetTopics-Request ---------------------- */
		request.setNotification(notifyType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create a PublishRequest.
	 * 
	 * @param publishType
	 *            The request type.
	 * @return The PublishRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createPublishRequest(final PublishType publishType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final Publish request = new Publish();
		/* generating valid Publish-Request ---------------------- */
		request.setPublish(publishType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create a RemoveTopicRequest.
	 * 
	 * @param removeTopicType
	 *            The request type.
	 * @return The RemoveTopicRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createRemoveTopicRequest(
			final RemoveTopicType removeTopicType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final RemoveTopic request = new RemoveTopic();
		/* generating valid RemoveTopic-Request ---------------------- */
		request.setRemoveTopic(removeTopicType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create a ReservationRequest.
	 * 
	 * @param createReservationType
	 *            The request type.
	 * @return The ReservationRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createReservationRequest(
			final CreateReservationType createReservationType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final CreateReservation request = new CreateReservation();
		/* generating valid CreateReservation-Request ---------------------- */
		request.setCreateReservation(createReservationType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create a SubscribeRequest.
	 * 
	 * @param subscribeType
	 *            The request type.
	 * @return The SubscribeRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createSubscribeRequest(
			final SubscribeType subscribeType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final Subscribe request = new Subscribe();
		/* generating valid Subscribe-Request ---------------------- */
		request.setSubscribe(subscribeType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	/**
	 * Create a UnsubscribeRequest.
	 * 
	 * @param unsubscribeType
	 *            The request type.
	 * @return The UnsubscribeRequest.
	 * @throws UnexpectedFaultException
	 * @throws InvalidRequestFaultException
	 * @throws InvalidRequestFault_Exception
	 *             If the request does not match the given XSD.
	 * @throws UnexpectedFault_Exception
	 */
	public static Element createUnsubscribeRequest(
			final UnsubscribeType unsubscribeType)
			throws InvalidRequestFaultException, UnexpectedFaultException {
		final Unsubscribe request = new Unsubscribe();
		/* generating valid Unsubscribe-Request ---------------------- */
		request.setUnsubscribe(unsubscribeType);
		/* ----------------------------------------------------------------- */

		/* serializing the request ----------------------------------------- */
		final Element requestElement = JaxbSerializer.getInstance()
				.objectToElement(request);
		/* ----------------------------------------------------------------- */

		return requestElement;
	}

	public static String getDebugMsg(final GetStatusResponseType response) {
		String debug = "<status>";
		for (final ServiceStatus s : response.getServiceStatus()) {
			debug += "<service id=" + s.getServiceID() + ">";
			debug += "<status code=" + s.getStatus().ordinal() + " />";
			for (final DomainStatusType ds : s.getDomainStatus()) {
				debug += "<domainstatus domain=" + ds.getDomain() + " code="
						+ ds.getStatus().ordinal() + " />";
			}
			for (final ConnectionStatusType c : s.getConnections()) {
				debug += "<connection id=" + c.getConnectionID() + ">";
				debug += "<status code=" + c.getStatus().ordinal() + " />";
				for (final DomainConnectionStatusType ds : c.getDomainStatus()) {
					debug += "<domainstatus domain=" + ds.getDomain()
							+ " code=" + ds.getStatus().getStatus().ordinal()
							+ " />";
				}
				debug += "</connection>";
			}
			debug += "</service>";
		}
		debug += "</status>";
		return debug;
	}

	public static URI getEmptyURI() {
		try {
			return new URI("");
		} catch (final URISyntaxException exception) {
			throw new RuntimeException("Internal Error: 0x234bef79");
		}
	}

	public static boolean isFinalStatus(final StatusType st) {
		if ((st != StatusType.CANCELLED_BY_SYSTEM)
				&& (st != StatusType.CANCELLED_BY_USER)
				&& (st != StatusType.COMPLETED)) {
			return false;
		}
		return true;
	}

	/**
	 * Utility classes should not have a public constructor.
	 */
	private WebserviceUtils() {
		throw new InstantiationError();
	}

}
