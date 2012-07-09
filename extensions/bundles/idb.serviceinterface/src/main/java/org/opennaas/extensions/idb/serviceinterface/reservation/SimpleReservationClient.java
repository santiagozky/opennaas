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

package org.opennaas.extensions.idb.serviceinterface.reservation;

import java.net.URISyntaxException;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.soap.AddressingFeature;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.core.resources.helpers.Helpers;
import org.opennaas.core.resources.helpers.Tuple;
import org.opennaas.extensions.idb.serviceinterface.EndpointReference;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.ActivateResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.ActivateType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.BindResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.BindType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CancelReservationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CancelReservationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.ConnectionConstraintType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CreateReservationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CreateReservationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EndpointNotFoundFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EndpointType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.FixedReservationConstraintType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.InvalidRequestFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.IsAvailableResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.IsAvailableType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.MalleableReservationConstraintType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkReservationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkReservationService;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NotificationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NotificationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.OperationNotAllowedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.OperationNotSupportedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.ReservationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.ServiceConstraintType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TimeoutFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnexpectedFault_Exception;

public class SimpleReservationClient {

	private final Log logger;

	public static final CreateReservationType getCreateReservationRequest(
			final int bandwidth, final int delay, final int duration,
			final XMLGregorianCalendar startTime,
			final Tuple<String, String>... ep) {
		final CreateReservationType resReq = new CreateReservationType();

		final ServiceConstraintType service = new ServiceConstraintType();
		final FixedReservationConstraintType constraints = new FixedReservationConstraintType();

		constraints.setDuration(duration);
		constraints.setStartTime(startTime);

		service.setTypeOfReservation(ReservationType.FIXED);
		service.setFixedReservationConstraints(constraints);
		service.setAutomaticActivation(true);
		service.setServiceID(1); // Fix for ARGIA system

		for (int i = 0; i < ep.length; i++) {
			final ConnectionConstraintType connection = new ConnectionConstraintType();

			final EndpointType src = new EndpointType();
			src.setEndpointId(ep[i].getFirstElement());
			final EndpointType dst = new EndpointType();
			dst.setEndpointId(ep[i].getSecondElement());

			connection.setConnectionID(i + 1);
			connection.setMinBW(bandwidth);
			connection.setMaxDelay(Integer.valueOf(delay));
			connection.setSource(src);
			connection.getTarget().add(dst);
			connection.setDirectionality(1);

			service.getConnections().add(connection);

		}

		resReq.getService().add(service);

		return resReq;
	}

	/**
	 * @param source
	 * @param target
	 * @param bandwidth
	 * @param delay
	 * @param duration
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	public static IsAvailableType getIsAvailableRequest(final String source,
			final String target, final int bandwidth, final int delay,
			final int duration) throws DatatypeConfigurationException {
		final ServiceConstraintType service = new ServiceConstraintType();
		final FixedReservationConstraintType constraints = new FixedReservationConstraintType();
		final ConnectionConstraintType connection = new ConnectionConstraintType();

		constraints.setDuration(duration);
		constraints.setStartTime(DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(new GregorianCalendar()));

		service.setTypeOfReservation(ReservationType.FIXED);
		service.setFixedReservationConstraints(constraints);
		service.setAutomaticActivation(true);
		service.setServiceID(Helpers.getPositiveRandomInt());

		final EndpointType src = new EndpointType();
		src.setEndpointId(target);
		final EndpointType dst = new EndpointType();
		dst.setEndpointId(source);

		connection.setMinBW(bandwidth);
		connection.setMaxDelay(Integer.valueOf(delay));
		connection.setSource(src);
		connection.getTarget().add(dst);
		connection.setDirectionality(1);
		connection.setConnectionID(Helpers.getPositiveRandomInt());

		service.getConnections().add(connection);

		final IsAvailableType request = new IsAvailableType();
		request.getService().add(service);
		return request;
	}

	private final NetworkReservationPortType client;
	private EndpointReference endpoint;

	/**
	 * Constructor from superclass.
	 * 
	 * @param endpointReference
	 */
	public SimpleReservationClient(final EndpointReference endpointReference) {
		logger = LogFactory.getLog(this.getClass());

		NetworkReservationService service;

		this.endpoint = endpointReference;
		service = new NetworkReservationService(endpointReference.getWSDL());

		client = service.getNetworkReservationPortType(new AddressingFeature());

	}

	/**
	 * Constructor from Superclass.
	 * 
	 * @param endpointReference
	 * @throws URISyntaxException
	 * @throws URISyntaxException
	 */
	public SimpleReservationClient(final NetworkReservationPortType webservice,
			EndpointReference endpoint) {
		logger = LogFactory.getLog(this.getClass());
		this.endpoint = endpoint;
		client = webservice;

	}

	/**
	 * Constructor from Superclass.
	 * 
	 * @param endpointReference
	 * @throws URISyntaxException
	 */
	public SimpleReservationClient(final String endpointReference) {
		logger = LogFactory.getLog(this.getClass());

		NetworkReservationService service;
		try {
			EndpointReference endpoint = new EndpointReference(
					endpointReference);
			this.endpoint = endpoint;
			service = new NetworkReservationService(endpoint.getWSDL());

		} catch (URISyntaxException e) {
			service = new NetworkReservationService();
			logger.error("Could not get convert to URL " + endpointReference);
			e.printStackTrace();
		}

		client = service.getNetworkReservationPortType(new AddressingFeature());
	}

	/**
	 * @param request
	 * @return
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 */
	public ActivateResponseType activate(final ActivateType activateType)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		return client.activate(activateType);
	}

	/**
	 * 
	 * @param reservationID
	 * @param serviceID
	 * @return
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 */
	public ActivateResponseType activate(final String reservationID,
			final int serviceID) throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		final ActivateType request = new ActivateType();
		request.setReservationID(reservationID);
		request.setServiceID(serviceID);
		return client.activate(request);
	}

	/**
	 * @param request
	 * @return
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 */
	public BindResponseType bind(final BindType request)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {

		return client.bind(request);
	}

	/**
	 * 
	 * @param reservationID
	 * @param TNA
	 * @param IP
	 * @return
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 */
	public BindResponseType bind(final String reservationID, final String TNA,
			final String IP) throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		final BindType request = new BindType();
		request.setReservationID(reservationID);
		request.setEndpointID(TNA);
		request.getIPAdress().add(IP);

		BindResponseType res = this.bind(request);

		return res;
	}

	/**
	 * @param request
	 * @return
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 */
	public CancelReservationResponseType cancelReservation(
			final CancelReservationType request)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {

		return client.cancelReservation(request);
	}

	public CancelReservationResponseType cancelReservation(
			final String reservationID) throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		final CancelReservationType request = new CancelReservationType();
		request.setReservationID(reservationID);

		return client.cancelReservation(request);
	}

	/**
	 * @param request
	 * @return
	 * @throws EndpointNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 */
	public CreateReservationResponseType createMalleableReservation(
			final CreateReservationType request)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {

		return client.createReservation(request);
	}

	/**
	 * @return
	 * @throws EndpointNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws DatatypeConfigurationException
	 * @throws SoapFault
	 * @throws DatatypeConfigurationException
	 */
	public CreateReservationResponseType createMalleableReservation(
			final String source, final String target, final long dataAmount,
			final XMLGregorianCalendar startTime,
			final XMLGregorianCalendar deadline, final int minBW,
			final int maxBW, final int delay)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {
		final CreateReservationType resReq = new CreateReservationType();

		final ServiceConstraintType service = new ServiceConstraintType();
		final MalleableReservationConstraintType constraints = new MalleableReservationConstraintType();
		final ConnectionConstraintType connection = new ConnectionConstraintType();

		constraints.setStartTime(startTime);
		constraints.setDeadline(deadline);

		service.setTypeOfReservation(ReservationType.MALLEABLE);
		service.setMalleableReservationConstraints(constraints);
		service.setAutomaticActivation(true);
		service.setServiceID(1); // Fix for ARGIA system

		final EndpointType src = new EndpointType();
		src.setEndpointId(source);
		final EndpointType dst = new EndpointType();
		dst.setEndpointId(target);

		connection.setDataAmount(dataAmount);
		connection.setMinBW(minBW);
		connection.setMaxBW(maxBW);
		connection.setMaxDelay(delay);
		connection.setSource(src);
		connection.getTarget().add(dst);
		connection.setConnectionID(1); // Fix for ARGIA system

		service.getConnections().add(connection);

		resReq.setJobID(Helpers.getPositiveRandomLong());
		resReq.getService().add(service);

		return client.createReservation(resReq);
	}

	/**
	 * @param request
	 * @return
	 * @throws EndpointNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 */
	public CreateReservationResponseType createReservation(
			final CreateReservationType request)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {

		return client.createReservation(request);
	}

	public CreateReservationResponseType createReservation(final int bandwidth,
			final int delay, final int duration,
			final XMLGregorianCalendar startTime,
			final Tuple<String, String>... ep)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {

		final CreateReservationType resReq = SimpleReservationClient
				.getCreateReservationRequest(bandwidth, delay, duration,
						startTime, ep);

		return client.createReservation(resReq);
	}

	public CreateReservationResponseType createReservation(final int bandwidth,
			final int delay, final int duration,
			final XMLGregorianCalendar startTime,
			final Tuple<String, String> tuple, final String samlAssertion)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {

		final CreateReservationType resReq = this.getCreateReservationRequest(
				bandwidth, delay, duration, startTime, tuple, samlAssertion);

		return client.createReservation(resReq);
	}

	/**
	 * @return
	 * @throws EndpointNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws DatatypeConfigurationException
	 * @throws SoapFault
	 * @throws DatatypeConfigurationException
	 */
	@SuppressWarnings("unchecked")
	public CreateReservationResponseType createReservation(final String source,
			final String target, final int bandwidth, final int delay,
			final int duration) throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {
		XMLGregorianCalendar startTime;
		try {
			startTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					new GregorianCalendar());
		} catch (final DatatypeConfigurationException e) {
			throw new RuntimeException(
					"A serious configuration error was detected ...", e);
		}

		return this.createReservation(bandwidth, delay, duration, startTime,
				new Tuple<String, String>(source, target));
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @param bandwidth
	 * @param delay
	 * @param duration
	 * @param samlAssertion
	 * @return
	 * @throws InvalidRequestFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws EndpointNotFoundFault_Exception
	 */
	public CreateReservationResponseType createReservation(final String source,
			final String target, final int bandwidth, final int delay,
			final int duration, final String samlAssertion)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {
		XMLGregorianCalendar startTime;
		try {
			startTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					new GregorianCalendar());
		} catch (final DatatypeConfigurationException e) {
			throw new RuntimeException(
					"A serious configuration error was detected ...", e);
		}

		return this.createReservation(bandwidth, delay, duration, startTime,
				new Tuple<String, String>(source, target), samlAssertion);
	}

	/**
	 * A simple API to create a reservation in advance.
	 * 
	 * @param source
	 *            The source TNA.
	 * @param target
	 *            The target TNA.
	 * @param startTime
	 *            The start time.
	 * @param bandwidth
	 *            The requested path bandwidth.
	 * @param delay
	 *            The requested path delay.
	 * @param duration
	 *            The duration of the reservation.
	 * @return The according reservation IDs.
	 * @throws EndpointNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 *             If an error occurs.
	 */
	@SuppressWarnings("unchecked")
	// supresswarnings because of JVM not liking arrays of generic types. only
	// fixable in Java7
	public CreateReservationResponseType createReservation(final String source,
			final String target, final XMLGregorianCalendar startTime,
			final int bandwidth, final int delay, final int duration)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {
		return this.createReservation(bandwidth, delay, duration, startTime,
				new Tuple<String, String>(source, target));
	}

	@SuppressWarnings("unchecked")
	private CreateReservationType getCreateReservationRequest(
			final int bandwidth, final int delay, final int duration,
			final XMLGregorianCalendar startTime,
			final Tuple<String, String> tuple, final String samlAssertion) {
		final CreateReservationType result = SimpleReservationClient
				.getCreateReservationRequest(bandwidth, delay, duration,
						startTime, tuple);
		result.setSamlAssertion(samlAssertion);
		return result;
	}

	/**
	 * @param request
	 * @return
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 */
	public GetReservationsResponseType getReservations(
			final GetReservationsType request)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {

		return client.getReservations(request);
	}

	/**
	 * 
	 * @param timeframeInSeconds
	 *            Time in seconds to ask for around the current timestamp
	 * @return
	 * @throws SoapFault
	 * @throws DatatypeConfigurationException
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public GetReservationsResponseType getReservations(
			final long timeframeInSeconds)
			throws DatatypeConfigurationException,
			InvalidRequestFault_Exception, OperationNotAllowedFault_Exception,
			TimeoutFault_Exception, OperationNotSupportedFault_Exception,
			UnexpectedFault_Exception {
		final XMLGregorianCalendar startTime = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(new GregorianCalendar());
		final XMLGregorianCalendar endTime = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(new GregorianCalendar());
		final Duration duration = DatatypeFactory.newInstance().newDuration(
				timeframeInSeconds * 1000);

		startTime.add(duration.negate());
		endTime.add(duration);

		final GetReservationsType request = new GetReservationsType();
		request.setPeriodStartTime(startTime);
		request.setPeriodEndTime(endTime);

		return client.getReservations(request);
	}

	/**
	 * @param request
	 * @return
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 */
	public GetStatusResponseType getStatus(final GetStatusType request)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {

		return client.getStatus(request);
	}

	public GetStatusResponseType getStatus(final String reservationID)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		final GetStatusType getStatus = new GetStatusType();

		return client.getStatus(getStatus);
	}

	/**
	 * @param request
	 * @return
	 * @throws EndpointNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws SoapFault
	 */
	public IsAvailableResponseType isAvailable(final IsAvailableType isAvailable)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {

		return client.isAvailable(isAvailable);
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @return
	 * @throws SoapFault
	 * @throws DatatypeConfigurationException
	 * @throws EndpointNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws TimeoutFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public IsAvailableResponseType isAvailable(final String source,
			final String target, final int bandwidth, final int delay,
			final int duration) throws DatatypeConfigurationException,
			InvalidRequestFault_Exception, OperationNotAllowedFault_Exception,
			TimeoutFault_Exception, OperationNotSupportedFault_Exception,
			UnexpectedFault_Exception, EndpointNotFoundFault_Exception {

		final IsAvailableType request = SimpleReservationClient
				.getIsAvailableRequest(source, target, bandwidth, delay,
						duration);

		return client.isAvailable(request);
	}

	/**
	 * @param request
	 * @return
	 * @throws OperationNotSupportedFault_Exception
	 * @throws SoapFault
	 */
	public NotificationResponseType notification(final NotificationType request)
			throws OperationNotSupportedFault_Exception {

		return client.notification(request);
	}

	public EndpointReference getEndpointReference() {
		return endpoint;
	}
}
