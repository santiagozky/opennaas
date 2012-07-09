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

package org.opennaas.extensions.idb.serviceinterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.UnexpectedFaultException;
import org.opennaas.extensions.idb.serviceinterface.databinding.validator.SemanticValidator;
import org.opennaas.extensions.idb.serviceinterface.handler.LoggedHandler;

/**
 * @author gassen
 */
public abstract class RequestHandler extends LoggedHandler {
	/** Known getTypeMethods map. */
	private final HashMap<Class<?>, Method> getTypeMethods = new HashMap<Class<?>, Method>();

	/** Known setTypeMethods map. */
	private final HashMap<Class<?>, Method> setTypeMethods = new HashMap<Class<?>, Method>();

	// private final SecurityHandler securityHandler = new SecurityHandler();

	/**
	 * Get the Type Object from Message Object.
	 * 
	 * @param object
	 *            Message Object
	 * @return Type Object
	 * @throws IllegalAccessException
	 *             Error while method Invocation
	 * @throws InvocationTargetException
	 *             Error while method Invocation
	 * @throws NoSuchMethodException
	 *             Non-Standart Typename
	 */
	protected Object getType(final Object object)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// Get Class
		final Class<?> requestClass = object.getClass();

		// Get getter Method
		final Method runMethod;

		if (this.getTypeMethods.containsKey(requestClass)) {

			runMethod = this.getTypeMethods.get(requestClass);

		} else {
			// build getter method name
			final String className = requestClass.getName();

			final String methodName = "get"
					+ className.substring(className.lastIndexOf('.') + 1);

			runMethod = object.getClass().getMethod(methodName);

			this.getTypeMethods.put(requestClass, runMethod);
		}

		// Invoke method
		final Object objResult = runMethod.invoke(object);

		return objResult;
	}

	/**
	 * Method to handle a request and send out an exception message if something
	 * went wrong.
	 * 
	 * @param request
	 *            Request Element
	 * @param methodName
	 *            Name of Handler method, null if equals default Name
	 * @return Result Message, Exception MAssage in case of errors
	 * @throws SoapFault
	 *             Error occured during process
	 */
	public final Object handle(final Object request, final String methodName) {

		return this.runRequest(request, methodName);

	}

	/**
	 * Method to handle a request and send out an exception message if something
	 * went wrong.
	 * 
	 * @param request
	 *            Request Element
	 * @param methodName
	 *            Name of Handler method, null if equals default Name
	 * @return Result Message, Exception MAssage in case of errors
	 * @throws SoapFault
	 *             Error occured during process
	 */
	public final Object handle(final String methodName, final Object request) {
		return this.handle(request, methodName);
	}

	/**
	 * This method may be overloaded in any RequestHandler to create an own
	 * method invocation behavior.
	 * 
	 * @param objRequestType
	 *            Request Object
	 * @param methodName
	 *            Name of the Requested method
	 * @return Result Object (according to invoked method)
	 * @throws Throwable
	 *             Any invocation error
	 */
	protected Object invokeMethod(final Object objRequestType,
			final String methodName) throws Throwable {
		// Check if method is already hashed
		final Method runMethod = this.getMethod(objRequestType.getClass(),
				methodName);

		// Run appropriate Request Handler
		Object dummyResult = null;
		try {
			dummyResult = runMethod.invoke(this, objRequestType);
		} catch (final InvocationTargetException e) {
			// Try to throw the original fault
			if (null != e.getCause()) {
				throw e.getCause();
			}
			throw e;
		}

		// The invoked method returned null. Throw exception to prevent
		// NullPointerExceptions
		if (null == dummyResult) {
			throw new UnexpectedFaultException(this.getClass().getName()
					+ " returned null while processing " + runMethod.getName()
					+ " ( " + objRequestType.getClass().getName() + " )");
		}

		return dummyResult;
	}

	/**
	 * Function to detect the type of the element and calls the appropriate
	 * request handler.
	 * 
	 * @param request
	 *            Element Request
	 * @param methodName
	 *            Name of Handler method, null if equals default Name
	 * @return Element Response
	 * @throws Throwable
	 *             Error while handling request
	 */
	protected final Object runRequest(final Object request,
			final String methodName) {

		this.getPerformanceLogger().debug("start_requestHandler");

		final long startTime = System.currentTimeMillis();

		final long intermediateTime2 = System.currentTimeMillis();

		// Run appropriate Request Handler
		Object result;
		try {
			result = this.invokeMethod(request, methodName);
		} catch (Throwable e) {

			e.printStackTrace();
			throw new UnexpectedFaultException(e);
		}

		final long intermediateTime3 = System.currentTimeMillis();
		long duration = intermediateTime3 - intermediateTime2;
		this.getPerformanceLogger().info(
				"duration_processing " + duration + "ms");

		return result;
	}

	/**
	 * Put the type Object into the Message Object.
	 * 
	 * @param object
	 *            Type Object
	 * @return Message Object
	 * @throws InstantiationException
	 *             Error while method Invocation
	 * @throws IllegalAccessException
	 *             Error while method Invocation
	 * @throws InvocationTargetException
	 *             Error while method Invocation
	 * @throws ClassNotFoundException
	 *             Non-standard Typename
	 * @throws NoSuchMethodException
	 *             Non-standard Typename
	 */
	protected Object setType(final Object object)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException,
			NoSuchMethodException {

		// CLass
		final Class<?> requestClass = object.getClass();
		// Setter method
		final Method runMethod;

		// Get envelope class name
		final String className = requestClass.getName().replace("Type", "");

		// Instantiate envelope class;
		final Class<?> resultClass = Class.forName(className);

		// Return Object
		final Object objResult = resultClass.newInstance();

		if (this.setTypeMethods.containsKey(resultClass)) {
			runMethod = this.setTypeMethods.get(resultClass);
		} else {
			// Get name of setter Method
			final String methodName = "set"
					+ className.substring(className.lastIndexOf('.') + 1);

			runMethod = resultClass.getMethod(methodName, requestClass);

			this.setTypeMethods.put(resultClass, runMethod);
		}

		// Invoke Setter Method
		runMethod.invoke(objResult, object);

		return objResult;
	}
}
