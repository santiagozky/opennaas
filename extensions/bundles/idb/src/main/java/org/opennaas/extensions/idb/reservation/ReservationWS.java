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

package org.opennaas.extensions.idb.reservation;

import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.opennaas.extensions.idb.exception.database.DatabaseException;
import org.opennaas.extensions.idb.reservation.handler.JobOperationsHandler;
import org.opennaas.extensions.idb.reservation.handler.MiscOperationsHandler;
import org.opennaas.extensions.idb.reservation.handler.NotificationConsumerHandler;
import org.opennaas.extensions.idb.reservation.handler.ReservationManagementHandler;
import org.opennaas.extensions.idb.reservation.handler.ReservationOperationsHandler;
import org.opennaas.extensions.idb.reservation.handler.ReservationSetupHandler;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.ActivateResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.ActivateType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.BindResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.BindType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CancelJobResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CancelJobType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CancelReservationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CancelReservationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CompleteJobResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CompleteJobType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CreateReservationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CreateReservationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.IsAvailableResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.IsAvailableType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkReservationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkReservationPortTypeImpl;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NotificationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NotificationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnexpectedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.reservation.CommonReservationWS;

/** Provision Request Handler. */
@WebService(serviceName = "networkReservationService", portName = "networkReservationPortType", targetNamespace = "http://opennaas.org/nsp/webservice/reservation")
public final class ReservationWS extends CommonReservationWS {

	private final String generateGRI() {
		byte[] randomBytes = new byte[20];
		new Random().nextBytes(randomBytes);

		String result = "";
		for (byte curr : randomBytes) {
			String hex = Integer.toHexString(curr - Byte.MIN_VALUE)
					.toUpperCase();

			if (1 == hex.length()) {
				hex = "0" + hex;
			}

			result += hex;
		}

		return result;
	}

	private final String generateToken() {
		return this.generateGRI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.reservation.handler.IReservation#activate
	 * (org.
	 * opennaas.extensions.idb.serviceinterface.databinding.jaxb.ActivateType)
	 */

	@Override
	public ActivateResponseType activate(final ActivateType element)
			throws UnexpectedFault_Exception {
		ActivateResponseType response;
		try {
			response = ReservationOperationsHandler.getInstance().activation(
					element);
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected problem ", e);
		}

	}

	/*
	 * Handler
	 * =========================================================================
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.reservation.handler.IReservation#bind(org
	 * .opennaas.extensions.idb.serviceinterface.databinding.jaxb.BindType)
	 */
	@Override
	public BindResponseType bind(final BindType element) {
		final BindResponseType response = MiscOperationsHandler.getInstance()
				.bind(element);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.reservation.handler.IReservation#cancelJob
	 * (org
	 * .opennaas.extensions.idb.serviceinterface.databinding.jaxb.CancelJobType)
	 */
	@Override
	public CancelJobResponseType cancelJob(final CancelJobType element)
			throws UnexpectedFault_Exception {
		CancelJobResponseType response;
		try {
			response = JobOperationsHandler.getInstance().cancelJob(element);
		} catch (DatabaseException e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected problem ", e);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opennaas.extensions.idb.reservation.handler.IReservation#
	 * cancelReservation
	 * (org.opennaas.extensions.idb.serviceinterface.databinding
	 * .jaxb.CancelReservationType)
	 */
	@Override
	public CancelReservationResponseType cancelReservation(
			final CancelReservationType element)
			throws UnexpectedFault_Exception {
		CancelReservationResponseType response;
		try {
			response = ReservationOperationsHandler.getInstance()
					.cancelReservation(element);
		} catch (DatabaseException e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected database problem ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected problem ", e);
		}
		return response;
	}

	/**
	 * Singleton - Cloning _not_ supported!
	 * 
	 * @return Should never return anything...
	 * @throws CloneNotSupportedException
	 *             Singleton hates to be cloned!
	 */
	@Override
	@WebMethod(exclude = true)
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.reservation.handler.IReservation#completeJob
	 * (org
	 * .opennaas.extensions.idb.serviceinterface.databinding.jaxb.CompleteJobType
	 * )
	 */
	@Override
	public CompleteJobResponseType completeJob(final CompleteJobType element)
			throws UnexpectedFault_Exception {
		CompleteJobResponseType response;
		try {
			response = JobOperationsHandler.getInstance().completeJob(element);
		} catch (DatabaseException e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected database problem ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected problem ", e);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opennaas.extensions.idb.reservation.handler.IReservation#
	 * createReservation
	 * (org.opennaas.extensions.idb.serviceinterface.databinding
	 * .jaxb.CreateReservationType)
	 */
	@Override
	public CreateReservationResponseType createReservation(
			final CreateReservationType element)
			throws UnexpectedFault_Exception {

		String token = null;
		String gri = null;

		// Backup GRI
		if (null == element.getGRI()) {
			gri = this.generateGRI();

			element.setGRI(gri);
		} else {
			gri = element.getGRI();
		}

		// Backup Token
		if (null == element.getToken()) {
			token = this.generateToken();

			element.setToken(token);
		} else {
			token = element.getToken();
		}

		CreateReservationResponseType response;
		try {
			response = ReservationSetupHandler.getInstance().createReservation(
					element);
		} catch (DatabaseException e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected database problem ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected problem ", e);
		}

		// TODO: This case should never happen
		if (null == response.getGRI()) {
			response.setGRI(gri);
		}
		if (null == response.getToken()) {
			response.setToken(token);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.reservation.handler.IReservation#getReservation
	 * (org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.
	 * GetReservationType)
	 */
	@Override
	public GetReservationResponseType getReservation(
			final GetReservationType element) throws UnexpectedFault_Exception {
		GetReservationResponseType responseType;
		try {
			responseType = ReservationManagementHandler.getInstance()
					.getReservation(element);
		} catch (DatabaseException e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected database problem ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected problem ", e);
		}

		return responseType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.reservation.handler.IReservation#getReservations
	 * (org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.
	 * GetReservationsType)
	 */
	@Override
	public GetReservationsResponseType getReservations(
			final GetReservationsType element) throws UnexpectedFault_Exception {
		GetReservationsResponseType responseType;
		try {
			responseType = ReservationManagementHandler.getInstance()
					.getReservations(element);
		} catch (DatabaseException e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected database problem ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected problem ", e);
		}

		return responseType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.reservation.handler.IReservation#getStatus
	 * (org
	 * .opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusType)
	 */
	@Override
	public GetStatusResponseType getStatus(final GetStatusType element)
			throws UnexpectedFault_Exception {
		GetStatusResponseType response;
		try {
			response = ReservationOperationsHandler.getInstance().status(
					element);
		} catch (DatabaseException e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected database problem ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected problem ", e);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.reservation.handler.IReservation#isAvailable
	 * (org
	 * .opennaas.extensions.idb.serviceinterface.databinding.jaxb.IsAvailableType
	 * )
	 */
	@Override
	public IsAvailableResponseType isAvailable(final IsAvailableType element)
			throws UnexpectedFault_Exception {
		IsAvailableResponseType response;
		try {
			response = ReservationSetupHandler.getInstance().isAvailable(
					element);
		} catch (DatabaseException e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected database problem ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnexpectedFault_Exception("unexpected problem ", e);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.opennaas.extensions.idb.reservation.handler.IReservation#notification
	 * (org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.
	 * NotificationType)
	 */
	@Override
	public NotificationResponseType notification(final NotificationType element) {
		return NotificationConsumerHandler.getInstance().notification(element);
	}

}
