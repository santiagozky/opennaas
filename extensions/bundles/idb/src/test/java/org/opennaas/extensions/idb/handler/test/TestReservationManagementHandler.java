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
package org.opennaas.extensions.idb.handler.test;

import static org.junit.Assert.assertNotNull;

import java.net.URISyntaxException;
import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opennaas.extensions.idb.database.hibernate.Connections;
import org.opennaas.extensions.idb.database.hibernate.Domain;
import org.opennaas.extensions.idb.database.hibernate.Endpoint;
import org.opennaas.extensions.idb.database.hibernate.Reservation;
import org.opennaas.extensions.idb.database.hibernate.Service;
import org.opennaas.extensions.idb.exception.database.DatabaseException;
import org.opennaas.extensions.idb.reservation.ReservationRequestHandler;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.InvalidRequestFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.NetworkReservationPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.OperationNotAllowedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.OperationNotSupportedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnexpectedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.InvalidReservationIDFaultException;
import org.opennaas.extensions.idb.utils.ReservationHelpers;
import org.opennaas.extensions.idb.utils.TopologyHelpers;

/**
 * @author zimmerm2
 */
public class TestReservationManagementHandler {

	private static ReservationRequestHandler handler;
	private static Domain sourceDomain;
	private static Domain destinationDomain;
	private static Reservation testReservation;
	private static Reservation testReservation2;
	private static long testReservationID;
	private static Service testService;
	private static int testServiceUserID = 12051205;
	private static Connections testConnection;
	private static int testConnectionUserID = 2111115;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		TestReservationManagementHandler.handler = new ReservationRequestHandler();
		TestReservationManagementHandler.sourceDomain = TopologyHelpers
				.getTestDomain("TRMH-SourceDomain");
		Endpoint end1 = (Endpoint) TestReservationManagementHandler.sourceDomain
				.getEndpoints().toArray()[0];
		Endpoint end2 = (Endpoint) TestReservationManagementHandler.sourceDomain
				.getEndpoints().toArray()[1];
		TestReservationManagementHandler.sourceDomain.save();
		end1.save();
		end2.save();

		TestReservationManagementHandler.destinationDomain = TopologyHelpers
				.getTestDomain("TRMH-DestinationDomain");
		end1 = (Endpoint) TestReservationManagementHandler.destinationDomain
				.getEndpoints().toArray()[0];
		end2 = (Endpoint) TestReservationManagementHandler.destinationDomain
				.getEndpoints().toArray()[1];
		TestReservationManagementHandler.destinationDomain.save();
		end1.save();
		end2.save();

		TestReservationManagementHandler.testReservation = ReservationHelpers
				.getTestReservation();
		TestReservationManagementHandler.testReservation.setReservationId(0);

		TestReservationManagementHandler.testService = ReservationHelpers
				.getTestService();
		TestReservationManagementHandler.testService
				.setServiceId(TestReservationManagementHandler.testServiceUserID);
		TestReservationManagementHandler.testService.setStartTime(new Date());
		TestReservationManagementHandler.testService.setDuration(360);
		TestReservationManagementHandler.testService
				.setReservation(TestReservationManagementHandler.testReservation);

		TestReservationManagementHandler.testConnection = ReservationHelpers
				.getTestConnection();
		TestReservationManagementHandler.testConnection
				.setConnectionId(TestReservationManagementHandler.testConnectionUserID);
		Object[] ends = TestReservationManagementHandler.sourceDomain
				.getEndpoints().toArray();
		TestReservationManagementHandler.testConnection
				.setStartpoint((Endpoint) ends[0]);
		TestReservationManagementHandler.testConnection
				.addEndpoint((Endpoint) ends[1]);
		TestReservationManagementHandler.testConnection
				.setService(TestReservationManagementHandler.testService);

		TestReservationManagementHandler.testService
				.addConnection(TestReservationManagementHandler.testConnection);
		TestReservationManagementHandler.testReservation
				.addService(TestReservationManagementHandler.testService);
		TestReservationManagementHandler.testReservation.save();

		TestReservationManagementHandler.testReservationID = TestReservationManagementHandler.testReservation
				.getReservationId();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (null != TestReservationManagementHandler.testReservation)
			TestReservationManagementHandler.testReservation.delete();
		if (null != TestReservationManagementHandler.testReservation2)
			TestReservationManagementHandler.testReservation2.delete();
		if (null != TestReservationManagementHandler.sourceDomain)
			TestReservationManagementHandler.sourceDomain.delete();
		if (null != TestReservationManagementHandler.destinationDomain)
			TestReservationManagementHandler.destinationDomain.delete();
	}

	/**
	 * Test method for
	 * {@link org.opennaas.extensions.idb.reservation.handler.ReservationManagementHandler#getReservation(org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetReservationType)}
	 * .
	 * 
	 * @throws URISyntaxException
	 * @throws DatabaseException
	 * @throws InvalidReservationIDFaultException
	 * @throws UnexpectedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws OperationNotAllowedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	@Test
	public final void testGetReservation() throws DatabaseException,
			InvalidReservationIDFaultException, InvalidRequestFault_Exception,
			OperationNotAllowedFault_Exception,
			OperationNotSupportedFault_Exception, UnexpectedFault_Exception {
		final GetReservationType getType = new GetReservationType();
		getType.setReservationID(String
				.valueOf(TestReservationManagementHandler.testReservationID));

		TestReservationManagementHandler.handler = new ReservationRequestHandler();
		ReservationRequestHandler hand = TestReservationManagementHandler.handler;
		assertNotNull("Handler should not be null", hand);
		final GetReservationResponseType resultWithoutServiceID = hand
				.getReservation(getType);
		Assert.assertFalse("Should contain data", resultWithoutServiceID
				.getService().isEmpty());

		getType.getServiceID()
				.add(new Integer(
						TestReservationManagementHandler.testServiceUserID));
		final GetReservationResponseType resultWithServiceID = TestReservationManagementHandler.handler
				.getReservation(getType);
		Assert.assertFalse("Should contain data", resultWithServiceID
				.getService().isEmpty());
	}

}
