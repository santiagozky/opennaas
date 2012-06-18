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

package org.opennaas.extensions.idb.da.dummy.webservice;

import org.w3c.dom.Element;

import org.opennaas.extensions.idb.da.dummy.handler.ReservationHandler;
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
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EndpointNotFoundFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.InvalidRequestFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.IsAvailableResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.IsAvailableType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkReservationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NotificationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NotificationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.OperationNotAllowedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.OperationNotSupportedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TimeoutFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnexpectedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.topology.registrator.AbstractTopologyRegistrator;

/**
 * Dummy Reservation Webservice.
 * 
 * @author Alexander Willner (willner@cs.uni-bonn.de)
 */
public final class ReservationWS implements NetworkReservationPortType {

	/**
	 * Default constructor that initializes the ContextListener.
	 */
	public ReservationWS() {
		if (AbstractTopologyRegistrator.getLatestInstance() == null) {
			new ContextListener();
		}
	}

	@Override
	public ActivateResponseType activate(ActivateType activate)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		return ReservationHandler.getInstance().activate(activate);
	}

	@Override
	public BindResponseType bind(BindType bind)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		return ReservationHandler.getInstance().bind(bind);
	}

	@Override
	public CancelJobResponseType cancelJob(CancelJobType cancelJob)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		return ReservationHandler.getInstance().cancelJob(cancelJob);
	}

	@Override
	public CancelReservationResponseType cancelReservation(
			CancelReservationType cancelReservation)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		return ReservationHandler.getInstance().cancelReservation(
				cancelReservation);
	}

	@Override
	public CompleteJobResponseType completeJob(CompleteJobType completeJob)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		return ReservationHandler.getInstance().completeJob(completeJob);
	}

	@Override
	public CreateReservationResponseType createReservation(
			CreateReservationType createReservation)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {
		return ReservationHandler.getInstance().createReservation(
				createReservation);
	}

	@Override
	public GetReservationResponseType getReservation(
			GetReservationType getReservation)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		throw new OperationNotSupportedFault_Exception(
				"operation not supported"); // the method doesnt seems to be
											// implemented anywhere
	}

	@Override
	public GetReservationsResponseType getReservations(
			GetReservationsType getReservations)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		return ReservationHandler.getInstance()
				.getReservations(getReservations);
	}

	@Override
	public GetStatusResponseType getStatus(GetStatusType getStatus)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		return ReservationHandler.getInstance().getStatus(getStatus);
	}

	@Override
	public IsAvailableResponseType isAvailable(IsAvailableType isAvailable)
			throws InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception, TimeoutFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception,
			EndpointNotFoundFault_Exception {
		return ReservationHandler.getInstance().isAvailable(isAvailable);
	}

	@Override
	public NotificationResponseType notification(NotificationType notification)
			throws OperationNotSupportedFault_Exception {
		throw new OperationNotSupportedFault_Exception(
				"operation not supported"); // the method doesnt seems to be
											// implemented anywhere
	}

}
