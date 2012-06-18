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

/**
 * 
 */
package org.opennaas.extensions.idb.serviceinterface.reservation;

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
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.IsAvailableResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.IsAvailableType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.OperationNotSupportedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.OperationNotSupportedFaultException;
import org.opennaas.extensions.idb.serviceinterface.utils.ForwardingHelper;

/**
 * @author willner
 * 
 */
public class CommonReservationHandler {

	private static CommonReservationHandler selfInstance;

	/**
	 * Instance getter.
	 * 
	 * @return Singleton Instance
	 */
	public static CommonReservationHandler getInstance() {
		if (CommonReservationHandler.selfInstance == null) {
			CommonReservationHandler.selfInstance = new CommonReservationHandler();
		}
		return CommonReservationHandler.selfInstance;
	}

	private final ForwardingHelper forwardingHelper = ForwardingHelper
			.getInstance();

	/**
	 * @param request
	 * @return
	 * @throws
	 */
	public ActivateResponseType activate(final ActivateType request) {
		throw new OperationNotSupportedFaultException("Not implemented yet.");
	}

	/**
	 * @throws OperationNotSupportedFault_Exception
	 * @param request
	 * @return
	 * @throws
	 */
	public BindResponseType bind(final BindType request) {
		throw new OperationNotSupportedFaultException("Not implemented yet.");
	}

	/**
	 * @throws OperationNotSupportedFault_Exception
	 * @param request
	 * @return
	 * @throws
	 */
	public CancelJobResponseType cancelJob(final CancelJobType request) {
		throw new OperationNotSupportedFaultException("Not implemented yet.");
	}

	/**
	 * @param request
	 * @return
	 * @throws
	 */
	public CancelReservationResponseType cancelReservation(
			final CancelReservationType request) {
		throw new OperationNotSupportedFaultException("Not implemented yet.");
	}

	/**
	 * @throws OperationNotSupportedFault_Exception
	 * @param request
	 * @return
	 * @throws
	 */
	public CompleteJobResponseType completeJob(final CompleteJobType request) {
		throw new OperationNotSupportedFaultException("Not implemented yet.");
	}

	/**
	 * @param request
	 * @return
	 * @throws
	 */
	public CreateReservationResponseType createReservation(
			final CreateReservationType request) {
		throw new OperationNotSupportedFaultException("Not implemented yet.");
	}

	/**
	 * @param request
	 * @return
	 * @throws
	 */
	public GetReservationsResponseType getReservations(
			final GetReservationsType request) {
		throw new OperationNotSupportedFaultException("Not implemented yet.");
	}

	/**
	 * @param request
	 * @return
	 * @throws
	 */
	public GetStatusResponseType getStatus(final GetStatusType request) {
		throw new OperationNotSupportedFaultException("Not implemented yet.");
	}

	/**
	 * @param request
	 * @return
	 * @throws
	 * @throws RuntimeException
	 */
	public IsAvailableResponseType isAvailable(final IsAvailableType request) {
		throw new OperationNotSupportedFaultException("Not implemented yet.");
	}
}
