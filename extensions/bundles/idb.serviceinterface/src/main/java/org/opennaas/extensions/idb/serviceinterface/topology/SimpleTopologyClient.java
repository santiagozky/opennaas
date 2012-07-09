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
package org.opennaas.extensions.idb.serviceinterface.topology;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.soap.AddressingFeature;

import org.w3c.dom.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.extensions.idb.serviceinterface.EndpointReference;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddDomainResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddDomainType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddEndpointResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddEndpointType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddLinkResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddLinkType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddOrEditDomainResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddOrEditDomainType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteDomainResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteDomainType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteEndpointResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteEndpointType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteLinkResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteLinkType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DomainAlreadyExistsFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DomainInformationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DomainNotFoundFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditDomainResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditDomainType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditEndpointResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditEndpointType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditLinkResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditLinkType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EndpointAlreadyExistsFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EndpointType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetDomainsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetDomainsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetEndpointsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetEndpointsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetLinksResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetLinksType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.InvalidRequestFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.OperationNotAllowedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.OperationNotSupportedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TopologyIFPortType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.TopologyIFService;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnexpectedFault_Exception;
import org.opennaas.extensions.idb.serviceinterface.databinding.utils.WebserviceUtils;

/**
 * @author willner
 */
public class SimpleTopologyClient implements TopologyIFPortType {

	TopologyIFPortType client;
	Log logger;

	/**
	 * @param endpointReference
	 */
	public SimpleTopologyClient(final EndpointReference endpointReference) {
		logger = LogFactory.getLog(this.getClass());
		// create the service using the supplied endpoint
		TopologyIFService topologyService;
		try {
			topologyService = new TopologyIFService(endpointReference.getURI()
					.toURL());
		} catch (MalformedURLException e) {
			topologyService = new TopologyIFService();
			logger.error("could not convert into URL "
					+ endpointReference.getURI());
			e.printStackTrace();
		}
		client = topologyService.getTopologyIFPort(new AddressingFeature());

	}

	/**
	 * 
	 * @param topologyWS
	 */
	public SimpleTopologyClient(final TopologyIFPortType topologyWS) {
		logger = LogFactory.getLog(this.getClass());
		client = topologyWS;
	}

	/**
	 * @param endpointReference
	 */
	public SimpleTopologyClient(final String endpointReference) {
		logger = LogFactory.getLog(this.getClass());
		TopologyIFService topologyService;
		try {
			topologyService = new TopologyIFService(new URL(endpointReference));

		} catch (MalformedURLException e) {
			topologyService = new TopologyIFService();
			logger.error("could not convert into URL " + endpointReference);
			e.printStackTrace();
		}
		client = topologyService.getTopologyIFPort(new AddressingFeature());
	}

	/**
	 * generate a AddDomainRequest, call the Web Service and return a
	 * AddDomainResponseType.
	 * 
	 * @param addDomain
	 *            AddDomainType
	 * @return AddDomainResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws DomainAlreadyExistsFault_Exception
	 */
	public AddDomainResponseType addDomain(final AddDomainType addDomain)
			throws DomainAlreadyExistsFault_Exception,
			InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {

		return client.addDomain(addDomain);

	}

	/**
	 * 
	 * @param identifier
	 * @param resEPR
	 * @param TNAPrefix
	 * @return The response @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 * @throws DomainAlreadyExistsFault_Exception
	 */
	public AddDomainResponseType addDomain(final String identifier,
			final String resEPR, final String TNAPrefix)
			throws DomainAlreadyExistsFault_Exception,
			InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		final AddDomainType addDomain = new AddDomainType();
		final DomainInformationType dit = this.getDomainInformation(identifier,
				resEPR, TNAPrefix);
		addDomain.setDomain(dit);
		return client.addDomain(addDomain);

	}

	/**
	 * generate a AddEndpointRequest, call the Web Service and return a
	 * AddEndpointResponseType.
	 * 
	 * @param addEndpoint
	 *            AddEndpointType
	 * @return AddEndpointResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws DomainNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws EndpointAlreadyExistsFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public AddEndpointResponseType addEndpoint(final AddEndpointType addEndpoint)
			throws InvalidRequestFault_Exception,
			EndpointAlreadyExistsFault_Exception, UnexpectedFault_Exception,
			DomainNotFoundFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {

		return client.addEndpoint(addEndpoint);
	}

	/**
	 * generate a AddEndpointRequest, call the Web Service and return a
	 * AddEndpointResponseType.
	 * 
	 * @param endpoint
	 *            EndpointType
	 * @return AddEndpointResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws DomainNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws EndpointAlreadyExistsFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public AddEndpointResponseType addEndpoint(final EndpointType endpoint)
			throws InvalidRequestFault_Exception,
			EndpointAlreadyExistsFault_Exception, UnexpectedFault_Exception,
			DomainNotFoundFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		final AddEndpointType addEndpointType = new AddEndpointType();
		addEndpointType.setEndpoint(endpoint);
		return client.addEndpoint(addEndpointType);
	}

	/**
	 * generate a AddLinkRequest, call the Web Service and return a
	 * AddLinkResponseType.
	 * 
	 * @param addLink
	 *            AddLinkType
	 * @return AddLinkResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public AddLinkResponseType addLink(final AddLinkType addLink)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {

		return client.addLink(addLink);
	}

	/**
	 * generate a GetEndpointsRequest, call the Web Service and return a
	 * GetEndpointsResponseType.
	 * 
	 * @param getEndpoints
	 *            GetEndpointsType
	 * @return GetEndpointsResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public AddOrEditDomainResponseType addOrEditDomain(
			final AddOrEditDomainType addOrEditDomainType)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {

		return client.addOrEditDomain(addOrEditDomainType);
	}

	/**
	 * 
	 * @param identifier
	 * @param resEPR
	 * @param TNAPrefix
	 * @return The result. @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public AddOrEditDomainResponseType addOrEditDomain(final String identifier,
			final String resEPR, final String TNAPrefix)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		final AddOrEditDomainType addOrEditDomainType = new AddOrEditDomainType();
		addOrEditDomainType.setDomain(this.getDomainInformation(identifier,
				resEPR, TNAPrefix));
		return client.addOrEditDomain(addOrEditDomainType);
	}

	/**
	 * generate a DeleteDomainRequest, call the Web Service and return a
	 * DeleteDomainResponseType.
	 * 
	 * @param deleteDomain
	 *            DeleteDomainType
	 * @return DeleteDomainResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws DomainNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public DeleteDomainResponseType deleteDomain(
			final DeleteDomainType deleteDomain)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			DomainNotFoundFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {

		return client.deleteDomain(deleteDomain);
	}

	/**
	 * 
	 * @param identifier
	 * @return The result. @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws DomainNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public DeleteDomainResponseType deleteDomain(final String identifier)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			DomainNotFoundFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		final DeleteDomainType deleteDomain = new DeleteDomainType();
		deleteDomain.setDomainId(identifier);
		return client.deleteDomain(deleteDomain);
	}

	/**
	 * generate a DeleteEndpointRequest, call the Web Service and return a
	 * DeleteEndpointResponseType.
	 * 
	 * @param deleteEndpoint
	 *            DeleteEndpointType
	 * @return DeleteEndpointResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public DeleteEndpointResponseType deleteEndpoint(
			final DeleteEndpointType deleteEndpoint)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		return client.deleteEndpoint(deleteEndpoint);
	}

	/**
	 * generate a DeleteLinkRequest, call the Web Service and return a
	 * DeleteLinkResponseType.
	 * 
	 * @param deleteLink
	 *            DeleteLinkType
	 * @return DeleteLinkResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public DeleteLinkResponseType deleteLink(final DeleteLinkType deleteLink)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		return client.deleteLink(deleteLink);
	}

	/**
	 * generate a EditDomainRequest, call the Web Service and return a
	 * EditDomainResponseType.
	 * 
	 * @param editDomain
	 *            EditDomainType
	 * @return EditDomainResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws DomainNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public EditDomainResponseType editDomain(final EditDomainType editDomain)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			DomainNotFoundFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		return client.editDomain(editDomain);
	}

	/**
	 * 
	 * @param identifier
	 * @param resEPR
	 * @param TNAPrefix
	 * @return @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws DomainNotFoundFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public EditDomainResponseType editDomain(final String identifier,
			final String resEPR, final String TNAPrefix)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			DomainNotFoundFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		final EditDomainType editDomain = new EditDomainType();
		editDomain.setDomain(this.getDomainInformation(identifier, resEPR,
				TNAPrefix));
		return client.editDomain(editDomain);
	}

	/**
	 * generate a EditEndpointRequest, call the Web Service and return a
	 * EditEndpointResponseType.
	 * 
	 * @param editEndpoint
	 *            EditEndpointType
	 * @return EditEndpointResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public EditEndpointResponseType editEndpoint(
			final EditEndpointType editEndpoint)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {

		return client.editEndpoint(editEndpoint);
	}

	/**
	 * generate a EditLinkRequest, call the Web Service and return a
	 * EditLinkResponseType.
	 * 
	 * @param editLink
	 *            EditLinkType
	 * @return EditLinkResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public EditLinkResponseType editLink(final EditLinkType editLink)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {

		return client.editLink(editLink);
	}

	public DomainInformationType getDomainInformation(final String identifier,
			final String resEPR, final String TNAPrefix) {
		final DomainInformationType dit = new DomainInformationType();
		dit.setDomainId(identifier);
		dit.setReservationEPR(resEPR);
		dit.getTNAPrefix().add(TNAPrefix);
		return dit;
	}

	/**
	 * 
	 * @return @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public GetDomainsResponseType getDomains()
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		return client.getDomains(new GetDomainsType());

	}

	/**
	 * generate a GetDomainsRequest, call the Web Service and return a
	 * GetDomainsResponseType.
	 * 
	 * @param getDomains
	 *            GetDomainsType
	 * @return GetDomainsResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public GetDomainsResponseType getDomains(final GetDomainsType getDomains)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {

		return client.getDomains(getDomains);
	}

	/**
	 * generate a GetEndpointsRequest, call the Web Service and return a
	 * GetEndpointsResponseType.
	 * 
	 * @param getEndpoints
	 *            GetEndpointsType
	 * @return GetEndpointsResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public GetEndpointsResponseType getEndpoints(
			final GetEndpointsType getEndpoints)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {

		return client.getEndpoints(getEndpoints);
	}

	/**
	 * 
	 * @param domainID
	 * @return @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public GetEndpointsResponseType getEndpoints(final String domainID)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		final GetEndpointsType getEndpoints = new GetEndpointsType();
		getEndpoints.setDomain(domainID);
		return client.getEndpoints(getEndpoints);
	}

	/**
	 * generate a GetLinksRequest, call the Web Service and return a
	 * GetLinksResponseType.
	 * 
	 * @param getLinks
	 *            GetLinksType
	 * @return GetLinksResponseType @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public GetLinksResponseType getLinks(final GetLinksType getLinks)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {

		return client.getLinks(getLinks);
	}

	/**
	 * 
	 * @param domainID
	 * @return @
	 * @throws OperationNotAllowedFault_Exception
	 * @throws OperationNotSupportedFault_Exception
	 * @throws UnexpectedFault_Exception
	 * @throws InvalidRequestFault_Exception
	 */
	public GetLinksResponseType getLinks(final String domainID)
			throws InvalidRequestFault_Exception, UnexpectedFault_Exception,
			OperationNotSupportedFault_Exception,
			OperationNotAllowedFault_Exception {
		final GetLinksType getLinks = new GetLinksType();
		getLinks.setDomainId(domainID);
		return client.getLinks(getLinks);
	}
}
