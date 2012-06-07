package org.opennaas.extensions.idb.topology;

import org.opennaas.extensions.idb.exception.database.DatabaseException;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddDomainResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddDomainType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddEndpointResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddEndpointType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddLinkResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddLinkType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteDomainResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteDomainType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteEndpointResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteEndpointType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteLinkResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DeleteLinkType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DomainInformationType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditDomainResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditDomainType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditEndpointResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditEndpointType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditLinkResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.EditLinkType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetDomainsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetDomainsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetEndpointsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetEndpointsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetLinksResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetLinksType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.DomainAlreadyExistsFaultException;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.DomainNotFoundFaultException;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.EndpointAlreadyExistsFaultException;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.InvalidRequestFaultException;

public interface ITopologyCapabilityService {

	public GetLinksResponseType getLinks(final GetLinksType element);

	public GetEndpointsResponseType getEndpoints(final GetEndpointsType element)
			throws DatabaseException;

	public GetDomainsResponseType getDomains(final GetDomainsType element)
			throws DatabaseException;

	public EditLinkResponseType editLink(final EditLinkType element);

	public EditEndpointResponseType editEndpoint(final EditEndpointType element)
			throws DatabaseException;

	public EditDomainResponseType editDomain(final EditDomainType element)
			throws DatabaseException;

	public DeleteLinkResponseType deleteLink(final DeleteLinkType element);

	public DeleteEndpointResponseType deleteEndpoint(
			final DeleteEndpointType element) throws DatabaseException;

	public DeleteDomainResponseType deleteDomain(final DeleteDomainType element)
			throws DatabaseException, DomainNotFoundFaultException;

	public boolean addOrEditDomain(final DomainInformationType domInfo,
			final boolean checkSeq) throws DatabaseException;

	public AddLinkResponseType addLink(final AddLinkType element);

	public AddEndpointResponseType addEndpoint(final AddEndpointType element)
			throws DatabaseException, DomainNotFoundFaultException,
			InvalidRequestFaultException, EndpointAlreadyExistsFaultException;

	public AddDomainResponseType addDomain(final AddDomainType element)
			throws DatabaseException, DomainAlreadyExistsFaultException;

}
