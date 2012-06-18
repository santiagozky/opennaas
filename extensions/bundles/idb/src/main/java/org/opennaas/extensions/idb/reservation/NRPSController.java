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

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.opennaas.extensions.idb.da.dummy.webservice.MalleableReservationWS;
import org.opennaas.extensions.idb.da.dummy.webservice.ReservationWS;
import org.opennaas.extensions.idb.serviceinterface.EndpointReference;

import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.Activate;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.ActivateResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.ActivateType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CancelReservationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CreateReservation;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CreateReservationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.CreateReservationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetStatusType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.IsAvailableType;
import org.opennaas.extensions.idb.serviceinterface.databinding.utils.JaxbSerializer;
import org.opennaas.extensions.idb.serviceinterface.reservation.SimpleReservationClient;
import org.opennaas.extensions.idb.serviceinterface.utils.Config;

import org.opennaas.extensions.idb.Constants;
import org.opennaas.extensions.idb.database.hibernate.Domain;
import org.w3c.dom.Element;

/**
 * This class implements a NRPS controller. It is used to communicate with an
 * instance of an Adapter/NRPS, so it contains the information required to
 * contact it: IP and proxy to send the messages, and other useful information.
 */
public class NRPSController extends Thread {

	/**
	 * Log.
	 */
	private final Log logger;

	/**
	 * Domain where the NRPS belongs to.
	 */
	private final Domain domain;

	/**
	 * Proxy for the reservationEPR.
	 */
	private SimpleReservationClient proxyRSV;

	/**
	 * RSV Web Service URI.
	 */
	private EndpointReference wsRsvEpr;

	/**
	 * TOPO Web Service URI.
	 */
	private EndpointReference wsTopoEpr;

	/**
	 * Operation to execute.
	 */
	private final String op;

	/**
	 * Message to send.
	 */
	private final Serializable msg;

	/**
	 * Result of the operation.
	 */
	private Serializable result = null;

	/** Indicates if this Controller has to trigger a rollback */
	private boolean rollback = false;

	/** Stores the Exception (if any) */
	private Exception exception = null;

	/**
	 * Constructor for the controller of an NRPS.
	 * 
	 * @param dom
	 *            Domain name
	 * @param operation
	 * @param message
	 */
	public NRPSController(final Domain dom, final String operation,
			final Serializable message, final boolean malleable,
			final Log performanceLogger) {

		logger = LogFactory.getLog(this.getClass());
		this.op = operation;
		this.msg = message;
		this.domain = dom;
		this.setDaemon(true);
		this.setName(Thread.currentThread().getName() + "_NRPSController@"
				+ dom.getName());

		this.wsRsvEpr = new EndpointReference(dom.getReservationURI());
		if (Config.isTrue(Constants.idbProperties, "passViaWebservice")) {
			this.proxyRSV = new SimpleReservationClient(this.wsRsvEpr);
		} else {
			if (malleable) {
				this.proxyRSV = new SimpleReservationClient(
						new MalleableReservationWS());
			} else {
				this.proxyRSV = new SimpleReservationClient(new ReservationWS());
			}
		}

		this.logger.info("NRPSController created for " + dom.getName());
	}

	/**
	 * This Method is used to send an ActivationRequest message to the NRPS. It
	 * uses a request sender that executes the work in a thread.
	 * 
	 * @param request
	 *            Message to send to the NRPSAdapter
	 * @return Response from the NRPS
	 */
	public void activateReservation(final ActivateType request) {

		this.start();
	}

	/**
	 * This Method is used to send a CancelReservation message to the NRPS. It
	 * uses a request sender that executes the work in a thread.
	 * 
	 * @param request
	 *            Message to send to the NRPSAdapter
	 * @return Response from the NRPS
	 */
	public void cancelReservation(final CancelReservationType request) {

		this.start();
	}

	/**
	 * This Method is used to send a ReservationRequest message to the NRPS. It
	 * uses a request sender that executes the work in a thread.
	 * 
	 * @param request
	 *            Message to send to the NRPSAdapter
	 * @return Response from the NRPS
	 */
	public void createReservation(final CreateReservationType request) {

		this.start();
	}

	@Override
	protected final void finalize() {
		this.interrupt();
		// Makes null the proxy to kill it in GC
		this.proxyRSV = null;
	}

	public Domain getDomain() {
		return this.domain;
	}

	public Exception getException() {
		return this.exception;
	}

	public Serializable getMsg() {
		return this.msg;
	}

	public SimpleReservationClient getProxyRSV() {
		return this.proxyRSV;
	}

	/**
	 * 
	 * @param request
	 */
	public void getReservations(final GetReservationsType request) {

		this.logger.info("NRPSController.GetReservations");

		this.start();
	}

	/**
	 * Gives the result of the request to the NRPS.
	 * 
	 * @return The result from the NRPS or null if there is no result
	 */
	public final Serializable getResult() {

		if (this.result != null) {
			return this.result;
		}

		this.logger.error("Request Sender: received NULL result from NRPS");
		return null;
	}

	/**
	 * This Method is used to send a getStatus message to the NRPS. It uses a
	 * request sender that executes the work in a thread.
	 * 
	 * @param request
	 *            Message to send to the NRPSAdapter
	 * @return Response from the NRPS
	 */
	public void getStatus(final GetStatusType request) {

		this.start();
	}

	public EndpointReference getWsRsvEpr() {
		return this.wsRsvEpr;
	}

	public EndpointReference getWsTopoEpr() {
		return this.wsTopoEpr;
	}

	/**
	 * This Method is used to send a isAvailable message to the NRPS. It uses a
	 * request sender that executes the work in a thread.
	 * 
	 * @param request
	 *            Message to send to the NRPSAdapter
	 * @return Response from the NRPS
	 */
	public void isAvailable(final IsAvailableType request) {

		this.logger.info("NRPSController.ISAvailable");

		this.start();
	}

	public boolean isRollback() {
		return this.rollback;
	}

	public boolean isSetException() {
		return this.exception != null;
	}

	@Override
	public void run() {
		this.logger.info("Starting sender thread...");

		if (this.op.equalsIgnoreCase("createReservation")) {
			final CreateReservationResponseType res;
			try {

				res = this.proxyRSV
						.createReservation((CreateReservationType) this.msg);
				this.result = res;

			} catch (final Exception e) {
				this.rollback = true;
				this.exception = e;
				e.printStackTrace();
			}
		} else if (this.op.equalsIgnoreCase("activateReservation")) {
			ActivateResponseType response;
			try {
				response = this.proxyRSV.activate((ActivateType) this.msg);
				this.result = response;

			} catch (final Exception e) {
				this.exception = e;
				e.printStackTrace();
			}
		} else if (this.op.equalsIgnoreCase("cancelReservation")) {

			try {
				this.result = this.proxyRSV
						.cancelReservation((CancelReservationType) this.msg);
			} catch (final Exception e) {
				this.exception = e;
				e.printStackTrace();
			}
		} else if (this.op.equalsIgnoreCase("getStatus")) {
			try {
				this.result = this.proxyRSV.getStatus((GetStatusType) this.msg);
			} catch (final Exception e) {
				this.exception = e;
				e.printStackTrace();
			}
		} else if (this.op.equalsIgnoreCase("isAvailable")) {

			try {
				this.result = this.proxyRSV
						.isAvailable((IsAvailableType) this.msg);
			} catch (final Exception e) {
				this.exception = e;
				e.printStackTrace();
			}
		} else if (this.op.equalsIgnoreCase("getReservations")) {

			try {
				this.result = this.proxyRSV
						.getReservations((GetReservationsType) this.msg);
			} catch (final Exception e) {
				this.exception = e;
				e.printStackTrace();
			}
		}

		this.logger.info("finished");

	}

	public void setProxyRSV(final SimpleReservationClient proxyRSV) {
		this.proxyRSV = proxyRSV;
	}

	public void setWsRsvEpr(final EndpointReference wsRsvEpr) {
		this.wsRsvEpr = wsRsvEpr;
	}

	public void setWsTopoEpr(final EndpointReference wsTopoEpr) {
		this.wsTopoEpr = wsTopoEpr;
	}
}
