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

package org.opennaas.core.utils;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** NspLogger. */
/** modified by santiagozky to use commons-logging **/
public class PhLogger {

	private static String prefix = "idb";
	private static HashMap<String, Log> existingLoggers = new HashMap<String, Log>();

	/**
	 * 
	 * @return phosphorus logging-instance
	 */
	public static Log getLogger() {
		Log logger = LogFactory.getLog("org.opennaas.extensions.idb");

		return logger;
	}

	/**
	 * @param name
	 *            Log by name
	 * @return Log
	 */
	public static Log getLogger(final String name) {

		Log logger = LogFactory.getLog(name);
		return logger;
	}

	/**
	 * @param clazz
	 *            Log by class
	 * @return Log
	 */
	public static Log getLogger(final Class<?> clazz) {

		Log logger = LogFactory.getLog(clazz.getName());
		return logger;
	}

	/**
	 * @return RootLogger
	 */
	public static Log getRootLogger() {

		return LogFactory.getLog("org.opennaas");
	}

	public static Log getSeparateLogger(String name, String fileNamePrefix) {

		final String id = fileNamePrefix + name;
		Log log = PhLogger.existingLoggers.get(id);

		if (null == log) {
			log = LogFactory.getLog(id);
			PhLogger.existingLoggers.put(id, log);
		} else {
			return log;
		}

		return log;
	}

	/**
	 * default logger debugg
	 * 
	 * @param name
	 * @return
	 */
	public static Log getSeparateLogger(String name) {

		return getSeparateLogger(name, prefix);
	}

}
