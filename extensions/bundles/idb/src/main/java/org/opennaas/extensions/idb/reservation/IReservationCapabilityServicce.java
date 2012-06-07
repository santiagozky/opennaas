package org.opennaas.extensions.idb.reservation;

import java.rmi.UnexpectedException;

import org.apache.muse.ws.addressing.soap.SoapFault;
import org.opennaas.extensions.idb.exception.database.DatabaseException;
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
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NotificationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NotificationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.InvalidReservationIDFaultException;

public interface IReservationCapabilityServicce {

	/**
	 * Activation Handler.
	 * 
	 * @param element
	 *            Activation Request
	 * @return Activation Response @ * A DatabaseException
	 * @throws SoapFault
	 *             A SoapFault
	 * @throws DatabaseException
	 */
	public abstract ActivateResponseType activate(final ActivateType element)
			throws SoapFault, DatabaseException;

	/**
	 * Bind Handler.
	 * 
	 * @param element
	 *            Bind Request
	 * @return Bind Response
	 */
	public abstract BindResponseType bind(final BindType element);

	/**
	 * CancelJob Handler.
	 * 
	 * @param element
	 *            CancelJob Request
	 * @return CancelJob Response
	 * @throws DatabaseException
	 *             @ * DatabaseExeption
	 */
	public abstract CancelJobResponseType cancelJob(final CancelJobType element)
			throws DatabaseException;

	/**
	 * CancelReservation Handler.
	 * 
	 * @param element
	 *            CancelReservation Request
	 * @return CancelReservation Response @ * A DatabaseException
	 * @throws SoapFault
	 *             A SoapFault
	 * @throws DatabaseException
	 */
	public abstract CancelReservationResponseType cancelReservation(
			final CancelReservationType element) throws SoapFault,
			DatabaseException;

	/**
	 * CompleteJob Handler.
	 * 
	 * @param element
	 *            CompleteJob Request
	 * @return CompleteJob Response
	 * @throws DatabaseException
	 *             @ * DatabaseException
	 */
	public abstract CompleteJobResponseType completeJob(
			final CompleteJobType element) throws DatabaseException;

	/**
	 * Reservation Handler.
	 * 
	 * @param element
	 *            Reservation Request
	 * @return Reservation Response @ * A DatabaseException
	 * @throws SoapFault
	 *             A SoapFault
	 */
	public abstract CreateReservationResponseType createReservation(
			final CreateReservationType element) throws SoapFault,
			DatabaseException;

	/**
	 * GetReservation Handler.
	 * 
	 * @param element
	 *            GetReservation Request
	 * @return GetReservation Response @ * A DatabaseException
	 * @throws InvalidReservationIDFaultException
	 * @throws DatabaseException
	 */
	public abstract GetReservationResponseType getReservation(
			final GetReservationType element)
			throws InvalidReservationIDFaultException, DatabaseException;

	/**
	 * GetReservations Handler.
	 * 
	 * @param element
	 *            GetReservations Request
	 * @return GetReservations Response
	 * @throws DatabaseException
	 *             @ * DatabaseException
	 */
	public abstract GetReservationsResponseType getReservations(
			final GetReservationsType element) throws DatabaseException;

	/**
	 * Status Handler.
	 * 
	 * @param element
	 *            Status Request
	 * @return Status Response @ * A DatabaseException
	 * @throws SoapFault
	 *             A SoapFault
	 * @throws UnexpectedException
	 */
	public abstract GetStatusResponseType getStatus(final GetStatusType element)
			throws SoapFault, DatabaseException, UnexpectedException;

	/**
	 * . isAvailable Handler
	 * 
	 * @param element
	 *            isAvailable Request
	 * @return IsAvailable Response @ * DatabaseException
	 * @throws SoapFault
	 * @throws DatabaseException
	 */
	public abstract IsAvailableResponseType isAvailable(
			final IsAvailableType element) throws SoapFault, DatabaseException;

	/**
	 * notify Handler.
	 * 
	 * @param element
	 *            notify Request
	 * @return notify Response
	 */
	public abstract NotificationResponseType notification(
			final NotificationType element);

}