package org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions;

import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.DomainAlreadyExistsFault;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.BaseFaultType;
import org.opennaas.extensions.idb.serviceinterface.databinding.utils.AbstractFaultException;

/**
 * Autogenerated Fault Exception.
 *
 * Generated by scripts/generateFaultExceptions.java
 * Created on Thu Jun 14 18:41:25 2012
 */
public class DomainAlreadyExistsFaultException
        extends TopologyFaultException {

    /**
     * Autogenerated Constructor.
     *
     * @param message Message to be thrown
     * @param cause   Originator exception
     */
    public DomainAlreadyExistsFaultException(final String message,
            final Throwable cause) {
        super(new DomainAlreadyExistsFault(), message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * Autogenerated Constructor.
     *
     * @param message Message to be thrown
     */
    public DomainAlreadyExistsFaultException(final String message) {
        super(new DomainAlreadyExistsFault(), message);
        // TODO Auto-generated constructor stub
    }

    /**
     * Autogenerated Constructor.
     *
     * @param cause   Originator exception
     */
    public DomainAlreadyExistsFaultException(final Throwable cause) {
        super(new DomainAlreadyExistsFault(), cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * Autogenerated Constructor.
     */
    public DomainAlreadyExistsFaultException() {
        super(new DomainAlreadyExistsFault());
        // TODO Auto-generated constructor stub
    }

    /**
     * Autogenerated Extension Constructor.
     *
     * @param fault BaseFault
     * @param message Message to be thrown
     * @param cause   Originator exception
     */
    public DomainAlreadyExistsFaultException(final BaseFaultType fault, final String message,
            final Throwable cause) {
        super(fault, message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * Autogenerated Extension Constructor.
     *
     * @param fault BaseFault
     * @param message Message to be thrown
     */
    public DomainAlreadyExistsFaultException(final BaseFaultType fault, final String message) {
        super(fault, message);
        // TODO Auto-generated constructor stub
    }

    /**
     * Autogenerated Extension Constructor.
     *
     * @param fault BaseFault
     * @param cause   Originator exception
     */
    public DomainAlreadyExistsFaultException(final BaseFaultType fault, final Throwable cause) {
        super(fault, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * Autogenerated Extension Constructor.
     *
     * @param fault BaseFault
     */
    public DomainAlreadyExistsFaultException(final BaseFaultType fault) {
        super(fault);
        // TODO Auto-generated constructor stub
    }

}